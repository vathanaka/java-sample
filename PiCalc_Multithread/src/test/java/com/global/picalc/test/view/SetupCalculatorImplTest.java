package com.global.picalc.test.view;

import static org.junit.Assert.*;

import org.junit.Test;

//import com.gbst.picalc.view.impl.CalculatorImpl;
import com.global.picalc.view.impl.SetupCalculatorImpl;

public class SetupCalculatorImplTest {
	
	SetupCalculatorImpl s = new SetupCalculatorImpl();

	@Test
	public void testGetNoOfThreadWithN1B() {
		long noOfIteration = 1000000000L;
		
		int expectedThread = 100;
		int actualThread = 0;
		
		actualThread = s.setNoOfThread(noOfIteration);
		
		assertEquals(expectedThread, actualThread);
	}
	
	@Test
	public void testGetNoOfThreadWithN1B1() {
		long noOfIteration = 1000000001L;
		
		int expectedThread = 101;
		int actualThread = 0;
		
		actualThread = s.setNoOfThread(noOfIteration);
		
		assertEquals(expectedThread, actualThread);
	}

	@Test
	public void testGetRemainderIterationWithN1B() {
		long noOfIteration = 1000000000L;
		
		long expectedRemainder = 0L;
		long actualRemainder = 0L;
		
		actualRemainder = s.setRemainderIteration(noOfIteration);
		
		assertEquals(expectedRemainder, actualRemainder);
	}
	
	@Test
	public void testGetRemainderIterationWithN1B1() {
		long noOfIteration = 1000000001L;
		
		long expectedRemainder = 1L;
		long actualRemainder = 0L;
		
		actualRemainder = s.setRemainderIteration(noOfIteration);
		
		assertEquals(expectedRemainder, actualRemainder);
	}
}
