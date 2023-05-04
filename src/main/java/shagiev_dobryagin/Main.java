package shagiev_dobryagin;

public class Main {
    public static void main(String[] args) {
        var t = new Trigonometry(new TrigonometrySeries());
        var task = new TrigonometryTask(t);
        System.out.printf("%f", task.calc(11 *  Math.PI / 6, 0.0001));
    }
}