package com.day29;

public class StateAnalyzerException extends Exception {

	private static final long serialVersionUID = 3369988279518561290L;

	/**
	 * Java Enums can be thought of as classes which have a fixed set of constants
	 * (a variable that does not change). The Java enum constants are static and
	 * final implicitly.
	 * 
	 * @author user -Almas
	 *
	 */
	public enum ExceptionType {
		INVALID_FILE_PATH
	}

	public ExceptionType type;

	/**
	 * create parameterized constructor of StateAnalyzerException
	 * 
	 * @param message -passing meassage
	 * @param type    -exception type
	 */
	public StateAnalyzerException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
