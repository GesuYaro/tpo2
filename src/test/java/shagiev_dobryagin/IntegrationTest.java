package shagiev_dobryagin;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.ArgumentMatcher;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
  private static BigFunc bigFunc;
  private static Trigonometry trigonometry;
  private static Logarithmometry logarithmometry;
  private static TrigonometrySeries trigonometrySeries;
  private static NaturalLogarithmometry naturalLogarithmometry;

  @BeforeEach
  public void beforeAll() {
    mockTrigonometry();
    mockLogarithmometry();

    bigFunc = new BigFunc(trigonometry, logarithmometry);
  }

  @Order(1)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFunc_cases.csv")
  public void bigFunc(double x, double y) {
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(x), y));
  }

  @Order(2)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFunc_cases.csv")
  public void bigFuncWithTrigonometry(double x, double y) {
    trigonometry = new Trigonometry(trigonometrySeries);
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(x), y));
  }

  @Order(3)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFunc_cases.csv")
  public void bigFuncWithLogarithmometry(double x, double y) {
    logarithmometry = new Logarithmometry(naturalLogarithmometry);
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(x), y));
  }

  @Order(4)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFunc_cases.csv")
  public void bigFuncWithAllModules(double x, double y) {
    trigonometrySeries = new TrigonometrySeries();
    naturalLogarithmometry = new NaturalLogarithmometry();
    logarithmometry = new Logarithmometry(naturalLogarithmometry);
    trigonometry = new Trigonometry(trigonometrySeries);
    bigFunc = new BigFunc(trigonometry, logarithmometry);
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(x), y));
  }

  private static void mockTrigonometry() {
    trigonometrySeries = mock();
    trigonometry = mock();
    var sin = new FuncCSVStub("resources/sin.csv");
    var cos = new FuncCSVStub("resources/cos.csv");
    var tan = new FuncCSVStub("resources/tan.csv");
    var cot = new FuncCSVStub("resources/cot.csv");
    var sec = new FuncCSVStub("resources/sec.csv");
    var csc = new FuncCSVStub("resources/csc.csv");

    when(trigonometrySeries.decomposeToSeries(anyDouble(), anyDouble())).thenAnswer(invocation -> cos.calc(invocation.getArgument(0)));
    when(trigonometry.cos(anyDouble(), anyDouble())).thenAnswer(invocation -> cos.calc(invocation.getArgument(0)));
    when(trigonometry.sin(anyDouble(), anyDouble())).thenAnswer(invocation -> sin.calc(invocation.getArgument(0)));
    when(trigonometry.tan(anyDouble(), anyDouble())).thenAnswer(invocation -> tan.calc(invocation.getArgument(0)));
    when(trigonometry.cot(anyDouble(), anyDouble())).thenAnswer(invocation -> cot.calc(invocation.getArgument(0)));
    when(trigonometry.sec(anyDouble(), anyDouble())).thenAnswer(invocation -> sec.calc(invocation.getArgument(0)));
    when(trigonometry.csc(anyDouble(), anyDouble())).thenAnswer(invocation -> csc.calc(invocation.getArgument(0)));
  }

  private static void mockLogarithmometry() {
    var lnStub = new FuncCSVStub("resources/ln.csv");
    var log2Stub = new FuncCSVStub("resources/log2.csv");
    var log3Stub = new FuncCSVStub("resources/log3.csv");
    var log5Stub = new FuncCSVStub("resources/log5.csv");
    var log10Stub = new FuncCSVStub("resources/log10.csv");
    naturalLogarithmometry = mock();
    logarithmometry = mock();

    when(naturalLogarithmometry.ln(anyDouble())).thenAnswer(invocation -> {
      return lnStub.calc(invocation.getArgument(0));
    });
    when(logarithmometry.ln(anyDouble())).thenAnswer(invocation -> lnStub.calc(invocation.getArgument(0)));
    when(logarithmometry.log(doubleThat(doubleMatcher(2)), anyDouble()))
      .thenAnswer(invocation -> log2Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(3)), anyDouble()))
      .thenAnswer(invocation -> log3Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(5)), anyDouble()))
      .thenAnswer(invocation -> log5Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(10)), anyDouble()))
      .thenAnswer(invocation -> log10Stub.calc(invocation.getArgument(1)));
  }

  private static boolean doubleEqualsWithBigEps(double d1, double d2) {
    return abs(d1 - d2) < 0.001;
  }

  private static boolean doubleEquals(double d1, double d2) {
    return abs(d1 - d2) < 0.000001;
  }

  private static ArgumentMatcher<Double> doubleMatcher(double value) {
    return d -> doubleEquals(d, value);
  }
}
