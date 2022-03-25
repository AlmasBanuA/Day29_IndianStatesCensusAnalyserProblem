package com.day29;

/**
 * UC1:-   Ability for the analyser to load the Indian States Census Information from a csv file 
 * TC1.1:- Given the States Census CSV file, Check to ensure the Number of Record matches
 */

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import com.day29.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {

	private final String CENSUS_CSV_PATH = "C:\Users\user\Desktop\CSV\IndiaStateCensusData.csv";

	/**
	 * create method readCSVData that reads the data from csv file
	 * 
	 * @return -return to method created
	 * @throws IOException -throws exception
	 */
	public int readCSVData() throws IOException {
		int count = 0;

		/**
		 * taking try and Catch block to handle the exceptions
		 */
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CENSUS_CSV_PATH));
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
					.withIgnoreLeadingWhiteSpace(true).withSkipLines(1).withType(CSVStateCensus.class).build();

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
