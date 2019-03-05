package com.serene.tests.features.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
    public static String BASE_URL = "http://172.17.228.107:9090";

    private static RequestSpecification formRequest(String key, String token, Map<String, String> otherParams) {
        Map<String, String > params = new HashMap<String, String>();
        params.put("key", key);
        params.put("token", token);
        params.putAll(otherParams);

        return RestAssured.given().contentType("application/json").queryParams(params);
    }

    public static Response postRequest(String key, String token, Map<String, String> otherParams, String path) {
        String url = BASE_URL + "/" + path;
        RequestSpecification requestSpecification = formRequest(key, token, otherParams);
        return requestSpecification.post(url);
    }
}
