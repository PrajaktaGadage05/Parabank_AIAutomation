package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BasePage{

    @FindBy(xpath = "//a[normalize-space()='Log Out']")
    WebElement logoutButton;



    public LogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setLogoutButton(){
        logoutButton.click();
    }

}

