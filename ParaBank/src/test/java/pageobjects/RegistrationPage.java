package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{


    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement getRegisterButton;

    @FindBy(id = "customer.firstName")
    WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    WebElement lastNameField;

    @FindBy(id = "customer.address.street")
    WebElement addressField;

    @FindBy(id = "customer.address.city")
    WebElement cityField;

    @FindBy(id = "customer.address.state")
    WebElement stateField;

    @FindBy(id = "customer.address.zipCode")
    WebElement zipCodeField;

    @FindBy(id = "customer.phoneNumber")
    WebElement phoneField;

    @FindBy(id = "customer.ssn")
    WebElement ssnField;

    @FindBy(id = "customer.username")
    WebElement usernameField;

    @FindBy(id = "customer.password")
    WebElement passwordField;

    @FindBy(id = "repeatedPassword")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Register']")
    WebElement registerButton;

    @FindBy(id = "leftPanel")
    WebElement leftPanel;

    public RegistrationPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void setRegisterButton(){
        getRegisterButton.click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void enterState(String state) {
        stateField.sendKeys(state);
    }

    public void enterZipCode(String zipCode) {
        zipCodeField.sendKeys(zipCode);
    }

    public void enterPhone(String phone) {
        phoneField.sendKeys(phone);
    }

    public void enterSSN(String ssn) {
        ssnField.sendKeys(ssn);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public boolean isConfirmationDisplayed() {
        return leftPanel.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}
