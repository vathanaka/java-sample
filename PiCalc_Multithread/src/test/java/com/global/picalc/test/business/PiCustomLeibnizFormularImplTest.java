package com.global.picalc.test.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.global.picalc.business.impl.PiCustomLeibnizFormularImpl;
import org.junit.Test;

public class PiCustomLeibnizFormularImplTest {

	@Test
	public void testCallWithN100M() {
		PiCustomLeibnizFormularImpl p = new PiCustomLeibnizFormularImpl();
		double actualPI = 0D;
		double expectedPI  = Math.PI;
		
		try {
			actualPI = p.compute(100000000,0.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(expectedPI, actualPI, 0.00000001);
	}

	
	@Test
	public void testCallWithN100K() {
		PiCustomLeibnizFormularImpl p = new PiCustomLeibnizFormularImpl();
		double actualPI = 0D;
		double expectedPI  = Math.PI;
		
		try {
			actualPI = p.compute(100000,0.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(expectedPI, actualPI, 0.00001);
	}
	
	@Test
	public void testCallWithN1k() {
		PiCustomLeibnizFormularImpl p = new PiCustomLeibnizFormularImpl();
		double actualPI = 0D;
		double expectedPI  = Math.PI;
		
		try {
			actualPI = p.compute(1000,0.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(expectedPI, actualPI, 0.001);
	}
	
	public void testGetStartNoListWith10Thread() {
		int noOfThread = 10;
		long SCALE = 10000000L;
		PiCustomLeibnizFormularImpl p  = new PiCustomLeibnizFormularImpl();
		
		ArrayList<Double> expectedList = new ArrayList<Double>(noOfThread);
		
		double expectedStartNo1 = 0;
		double expectedStartNo2 = 40000000;
		double expectedStartNo3 = 80000000;
		double expectedStartNo4 = 120000000;
		double expectedStartNo5 = 160000000;
		double expectedStartNo6 = 200000000;
		double expectedStartNo7 = 240000000;
		double expectedStartNo8 = 280000000;
		double expectedStartNo9 = 320000000;
		double expectedStartNo10 = 360000000;
		
		expectedList.add(0, expectedStartNo1);
		expectedList.add(1, expectedStartNo2);
		expectedList.add(2, expectedStartNo3);
		expectedList.add(3, expectedStartNo4);
		expectedList.add(4, expectedStartNo5);
		expectedList.add(5, expectedStartNo6);
		expectedList.add(6, expectedStartNo7);
		expectedList.add(7, expectedStartNo8);
		expectedList.add(8, expectedStartNo9);
		expectedList.add(9, expectedStartNo10);

		
		ArrayList<Double> actualList = p.getStartNoList(noOfThread, SCALE);
		
		double expectedNo = 0L;
		double actualNo = 0L;
		for(int i = 0; i < noOfThread; i++){
			expectedNo = expectedList.get(i);
			actualNo = actualList.get(i);
			assertEquals(expectedNo, actualNo, 0.01);
		}
		
	}

}
