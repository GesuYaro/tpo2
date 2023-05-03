package shagiev_dobryagin;

public class TrigonometrySeries {

    public double cos(double x, double e) {
        double elem;
        double sum = 1;
        long fakt2n = 2;
        long n = 1;
        do {
            elem = Math.pow(-1, n) * Math.pow(x, 2*n) / fakt2n;
            fakt2n = fakt2n * (2*n + 1) * (2*n + 2);
            sum += elem;
            n++;
        } while (Math.abs(elem) > e);
        return sum;
    }
}
