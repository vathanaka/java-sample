package com.global.picalc.view.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.global.picalc.business.Formula;
import com.global.picalc.business.enums.FormulaType;
import com.global.picalc.core.impl.CalculatorImpl;
import com.global.picalc.injector.FormulaInjector;
import com.global.picalc.injector.impl.FormulaInjectorFactoryImpl;
import com.global.picalc.view.SetupCalculator;
import org.apache.log4j.Logger;

import com.global.picalc.injector.FormulaInjectorFactory;

public class SetupCalculatorImpl implements SetupCalculator, Runnable {
	private final static Logger logger = Logger.getLogger(SetupCalculatorImpl.class);
	private static FormulaInjectorFactory formulaFactory = new FormulaInjectorFactoryImpl();
	private long noOfIteration = 0L;
	private long SCALE = 10000000L;
	private long remainderIteration = 0L;
	private int noOfThread = 0;	
	private double supposeEndTime = 0D;
	private ArrayList<Double> startNo = null;
	private FormulaInjector formulaInjector = null;
	private Formula formula = null;
	private Scanner userInput = new Scanner(System.in);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setupEnvironment() {
		printWelcome();

		//Get formula form user input => create new formulaInjector instance
		printAskForFormula();

		printInputN();

		printScaleNumber();

		printAskToInputTimer();

		//Get remainderIteration( remainderIteration != 0 )
		setRemainderIteration(noOfIteration);
		if (remainderIteration == 0) {
			setRemainderIteration(SCALE);
		} else {
			setRemainderIteration(noOfIteration);
		}

		setNoOfThread(noOfIteration);
		startNo = formula.getStartNoList(noOfThread, SCALE);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		printInputEnterToStop();
		BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in));
		
		//Break the loop if user press <ENTER>
		try {
			while(!readInput.ready());
			CalculatorImpl.cancel();
			readInput.close();
		} catch (IOException e) {
//			e.printStackTrace();
			logger.error("Sorry, we've got IO exception", e);
		}
	}
	

	public void setNoOfIteration(long noOfIteration) {
		this.noOfIteration = noOfIteration + 1;
}

	/**
	 * getNoOfThread() to get the noOfThread from noOfIteration ( user's input ) and SCALE
	 * 
	 * @param noOfIteration (user's input)
	 */
	public int setNoOfThread(long noOfIteration) {
		if (noOfIteration % SCALE == 0) {
			noOfThread = (int) (noOfIteration / SCALE);
		} else {
			noOfThread = (int) ((noOfIteration / SCALE) + 1);
		}
		
		return noOfThread;
	}

	/**
	 * getRemainderIteration() : get the remainderIteration from noOfIteration ( user's input ) and SCALE
	 * 
	 * @param noOfIteration (user's input)
	 */
	public long setRemainderIteration(long noOfIteration) {
		remainderIteration = (noOfIteration % SCALE);
		return remainderIteration;
	}


	/**
	 * Setter method of SCALE for solution to allow user to change SCALE
	 *
	 * @param inputSCALE user's input for SCALE
	 */
	public void setSCALE(long inputSCALE) {
		SCALE = inputSCALE;
	}

	/**
	 * getSCALE() : getter method for SCALE field
	 *
	 * @return SCALE
	 */
	public long getSCALE() {
		return SCALE;
	}
	
	/**
	 * getNoOfIteration() : getter method for noOfIeration field
	 * 
	 * @return noOfIeration
	 */
	public long getNoOfIteration() {
		return noOfIteration;
	}

	/**
	 * getRemainderIteration() : getter method for remainderIteration field
	 * @return remainderIteration
	 */
	public long getRemainderIteration() {
		return remainderIteration;
	}

	/**
	 * getNoOfThread() : getter method fpr noOfThread field
	 * 
	 * @return noOfThread
	 */
	public int getNoOfThread() {
		return noOfThread;
	}

	/**
	 * getStartNo() : getter method for  startNo in ArrayList<Long>
	 * 
	 * @return startNo
	 */
	public ArrayList<Double> getStartNo() {
		return startNo;
	}

	/**
	 * getFormulaInjector() : getter method for formulaInjector field
	 * 
	 * @return formulaInjector
	 */
	public FormulaInjector getFormulaInjector() {
		return formulaInjector;
	}

	/**
	 * getFormula() : getter method for formula field
	 * 
	 * @return formula
	 */
	public Formula getFormula() {
		return formula;
	}

	public void printWelcome() {
		System.out.println("This is a sample PI Calculator based on Leibniz formula.");
	}
	
	public void printAskForFormula(){
		boolean validatedFormula = false;
		
		do {
			System.out.printf("Please select formula [1-2] :  %n1: Custom Leibniz formula %n2: Leibniz Formula%n");
			String formulaTypeCode = userInput.next();
			
			try {
				FormulaType type = FormulaType.get(formulaTypeCode);
				formulaInjector = formulaFactory.create(type);
				formula = formulaInjector.getFormula();
				validatedFormula = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid Formula Type");
				logger.error("Sorry, we've got an Illegal Argument Exception", e);
				validatedFormula = false;
			}
			
		} while (!validatedFormula);
	}

	public void printInputN() {
		long n = 0L;
		System.out.print("Please input n ( no of iteration ) to calculate PI : ");
		do {
			while (!userInput.hasNextLong()) {
				System.out.println("Invalid number");
				System.out.print("Please re-enter the n ( no of iteration ) : ");
				userInput.next();
			}
			
			n = userInput.nextLong();
			if (n >= 0) {
				setNoOfIteration(n);
				n = 1;
			} else {
				System.out.println("Invalid no of iteration. No of Iteration must greater than 0");
				System.out.println("Please re-enter the n ( no of iteration ) : ");
			}

		} while (n < 0);

	}

	public void printScaleNumber() {
		System.out.println("System will use the iteration number for each Thread to calculate : " + getSCALE());
	}
	
	public void printAskToInputTimer(){
		System.out.print("Do you want to set Timer ? ( to stop application after X seconds ) [y to set Timer - anykey to skip] :  ");
		if(userInput.next().equals("y")){
			printInputTimer();
			setSupposeEndTime(userInput.nextDouble());
		}else{
			setSupposeEndTime(0.0);
		}
	}
	
	public void printInputTimer(){
		System.out.println("Please enter the Timer ( in second ) : ");
	}

	public double getSupposeEndTime() {
		return supposeEndTime;
	}

	public void setSupposeEndTime(double supposeEndTime) {
		this.supposeEndTime = supposeEndTime;
	}
	
	public void printInputEnterToStop() {
		System.out.println("Pi number is being computed. If you want to get the result, please press <Enter>");
	}
}
