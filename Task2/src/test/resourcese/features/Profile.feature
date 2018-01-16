Feature: Logging Out of Profile
  As an agent
  I want to log out of my account
  In order to stop using the system

  Background: User navigates to Profile Website
    Given I am on the "Profile Website" on page URL "Localhost/Profile"
    Then I should see "Log out" button

  Scenario: Logging out
    Given I am a logged in agent
    When I click on “Log out”
    Then I should be logged out