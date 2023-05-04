package shagiev_dobryagin;

import java.io.File;
import java.util.NavigableMap;
import java.util.TreeMap;

public class FuncCSVStub {
  private final String fileName;

  private final NavigableMap<Double, Double> mappings;

  public FuncCSVStub(String fileName) {
    this.fileName = fileName;
    mappings = fetch(new File(fileName));
  }

  public double calc(double x) {
    var xLeft = mappings.floorEntry(x);
    var xRight = mappings.ceilingEntry(x);

    if(xRight == null) return xLeft.getValue();
    if(xLeft == null ) return xRight.getValue();

    throw new IllegalStateException();
  }

  private static NavigableMap<Double, Double> fetch(File file) {
    var rows = CsvProcessor.readCSV(file);
    var result = new TreeMap<Double, Double>();

    rows.forEach(row -> {
      var x = Double.parseDouble(row.get(0));
      var y = Double.parseDouble(row.get(1));
      result.put(x, y);
    });

    return result;
  }
}
