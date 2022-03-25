package com.day29;

import java.io.IOException;

/**
 * UC1:-   Ability for the analyser to load the Indian States Census Information from a csv file 
 * TC1.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 * TC1.2:- Given the State Census CSV File if incorrect Returns a custom Exception
 * TC1.3:- Given the State Census CSV File when correct but type incorrect Returns a custom Exception
 */

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.day29.exception.StateAnalyzerException;
import com.day29.exception.StateAnalyzerException.ExceptionType;
import com.day29.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {

	/**
	 * create method readCSVData that reads the data from csv file
	 * 
	 * @throws IOException -throws exception
	 */
	public int readCSVData(String FilePath) throws StateAnalyzerException {
		int count = 0;

		/**
		 * taking try and Catch block to handle the catch exceptions
		 */
		try {
			try {
				Files.newBufferedReader(Paths.get(FilePath));
			} catch (IOException exception) {
				throw new StateAnalyzerException("Inavlid Path Name", ExceptionType.INVALID_FILE_PATH);
			}
			Reader reader = Files.newBufferedReader(Paths.get(FilePath));
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<CSVStateCensus>(reader);

			try {
				csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSkipLines(1).withType(CSVStateCensus.class);
			} catch (IllegalStateException exception) {
				throw new StateAnalyzerException("Invalid Class Type.", ExceptionType.INVALID_CLASS_TYPE);
			}

			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			/**
			 * An Iterator is an object that can be used to loop through collections, like
			 * ArrayList and HashSet. It is called an "iterator" because "iterating" is the
			 * technical term for looping.
			 */
			Iterator<CSVStateCensus> csvIterator = csvToBean.iterator();

			/**
			 * To loop through a collection, use the hasNext() and next() methods of the
			 * Iterator
			 */
			while (csvIterator.hasNext()) {
				count++;
				CSVStateCensus csvData = csvIterator.next();
				System.out.println(csvData);
			}
			return count;

		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return 0;
	}
}
