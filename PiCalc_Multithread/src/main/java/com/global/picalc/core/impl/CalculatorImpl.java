package com.global.picalc.core.impl;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.global.picalc.business.Formula;
import com.global.picalc.business.impl.ThreadHandler;
import com.global.picalc.core.Calculator;
import com.global.picalc.utils.CalculateTimer;
import com.global.picalc.utils.PiTemporaryResult;
import com.global.picalc.view.impl.SetupCalculatorImpl;

/**
 * Implementation of SetupCalculator Interface class
 *
 * Calculate approximate PI number with the user's iteration Program using Multithreading solution
 * 
 * @author vathanaka
 *
 */

public class CalculatorImpl implements Calculator {
	private final static Logger logger = Logger.getLogger(CalculatorImpl.class);
	private static double finalPI = 0D;
	private double tempResult = 0D;
	private static volatile boolean done = false;
	
	private SetupCalculatorImpl setupCalculatorImpl = null;
	
	public CalculatorImpl (SetupCalculatorImpl setupCalculatorImpl){
		this.setupCalculatorImpl = setupCalculatorImpl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startCalculator() {

		//Start CalculateTimer to get the execution time later
		CalculateTimer.setStartTime();

		long noOfIteration =  setupCalculatorImpl.getNoOfIteration();
		long SCALE = setupCalculatorImpl.getSCALE();
		long remainderIteration = setupCalculatorImpl.getRemainderIteration(); 
		int processors = Runtime.getRuntime().availableProcessors();
		int noOfThread  = setupCalculatorImpl.getNoOfThread();
		double supposeEndTime = setupCalculatorImpl.getSupposeEndTime();
		ArrayList<Double> startNo = setupCalculatorImpl.getStartNo();
		ExecutorService executorPI = Executors.newFixedThreadPool(processors);
		Callable<Double>  callableToSubmit = null;
		Formula formula = setupCalculatorImpl.getFormula();
		ArrayList<Future<Double>> resultList = new ArrayList<Future<Double>>(processors);


		if(supposeEndTime != 0){
			CalculateTimer.setEndTime(supposeEndTime * 1000);
		}else{
			CalculateTimer.setEndTime(Double.MAX_VALUE);
		}
		

		//Flow process if noOfIteration greater than SCALE
		double startNumber;
		if (noOfIteration > SCALE) {
			//Start the loop to create Callable<Double> instances ,wrap it by FutureTask<Double> and execute by ExecutorService
			CalculateLoop: for (int i = 1; i <= noOfThread; i++) {
				
				if(done){
					break CalculateLoop;
				}	
				
				double temporaryEndTime = System.currentTimeMillis();
				if(temporaryEndTime >= CalculateTimer.getEndTime()){
					cancel();
				}
				
				startNumber = startNo.get((i - 1));
				if (i < noOfThread) {
					callableToSubmit = new ThreadHandler(SCALE, startNumber, formula);
				}

				//The last thread will have the iteration number = remainderIteration
				if (i == noOfThread) {
					callableToSubmit = new ThreadHandler(remainderIteration, startNumber, formula);
				}
				FutureTask<Double> futureX = new FutureTask<Double>(callableToSubmit);
				executorPI.execute(futureX);
				resultList.add(futureX);
				
				if (resultList.size() == (processors)) {
					tempResult += PiTemporaryResult.getTempResult(resultList);
				}
			}

		//Flow process if noOfIteration is lesser than SCALE / or equal to SCALE
		} else {
			startNumber = 0D;
			callableToSubmit = new ThreadHandler(noOfIteration, startNumber, formula);
			FutureTask<Double> futureX = new FutureTask<Double>(callableToSubmit);
			executorPI.execute(futureX);
			resultList.add(futureX);
		}

		//Flow process if user press <ENTER> key
		if (done) {
			tempResult += PiTemporaryResult.getTempResult(resultList);
			finalPI = tempResult;
			executorPI.shutdownNow();

		//Flow process if user not press <ENTER> key
		} else {
			tempResult += PiTemporaryResult.getTempResult(resultList);
			finalPI = tempResult;
			executorPI.shutdown();
			try {
				executorPI.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				logger.error("Sorry, we've got Interrupted Exception", e);
//				e.printStackTrace();
			}
		}

		//Stop the CalculateTimer and get the duration ( of the execution ) in microSecond
		CalculateTimer.setEndTime();
		CalculateTimer.setTotalTime();
		CalculateTimer.printTotalTime();
	}

	/**
	 * cancel() to change the value of done boolean from default value (as false) to true to stop the computation immediately
	 */
	public static void cancel() {
		done = true;
	}


	/**
	 * getPI() : getter method for finalPI field
	 * 
	 * @return finalPI
	 * 
	 */
	public static double getPI() {
		return finalPI;
	}
	
	public static double getOddNumber(){
		return (Math.PI - finalPI);
	}
}
