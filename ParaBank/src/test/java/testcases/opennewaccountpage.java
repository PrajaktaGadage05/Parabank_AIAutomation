package testcases;

import org.testng.annotations.Test;
import pageobjects.NewAccountPage;
import testbase.BaseClass;
//import utilities.MemoryUtils;

//import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;

public class opennewaccountpage extends BaseClass {

    //MemoryUsage beforeMemoryUsage = MemoryUtils.getMemoryUsage();


    @Test(groups = {"master"},priority = 2)
    public void testOpenNewAccount() throws InterruptedException {

        NewAccountPage newAccountPage = new NewAccountPage(driver);

        TimeUnit.SECONDS.sleep(3);
        newAccountPage.setGetOpenAccountButton();

        TimeUnit.SECONDS.sleep(3);
        newAccountPage.selectAccountType("SAVINGS");

/*        TimeUnit.SECONDS.sleep(2);
        newAccountPage.enterFromAccountId("14454");*/ // Choose an account ID if required



        TimeUnit.SECONDS.sleep(3);
        newAccountPage.clickOpenAccountButton();

        TimeUnit.SECONDS.sleep(3);
        if (newAccountPage.isSuccessMessageDisplayed()) {
            System.out.println("New account opened successfully!");
        } else {
            System.out.println("Failed to open a new account or encountered an error.");
        }



        /*MemoryUsage afterMemoryUsage = MemoryUtils.getMemoryUsage();

        // Generate HTML report
        String htmlReport = MemoryUtils.generateHTMLReport("Memory Consumption Report", beforeMemoryUsage, afterMemoryUsage);

        // Write report to file and open it
        String fileName = "C:\\Users\\pragadge\\IdeaProjects\\ParaBank\\memory_report.html";
        MemoryUtils.writeToFile(fileName, htmlReport);
        MemoryUtils.openFileWithDefaultBrowser(fileName);
*/


    }


}
