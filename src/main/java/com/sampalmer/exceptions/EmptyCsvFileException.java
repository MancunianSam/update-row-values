package com.sampalmer.exceptions;

public class EmptyCsvFileException extends RuntimeException {

	public EmptyCsvFileException(String message) {
		super(message);
	}
}
