package com.serene.tests.features.steps;

import com.serene.tests.features.service.HTTPRequest;
import com.serene.tests.features.utils.PropertiesReader;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class AlbumSteps {
    String user;
    String password;
    String artist;
    String genre;
    String releaseYear;
    String title;
    String trackcount;
    RequestSpecification requestSpecification;
    Response response;

    @Given("^The user wants to create an album with the artist \"([^\"]*)\", genre \"([^\"]*)\", release year \"([^\"]*)\", title \"([^\"]*)\", track count \"([^\"]*)\"$")
    public void theUserWantsToCreateAnAlbumWithTheArtistGenreReleaseYearTitleTrackCount(String artist, String genre, String releaseYear, String title, String trackcount) {
        this.artist = artist;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.title = title;
        this.trackcount = trackcount;

        user = PropertiesReader.getValueByKey("api.user");
        password = PropertiesReader.getValueByKey("api.password");
    }

    @When("^The post request to create an album is send$")
    public void thePostRequestToCreateAnAlbumIsSend() {
        Map<String, String> params = new HashMap<String, String>();

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath("albums");
        builder.setContentType("application/json");
        builder.setBody("{\n" +
                "    \"artist\": \"Muse\",\n" +
                "    \"genre\": \"Indie Rock\",\n" +
                "    \"releaseYear\": \"1994\",\n" +
                "    \"title\": \"Showbiz\",\n" +
                "    \"trackCount\": 0\n" +
                "}");
        requestSpecification = builder.build();
        requestSpecification = RestAssured.given().spec(requestSpecification);
        requestSpecification.log().all();

        response = requestSpecification.when().post("http://172.17.228.107:9090/albums");
    }

    @Then("^The service should create the album$")
    public void theServiceShouldCreateTheAlbum() {
        Assert.assertTrue("The API return an empty message",response.getBody().asString().length() > 0);
    }
}
