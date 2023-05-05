package shagiev_dobryagin;

import java.util.function.BiFunction;

public class TrigonometrySeries {
  private final BiFunction<Double, Integer, Double> cosSeries = (x, n) -> (Math.pow(-1, n) * Math.pow(x, 2 * n)) * getInverseFactorial(2 * n);

  public double decomposeToSeries(double x, double e) {
    if (Double.isNaN(x) || Double.isInfinite(x)) {
      return Double.NaN;
    }
    double sum = 0;
    double prev = 0;
    double curr = Double.MAX_VALUE;
    int n = 0;
    while (Math.abs(curr - prev) >= e) {
      prev = curr;
      curr = cosSeries.apply(x, n++);
      sum += curr;
    }
    return sum;
  }

  private double getInverseFactorial(int n) {
    double res = 1;
    for (int i = 1; i <= n; ++i) {
      res /= i;
    }
    return res;
  }

}
