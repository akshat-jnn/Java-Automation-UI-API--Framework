package atlys.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import atlys.utils.JsonDataReader;
import atlys.utils.WaitUtils;
import model.ApplicationDataAPI;

public class BasePage {
	public WebDriver driver;
	public WaitUtils waitUtils;
	ApplicationDataAPI applicationData = JsonDataReader.getDataFromApi();
	protected static final Logger logger = LogManager.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		PageFactory.initElements(driver, this);
	}

	public void enterText(WebElement element, String text) {
		waitUtils.waitForElementToBeVisible(element);
		scrollToElement(element);
		element.clear();
		element.sendKeys(text);
		logger.info("Entered text '{}' in element: {}", text, element);
	}

	public void clickElement(WebElement element) {
		try {
			waitUtils.waitForElementToBeClickable(element);
			scrollToElement(element);
			if (!isElementDisplayed(element))
				throw new ElementNotInteractableException("Element is not visible or not enabled for clicking.");
			element.click();
			logger.info("Clicked element: {}", element);
		} catch (NoSuchElementException e) {
			logger.warn("Element not found, cannot perform click");
			throw new RuntimeException("Element not found, cannot perform click.", e);
			
		}
	}

	public void selectDropdownByVisibleText(WebElement ele, String text) {
		try {
			waitUtils.waitForElementToBeVisible(ele);
			Select select = new Select(ele);

			boolean optionFound = false;
			for (WebElement option : select.getOptions()) {
				if (option.getText().trim().equalsIgnoreCase(text)) {
					select.selectByVisibleText(option.getText());
					logger.info("Selected Option : {}", option.getText());
					optionFound = true;
					break;
				}
			}

			if (!optionFound) {
				select.selectByVisibleText("Others");
				logger.info("Selected Option : Others");
			}

		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
			logger.warn("No Options available to select");
			throw e;
		} catch (Exception e) {
			System.err.println("Error while selecting dropdown option: " + e.getMessage());
			logger.warn("Error while selecting dropdown option");
			throw e;
		}

	}

	protected boolean isElementDisplayed(WebElement element) {
		if (element == null) {
			 logger.warn("Element reference is null");
			return false;
		}
		try {
			waitUtils.waitForElementToBeVisible(element);
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			 logger.warn("Element not found or not visible within wait time: {}",element);
			return false;
		} catch (Exception e) {
			 logger.error("Unexpected exception while checking element visibility: {}",e.getMessage());
			return false;
		}
	}

	protected String getElementText(WebElement ele) {
		scrollToElement(ele);
		waitUtils.waitForElementToBeVisible(ele);
		if (!isElementDisplayed(ele))
			throw new ElementNotInteractableException("Element is not visible or not enabled for clicking.");
		String text = ele.getText();
            logger.info("Retrieved text '{}' from element: {}", text, ele);
		return text;

	}

	protected void scrollToElement(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        logger.info("Scrolled to element: {}", ele);
	}

	public String convertDateToDesiredFormat(String dateInDDMMYYYY) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = inputFormat.parse(dateInDDMMYYYY);
			return outputFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid date format: " + dateInDDMMYYYY);
		}
	}

	public void datePicker(String dateToPick, WebElement element) {
		clickElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
		enterText(element, dateToPick);
	}
}
