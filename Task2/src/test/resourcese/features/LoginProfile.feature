Feature: Login Profile
  As an agent
  I want to login to my account
  In order to send and read messages

  Background: User navigates to login website
    Given I am on the "Login Website" page on URL "Localhost"
    Then I should see "Agent you may login" message

  Scenario: Successful Login
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id
    Then the supervisor should give me a valid key
    When I log in using that key
    Then I should be allowed to log in

  Scenario: Login timeout
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id
    Then the supervisor should give me a valid key
    When I wait for 65 seconds
    And I log in using that key
    Then I should not be allowed to log in
