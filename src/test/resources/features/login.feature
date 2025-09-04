Feature: User Login
  As a registered user
  I want to log in to the application
  So that I can access my account

  Background:
    Given I am on the login page

  @Smoke @DQ-2
  Scenario: Successful login with valid credentials
    When I enter username "validUser" and password "validPass123"
    And I click the login button
    Then I should be redirected to the dashboard page

  Scenario Outline: Login with invalid credentials
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see an error message "<errorMessage>"

    Examples:
      | username    | password     | errorMessage                     |
      | invalidUser | validPass123 | Invalid username or password.    |
      | validUser   | wrongPass    | Invalid username or password.    |
      | ""          | validPass123 | Username is required.            |
      | validUser   | ""           | Password is required.            |