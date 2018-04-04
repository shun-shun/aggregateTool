package aggregateTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import config.Config;
import config.Practice;
import converter.JsonConverter;
import operation.FileOut;

public class Main {

	public static final String CONFIG_NAME= "Config.json";

	public static final String JAVA_JAR_EXEC = "java -jar";

	public static final String BLUNK = " ";

	public static Config config = null;

	public static void main(String[] args) {

		init();
		if(config == null) {
			return;
		}

		File rootFile = new File(config.getPath());
		String[] employeeList = rootFile.list();
		if(employeeList == null) {
			return;
		}
		for(String employee : employeeList) {
			FileOut.println(employee);
			File employeeFile = new File(rootFile, employee);
			fileOperation(employeeFile);
		}
	}

	public static void fileOperation(File employeeFile) {
		String[] practiceList = employeeFile.list();
		if (practiceList == null) {
			return;
		}
		for(String practiceName : practiceList) {
			//jarファイル以外の場合は実行しない
			if(practiceName.endsWith(".jar")) {
				FileOut.print(practiceName);
				File practiceFile = new File(employeeFile,practiceName);
				Practice practice = selectPractice(practiceName);
				prcessKicker(practiceFile,practice.getInput());
			} else {
				FileOut.println(practiceName + " 未完成");
			}
		}
	}

	public static void prcessKicker(File practiceJar, String inputParam) {

		try {
			Runtime runtime = Runtime.getRuntime();
			String command = JAVA_JAR_EXEC + BLUNK + practiceJar.getPath() + BLUNK + inputParam;
			Process process = runtime.exec(command);
			process.waitFor();
			process.destroy();
			BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("Shift-jis")));
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName("Shift-jis")));
			FileOut.println(outReader);
			FileOut.println(errorReader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Practice selectPractice(String practiceName) {
		for (Practice practice : config.getPracticeList()) {
			if(practiceName.equals(practice.getPracticeName())) {
				return practice;
			}
		}
		throw new IllegalStateException("対応する問題がありません");
	}

	public static void init() {
		String configJsonPath = Main.class.getClassLoader().getResource(CONFIG_NAME).getPath();

		try {
			config = JsonConverter.toObject(new File(configJsonPath), Config.class);
			FileOut.config = config;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
