package com.day29;

import java.io.IOException;

/**
 * UC1:-   Ability for the analyser to load the Indian States Census Information from a csv file 
 * TC1.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 * TC1.2:- Given the State Census CSV File if incorrect Returns a custom Exception
 * TC1.3:- Given the State Census CSV File when correct but type incorrect Returns a custom Exception
 * TC1.4:_ Given the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
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

		/**
		 * taking try and Catch block to handle the catch exceptions
		 */
		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader = Files.newBufferedReader(Paths.get(FilePath));
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
					.withIgnoreLeadingWhiteSpace(true).withSkipLines(1).withType(CSVStateCensus.class).build();

			/**
			 * An Iterator is an object that can be used to loop through collections, like
			 * ArrayList and HashSet. It is called an "iterator" because "iterating" is the
			 * technical term for looping.
			 */
			Iterator<CSVStateCensus> csvIterator = csvToBean.iterator();

			Iterable<CSVStateCensus> csvItrable = () -> csvIterator;
			int count = (int) StreamSupport.stream(csvItrable.spliterator(), false).count();

			/**
			 * Check delimitor
			 */
			BufferedReader br = new BufferedReader(reader);
			boolean flagCorrectDelim = true;

			/**
			 * To loop through a collection, use the hasNext() and next() methods of the
			 * Iterator
			 */
			while (br.readLine() != null) {
				if (!br.readLine().contains(",")) {
					flagCorrectDelim = false;
				}
			}
			if (!flagCorrectDelim) {
				throw new StateAnalyzerException("Invalid Delimitor", ExceptionType.INVALID_DELIM);
			}

			return count;

		} catch (IOException exception) {
			throw new StateAnalyzerException("Inavlid Path Name", ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException exception) {
			throw new StateAnalyzerException("Invalid Class Type.", ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}
