package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;


public class PlaylistApi {
    @Step
    public static Response post(Playlist requestPlaylist){
       return RestResource.post(USERS +"/"+ConfigLoader.getInstance().getUserId()+
               PLAYLISTS,getToken(),requestPlaylist);
    }
    @Step
    public static Response get(String playListId){
        return RestResource.get(PLAYLISTS +"/"+playListId,getToken());
    }
    @Step
    public static Response update(Playlist requestPlaylist, String playListId){
        return RestResource.update(requestPlaylist,PLAYLISTS +"/"+playListId,getToken());

    }
    @Step
    public static Response post(Playlist requestPlaylist, String token){
        return RestResource.post(USERS +"/"+ConfigLoader.getInstance().getUserId()+
                PLAYLISTS,token,requestPlaylist);
    }
}
