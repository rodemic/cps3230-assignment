package com.cucumbertest.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MessageSystemStepDef implements En {
    WebDriver browser;

    public MessageSystemStepDef() {
        Given("^I am a logged in agent$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I attempt to send (\\d+) messages$", (Integer arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^the messages should be successfully sent$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I try to send another message$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^the system will inform me that I have exceeded my quota$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^I will be logged out$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I attempt to send the message <message> to another agent$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^the other agent should receive the message <new-message>$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on “Log out”$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^I should be logged out$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }
}
