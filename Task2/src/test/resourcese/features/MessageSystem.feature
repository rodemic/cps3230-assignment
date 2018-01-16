Feature: Using The Message System
  As an agent
  I want to send messages to other agents and read messages from other agents

  Background: User navigates to the Messaging System website
    Given I am on the "Message System Website" on page URL "localhost/Message-System"
    Then I should see "Send Message" message
    And I should see "Read Messages" message
    And I should see "Exit Message System" message

  Scenario: Surpassing message limit
    Given I am a logged in agent
    When I attempt to send 25 messages
    Then the messages should be successfully sent
    When I try to send another message
    Then the system will inform me that I have exceeded my quota
    And I will be logged out

  Scenario Outline: Blocked words
    Given I am a logged in agent
    When I attempt to send the message <message> to another agent
    Then the other agent should receive the message <new-message>
    Examples:
      |message | new-message |
      |Hello there | Hello there |
      |Send recipe now | Send now |
      |Nuclear recipe is ready | is ready |
      |GinGer nuclear RECipE. | . |