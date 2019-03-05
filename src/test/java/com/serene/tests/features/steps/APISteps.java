package com.serene.tests.features.steps;

import org.junit.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.thucydides.core.annotations.Step;


public class APISteps {
	
    private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    private RequestSpecification requestSpec;
	
        @Step
        public void givenASong (String song){
        	RequestSpecBuilder builder = new RequestSpecBuilder();
        	builder.setBasePath("albums/"+song);
        	builder.setContentType("application/json");
        	requestSpec = builder.build();
        	requestSpec = RestAssured.given().spec(requestSpec);
        	requestSpec.log().all();

        }

        @Step
        public void givenASong(){
        	RequestSpecBuilder builder = new RequestSpecBuilder();
        	builder.setBasePath("albums");
        	builder.setContentType("application/json");
        	builder.setBody("data goes here");
        	requestSpec = builder.build();
        	requestSpec = RestAssured.given().spec(requestSpec);
        	requestSpec.log().all();

        }
        
        @Step     
        public void getSong()  {
        	res = requestSpec.when().get().then().assertThat().statusCode(200).and().extract().response();
          }

          @Step
        public void postSong()  {
        	res = requestSpec.when().post();
          }

        @Step     
        public void verifySuccess() {
            Assert.assertTrue("The API return an empty message",res.getBody().asString().length() > 0);

        }
        
        @Step     
        public void verifyLoginFailure(String username,String password) {

        }

    	
   }


