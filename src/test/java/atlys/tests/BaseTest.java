package atlys.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import atlys.utils.DriverFactory;
import atlys.utils.configReader;

public class BaseTest {
	protected WebDriver driver;
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);

	@BeforeSuite
	@Parameters({ "browser" })
	public void setup(@Optional("chrome") String browser) {
		logger.info("Setting up test environment");
		driver = DriverFactory.getDriver(browser);
		driver.manage().window().maximize();
		String url = configReader.getProperty("app.url");
		driver.get(url);
		logger.info("Navigated to: {}", url);
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			logger.info("Closing browser");
			driver.quit();
		}
	}
}
