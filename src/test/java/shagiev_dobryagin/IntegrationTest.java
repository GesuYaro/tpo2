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
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(3), y));
  }

  @Order(2)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFuncWithTrigonometry_cases.csv")
  public void bigFuncWithTrigonometry(double x, double y) {
    trigonometry = new Trigonometry(trigonometrySeries);
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(3), y));
  }

  @Order(3)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFuncWithLogarithmometry_cases.csv")
  public void bigFuncWithLogarithmometry(double x, double y) {
    logarithmometry = new Logarithmometry(naturalLogarithmometry);
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(3), y));
  }

  @Order(4)
  @ParameterizedTest
  @CsvFileSource(resources = "bigFunc_allModules.csv")
  public void bigFuncWithAllModules(double x, double y) {
    trigonometry = new Trigonometry(new TrigonometrySeries());
    logarithmometry = new Logarithmometry(new NaturalLogarithmometry());
    assertTrue(doubleEqualsWithBigEps(bigFunc.calc(3), y));
  }

  private static void mockTrigonometry() {
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
  }

  private static void mockLogarithmometry() {
    var lnStub = new FuncCSVStub("ln.csv");
    var log2Stub = new FuncCSVStub("log2.csv");
    var log3Stub = new FuncCSVStub("log3.csv");
    var log5Stub = new FuncCSVStub("log5.csv");
    var log10Stub = new FuncCSVStub("log10.csv");
    naturalLogarithmometry = mock();
    logarithmometry = mock();

    when(naturalLogarithmometry.ln(any())).thenAnswer(invocation -> lnStub.calc(invocation.getArgument(0)));
    when(logarithmometry.ln(any())).thenAnswer(invocation -> lnStub.calc(invocation.getArgument(0)));
    when(logarithmometry.log(doubleThat(doubleMatcher(2)), any()))
      .thenAnswer(invocation -> log2Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(3)), any()))
      .thenAnswer(invocation -> log3Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(5)), any()))
      .thenAnswer(invocation -> log5Stub.calc(invocation.getArgument(1)));
    when(logarithmometry.log(doubleThat(doubleMatcher(10)), any()))
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
