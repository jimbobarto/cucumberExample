package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectModel.HomePage;

public class EBay extends BaseStepDefinitions {
    private String searchString;


    @Given("^I am on the eBay homescreen")
    public void goToHomeScreen() {
        String eBayUrl = "https://www.ebay.co.uk";
        driver.get(eBayUrl);
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.searchField()));
    }

    @When("I search for \"([^\"]+)\"")
    public void search(String searchString) {
        this.searchString = searchString;

        HomePage.searchField().clear();
        HomePage.searchField().sendKeys(this.searchString);
        HomePage.searchField().sendKeys(Keys.RETURN);

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
    }

    @Then("^I should see the search results for \"([^\"]*)\" sorted by \"([^\"]*)\"$")
    public void checkThatSortedResultsAppear(String searchCriteria, String sortOrder) throws Throwable {
        assert(HomePage.searchResultsCount() > 0);
    }
}
