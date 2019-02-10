package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;

import java.util.stream.Stream;

public class CsvParser {

	public Stream<String> getHeaderFromRows(Stream<String> rows) {
		String[] columnHeaders = rows.findFirst()
				.map(r -> r.split(","))
				.orElseThrow(EmptyCsvFileException::new);

		return Stream.of(columnHeaders).map(String::trim);
	}

	public Stream<String> getSelectedRow(Stream<String> rows, String csvFileName) {
		return Stream.of();
	}
}
