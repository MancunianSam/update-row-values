package com.sampalmer.csv;

import com.sampalmer.exceptions.EmptyCsvFileException;
import com.sampalmer.exceptions.InvalidIndexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTest {

	private CsvParser parser;

	@BeforeEach
	public void beforeEach() {
		parser = new CsvParser();
	}

	@Test
	public void testGetHeaderWithWhitespace() {
		Stream<String> rows = Stream.of("a, b,  c", "d,e,f");
		Stream<String> headerFromRows = parser.getHeaderFromRows(rows);

		assertEquals(Arrays.asList("a", "b", "c"), headerFromRows.collect(Collectors.toList()));
	}

	@Test
	public void testGetHeaderWithoutWhitespace() {
		Stream<String> rows = Stream.of("a,b,c", "d,e,f");
		Stream<String> headerFromRows = parser.getHeaderFromRows(rows);

		assertEquals(Arrays.asList("a", "b", "c"), headerFromRows.collect(Collectors.toList()));
	}

	@Test
	public void testGetHeaderFromEmptyInput() {
		Stream<String> rows = Stream.of();

		Executable executable = () -> parser.getHeaderFromRows(rows);

		assertThrows(EmptyCsvFileException.class, executable);
	}

	@Test
	public void testRowMatchFilename() {
		boolean result = parser.rowMatchesFilename(new String[]{"a","b","c"}, "a");

		assertTrue(result);
	}

	@Test
	public void testRowNoMatchFilename() {
		boolean result = parser.rowMatchesFilename(new String[]{"a","b","c"}, "d");

		assertFalse(result);
	}

	@Test
	public void testReplaceEntireColumnAtIndex() {
		String[] columns = parser.replaceAtIndex(new String[]{"test1", "test2", "test3"}, "test2", "test4", 1);

		assertArrayEquals(new String[]{"test1", "test4", "test3"}, columns);
	}

	@Test
	public void testReplacePartialColumnAtIndex() {
		CsvParser parser = new CsvParser();

		String[] columns = parser.replaceAtIndex(new String[]{"test1 test2", "test3", "test4"}, "test2", "test5", 0);

		assertArrayEquals(new String[]{"test1 test5", "test3", "test4"}, columns);
	}

	@Test
	public void testReplaceNoMatchAtIndex() {
		CsvParser parser = new CsvParser();

		String[] columns = parser.replaceAtIndex(new String[]{"test1", "test2", "test3"}, "test4", "test5", 2);

		assertArrayEquals(new String[]{"test1", "test2", "test3"}, columns);
	}

	@Test
	public void testReplaceInvalidIndex() {
		CsvParser parser = new CsvParser();

		Executable executable = () -> parser.replaceAtIndex(new String[]{"test1", "test2", "test3"}, "test4", "test5", 3);

		assertThrows(InvalidIndexException.class, executable);
	}




}
