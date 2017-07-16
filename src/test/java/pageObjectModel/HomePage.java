package pageObjectModel;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webDriver.Driver;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

public class HomePage {
    final static Logger logger = Logger.getLogger(HomePage.class);

    static WebDriver driver = Driver.getCurrentDriver();

    public static WebElement searchField() {
        return driver.findElement(By.id("gh-ac"));
    }

    public static WebElement resultsCountSpan() {
        return driver.findElement(By.cssSelector("h1.rsHdr span.rcnt"));
    }

    public static WebElement resultsString() {
        return driver.findElement(By.cssSelector("h1.rsHdr span.kwcat b"));
    }

    public static Integer searchResultsCount() {
        String countText = resultsCountSpan().getText();
        Integer count = 0;
        try {
            count = NumberFormat.getNumberInstance(java.util.Locale.US).parse(countText).intValue();
        }
        catch (ParseException e) {
            logger.error("Could not parse the number of search results: " + e.getMessage());
        }

        return count;
    }

    public static String searchResultsString() {
        return driver.findElement(By.cssSelector("h1.rsHdr span.kwcat b")).getText();
    }

    public static void sortResults(String sortOption) {
        sortMenu().click();

        WebElement sortMenuOption = driver.findElement(By.xpath("//ul[@id='SortMenu']/li/a[.='" + sortOption + "']"));
        sortMenuOption.click();
    }

    public static WebElement sortMenu() {
        return driver.findElement(By.cssSelector("a[aria-controls=SortMenu]"));
    }
}
