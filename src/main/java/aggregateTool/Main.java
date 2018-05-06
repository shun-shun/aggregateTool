package aggregateTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import config.Config;
import config.Practice;
import converter.JsonConverter;
import operation.FileOut;

public class Main {

    public static final String CONFIG_NAME = "Config.json";

    public static final String JAVA_JAR_EXEC = "java -jar";

    public static final String BLUNK = " ";

    public static Config config = null;

    public static void main(String[] args) {

        initialize();
        if (config == null) {
            System.out.println("設定ファイルの読み込みに失敗しました。");
            return;
        }

        try {
            List<File> employeeFilelist = getFileInstanceList(config.getPath());
            for (File employeeFile : employeeFilelist) {
                FileOut.csvDto.setEmployeeName(employeeFile.getName());
                if (employeeFile.isDirectory()) {
                    mappingJarArgument(employeeFile);
                }
            }
        } catch (NoSuchFileException e) {
            System.out.println("ファイルが見つかりません");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ファイルの入出力エラーが発生しました");
            e.printStackTrace();
        }
    }

    public static void mappingJarArgument(File employeeFile) throws IOException {
        List<File> practiceList = getFileInstanceList(employeeFile.getAbsolutePath());
        for (File practiceName : practiceList) {
            // jarファイル以外の場合は実行しない
            if (practiceName.getName().endsWith(".jar")) {
                FileOut.csvDto.setJarName(practiceName.getName());
                try {
                    prcessKicker(practiceName, mappingPractice(practiceName.getName()).getInput());
                    FileOut.print();
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void prcessKicker(File practiceJar, String inputParam) {

        try {
            Process process =
                    Runtime.getRuntime().exec(JAVA_JAR_EXEC + BLUNK + practiceJar.getPath() + BLUNK + inputParam);
            process.waitFor(2, TimeUnit.SECONDS);
            process.destroy();
            FileOut.csvDto.setArgument(inputParam);
            BufferedReader outReader =
                    new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("Shift-jis")));
            BufferedReader errorReader =
                    new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("Shift-jis")));
            String str = outReader.readLine();
            if (str != null) {
                FileOut.csvDto.setResult(str);
                return;
            }
            str = errorReader.readLine();
            FileOut.csvDto.setResult(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Practice mappingPractice(String practiceName) {
        for (Practice practice : config.getPracticeList()) {
            if (practiceName.equals(practice.getPracticeName())) {
                return practice;
            }
        }
        throw new IllegalArgumentException("対応する問題がありません 読み込まれたファイル名:" + practiceName);
    }

    public static void initialize() {
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

    private static List<File> getFileInstanceList(String filePathStr) throws IOException {
        try (Stream<Path> path = Files.list(Paths.get(filePathStr))) {
            return path.map(f -> f.toFile()).collect(Collectors.toList());
        }
    }

}
