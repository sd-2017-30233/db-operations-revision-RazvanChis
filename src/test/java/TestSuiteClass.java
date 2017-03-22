import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        EnrollmentQueryTests.class,
        CourseQueryTests.class,
        StudentQueryTests.class
})
public class TestSuiteClass {
}
