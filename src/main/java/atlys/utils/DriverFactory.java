package atlys.utils;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver getDriver(String browser) {
		WebDriver driver;

		switch (browser.toLowerCase()) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "chrome":
		default:
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();

			// Set download directory
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", System.getProperty("user.dir") + "/downloads");
			prefs.put("download.prompt_for_download", false);
			prefs.put("plugins.always_open_pdf_externally", true);
			options.setExperimentalOption("prefs", prefs);

			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			options.addArguments("--kiosk-printing");

			driver = new ChromeDriver(options);
			break;
		}

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(configReader.getProperty("implicit.wait"))));
		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(configReader.getProperty("page.load.timeout"))));

		return driver;
	}
}
