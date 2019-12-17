package scriptedJava

TestRunner localRunner = runner
EnvironmentConfiguration env = new EnvironmentConfiguration(true, false)

localRunner.instantiateEnvironment(env)

localRunner.runTest("test from groovy")

(1..10).sum()
