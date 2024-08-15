package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String refreshToken = "AQD4NRQQOsnfG-V1hnaRO_ubJuV1sT5MZbpP1woTBRKtaYpuK7cuO1weZhufnz8XlhW8jxbMYrtORCOuMw8G-qeSVGJx_PhMCghouhm_bQBDQ3FzixH03jcF5Jjmlp69vRc";
    private static String clientId = "31a0c11c389b43cc9fa4a836781b892a";
    private static String clientSecret = "0be23d72099a41d0b5446aadbe50946d";
    private static String accessToken;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        try{
            if (accessToken == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing Token...");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryTimeInSeconds = response.path("expires_in");
                expiry_time= Instant.now().plusSeconds(expiryTimeInSeconds - 300);
            } else{
                System.out.println("Token is good to use..");
            }

        }catch (Exception e){
            throw new RuntimeException("ABORT!!!....Failed to get token");
        }
        return accessToken;
    }
    private static Response renewToken(){
        HashMap<String,String> tokenMap = new HashMap<>();
        tokenMap.put("grant_type",ConfigLoader.getInstance().getGrantType());
        tokenMap.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        tokenMap.put("client_id", ConfigLoader.getInstance().getClientId());
        tokenMap.put("client_secret",ConfigLoader.getInstance().getClientSecret());

        Response response = RestResource.postAccount(tokenMap);

        try {
            if (response.statusCode() != 200) {
                throw new RuntimeException("ABORT!!!...Renew Token has Failed");
            }
             accessToken = response.path("access_token");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            throw e;
        }

        return response;
    }
}
