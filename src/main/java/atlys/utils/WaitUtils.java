package atlys.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import atlys.pages.BasePage;

import java.time.Duration;

import org.openqa.selenium.*;

public class WaitUtils {
	private WebDriver driver;
	private static final int DEFAULT_TIMEOUT = Integer.parseInt(configReader.getProperty("explicit.wait"));
	public WebDriverWait wait;

	public WaitUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToBeVisible(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToBeClickable(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
}
