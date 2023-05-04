package shagiev_dobryagin;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatcher;

import static java.lang.Math.abs;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
  private static BigFunc bigFunc;
  private static Trigonometry trigonometry;
  private static Logarithmometry logarithmometry;
  private static TrigonometrySeries trigonometrySeries;
  private static NaturalLogarithmometry naturalLogarithmometry;

  @BeforeAll
  public static void beforeAll() {
    trigonometrySeries = mock();
    trigonometry = mock();
    var sin = new FuncCSVStub("sin.csv");
    var cos = new FuncCSVStub("cos.csv");
    var tan = new FuncCSVStub("tan.csv");
    var cot = new FuncCSVStub("cot.csv");
    var sec = new FuncCSVStub("sec.csv");
    var csc = new FuncCSVStub("csc.csv");

    when(trigonometrySeries.serializeCos(any(), any())).thenAnswer(invocation -> cos.calc(invocation.getArgument(0)));
    when(trigonometry.cos(any(), any())).thenAnswer(invocation -> cos.calc(invocation.getArgument(0)));
    when(trigonometry.sin(any(), any())).thenAnswer(invocation -> sin.calc(invocation.getArgument(0)));
    when(trigonometry.tan(any(), any())).thenAnswer(invocation -> tan.calc(invocation.getArgument(0)));
    when(trigonometry.cot(any(), any())).thenAnswer(invocation -> cot.calc(invocation.getArgument(0)));
    when(trigonometry.sec(any(), any())).thenAnswer(invocation -> sec.calc(invocation.getArgument(0)));
    when(trigonometry.csc(any(), any())).thenAnswer(invocation -> csc.calc(invocation.getArgument(0)));

    var lnStub = new FuncCSVStub("ln.csv");
    var log2Stub = new FuncCSVStub("log2.csv");
    var log3Stub = new FuncCSVStub("log3.csv");
    var log5Stub = new FuncCSVStub("log5.csv");
    var log10Stub = new FuncCSVStub("log10.csv");
    logarithmometry = mock();

    when(logarithmometry.ln(any())).thenAnswer(invocation -> lnStub.calc(invocation.getArgument(0)));
    when(logarithmometry.log(doubleThat(doubleMatcher(2)), any()))
      .thenAnswer(invocation -> log2Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(3)), any()))
      .thenAnswer(invocation -> log3Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(5)), any()))
      .thenAnswer(invocation -> log5Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(10)), any()))
      .thenAnswer(invocation -> log10Stub.calc(invocation.getArgument(1)));

    bigFunc = new BigFunc(trigonometry, logarithmometry);
  }

  @Test
  @Order(1)
  public void bigFunc() {

  }

  @Test
  @Order(2)
  public void bigFuncWithTrigonometry() {

  }

  @Test
  @Order(3)
  public void bigFuncWithLogarithmometry() {

  }

  private static ArgumentMatcher<Double> doubleMatcher(double value) {
    return d -> abs(d - value) < 0.000001;
  }
}
