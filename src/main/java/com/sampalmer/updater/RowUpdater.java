package com.sampalmer.updater;

import com.sampalmer.arguments.UpdateRowArguments;
import com.sampalmer.csv.CsvParser;
import com.sampalmer.files.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class RowUpdater {

	public void updateRowValues(UpdateRowArguments arguments) throws IOException {
		FileReader reader = new FileReader();
		List<String> csvFileRows = reader.getCsvFileRows(arguments.getPath());

		CsvParser parser = new CsvParser();

		List<String> header = parser
				.getHeaderFromRows(csvFileRows.stream())
				.collect(Collectors.toList());

		List<String> replacedRows = csvFileRows.stream()
				.map(row -> row.split(","))
				.map(row -> updateIfRowMatches(arguments, parser, header, row))
				.collect(Collectors.toList());

		Path output = Paths.get(Paths.get(arguments.getPath()).getParent().toString(), "output.csv");

		Files.write(output, replacedRows);

	}

	public String updateIfRowMatches(UpdateRowArguments arguments, CsvParser parser, List<String> header, String[] columns) {
		if (parser.rowMatchesFilename(columns, arguments.getCsvFileName())) {
			columns = parser.replaceAtIndex(columns, arguments.getOldValue(), arguments.getNewValue(), header.indexOf(arguments.getColumn()));
		}
		return String.join(",", columns);
	}
}
