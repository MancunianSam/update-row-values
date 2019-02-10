package com.sampalmer.updater;

import com.sampalmer.arguments.UpdateRowArguments;
import com.sampalmer.csv.CsvParser;

import java.io.IOException;
import java.util.List;

public class RowUpdater {

	public void updateRowValues(UpdateRowArguments arguments) throws IOException {


	}

	public String updateIfRowMatches(UpdateRowArguments arguments, CsvParser parser, List<String> header, String[] columns) {
		if (parser.rowMatchesFilename(columns, arguments.getCsvFileName())) {
			columns = parser.replaceAtIndex(columns, arguments.getOldValue(), arguments.getNewValue(), header.indexOf(arguments.getColumn()));
		}
		return String.join(",", columns);
	}
}
