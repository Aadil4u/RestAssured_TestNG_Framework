package com.spotify.api;

import com.spotify.utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.routes.Routes.API;
import static com.spotify.routes.Routes.TOKEN;
import static io.restassured.RestAssured.given;

public class Token_Manager {

    private static String  access_token;
    private static Instant expiry_time;

    public  static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParams.put("client_id",ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());


        Response response = given().spec(SpecBuilder.tokenRequestSpec())
                .formParams(formParams)
                .when().post(API + TOKEN)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();

        if (response.statusCode() !=200) {
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }

        return response;

    }

    public static synchronized String getAccessToken()  {
        try {
            if(access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing Token !!!");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds -300);
            } else  {
                System.out.println("Token Good To Use !!!");
            }
        } catch (Exception e) {
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }
        return  access_token;
    }

}
