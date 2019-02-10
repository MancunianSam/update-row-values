package com.sampalmer.exceptions;

public class EmptyCsvFileException extends RuntimeException {

	public EmptyCsvFileException() {
		super("The provided csv file is empty");
	}
}
