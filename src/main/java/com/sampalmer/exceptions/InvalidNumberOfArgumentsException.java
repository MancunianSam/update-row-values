package com.sampalmer.exceptions;

public class InvalidNumberOfArgumentsException extends RuntimeException {

	public InvalidNumberOfArgumentsException(int actual) {
		super("You have provided " + actual + " arguments but 5 are needed");
	}
}
