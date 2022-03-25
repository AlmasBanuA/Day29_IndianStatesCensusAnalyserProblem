package com.da29;

/**
 * TC1.5:- Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception
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

	@Test
	/**
	 * method created for given Census CSV File returns correct number of entries
	 * otherwise throws exception....
	 * 
	 * @throws IOException
	 */
	public void givenCensusCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException {
		int stateCount = analyser.readCSVData(CENSUS_CSV_PATH);
		assertEquals(28, stateCount);
	}

	@Test
	/**
	 * method created when given incorrect csv file path it will throws custom
	 * exception invalid File Path
	 */
	public void givenIncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath() {
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_PATH);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	@Test
	/**
	 * method created for the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
	 */
	public void givenIncorrectDelimiter_ThrowsCustomExceptionInvalidDelimiter() {
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_DELIM);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIM, e.type);
		}
	}

	@Test
	/**
	 * method created for the State Census CSV File when correct but csv header incorrect Returns a custom Exception
	 */
	public void givenIncorrectHeader_ThrowsCustomExceptionInvalidHeader() {
		try {
			analyser.readCSVData(INVALID_CENSUS_CSV_HEAD);
		} catch (StateAnalyzerException e) {
			e.printStackTrace();
			assertEquals(StateAnalyzerException.ExceptionType.INVALID_HEAD, e.type);
		}
	}
}
