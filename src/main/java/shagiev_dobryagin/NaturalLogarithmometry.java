package shagiev_dobryagin;

import static java.lang.Math.pow;

public class NaturalLogarithmometry {
  private static final double EPS = 0.00001;

  public double ln(double t) {
    return ln(t, EPS);
  }

  public double ln(double t, double eps) {
    if (t <= 0.) {
      throw new IllegalArgumentException();
    }

    final double yFirst = 20;
    double yCur;
    double yNext = yFirst;

    do {
      yCur = yNext;
      yNext = yCur - 1 + t / pow(Math.E, yCur);
    } while (yCur - yNext > eps);

    return yNext;
  }
}
