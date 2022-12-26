import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import scripts.SignUpTests;
import scripts.SmokeTests;

@Suite
@SelectClasses({SignUpTests.class, SmokeTests.class})
public class TestSuit {
}
