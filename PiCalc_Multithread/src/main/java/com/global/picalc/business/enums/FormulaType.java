package com.global.picalc.business.enums;

public enum FormulaType {
	CUSTOMLEIBNIZ("1"), LEIBNIZ("2");

	private String formulaCode;

	private FormulaType(String abbreviation) {
		this.formulaCode = abbreviation;
	}

	public String getFormulaCode() {
		return formulaCode;
	}

	public static FormulaType get(String code) {
		for (FormulaType t : values()) {
			if (t.formulaCode.equals(code)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Invalid formula type");
	}
}	