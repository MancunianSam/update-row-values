package com.sampalmer.updater;

import com.sampalmer.arguments.UpdateRowArguments;
import com.sampalmer.csv.CsvParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
}
