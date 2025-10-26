package atlys.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import atlys.pages.ApplicationPage;
import atlys.utils.JsonDataReader;
import model.ApplicationDataAPI;

public class ApplicationTest extends BaseTest {

	@Test(priority = 1, description = "Complete visa application form with JSON data")
	public void testApplicationWithJsonData() {
		try {

			ApplicationDataAPI testData = JsonDataReader.getDataFromApi();
			Assert.assertNotNull(testData, "Test data should not be null");
			// Initialize page object
			ApplicationPage appPage = new ApplicationPage(driver);
			Assert.assertTrue(appPage.isFormDisplayed(), "Visa application form should be displayed");
			appPage.fillFirstPageForm();
			appPage.fillSecondPageForm();
			appPage.fillLastPageForm();
			logger.info("All data filled in Form succesfully");
			Assert.assertTrue(appPage.isApplicationPrintButtonIsDisplayed(),
					"Visa form successfully submited...");
		} catch (Exception e) {

			logger.error("Test failed with exception", e);
			Assert.fail("Test failed: " + e.getMessage());
		}
	}
	
	@Test(priority = 2, description = "Submition of application form")
	public void testConfirmationOfPageSubmition() {
		try {
			ApplicationPage appPage = new ApplicationPage(driver);
			appPage.clickOnApplicationButtonForPrintOut();
			Assert.assertTrue(appPage.isFormPrintButtonIsDisplayed(), "Form Print Out button should be displayed");
			appPage.ClickOnFormPrintOutButton();
			
		} catch (Exception e) {
			logger.error("Test failed with exception", e);
            Assert.fail("Test failed: " + e.getMessage());
		}

	}
}
