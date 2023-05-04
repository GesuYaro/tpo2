package shagiev_dobryagin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
  public static List<? extends List<String>> readCSV(File file) {
    Scanner scanner;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException ignore) {
      throw new IllegalArgumentException();
    }

    var result = new ArrayList<ArrayList<String>>();

    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var row = new ArrayList<>(List.of(line.split(",")));
      result.add(row);
    }

    return result;
  }
}
