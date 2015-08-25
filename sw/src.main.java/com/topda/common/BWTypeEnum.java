package com.topda.common;

public enum BWTypeEnum {

	TIME(4),
	SIM(8),
	TEMP(2),;
	
	private Integer nCode;

	/**
	 * @param nCode
	 */
	private BWTypeEnum(Integer nCode) {
		this.nCode = nCode;
	}

	public Integer getValue() {
		return nCode;
	}
	public String toString() {
        return String.valueOf(this.nCode);
    }
}
