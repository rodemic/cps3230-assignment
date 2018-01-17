package com.cucumbertest.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class MessageSystemStepDef implements En {
    WebDriver browser;

    public MessageSystemStepDef() {
        Given("^I am a logged in agent$", () -> {
            // Write code here that turns the phrase above into concrete actions
            browser.get("http://localhost:8080/Task2_web_war_exploded/");
            browser.findElement(By.name("agentID")).sendKeys("agent1");
            browser.findElement(By.name("submit")).click();

            String loginkey = browser.findElement(By.name("LoginKeyBox")).getText();
            browser.findElement(By.name("LoginKeyInput")).sendKeys(loginkey);
            browser.findElement(By.name("submit")).click();

            throw new PendingException();
        });
        When("^I attempt to send (\\d+) messages$", (Integer messagesSent) -> {
            // Write code here that turns the phrase above into concrete actions
            for(int i = 0; i < messagesSent; i++){
                browser.findElement(By.name("SendMessageButton")).click();
                browser.findElement(By.name("TargetAgentField")).sendKeys("agent2");
                browser.findElement(By.name("MessageField")).sendKeys("Hello");
                browser.findElement(By.name("SubmitMessage")).click();
            }

            throw new PendingException();
        });
        Then("^the messages should be successfully sent$", () -> {
            // Write code here that turns the phrase above into concrete actions
            assertEquals("Message Successfully Sent", browser.findElement(By.name("SendMessageResponse")).getText());

            throw new PendingException();
        });
        When("^I try to send another message$", () -> {
            // Write code here that turns the phrase above into concrete actions
            browser.findElement(By.name("SendMessageButton")).click();
            browser.findElement(By.name("TargetAgentField")).sendKeys("agent2");
            browser.findElement(By.name("MessageField")).sendKeys("Hello");
            browser.findElement(By.name("SubmitMessage")).click();

            throw new PendingException();
        });
        Then("^the system will inform me that I have exceeded my quota$", () -> {
            // Write code here that turns the phrase above into concrete actions
            assertEquals("Error Page", browser.getTitle());

            throw new PendingException();
        });
        And("^I will be logged out$", () -> {
            // Write code here that turns the phrase above into concrete actions
            browser.findElement(By.name("OkButton")).click();
            assertEquals("Login Page", browser.getTitle());

            throw new PendingException();
        });
        When("^I attempt to send the message \"([^\"]*)\" to another agent$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            browser.findElement(By.name("SendMessageButton")).click();
            browser.findElement(By.name("TargetAgentField")).sendKeys("agent2");
            browser.findElement(By.name("MessageField")).sendKeys("Hello");
            browser.findElement(By.name("SubmitMessage")).click();

            throw new PendingException();
        });
        Then("^the other agent should receive the message \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            browser.get("http://localhost:8080/Task2_web_war_exploded/");
            browser.findElement(By.name("agentID")).sendKeys("agent2");
            browser.findElement(By.name("submit")).click();

            String loginkey = browser.findElement(By.name("LoginKeyBox")).getText();
            browser.findElement(By.name("LoginKeyInput")).sendKeys(loginkey);
            browser.findElement(By.name("submit")).click();

            browser.findElement(By.name("ReadMessageButton")).click();
            assertEquals("Hello", browser.findElement(By.name("Messages")).getText());

            throw new PendingException();
        });
        When("^I click on “Log out”$", () -> {
            // Write code here that turns the phrase above into concrete actions
            //han mur in bul
            browser.findElement(By.name("LogOutButton")).click();

            throw new PendingException();
        });
        Then("^I should be logged out$", () -> {
            // Write code here that turns the phrase above into concrete actions
            assertEquals("Login Page", browser.getTitle());

            throw new PendingException();
        });
    }
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }
}
