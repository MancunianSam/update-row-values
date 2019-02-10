package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	public void testGetSelectedRow() {
		Stream<String> rows = Stream.of("a,b,c", "d,e,f", "g,h,i");
		CsvParser parser = new CsvParser();

		Stream<String> result = parser.getSelectedRow(rows, "d");

		assertEquals(result.collect(Collectors.toList()), Collections.singletonList("d,e,f"));
	}
}
