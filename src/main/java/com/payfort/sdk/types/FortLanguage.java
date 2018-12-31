package com.payfort.sdk.types;

/**
 * Provides the allowed languages on FORT.
 * Default is ENGLISH
 *
 */
public enum FortLanguage {

	Arabic("ar"), English("en");
	
	private String code;
	
	private FortLanguage(String code) {
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}
}
