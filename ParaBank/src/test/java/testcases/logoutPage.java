package testcases;

import org.testng.annotations.Test;
import pageobjects.LogoutPage;
import testbase.BaseClass;

//import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class logoutPage extends BaseClass {

    @Test(groups = {"master"},priority = 5)
    public void testConfirmation() throws InterruptedException {

        LogoutPage logoutPage = new LogoutPage(driver);

        TimeUnit.SECONDS.sleep(3);
        logoutPage.setLogoutButton();

    }
}
