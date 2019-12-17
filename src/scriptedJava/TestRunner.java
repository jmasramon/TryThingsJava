package scriptedJava;

public class TestRunner {
    public void instantiateEnvironment(EnvironmentConfiguration envConf) {
        if (envConf.a) {
            System.out.println("envConf.a subsystem instantiated");
        } else if (envConf.b) {
            System.out.println("envConf.b subsystem instantiated");
        }

    }

    public void runTest(String testName) {
        System.out.println("Running test: " + testName);
    }
}
