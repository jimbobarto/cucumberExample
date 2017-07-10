package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectModel.SearchPage;
import webDriver.Driver;

import static org.junit.Assert.*;


public class Google {

    private String searchString;
    static WebDriver driver = Driver.getCurrentDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 10);


    @Given("^I want to google for \"([^\"]+)\"")
    public void getSearchString(String searchString) {
        this.searchString = searchString;
    }

    @When("I google")
    public void search() {
        String googleUrl = "https://www.google.co.uk";
        driver.get(googleUrl);
        wait.until(ExpectedConditions.elementToBeClickable(SearchPage.searchField()));

        SearchPage.searchField().clear();
        SearchPage.searchField().sendKeys(this.searchString);
        SearchPage.searchField().sendKeys(Keys.RETURN);

        wait.until(ExpectedConditions.titleContains("Google Search"));
    }

    @Then("^I should see the search results for \"([^\"]*)\"$")
    public void checkThtaResultsAppear(String searchCriteria) throws Throwable {
        assertTrue(SearchPage.searchResultsTitle().contains(searchCriteria));
    }
}
