package com.sampalmer;

import com.sampalmer.arguments.UpdateRowArguments;
import com.sampalmer.updater.RowUpdater;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
		UpdateRowArguments arguments = new UpdateRowArguments(args);
		RowUpdater updater = new RowUpdater();
		updater.updateRowValues(arguments);
	}


}
