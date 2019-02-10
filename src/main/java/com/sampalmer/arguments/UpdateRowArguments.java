package com.sampalmer.arguments;

public class UpdateRowArguments {

	private String path;
	private String csvFileName;
	private String column;
	private String oldValue;
	private String newValue;

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
