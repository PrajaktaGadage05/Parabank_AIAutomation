package testcases;

import org.testng.annotations.Test;
import pageobjects.TrasferFundsPage;
import testbase.BaseClass;

import java.util.concurrent.TimeUnit;

public class transferfundspage extends BaseClass {

    //MemoryUsage beforeMemoryUsage = MemoryUtils.getMemoryUsage();


    @Test(groups = {"master"},priority = 4)
    public void testTransferFunds() throws InterruptedException {

        TrasferFundsPage trasferFundsPage = new TrasferFundsPage(driver);

        TimeUnit.SECONDS.sleep(4);
        trasferFundsPage.setTRFundsLink();
        TimeUnit.SECONDS.sleep(3);
        trasferFundsPage.setAmount(100);
        TimeUnit.SECONDS.sleep(3);
        trasferFundsPage.selectFromAccountByIndex(1); // Select the second option in the dropdown
        TimeUnit.SECONDS.sleep(3);
        trasferFundsPage.TransferMoney();

        TimeUnit.SECONDS.sleep(3);
        if (trasferFundsPage.isTransferSuccessful()) {
            System.out.println("Test Case Passed:Transfer Complete!");
        } else {
            System.out.println("Test Case Failed:Cannot complete the transfer");
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

