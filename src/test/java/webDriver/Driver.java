package webDriver;

import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class Driver {
    public static WebDriver driver;
    final static Logger logger = Logger.getLogger(Driver.class);

    public synchronized static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/Users/jamesbartlett/workspace/cucumberExample/src/test/resources/drivers/chromedriver");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    public synchronized static void quit() {
        driver.quit();
        driver = null;
    }

    public static void unzip(String source, String destination){
//        String source = "some/compressed/file.zip";
//        String destination = "some/destination/folder";

        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
