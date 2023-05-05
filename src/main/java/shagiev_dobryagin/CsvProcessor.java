package shagiev_dobryagin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class CsvProcessor {
  private static final String DELIMITER = ",";

  public static List<? extends List<String>> readCSV(File file) {
    Scanner scanner;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException ignore) {
      ignore.printStackTrace();
      throw new IllegalArgumentException();
    }

    var result = new ArrayList<ArrayList<String>>();

    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var row = new ArrayList<>(List.of(line.split(DELIMITER)));
      result.add(row);
    }

    return result;
  }

  public static void writeCsv(File file, List<? extends List<String>> rows) {
    try (var writer = Files.newBufferedWriter(file.toPath(), CREATE)) {
      rows.stream()
        .map(row -> row.get(0) + DELIMITER + row.get(1))
        .forEach(line -> {
          try {
            writer.append(line);
            writer.append("\n");
          } catch (IOException ignored) {
          }
        });
    } catch (IOException ignored) {
    }
  }
}
