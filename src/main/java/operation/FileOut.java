package operation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import config.Config;
import dto.CsvDto;

public class FileOut {

    public static Config config;

    public static CsvDto csvDto = new CsvDto();

    private static FileOut fileOut = new FileOut();

    private FileOut() {

    }

    public static FileOut getFileOut() {
        return fileOut;
    }

    public static void print() {
        try (FileWriter fileWriter = new FileWriter(config.getOutputPath(), true);
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));) {
            printWriter.print(csvDto.getEmployeeName());
            printWriter.print(",");
            printWriter.print(csvDto.getJarName());
            printWriter.print(",");
            printWriter.print(csvDto.getArgument());
            printWriter.print(",");
            printWriter.print(csvDto.getResult() == null ? null : csvDto.getResult().replace("\"", " "));
            printWriter.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
