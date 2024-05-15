package com.global.picalc.injector;

import com.global.picalc.business.Formula;

/**
 * Interface to get the implementation of Formula
 *
 * @author vathanaka
 */
public interface FormulaInjector {

    /**
     * Based on the Injector to get the implementation of Formula
     *
     * @return Formula type
     */
	public Formula getFormula();
}
