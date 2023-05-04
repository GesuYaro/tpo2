package shagiev_dobryagin;

public class Logarithmometry {
  private final NaturalLogarithmometry naturalLogarithmometry;

  public Logarithmometry() {
    naturalLogarithmometry = new NaturalLogarithmometry();
  }

  public Logarithmometry(NaturalLogarithmometry naturalLogarithmometry) {
    this.naturalLogarithmometry = naturalLogarithmometry;
  }

  public double log(double base, double x) {
    return ln(x) / ln(base);
  }

  public double ln(double x) {
    return naturalLogarithmometry.ln(x);
  }
}
