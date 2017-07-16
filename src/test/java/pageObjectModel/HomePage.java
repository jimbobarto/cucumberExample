package pageObjectModel;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import webDriver.Driver;

import java.text.NumberFormat;
import java.text.ParseException;

public class HomePage {
    final static Logger logger = Logger.getLogger(HomePage.class);

    //static WebDriver driver = Driver.getCurrentDriver();

    public static WebElement searchField() {
        return Driver.getDriver().findElement(By.id("gh-ac"));
    }

    public static void enterTextInSearchField(String searchString) {
        WebElement searchField = searchField();
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.sendKeys(Keys.RETURN);
    }

    public static WebElement resultsCountSpan() {
        return Driver.getDriver().findElement(By.cssSelector("h1.rsHdr span.rcnt"));
    }

    public static WebElement resultsString() {
        return Driver.getDriver().findElement(By.cssSelector("h1.rsHdr span.kwcat b"));
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
        return Driver.getDriver().findElement(By.cssSelector("h1.rsHdr span.kwcat b")).getText();
    }

    public static void sortResults(String sortOption) {
        sortMenu().click();

        WebElement sortMenuOption = Driver.getDriver().findElement(By.xpath("//ul[@id='SortMenu']/li/a[.='" + sortOption + "']"));
        sortMenuOption.click();
    }

    public static WebElement sortMenu() {
        return Driver.getDriver().findElement(By.cssSelector("a[aria-controls=SortMenu]"));
    }

    public static String sortMenuText() {
        return sortMenu().getText();
    }

    public static Double getFirstPrice() {
        String price = Driver.getDriver().findElement(By.cssSelector("li.sresult.lvresult.clearfix.li:first-of-type")).findElement(By.cssSelector("ul.lvprices li.lvprice span.bold")).getText();
        logger.warn("First price: " + price);
        return Double.parseDouble(price.replaceAll("£", ""));
    }

    public static Double getLastPrice() {
        String price = Driver.getDriver().findElement(By.cssSelector("li.sresult.lvresult.clearfix.li:last-of-type")).findElement(By.cssSelector("ul.lvprices li.lvprice span.bold")).getText();
        logger.warn("Last price: " + price);
        return Double.parseDouble(price.replaceAll("£", ""));
    }
}
