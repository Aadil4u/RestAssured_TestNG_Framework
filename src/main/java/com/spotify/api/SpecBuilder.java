package com.spotify.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.routes.Routes.BASE_PATH;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
        .setBaseUri(System.getProperty("BASE_URI"))
        .setBasePath(BASE_PATH)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
        .build();
    }
    public static RequestSpecification tokenRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();
    }


    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
        .log(LogDetail.ALL)
                .build();
    }


}
