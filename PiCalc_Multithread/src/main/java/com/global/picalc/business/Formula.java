package com.global.picalc.business;

import java.util.ArrayList;

/**
 * Interface for calculating based on formula
 *
 * @author vathanaka
 */

public interface Formula{
    /**
     * Based on the formula implementation to compute and return a result
     *
     * @param iterationNumber
     * @param startNo
     * @return
     */
	double compute(long iterationNumber, double startNo);

    /**
     * Based on the formula implementation to separate the algorithm to multiple thread, each thread will handle a part of algorithm
     *
     * @param noOfThread
     * @param SCALE
     * @return
     */
	ArrayList<Double> getStartNoList(int noOfThread, long SCALE);
}
