package com.global.picalc.utils;

/**
 * Utility class to get execution time
 *
 * User: vathanaka
 */
public class CalculateTimer {
    private static double startTime = 0L;
    private static double endTime = 0L;
    private static double totalTime = 0L;
    
    

    public static void setStartTime() {
    	CalculateTimer.startTime = System.currentTimeMillis();
	}
    
	public static void setEndTime() {
		CalculateTimer.endTime = System.currentTimeMillis();
	}

	public static void setEndTime(double second) {
		CalculateTimer.endTime = startTime + second;
	}


	/**
     * getStartTime() : get the time at the beginning of the execution
     *
     * @return startTime
     */
    public static double getStartTime() {
        return startTime ;
    }

    /**
     * getEndTime() : get the time at the end of the execution
     *
     * @return endTime
     */
    public static double getEndTime() {
        return endTime ;
    }

    /**
     * setTotalTime() : set the total time of the execution = endTime - startTime
     * @return totalTime
     */
    public static double setTotalTime() {
         totalTime = endTime - startTime;
         return totalTime;
    }
    
    /**
     * getTotalTime() : get the total time of the execution 
     * 
     * @return totalTime
     */
    public static double getTotalTime(){
    	return totalTime;
    }

    /**
     * printTotalTime() : print the duration of the execution to console
     *
     */
    public static void printTotalTime(){
    	System.out.printf("%nDuration (in microsecond - should divide for 1000 to get time in second) : %n"+ (CalculateTimer.getTotalTime()) + "%n");
    }

}
