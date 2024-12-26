package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class TrasferFundsPage extends BasePage{


    public TrasferFundsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Transfer Funds']")
    WebElement TRFundsLink;

    @FindBy(xpath = "//input[@id='amount']")
    WebElement Amount;

    @FindBy(xpath = "//input[@value='Transfer']")
    WebElement Transfer;

    @FindBy(id = "fromAccountId")
    WebElement fromAccountDropdown;

    @FindBy(xpath = "//h1[normalize-space()='Transfer Complete!']")
    WebElement transferTitle;

    @FindBy(xpath = "//div[@id='showResult']//p[1]")
    WebElement transferMessage;



    public void setTRFundsLink() {
        TRFundsLink.click();
    }

    public void setAmount(int value) {
        Amount.sendKeys(String.valueOf(value));    }

    public void TransferMoney(){
        Transfer.click();
    }

    public void selectFromAccountByIndex(int index) {
        Select dropdown = new Select(fromAccountDropdown);
        dropdown.selectByIndex(index);
    }

    public boolean isTransferSuccessful() {
        return transferTitle.isDisplayed();
    }

    public String getTransferMessage() {
        return transferMessage.getText();
    }
}
