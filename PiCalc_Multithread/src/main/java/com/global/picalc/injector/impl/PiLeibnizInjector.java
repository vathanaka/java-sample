package com.global.picalc.injector.impl;

import com.global.picalc.business.Formula;
import com.global.picalc.business.impl.PiLeibnizFormulaImpl;
import com.global.picalc.injector.FormulaInjector;
import com.global.picalc.injector.FormulaWithInjector;

public class PiLeibnizInjector  implements FormulaInjector {

    /**
     * {@inheritDoc}
     */
	@Override
	public Formula getFormula() {
		return (new FormulaWithInjector(new PiLeibnizFormulaImpl()));
	}
}
