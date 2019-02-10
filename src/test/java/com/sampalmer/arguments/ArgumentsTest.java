package com.sampalmer.arguments;

import com.sampalmer.exceptions.InvalidNumberOfArgumentsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentsTest {

	@Test
	public void testValidArguments() {
		String[] args = new String[]{"path", "csvFileName", "column", "oldValue", "newValue"};
		UpdateRowArguments arguments = new UpdateRowArguments(args);
		assertEquals(arguments.getPath(), "path");
		assertEquals(arguments.getCsvFileName(), "csvFileName");
		assertEquals(arguments.getColumn(), "column");
		assertEquals(arguments.getOldValue(), "oldValue");
		assertEquals(arguments.getNewValue(), "newValue");
	}

	@Test
	public void testInvalidArguments() {
		String[] args = new String[]{"path", "csvFileName", "column"};
		Executable executable = () -> new UpdateRowArguments(args);

		InvalidNumberOfArgumentsException exception = assertThrows(InvalidNumberOfArgumentsException.class, executable);
		assertEquals(exception.getMessage(), "You have provided 3 arguments but 5 are needed");

	}

}
