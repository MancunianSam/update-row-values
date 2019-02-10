package com.sampalmer.updater;

import com.sampalmer.arguments.UpdateRowArguments;
import com.sampalmer.csv.CsvParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowUpdaterTest {

	private RowUpdater rowUpdater;

	@BeforeEach
	void beforeEach() {
		rowUpdater = new RowUpdater();
	}

	@Test
	public void testUpdateIfRowMatches() {
		String[] args = new String[]{"","csvFileName", "column1", "oldValue", "newValue"};
		UpdateRowArguments arguments = new UpdateRowArguments(args);

		List<String> header = Arrays.asList("filename","column1","column2");
		String[] columns = new String[]{"csvFileName","oldValue", "somethingElse"};
		String result = rowUpdater.updateIfRowMatches(arguments, new CsvParser(), header, columns);
		assertEquals("csvFileName,newValue,somethingElse", result);
	}

	@Test
	public void testUpdateIfNoRowMatches() {
		String[] args = new String[]{"","csvFileName", "column1", "oldValue", "newValue"};
		UpdateRowArguments arguments = new UpdateRowArguments(args);

		List<String> header = Arrays.asList("filename","column1","column2");
		String[] columns = new String[]{"aDifferentFilename","oldValue", "somethingElse"};
		String result = rowUpdater.updateIfRowMatches(arguments, new CsvParser(), header, columns);
		assertEquals("aDifferentFilename,oldValue,somethingElse", result);
	}

	@Test
	public void testUpdateIfRowMatchesButNoValueMatch() {
		String[] args = new String[]{"","csvFileName", "column1", "notFoundValue", "newValue"};
		UpdateRowArguments arguments = new UpdateRowArguments(args);

		List<String> header = Arrays.asList("filename","column1","column2");
		String[] columns = new String[]{"csvFileName","oldValue", "somethingElse"};
		String result = rowUpdater.updateIfRowMatches(arguments, new CsvParser(), header, columns);
		assertEquals("csvFileName,oldValue,somethingElse", result);
	}

	/**
	 * This is an end to end test which uses real file objects in a temporary folder.
	 */
	@Test
	public void testUpdateRowValues(@TempDir Path path) throws IOException {
		Path filePath = Files.createTempFile(path, "", "");
		List<String> rows = Arrays.asList("filename, origin, metadata, hash",
				"file1, London, \"a file about London\", hash",
				"file55, Londom, \"London was initially incorrectly spelled as Londom\", hash");
		Files.write(filePath, rows);
		String[] args = new String[]{filePath.toString(),"file55", "origin", "Londom", "London"};
		UpdateRowArguments arguments = new UpdateRowArguments(args);
		rowUpdater.updateRowValues(arguments);

		List<String> result = Files.lines(Paths.get(path.toString(), "output.csv"))
				.collect(Collectors.toList());

		List<String> expectedResult = Arrays.asList("filename, origin, metadata, hash",
				"file1, London, \"a file about London\", hash",
				"file55, London, \"London was initially incorrectly spelled as Londom\", hash");

		assertEquals(expectedResult, result);
	}
}
