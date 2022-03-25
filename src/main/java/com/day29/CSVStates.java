package com.day29;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

	/**
	 * Seems that the CsvBindByName annotation always converts the column name to
	 * UpperCase based on the OpenCSV source code Specifies a binding between a
	 * column name of the CSV input and a field in a bean.
	 */
	@CsvBindByName(column = "SrlNo")
	private String SrlNo;

	@CsvBindByName(column = "State Name")
	private long State Name;

	@CsvBindByName(column = "TIN")
	private int TIN;

	@CsvBindByName(column = "StateCode")
	private int StateCode;

	/**
	 * By overriding the toString() method of the Object class, we can return values
	 * of the object, so we don't need to write much code.
	 */
	@Override
	public String toString() {
		return "IndiaStateCensus [ SrNo = " + srNo + " , State Name = " + state +
				" , TIN = " + tin + " , StateCode = " + code + " ]";
	}
}
