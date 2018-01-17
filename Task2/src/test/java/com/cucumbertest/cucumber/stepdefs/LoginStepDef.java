package com.cucumbertest.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;

public class LoginStepDef implements En {

    WebDriver browser;

    public LoginStepDef() {
        Given("^I am an agent trying to log in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            browser.get("http://http://localhost:8080/Task2_web_war_exploded/");
            browser.findElement(By.name("agentID")).sendKeys("agent1");
            browser.findElement(By.name("submit")).click();

            throw new PendingException();
        });
        /*
        When("^I obtain a key from the supervisor using a valid id$", () -> {

            // Write code here that turns the phrase above into concrete actions
            browser.get("http://localhost:8080/")

            throw new PendingException();
        });
        */
        Then("^the supervisor should give me a valid key$", () -> {
            // Write code here that turns the phrase above into concrete actions
            String loginkey = browser.findElement(By.name("LoginKeyBox")).getText();
            assertEquals(10, loginkey.length());

            throw new PendingException();
        });
        When("^I log in using that key$", () -> {
            // Write code here that turns the phrase above into concrete actions
            String loginkey = browser.findElement(By.name("LoginKeyBox")).getText();
            browser.findElement(By.name("LoginKeyInput")).sendKeys(loginkey);
            browser.findElement(By.name("submit")).click();

            throw new PendingException();
        });
        Then("^I should be allowed to log in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            assertEquals("Messaging System", browser.getTitle());

            throw new PendingException();
        });
        When("^I wait for (\\d+) seconds$", (Integer timeStamp) -> {
            // Write code here that turns the phrase above into concrete actions
            TimeUnit.SECONDS.sleep(timeStamp);

            throw new PendingException();
        });
        Then("^I should not be allowed to log in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            assertEquals("Error Page",browser.getTitle());
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
