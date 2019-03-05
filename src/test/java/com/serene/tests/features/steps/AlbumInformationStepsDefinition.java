package com.serene.tests.features.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AlbumInformationStepsDefinition {

    @Before
    public void setup()
    {
        RestAssured.baseURI = "http://docker.for.mac.localhost:9090";

    }

    @After
    public void tearDown()
    {
        RestAssured.reset();
    }

    @Steps
    APISteps loginAPI;

    @Given("^I look for album with Id \"([^\"]*)\"$")
    public void iLookForSong(String song) throws Throwable {
        loginAPI.givenASong(song);

    }

    @When("^The album api is called$")
    public void theAlbumApiIsCalled() {
        loginAPI.getSong();
    }

    @Then("^The song information must be shown$")
    public void theSongInformationMustBeShown() {
        loginAPI.verifySuccess();
    }
}
