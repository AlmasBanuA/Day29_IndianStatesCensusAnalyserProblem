package com.da29;

/**
 * UC2:-   Ability for the analyser to load the Indian States Code Information from a csv
 * TC2.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 * TC2.2:- Given the State Census CSV File if incorrect Returns a custom Exception
 * TC2.3:- Given the State Census CSV File when correct but type incorrect Returns a custom Exception
 * TC2.4:- Given the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
 * TC2.5:- Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.day29.StateCensusAnalyzer;
import com.day29.exception.StateAnalyzerException;

public class StateCensusAnalyzerTest {

	private final String CENSUS_CSV_PATH = "C:\Users\user\Desktop\CSV\IndiaStateCensusData.csv";
	private final String INVALID_CENSUS_CSV_PATH = "IndiaStateCensusData.csv";
	private final String INVALID_CENSUS_CSV_DELIM = "C:\Users\user\Desktop\CSV\IndiaStateCensusDataDelim.csv";
	private final String INVALID_CENSUS_CSV_HEAD = "C:\Users\user\Desktop\CSV\IndiaStateCensusDataHead.csv";
	
	/**
	 * Paths for State Code Files
	 */
	private final String STATECODE_CSV_PATH="C:\Users\user\Desktop\CSV\IndianStateCode.csv";
	private final String INVALID_STATECODE_CSV_PATH="IndiaStateCode.csv";
	private final String INVALID_STATECODE_CSV_DELIM="C:\Users\user\Desktop\CSV\IndianStateCode.csv";
	private final String INVALID_STATECODE_CSV_HEAD="C:\Users\user\Desktop\CSV\IndiaStateCensusDataHead.csv";
	
	private StateCensusAnalyzer analyser;

	/**
	 * Annotating a public void method with @Before causes that method to be run
	 * before the Test method. The @Before methods of superclasses will be run
	 * before those of the current class.
	 */
	@Before
	public void init() {
		analyser = new StateCensusAnalyzer();
	}

	/**
	 * Test Cases For State Census
	 * @throws StateAnalyzerException
	 */
		@Test
		/**
		 * method created for given Census CSV File returns correct number of entries
		 * otherwise throws exception....
		 * 
		 * @throws IOException
		 */
		public void givenCensusCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException {
			int stateCount = analyser.readStateCensusCSVData(CENSUS_CSV_PATH);
			assertEquals(28, stateCount);
		}

	@Test
	/**
	 * method created when given incorrect csv file path it will throws custom
	 * exception invalid File Path
	 */
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath(){
		try {
			analyser.readStateCensusCSVData(INVALID_CENSUS_CSV_PATH);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	@Test
	/**
	 * method created for the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
	 */
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter(){
		try {
			analyser.readStateCensusCSVData(INVALID_CENSUS_CSV_DELIM);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM, e.type);
		}
	}

	@Test
	/**
	 * method created for the State Census CSV File when correct but csv header incorrect Returns a custom Exception
	 */
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader(){
		try {
			analyser.readStateCensusCSVData(INVALID_CENSUS_CSV_HEAD);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_HEAD, e.type);
		}
	}
	
	/**
	 * Test Cases For State Code
	 */
		@Test
		/**
		 * method created for given StateCode CSV file returns correct number of entries
		 */
		public void givenCodeCSVFileReturnsCorrectNoOfEntries(){
			int stateCount = analyser.readStateCodeCSVData(STATECODE_CSV_PATH);
			assertEquals(36, stateCount);
		}
		
		@Test
		/**
		 * method created when given incorrect csv file path it will throws custom
		 * exception invalid File Path
		 */
		public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath2(){
			try {
				analyser.readStateCodeCSVData(INVALID_STATECODE_CSV_PATH);
			} catch (StateAnalyzerException e) {
				e.printStackTrace();
				assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
			}
			
			@Test
			/**
			 * method created for the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
			 */
			public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter2(){
				try {
					analyser.readStateCodeCSVData(INVALID_STATECODE_CSV_DELIM);
				} catch (StateAnalyzerException e) {
					e.printStackTrace();
					assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM, e.type);
				}
		}
			@Test
			public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader2(){
				try {
					analyser.readStateCodeCSVData(INVALID_STATECODE_CSV_HEAD);
				} catch (StateAnalyzerException e) {
					e.printStackTrace();
					assertEquals(StateAnalyzerException.ExceptionType.INVALID_HEAD, e.type);
				}
			}

		}
