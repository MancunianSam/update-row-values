package com.sampalmer.exceptions;

public class InvalidIndexException extends RuntimeException {

	public InvalidIndexException() {
		super("The index given is invalid");
	}
}
