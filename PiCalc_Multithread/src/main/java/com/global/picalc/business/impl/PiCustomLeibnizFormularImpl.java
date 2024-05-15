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
public class PiCustomLeibnizFormularImpl implements Formula{
    /**
     * {@inheritDoc}
     */
	@Override
	public double compute(long iterationNumber, double startNo) {
		double tempPI = 0D;
		double a = 0;
		double b = 0;

		for (int k = 0; k < iterationNumber; k++) {
			a = startNo + 1;
			b = startNo + 3;
			tempPI += (4 / a) - (4 / b);
			startNo += 4;
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
			startNo = (i - 1) * SCALE * 4;
			startNoArray.add((i - 1), startNo);
		}
		
		return startNoArray;
	}
}
