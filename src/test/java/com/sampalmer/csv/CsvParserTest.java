package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTest {

	@Test
	public void testGetHeaderWithWhitespace() {
		Stream<String> rows = Stream.of("a, b,  c", "d,e,f");
		CsvParser parser = new CsvParser();
		Stream<String> headerFromRows = parser.getHeaderFromRows(rows);

		assertEquals(Arrays.asList("a", "b", "c"), headerFromRows.collect(Collectors.toList()));
	}

	@Test
	public void testGetHeaderWithoutWhitespace() {
		Stream<String> rows = Stream.of("a,b,c", "d,e,f");
		CsvParser parser = new CsvParser();
		Stream<String> headerFromRows = parser.getHeaderFromRows(rows);

		assertEquals(Arrays.asList("a", "b", "c"), headerFromRows.collect(Collectors.toList()));
	}

	@Test
	public void testGetHeaderFromEmptyInput() {
		Stream<String> rows = Stream.of();
		CsvParser parser = new CsvParser();

		Executable executable = () -> parser.getHeaderFromRows(rows);

		assertThrows(EmptyCsvFileException.class, executable);
	}

	@Test
	public void testRowMatchFilename() {
		CsvParser parser = new CsvParser();

		boolean result = parser.rowMatchesFilename("a,b,c", "d");

		assertTrue(result);
	}

	@Test
	public void testRowNoMatchFilename() {
		CsvParser parser = new CsvParser();

		boolean result = parser.rowMatchesFilename("d,b,c", "d");

		assertFalse(result);
	}
}
