package com.spotify.applicationApi;

import com.spotify.api.SpecBuilder;
import com.spotify.pojo.ErrorRoot;

import com.spotify.pojo.Playlist;
import com.spotify.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.spotify.api.Token_Manager.getAccessToken;
import static com.spotify.routes.Routes.PLAYLISTS;
import static com.spotify.routes.Routes.USERS;
import static io.restassured.RestAssured.given;

public class PlaylistApi {


    @Step()
    public static Response post(Playlist requestPlaylist) {
        return given().spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(getAccessToken())
                .body(requestPlaylist)
                .pathParam("userId", ConfigLoader.getInstance().getUserId())
                .when().post(USERS + "/{userId}/playlists")
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public static Response post(Playlist requestPlaylist, String access_token) {
        return given().
                baseUri("https://api.spotify.com")
                .basePath("/v1")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .body(requestPlaylist)
                .pathParam("userId", ConfigLoader.getInstance().getUserId())
                .log().all()
                .when().post(USERS + "/{userId}/playlists")
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();

    }

    public static Response get(String playlistId) {
        return given().spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(getAccessToken())
                .pathParam("playlistId", playlistId)
                .when().get(PLAYLISTS + "/{playlistId}")
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public static Response put(Playlist requestPlaylist, String playlistId) {
        return given().spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(getAccessToken())
                .body(requestPlaylist)
                .pathParam("playlistId", playlistId)
                .when().put(PLAYLISTS + "/{playlistId}")
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

}
