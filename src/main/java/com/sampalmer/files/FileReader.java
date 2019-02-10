package com.sampalmer.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

	public Stream<String> getCsvFileRows(String filePath) throws IOException {
		return Files.lines(Paths.get(filePath));
	}

}
