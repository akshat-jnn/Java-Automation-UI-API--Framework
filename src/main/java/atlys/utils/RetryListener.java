package atlys.utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
	private static final Logger logger = LogManager.getLogger(RetryListener.class);

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
		logger.info("RetryAnalyzer attached to test method: {}", testMethod != null ? testMethod.getName() : "Unknown");
	}
}
