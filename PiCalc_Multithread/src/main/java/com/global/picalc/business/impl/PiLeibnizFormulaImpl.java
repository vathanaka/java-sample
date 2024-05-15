package com.global.picalc.business.impl;

import java.util.ArrayList;

import com.global.picalc.business.Formula;

/**
 * Implementation of Calculator Interface class
 *
 * Calculate PI number based on Lebniz formula
 * 
 * @author vathanaka
 *
 */
public class PiLeibnizFormulaImpl implements Formula{

    /**
     * {@inheritDoc}
     */
    @Override
	public double compute(long iterationNumber, double startNo){
		double tempPI = 0D;

		double signal = (startNo % 2 == 0) ? 4 : -4;		
		for (double k = startNo; k < (startNo + iterationNumber); k++) {
			tempPI += signal / (2 * k + 1);
			signal = -signal;
		}

		return tempPI;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public ArrayList<Double> getStartNoList(int noOfThread, long SCALE) {
        ArrayList<Double> startNoArray = new ArrayList<Double>((int) noOfThread);
        double startNo = 0L;

		for (int i = 1; i <= noOfThread; i++) {
				startNo = (i - 1) * SCALE;
				startNoArray.add((i - 1), startNo);
		}

        return startNoArray;
    }
}
