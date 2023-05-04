package shagiev_dobryagin;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TrigonometrySeries {

    public double serializeCos(double x, double e) {
        x = prepareX(x);
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

    private double prepareX(double x) {
        x = Math.abs(x);
        x %= (2 * Math.PI);
        if (x > Math.PI) x-= 2*Math.PI;
        return Math.abs(x);
    }
}
