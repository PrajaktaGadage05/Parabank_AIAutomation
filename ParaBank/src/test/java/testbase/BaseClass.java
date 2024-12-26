package testbase;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.MemoryUtils;

import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
//import java.util.Properties;

public class BaseClass {

    static public WebDriver driver;
    //static public Logger logger;
    //public Properties p;
    private static MemoryUsage beforeMemoryUsage;
    private static MemoryUsage afterMemoryUsage;
    private static double beforeNetworkSpeed;
    private static double afterNetworkSpeed;
    private static String reportFilePath;



    @BeforeSuite(alwaysRun = true, groups = {"master"})
    @Parameters({"os", "browser"})


    public void setup(@Optional("windows") String os, @Optional("chrome") String br) throws IOException {

        beforeMemoryUsage = MemoryUtils.getMemoryUsage();

        // Set the path for the report file
        reportFilePath = Paths.get(System.getProperty("user.dir"), "memory_report.html").toString();

        beforeNetworkSpeed = MemoryUtils.measureNetworkSpeed("https://parabank.parasoft.com/parabank/register.htm");


       /* //Loading properties file
        FileInputStream file = new FileInputStream(".//src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        //Loading log4j2 file
        logger = LogManager.getLogger(BaseClass.class);*/


        //Launching browser based on the condition
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("No Matching Browser..");
                return;
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        //driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();

    }

    @AfterSuite(alwaysRun = true, groups = {"master"})
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                //logger.error("Error closing browser:", e);
            }
        }
        // Get memory usage after all tests
        afterMemoryUsage = MemoryUtils.getMemoryUsage();

        afterNetworkSpeed = MemoryUtils.measureNetworkSpeed("https://parabank.parasoft.com/parabank/register.htm");



        // Generate and write the HTML report
        String htmlReport = MemoryUtils.generateHTMLReport("Memory Consumption and Network Speed Report", beforeMemoryUsage, afterMemoryUsage, beforeNetworkSpeed, afterNetworkSpeed);
        MemoryUtils.writeToFile(reportFilePath, htmlReport);

        // Open the report in the default browser
        MemoryUtils.openFileWithDefaultBrowser(reportFilePath);
    }




    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }


}

