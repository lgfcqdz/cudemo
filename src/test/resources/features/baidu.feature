Feature: search on baidu

  @Smoke @DQ-1
  Scenario: Search on Baidu
    Given Open the baidu page.
    When I enter search term "Selenium"
    And I click the search button
    Then Results containing "Selenium" should be displayed

