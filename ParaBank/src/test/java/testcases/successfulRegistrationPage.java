package testcases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.RegistrationPage;
import testbase.BaseClass;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class successfulRegistrationPage extends BaseClass {



    @Test(groups = {"master"},priority = 1)
    public void testRegistration() throws InterruptedException {

        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Perform the registration
        TimeUnit.SECONDS.sleep(2);
        registrationPage.setRegisterButton();
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterFirstName("John");
        TimeUnit.SECONDS.sleep(2);;
        registrationPage.enterLastName("Doec3");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterAddress("123 Main St");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterCity("New York");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterState("NY");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterZipCode("10001");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterPhone("1234567890");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterSSN("123-45-6789");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterUsername("ABCTUU");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterPassword("TEST909");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.enterConfirmPassword("TEST909");
        TimeUnit.SECONDS.sleep(2);
        registrationPage.clickRegisterButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> registrationPage.isConfirmationDisplayed());

        // Verify the confirmation page
        if (registrationPage.isConfirmationDisplayed() && registrationPage.getPageTitle().contains("ParaBank | Customer Created")) {
            System.out.println("Test Case Passed: Confirmation page is displayed correctly.");
        } else {

            System.out.println("Test Case Failed: Confirmation page is not displayed correctly.");
        }
        /*MemoryUsage afterMemoryUsage = MemoryUtils.getMemoryUsage();

        // Generate HTML report
        String htmlReport = MemoryUtils.generateHTMLReport("Memory Consumption Report", beforeMemoryUsage, afterMemoryUsage);

        // Write report to file and open it
        String fileName = "C:\\Users\\pragadge\\IdeaProjects\\ParaBank\\memory_report.html";
        MemoryUtils.writeToFile(fileName, htmlReport);
        MemoryUtils.openFileWithDefaultBrowser(fileName);*/

    }


}

