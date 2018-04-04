package operation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import config.Config;

public class FileOut {

	public static Config config;

	private static FileOut fileOut = new FileOut();

	private FileOut() {

	}

	public static FileOut getFileOut() {
		return fileOut;
	}

	public static void print(String printString) {
		try {
			FileWriter fileWriter = new FileWriter(config.getOutputPath() ,true);
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
			printWriter.print(printString);
			printWriter.print(" ");
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void println(String printString) {
		try {
			FileWriter fileWriter = new FileWriter(config.getOutputPath() ,true);
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
			printWriter.println(printString);
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void println(BufferedReader bufferedReader) {
		try {
			FileWriter fileWriter = new FileWriter(config.getOutputPath(),true);
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
			String printString = bufferedReader.readLine();
			while(printString != null) {
				printWriter.println(printString);
				printString = bufferedReader.readLine();
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
