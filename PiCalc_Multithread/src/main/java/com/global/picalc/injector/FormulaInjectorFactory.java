package com.global.picalc.injector;

import com.global.picalc.business.enums.FormulaType;

/**
 * Factory for creating FormulaInjector instance
 *
 * @author vathanaka
 */
public interface FormulaInjectorFactory {
    /**
     * Based on FormulaType enum to create FormulaInjector instance
     *
     * @param type ( FormulaType enum )
     * @return FormulaInjector
     */
	FormulaInjector create(FormulaType type);
}
