package atlys.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer  {
	 private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
	    private int retryCount = 0;
	    private static final int MAX_RETRY_COUNT = 2; 

	    public boolean retry(ITestResult result) {
	        if (retryCount < MAX_RETRY_COUNT) {
	            retryCount++;
	            logger.warn("Retrying test '{}' - Attempt {}/{}", 
	                result.getName(), retryCount, MAX_RETRY_COUNT);
	            logger.warn("Test failed due to: {}", result.getThrowable().getMessage());
	            return true;
	        }
	        logger.error("Test '{}' failed after {} retries", 
	            result.getName(), MAX_RETRY_COUNT);
	        return false;
	    }
}
