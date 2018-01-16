package com.cucumbertest.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;

public class LoginStepDef implements En {
    public LoginStepDef() {
        Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$", (String arg0, String arg1) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^I should see \"([^\"]*)\" message$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Given("^I am an agent trying to log in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I obtain a key from the supervisor using a valid id$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^the supervisor should give me a valid key$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I log in using that key$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^I should be allowed to log in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I wait for (\\d+) seconds$", (Integer arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^I should not be allowed to log in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
