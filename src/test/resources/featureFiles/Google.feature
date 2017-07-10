@search
Feature: Google cats
  As a cat enthusiast
  I want to use Google to search for cats

  Scenario: Search for Cats
    Given I want to google for "Cats"
    When I google
    Then I should see the search results for "Cats"