package com.da29;

/**
 * TC1.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 */
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import com.day29.StateCensusAnalyzer;

public class StateCensusAnalyzerTest {

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
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() throws IOException {
		int stateCount = analyser.readCSVData();
		assertEquals(29, stateCount);
	}
}
