package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;
import com.sampalmer.exceptions.InvalidIndexException;

import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class CsvParser {

	public Stream<String> getHeaderFromRows(Stream<String> rows) {
		String[] columnHeaders = rows.findFirst()
				.map(r -> r.split(","))
				.orElseThrow(EmptyCsvFileException::new);

		return Stream.of(columnHeaders).map(String::trim);
	}

	public boolean rowMatchesFilename(String[] columns, String csvFilename) {
		if(columns.length > 0 && nonNull(columns[0])) {
			return columns[0].trim().equals(csvFilename);
		}
		return false;
	}

	public String[] replaceAtIndex(String[] columns, String oldValue, String newValue, int index) {
		if(isNull(columns) || index > columns.length - 1 || index < 0) {
			throw new InvalidIndexException();
		}

		String[] newColumns = columns.clone();
		String value = newColumns[index];
		if(nonNull(value)) {
			String replacedValue = value.replaceAll(oldValue, newValue);
			newColumns[index] = replacedValue;
		}
		return newColumns;
	}

}
