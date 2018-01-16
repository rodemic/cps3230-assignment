Feature: Using The Message System

  Scenario: Surpassing message limit
    Given I am a logged in agent
    When I attempt to send 25 messages
    Then the messages should be successfully sent
    When I try to send another message
    Then the system will inform me that I have exceeded my quota
    And I will be logged out

  Scenario Outline: Blocked words
    Given I am a logged in agent
    When I attempt to send the message "<message>" to another agent
    Then the other agent should receive the message "<new-message>"
    Examples:
      |message | new-message |
      |Hello there | Hello there |
      |Send recipe now | Send now |
      |Nuclear recipe is ready | is ready |
      |GinGer nuclear RECipE. | . |

  Scenario: Logging out
    Given I am a logged in agent
    When I click on “Log out”
    Then I should be logged out