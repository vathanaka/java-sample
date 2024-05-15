package com.global.picalc.business.impl;

import java.util.concurrent.Callable;

import com.global.picalc.business.Formula;

public class ThreadHandler implements Callable<Double>{
	private long iterationNumber = 0;
	private double startNo = 0D;
	private Formula formula = null;
	
	/**
	 * Constructor for ThreadHandler with iterationNumber and startNo
	 * 
	 * @param iterationNumber: no of iteration to be performed
	 * @param startNo: start number for computation ( based on formula )
	 */
	public ThreadHandler(long iterationNumber, double startNo, Formula formula) {
		this.iterationNumber = iterationNumber;
		this.startNo = startNo;
		this.formula = formula;
		
	}
	
	@Override
	public Double call() throws Exception {
		double tempPi = formula.compute(iterationNumber,startNo);
		return tempPi;
	}

}
