package shagiev_dobryagin;

import java.util.Stack;

import static java.lang.Math.pow;

public class BigFunc {
  private final Logarithmometry logarithmometry;
  private final Trigonometry trigonometry;

  public BigFunc() {
    this.logarithmometry = new Logarithmometry();
    this.trigonometry = new Trigonometry();
  }

  public BigFunc(Logarithmometry logarithmometry, Trigonometry trigonometry) {
    this.logarithmometry = logarithmometry;
    this.trigonometry = trigonometry;
  }

  public double calc(double x) {
    if (x <= 0) {
      return calcTrigonometry(x);
    } else {
      return calcLogs(x);
    }
  }

  private double calcTrigonometry(double x) {
    // TODO
    return 0;
  }

  private double calcLogs(double x) {
    if (x <= 0)
      throw new IllegalArgumentException();

    var stack = new Stack<Double>();

    stack.push(logarithmometry.log(2, x) / logarithmometry.log(10, x));
    stack.push(stack.pop() + logarithmometry.log(2, x) + logarithmometry.log(3, x));
    stack.push(stack.pop() * pow(logarithmometry.ln(x), 3));

    stack.push(logarithmometry.ln(x) / logarithmometry.log(2, x));
    stack.push(logarithmometry.log(3, x));
    stack.push(stack.pop() - stack.pop());
    stack.push(stack.pop() * stack.pop());

    stack.push(pow(pow(logarithmometry.log(3, x), 3), 2));
    stack.push(logarithmometry.log(10, x));
    stack.push(stack.pop() - stack.pop());
    stack.push(logarithmometry.log(5, x) - logarithmometry.ln(x) + logarithmometry.log(5, x));
    stack.push(stack.pop() / stack.pop());

    return -stack.pop() + stack.pop();
  }
}
