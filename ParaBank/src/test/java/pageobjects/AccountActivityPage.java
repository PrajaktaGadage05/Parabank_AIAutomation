package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountActivityPage extends BasePage {

    public AccountActivityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@id='newAccountId']")
    WebElement newAccoundIdLink;

    @FindBy(xpath = "//select[@id='month']")
    WebElement activityPeriod;

    @FindBy(xpath = "//select[@id='transactionType']")
    WebElement type;

    @FindBy(xpath = "//input[@value='Go']")
    WebElement goButton;

    @FindBy(xpath = "//a[normalize-space()='Funds Transfer Received']")
    WebElement fundsLink;

    public void setNewAccoundIdLink(){
        newAccoundIdLink.click();
    }

    public void ActivityPeriod(String activity){
        activityPeriod.sendKeys(activity);

    }
    public void setType(String type1){
        type.sendKeys(type1);
    }

    public void setGoButton(){
        goButton.click();
    }

    public void setFundsLink(){
        fundsLink.click();
    }

}
