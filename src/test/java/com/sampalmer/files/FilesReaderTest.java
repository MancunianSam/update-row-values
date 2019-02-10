package com.sampalmer.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesReaderTest {

	@Test
	public void testFileLoading(@TempDir Path path) throws IOException {
		Path filePath = Files.createTempFile(path, "", "");
		List<String> rows = Arrays.asList("a,b,c", "d,e,f");
		Files.write(filePath, rows);

		FileReader fileReader = new FileReader();
		List<String> resultRows = fileReader.getCsvFileRows(filePath.toString());

		assertEquals(rows, resultRows);
	}
}
