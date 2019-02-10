package com.sampalmer.arguments;

import com.sampalmer.exceptions.InvalidNumberOfArgumentsException;

public class UpdateRowArguments {

	private String path;
	private String csvFileName;
	private String column;
	private String oldValue;
	private String newValue;

	public UpdateRowArguments(String args[]) {
		if(args.length < 5) {
			throw new InvalidNumberOfArgumentsException(args.length);
		}
		this.path = args[0];
		this.csvFileName = args[1];
		this.column = args[2];
		this.oldValue = args[3];
		this.newValue = args[4];
	}

	public String getPath() {
		return path;
	}

	public String getCsvFileName() {
		return csvFileName;
	}

	public String getColumn() {
		return column;
	}

	public String getOldValue() {
		return oldValue;
	}

	public String getNewValue() {
		return newValue;
	}
}
