package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectModel.HomePage;
import webDriver.Driver;

public class EBay {
    static WebDriverWait wait;

    final static Logger logger = Logger.getLogger(EBay.class);

    @Before
    public void setUp() {
        wait = new WebDriverWait(Driver.getDriver(), 10);
    }

    @After
    public void cleanUp() {
        Driver.quit();
    }

    @Given("^I am on the eBay homescreen")
    public void goToHomeScreen() {
        String eBayUrl = "https://www.ebay.co.uk";
        Driver.getDriver().get(eBayUrl);
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.searchField()));
    }

    @When("I search for \"([^\"]+)\"")
    public void search(String searchString) {
        HomePage.enterTextInSearchField(searchString);

        wait.until(ExpectedConditions.textToBePresentInElement(HomePage.resultsString(), searchString));
    }

    @When("I sort by \"([^\"]+)\"")
    public void filter(String sortOption) {

        HomePage.sortResults(sortOption);

        wait.until(ExpectedConditions.textToBePresentInElement(HomePage.sortMenu(), sortOption));
    }

    @Then("^I should see the search results for \"([^\"]*)\"$")
    public void checkThatResultsAppear(String searchCriteria) throws Throwable {
        assert(HomePage.searchResultsCount() > 0);
        assert(HomePage.searchResultsString().equals(searchCriteria));
    }

    @Then("^I should see the search results for \"([^\"]*)\" sorted by \"([^\"]*)\"$")
    public void checkThatSortedResultsAppear(String searchCriteria, String sortOrder) throws Throwable {
        assert(HomePage.searchResultsCount() > 0);
        assert(HomePage.searchResultsString().equals(searchCriteria));
        assert(HomePage.sortMenuText().equals(sortOrder));

        assert(HomePage.getFirstPrice() <= HomePage.getLastPrice());
    }
}
