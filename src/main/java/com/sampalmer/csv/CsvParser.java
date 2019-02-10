package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;

import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class CsvParser {

	public Stream<String> getHeaderFromRows(Stream<String> rows) {
		String[] columnHeaders = rows.findFirst()
				.map(r -> r.split(","))
				.orElseThrow(EmptyCsvFileException::new);

		return Stream.of(columnHeaders).map(String::trim);
	}

	public boolean rowMatchesFilename(String row, String csvFilename) {
		if(nonNull(row)) {
			String[] columns = row.split(",");
			if(columns.length > 0 && nonNull(columns[0])) {
				return columns[0].trim().equals(csvFilename);
			}
		}
		return false;
	}
}
