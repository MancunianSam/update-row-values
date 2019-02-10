package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvParserTest {

	@Test
	public void testGetHeaderWithWhitespace() {
		List<String> rows = Arrays.asList("a, b,  c", "d,e,f");
		CsvParser parser = new CsvParser();
		List<String> headerFromRows = parser.getHeaderFromRows(rows);

		assertEquals(Arrays.asList("a", "b", "c"), headerFromRows);
	}

	@Test
	public void testGetHeaderWithoutWhitespace() {
		List<String> rows = Arrays.asList("a,b,c", "d,e,f");
		CsvParser parser = new CsvParser();
		List<String> headerFromRows = parser.getHeaderFromRows(rows);

		assertEquals(Arrays.asList("a", "b", "c"), headerFromRows);
	}

	@Test
	public void testGetHeaderFromEmptyInput() {
		List<String> rows = Collections.emptyList();
		CsvParser parser = new CsvParser();

		Executable executable = () -> parser.getHeaderFromRows(rows);

		assertThrows(EmptyCsvFileException.class, executable);
	}
}
