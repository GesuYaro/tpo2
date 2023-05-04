package shagiev_dobryagin;

public class TrigonometryTask {

    private Trigonometry t;

    public TrigonometryTask(Trigonometry t) {
        this.t = t;
    }

    public double calc(double x, double e) {
        return Math.pow((((t.sec(x, e) - t.ctg(x, e)) - (t.sec(x, e) / t.cos(x, e))) * (t.sec(x, e) - t.tan(x, e))), 2)
                / t.csc(x, e);
    }
}
