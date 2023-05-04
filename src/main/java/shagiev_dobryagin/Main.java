package shagiev_dobryagin;

import java.io.File;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    String resFolder = "resources";
    DataProcessor dataProcessor = new DataProcessor();
    var x = new ArrayList<Double>();
    for (double i = -10; i <= 10; i += 0.1) {
      x.add(i);
    }
    dataProcessor.writeResultsToFile(x, Math::sin, new File(resFolder,"sin.csv"));
    dataProcessor.writeResultsToFile(x, Math::cos, new File(resFolder, "cos.csv"));
    dataProcessor.writeResultsToFile(x, d -> 1 / Math.sin(d), new File(resFolder, "sec.csv"));
    dataProcessor.writeResultsToFile(x, d -> 1 / Math.cos(d), new File(resFolder, "csc.csv"));
    dataProcessor.writeResultsToFile(x, Math::tan, new File(resFolder, "tan.csv"));
    dataProcessor.writeResultsToFile(x, d -> 1 / Math.tan(d), new File(resFolder, "cot.csv"));
    dataProcessor.writeResultsToFile(x, Math::log, new File(resFolder, "ln.csv"));
    dataProcessor.writeResultsToFile(x, d -> Math.log(d) / Math.log(2), new File(resFolder, "log2.csv"));
    dataProcessor.writeResultsToFile(x, d -> Math.log(d) / Math.log(3), new File(resFolder, "log3.csv"));
    dataProcessor.writeResultsToFile(x, d -> Math.log(d) / Math.log(5), new File(resFolder, "log5.csv"));
    dataProcessor.writeResultsToFile(x, Math::log10, new File(resFolder, "log10.csv"));
  }
}