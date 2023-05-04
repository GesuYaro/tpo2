package shagiev_dobryagin;

import java.io.File;
import java.util.List;
import java.util.function.Function;

public class DataProcessor {


    public void writeResultsToFile(List<Double> x, Function<Double, Double> func, File file) {
        var y = x.stream()
                .map(d -> List.of(d.toString(), func.apply(d).toString()))
                .toList();
        CsvProcessor.writeCsv(file, y);
    }
}
