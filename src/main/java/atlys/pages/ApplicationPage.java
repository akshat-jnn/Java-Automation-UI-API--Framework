package atlys.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationPage extends BasePage {
	public ApplicationPage(WebDriver driver) {
		super(driver);
//        PageFactory.initElements(driver, this);
	}

	String firstNameFromData = applicationData.getFirstName().toUpperCase();

	@FindBy(id = "selectedCityList")
	public WebElement country_of_application_office;

	@FindBy(id = "application.Application_office")
	public WebElement city_of_application_office;

	@FindBy(xpath = "//input[@value='Next']")
	private WebElement nextButton;

	@FindBy(id = "application_Surname__Family_Name_")
	public WebElement surName;

	@FindBy(id = "application_First_name__s_")
	public WebElement firstName;

	@FindBy(id = "application_Surname_at_birth")
	public WebElement surNameAtBirth;

	@FindBy(id = "application_First_name__s_")
	public WebElement formerFamilyName;

	@FindBy(id = "application_Date_of_birth")
	public WebElement dob;

	@FindBy(id = "application_Place_of_birth")
	public WebElement placeOfBirth;

	@FindBy(id = "application_Country_of_birth")
	public WebElement countryOfBirth;

	@FindBy(id = "application_Current_nationality")
	public WebElement currentNationality;

	@FindBy(id = "application_Nationality_at_birth__if_different_")
	public WebElement nationalityAtBirthIfDifferent;

	@FindBy(id = "application_Sex")
	public WebElement gender;

	@FindBy(id = "application_Email")
	public WebElement email;

	@FindBy(id = "application_ReEnterEmail")
	public WebElement reEnterEmail;

	@FindBy(id = "application_Phone")
	public WebElement phone;

	@FindBy(id = "application_Phone_code")
	public WebElement phoneCodeNumber;

	@FindBy(id = "application_Type_of_travel_document")
	public WebElement typeOfTravelDoc;

	@FindBy(id = "application_Number_of_travel_document")
	public WebElement numberOfTravelDoc;

	@FindBy(id = "application_Date_of_issue")
	public WebElement dateOfIssue;

	@FindBy(id = "application_Issued_by")
	public WebElement issuedBy;

	@FindBy(id = "application_Valid_until")
	public WebElement validUntil;

	@FindBy(id = "application_Description_of_Issuer")
	public WebElement descriptionOfIssuer;

	@FindBy(id = "application_Applicants_home_country")
	public WebElement homeCountry;

	@FindBy(id = "application_Applicants_home_city")
	public WebElement homeCity;

	@FindBy(id = "application_Applicants_home_zip")
	public WebElement postalCode;

	@FindBy(id = "application_Applicants_home_address")
	public WebElement homeAddress;

	@FindBy(id = "application_Current_occupation")
	public WebElement occupation;

	@FindBy(id = "application_Current_occupation__if_other_")
	public WebElement occupation_other;

	@FindBy(id = "application_Employer_name")
	public WebElement employerName;

	@FindBy(id = "application_Educational_establishment")
	public WebElement eduEstablishment;

	@FindBy(id = "application_Main_purpose_s__of_the_journey")
	public WebElement purposeOfJourney;

	@FindBy(id = "application_Member_state_of_first_entry")
	public WebElement stateOfFirstMember;

	@FindBy(id = "application_Number_of_entries_requested")
	public WebElement entryRequested;

	@FindBy(id = "application_Intended_date_of_arrival")
	public WebElement arrivalDate;

	@FindBy(id = "application_Intended_date_of_departure")
	public WebElement departureDate;

	@FindBy(xpath = "//label[@for ='application_Duration_Checkbox']")
	public WebElement durationCheckBox;

	@FindBy(xpath = "//div[@class='form-check']/input")
	public List<WebElement> lastPageCheckbox;

	@FindBy(xpath = "//input[@value='Submit']")
	public WebElement submitbutton;

	public WebElement getApplicationPrintOutButton(String firstName) {
		String xpath = "//h2[contains(text(),'Dear " + firstName
				+ "')]/following-sibling::input[contains(@value,'Display application for print out')]";
		return driver.findElement(By.xpath(xpath));
	};

	@FindBy(xpath = "//button[contains(normalize-space(.), 'Print application')]")
	WebElement formPrintOutButton;

	public void fillFirstPageForm() {
		try {
			logger.info("Starting to fill first page of application form");
			if (applicationData.getCountryOfApplicationOffice() != null) {
				clickElement(country_of_application_office);
				selectDropdownByVisibleText(country_of_application_office,
						applicationData.getCountryOfApplicationOffice());
			}
			if (applicationData.getCityOfApplicationOffice() != null) {
				clickElement(city_of_application_office);
				selectDropdownByVisibleText(city_of_application_office, applicationData.getCityOfApplicationOffice());
			}
			clickElement(nextButton);
			logger.info("Successfully filled all form fields for first page");
		} catch (Exception e) {
			logger.error("Error while filling First page of application form", e);
			throw new RuntimeException("Failed to fill First page of application form: " + e.getMessage(), e);
		}
	}

	public void fillSecondPageForm() {
		try {
			logger.info("Starting to fill Second page of application form");
			if (applicationData.getLastName() != null) {
				enterText(surName, applicationData.getLastName());
			}
			if (applicationData.getFirstName() != null) {
				enterText(firstName, applicationData.getFirstName());
			}
			if (applicationData.getDateOfBirth() != null) {
				datePicker(convertDateToDesiredFormat(applicationData.getDateOfBirth()), dob);
			}
			if (applicationData.getPlaceOfBirth() != null) {
				enterText(placeOfBirth, applicationData.getPlaceOfBirth());
			}
			if (applicationData.getCountryOfBirth() != null) {
				clickElement(countryOfBirth);
				selectDropdownByVisibleText(countryOfBirth, applicationData.getCountryOfBirth());

			}

			if (applicationData.getNationality() != null) {
				clickElement(currentNationality);
				selectDropdownByVisibleText(currentNationality, applicationData.getNationality());
			}

			if (applicationData.getGender() != null) {
				clickElement(gender);
				selectDropdownByVisibleText(gender, applicationData.getGender());
			}

			if (applicationData.getApplicantEmail() != null) {
				enterText(email, applicationData.getApplicantEmail());
				enterText(reEnterEmail, applicationData.getApplicantEmail());
			}

			if (applicationData.getMobileNumber() != null) {
				enterText(phone, applicationData.getMobileNumber());
			}

			// As data is not present in sample data api Hence using hard coded data
			clickElement(typeOfTravelDoc);
			selectDropdownByVisibleText(typeOfTravelDoc, "Ordinary passport");

			if (applicationData.getPassportNumber() != null) {
				enterText(numberOfTravelDoc, applicationData.getPassportNumber());
			}

			if (applicationData.getPassportIssueDate() != null) {
				datePicker(convertDateToDesiredFormat(applicationData.getPassportIssueDate()), dateOfIssue);
			}

			if (applicationData.getPassportIssuedByCountry() != null) {
				clickElement(issuedBy);
				selectDropdownByVisibleText(issuedBy, applicationData.getPassportIssuedByCountry());
			}

			if (applicationData.getPassportValidUntil() != null) {
				datePicker(convertDateToDesiredFormat(applicationData.getPassportValidUntil()), validUntil);
			}

			if (applicationData.getDescOfPassportIssuer() != null) {
				enterText(descriptionOfIssuer, applicationData.getDescOfPassportIssuer());
			}

			if (applicationData.getCountryOfResidence() != null) {
				clickElement(homeCountry);
				selectDropdownByVisibleText(homeCountry, applicationData.getCountryOfResidence());
			}

			if (applicationData.getAddressCity() != null) {
				enterText(homeCity, applicationData.getAddressCity());
			}

			if (applicationData.getAddressPincode() != null) {
				enterText(postalCode, applicationData.getAddressPincode());
			}

			if (applicationData.getStreetAddress() != null) {
				enterText(homeAddress, applicationData.getStreetAddress());
			}

			if (applicationData.getOccupation() != null) {
				clickElement(occupation);
				selectDropdownByVisibleText(occupation, applicationData.getOccupation());
				enterText(occupation_other, applicationData.getOccupation());
			}

			if (applicationData.getEmployerName() != null) {
				enterText(employerName, applicationData.getEmployerName());
			}

			if (applicationData.getEducationEstablishment() != null) {
				enterText(eduEstablishment, applicationData.getEducationEstablishment());
			}
			// data is not present in sample data api Hence using hard coded data
			clickElement(purposeOfJourney);
			selectDropdownByVisibleText(purposeOfJourney, "Tourism");
			clickElement(stateOfFirstMember);
			selectDropdownByVisibleText(stateOfFirstMember, "Denmark");
			clickElement(entryRequested);
			selectDropdownByVisibleText(entryRequested, "One");

			if (applicationData.getDateOfArrival() != null) {
				datePicker("\"10/05/2026\"", arrivalDate);

			}
			if (applicationData.getDateOfDeparture() != null) {
				datePicker("\"10/20/2026\"", departureDate);
			}

			clickElement(durationCheckBox);
			clickElement(nextButton);
			logger.info("Successfully filled all form fields for second page");
		} catch (Exception e) {
			logger.error("Error while filling second page of application form", e);
			throw new RuntimeException("Failed to fill second page of application form: " + e.getMessage(), e);
		}
	}

	public void fillLastPageForm() {
		if (isElementDisplayed(submitbutton)) {
			System.out.println(lastPageCheckbox.size());
			try {
				for (WebElement checkboxLabel : lastPageCheckbox) {

					checkboxLabel.click();

				}
			} catch (Exception e) {
				logger.error("EError while selecting checkboxes:" + e.getMessage());
				throw new RuntimeException(e);
			}
			clickElement(submitbutton);
			logger.info("Successfully filled all form fields and submit application");
		}
	}

	public boolean isFormDisplayed() {
		return isElementDisplayed(country_of_application_office);
	}

	public boolean isSubmitButtonDisplayed() {
		return isElementDisplayed(submitbutton);
	}

	public boolean isApplicationPrintButtonIsDisplayed() {
		WebElement element = getApplicationPrintOutButton(firstNameFromData);
		return isElementDisplayed(element);
	}

	public void clickOnApplicationButtonForPrintOut() {
		WebElement element = getApplicationPrintOutButton(firstNameFromData);
		clickElement(element);
	}

	public boolean isFormPrintButtonIsDisplayed() {
		return isElementDisplayed(formPrintOutButton);
	}

	public void ClickOnFormPrintOutButton() {
		clickElement(formPrintOutButton);
	}

}
