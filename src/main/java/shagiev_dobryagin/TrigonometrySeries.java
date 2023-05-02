package shagiev_dobryagin;

public class TrigonometrySeries {

    public double cos(double x, double e) {
        double sum = 1;
        long fakt2n = 2;
        int n = 1;
        do {
            fakt2n = fakt2n * (fakt2n + 1) * (fakt2n + 2);
            double elem = Math.pow(-1, n) * Math.pow(x, 2*n) / fakt2n;
        }
    }
}
