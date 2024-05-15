package com.global.picalc.utils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * Utility class to get a temporary computation result
 *
 * User: vathanaka
 */
public class PiTemporaryResult {
//	final static Logger logger = Logger.getLogger(PiTemporaryResult.class);
	
    /**
     * Get & sum all value present in ArrayList<Future<Double>>
     *
     * Use get() to get temporary result in Future<Double> type
     *
     * Use get(index) to get actual value in Double type
     *
     * @param tempList: store the result of the computation of each thread
     * @return tempResult
     */
	public static double getTempResult(ArrayList<Future<Double>> tempList){
        double tempResult = 0D;
        int tempQueueSize = tempList.size();

        
        //For loop to sum all entries ( temporary result of each thread ) in the list       
        for(int i = 0; i < tempQueueSize ; i++){
            try {
                tempResult += tempList.get(i).get();
            } catch (InterruptedException e) {
//            	logger.error("Sorry, something wrong!", e);
            	e.printStackTrace();
            } catch (ExecutionException e) {
//            	logger.error("Sorry, something wrong!", e);
            	e.printStackTrace();
            }
        }
        
        //Remove all entries in the List after get the sum of all temporary result in the list
        tempList.clear();
        return tempResult;
    }
}
