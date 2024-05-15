package com.global.picalc.injector;

import java.util.ArrayList;

import com.global.picalc.business.Formula;

public class FormulaWithInjector implements Formula{

	private Formula formula;
	
	public FormulaWithInjector(Formula formula){
		this.formula = formula;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public double compute(long iterationNumber, double startNo) {
		return this.formula.compute(iterationNumber, startNo);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public ArrayList<Double> getStartNoList(int noOfThread, long SCALE) {
		return this.formula.getStartNoList(noOfThread, SCALE);
	}

}
