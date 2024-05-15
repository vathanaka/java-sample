package com.global.picalc.injector.impl;

import com.global.picalc.injector.FormulaInjectorFactory;
import com.global.picalc.business.enums.FormulaType;
import com.global.picalc.injector.FormulaInjector;


public class FormulaInjectorFactoryImpl implements FormulaInjectorFactory{

    /**
     * {@inheritDoc}
     */
	@Override
	public FormulaInjector create(FormulaType formulaType) {
		FormulaInjector formulaInjector = null;
		
		switch (formulaType) {
		case CUSTOMLEIBNIZ:
			formulaInjector = new PiCustomLeibnizInjector();
			break;

		case LEIBNIZ:
			formulaInjector = new PiLeibnizInjector();
			break;
		}
		
		return formulaInjector;
	}

}
