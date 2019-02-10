package com.sampalmer.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

	public List<String> getCsvFileRows(String filePath) throws IOException {
		Stream<String> lines = Files.lines(Paths.get(filePath));
		return lines.collect(Collectors.toList());
	}

}
