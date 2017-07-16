@search
Feature: Searching on eBay
  As a new customer (not logged in) on eBay,
  I want to be able to shop for an item I want
  to look for the cheapest item available
  and use the filtering options available.

  Scenario: Search for iPhone
    Given I am on the eBay homescreen
    When I search for "iPhone"
    Then I should see the search results for "iPhone"

  Scenario: Search for the cheapest iPhone
    Given I am on the eBay homescreen
    When I search for "iPhone"
    And I sort by "Lowest price"
    Then I should see the search results for "iPhone" sorted by "Lowest price"