package shagiev_dobryagin;

public class Trigonometry {

  private final TrigonometrySeries trigonometrySeries;

  public Trigonometry() {
    this.trigonometrySeries = new TrigonometrySeries();
  }

  public Trigonometry(TrigonometrySeries trigonometrySeries) {
    this.trigonometrySeries = trigonometrySeries;
  }

  public double cos(double x, double e) {
    return trigonometrySeries.serializeCos(x, e);
  }

  public double sin(double x, double e) {
    x = Math.PI / 2 - x;
    return cos(x, e);
  }

  public double sec(double x, double e) {
    return 1 / sin(x, e);
  }

  public double csc(double x, double e) {
    return 1 / cos(x, e);
  }

  public double tan(double x, double e) {
    return sin(x, e) / cos(x, e);
  }

  public double cot(double x, double e) {
    return cos(x, e) / sin(x, e);
  }
}
