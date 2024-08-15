package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.base.BaseTest;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;

import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.spotify.oauth2.api.applicationApi.PlaylistApi.post;
import static com.spotify.oauth2.api.applicationApi.PlaylistApi.get;
import static com.spotify.oauth2.api.applicationApi.PlaylistApi.update;


public class PlaylistTests extends BaseTest {
    @Step
    public Playlist playlistCommon(String name, String description, boolean _public){
         return Playlist.builder().name(name).description(description)._public(_public)
                 .build();
    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlayList, Playlist requestPlayList){
        assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
    }
    @Step
    public void assertCode(int actualStatusCode , StatusCode statusCode){
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }
    @Step
    public void assertErrorCode(ErrorRoot responseErr , StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(),equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(),equalTo(statusCode.msg));
    }

    @Description ("Should be able to create a playlist")
    @Test (description = "Should be able to create a playlist",priority = 1)
    public void createPlaylist(){
        Playlist requestPlaylist =  playlistCommon(generateName(),generateDescription(),false);

        Response response = post(requestPlaylist); //**** here post method is coming form playlistApi Class******
        assertCode(response.statusCode(),StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);
    }

    @Description ("Should be able to fetch playlist")
    @Test (description = "Should be able to fetch playlist",priority = 3)
    public void getPlaylist(){
        Playlist requestPlaylist = playlistCommon("latest updated Playlist1","latest updated description1",true);

        Response response = get(DataLoader.getInstance().getPlayListId()); //**** here get method is coming form playlistApi Class******
        assertCode(response.statusCode(),StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);
    }

    @Description ("Should be able to update playlist")
    @Test (description = "Should be able to update a playlist",priority = 2)
    public void updatePlaylist(){
        Playlist requestPlaylist = playlistCommon("latest updated Playlist1","latest updated description1",false);

        Response response = update(requestPlaylist,DataLoader.getInstance().updatePlayListId()); //**** here update method is coming form playlistApi Class******
        assertCode(response.statusCode(),StatusCode.CODE_200);
    }

    @Description ("Should not be able to fetch playlist")
    @Test (description = "Should not be able to update a playlist",priority = 4)
    public void shouldNotCreatePlaylist(){
        Playlist requestPlaylist = playlistCommon("",generateDescription(),false);

        Response response = post(requestPlaylist); //**** here post method is coming form playlistApi Class******
        assertErrorCode(response.as(ErrorRoot.class),StatusCode.CODE_400);
    }

    @Description ("Should not be able to create a playlist with invalid token")
    @Test (description = "Should not be able to create a playlist with invalid token",priority = 5)
    public void shouldNotCreatePlaylistWithInvalidToken(){
        String invalidToken = "Bearer 12345";
        Playlist requestPlaylist = playlistCommon(generateName(),generateDescription(),false);

        Response response = post(requestPlaylist,invalidToken); //**** here post method is coming form playlistApi Class******
        assertErrorCode(response.as(ErrorRoot.class),StatusCode.CODE_401);
    }
}
