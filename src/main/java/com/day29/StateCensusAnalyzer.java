package com.day29;

import java.io.IOException;

/**
 * UC1:-   Ability for the analyser to load the Indian States Census Information from a csv file 
 * TC1.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 * TC1.2:- Given the State Census CSV File if incorrect Returns a custom Exception
 * TC1.3:- Given the State Census CSV File when correct but type incorrect Returns a custom Exception
 * TC1.4:- Given the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
 * TC1.5:- Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception
 * 
 * UC2:-   Ability for the analyser to load the Indian States Code Information from a csv
 * TC2.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 * TC2.2:- Given the State Census CSV File if incorrect Returns a custom Exception
 * TC2.3:- Given the State Census CSV File when correct but type incorrect Returns a custom Exception
 * TC2.4:- Given the State Census CSV File when correct but delimiter incorrect Returns a custom Exception
 * TC2.5:- Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception

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
import com.day29.model.CSVStates;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {

	/**
	 * create method readStateCSVData that reads the State data from csv file
	 * 
	 * @throws IOException -throws exception
	 */
	public int readStateCensusCSVData(String FilePath) throws StateAnalyzerException {

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

			BufferedReader br = new BufferedReader(reader);

			/**
			 * To loop through a collection, use the hasNext() and next() methods of the
			 * Iterator Check header
			 */
			while (br.readLine() != null) {
				String[] head = br.readLine().split(",");
				boolean flagCorrectHead = head[0] == "State" && head[1] == "Population" && head[2] == "AreaInSqKm"
						&& head[3] == "DensityPerSqKm";
				if (!flagCorrectHead) {
					throw new StateAnalyzerException("Invalid Headers", ExceptionType.INVALID_HEAD);
				}
				break;
			}

			/**
			 * Check delimitor
			 */
			boolean flagCorrectDelim = true;
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
			throw new StateAnalyzerException("Invalid Path Name", ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException exception) {
			throw new StateAnalyzerException("Invalid Class Type.", ExceptionType.INVALID_CLASS_TYPE);
		}
	}

	/**
	 * method to readStateCSV data from the csv file
	 * 
	 * @param FilePath -pass csv file path
	 * @return -return to method created
	 */
	public int readStateCodeCSVData(String FilePath) {

		try {
			Files.newBufferedReader(Paths.get(FilePath));
			Reader reader = Files.newBufferedReader(Paths.get(FilePath));
			CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder<CSVStates>(reader).withIgnoreLeadingWhiteSpace(true)
					.withSkipLines(1).withType(CSVStates.class).build();

			Iterator<CSVStates> csvIterator = csvToBean.iterator();

			Iterable<CSVStates> csvItrable = () -> csvIterator;
			int count = (int) StreamSupport.stream(csvItrable.spliterator(), false).count();

			BufferedReader br = new BufferedReader(reader);

			/**
			 * Check Header
			 */
			while (br.readLine() != null) {
				String[] head = br.readLine().split(",");
				boolean flagCorrectHead = head[0] == "SrNo" && head[1] == "State Name" && head[2] == "TIN"
						&& head[3] == "StateCode";
				if (!flagCorrectHead) {
					throw new StateAnalyzerException("Invalid Headers", ExceptionType.INVALID_HEAD);
				}
				break;
			}

			/**
			 * Check delimitor
			 */
			boolean flagCorrectDelim = true;
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
			throw new StateAnalyzerException("Invalid Path Name", ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException exception) {
			throw new StateAnalyzerException("Invalid Class Type.", ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}
