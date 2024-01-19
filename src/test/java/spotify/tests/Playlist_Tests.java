package spotify.tests;


import com.spotify.api.StatusCode;
import com.spotify.applicationApi.PlaylistApi;
import com.spotify.pojo.ErrorRoot;
import com.spotify.pojo.Playlist;
import com.spotify.utils.ConfigLoader;
import com.spotify.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.utils.FakerUtils.generateDescription;
import static com.spotify.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist")
public class Playlist_Tests {

    @Step()
    public Playlist playlistBuilder(String playlistName, String playlistDescription, boolean isPublic) {
       return Playlist.builder()
               .name(playlistName)
               .description(playlistDescription)
               ._public(isPublic).build();
    }

    @Step()
    public void assertPlaylistEqual(Playlist requestPlaylist, Playlist resposePlaylist) {
        assertThat(requestPlaylist.getName(), equalTo(resposePlaylist.getName()));
        assertThat(requestPlaylist.getDescription(), equalTo(resposePlaylist.getDescription()));
        assertThat(requestPlaylist.get_public(), equalTo(resposePlaylist.get_public()));
    }

    public void assertError(ErrorRoot responseError, int expectedStatusCode, String expectedMsg) {
        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMsg));
    }


    @Story("Able To Create Playlist")
    @Link("https://www.Google.com")
    @Description("Able To Create Playlist")
    @Test(description = "Able To Create Playlist")
    public void shouldBeAbleToCreatePlaylist() {

        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(),false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(201));

        Playlist resposePlaylist = response.as(Playlist.class);
       assertPlaylistEqual(requestPlaylist,resposePlaylist);


    }


    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Description("Get A Particular Playlist")
    @Test (description = "Get A Particular Playlist")
    public void getPlaylist() {

        Playlist requestPlaylist = playlistBuilder("Update Pojo Playlist 1", "Update Pojo playlist description",true);

        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        Playlist resposePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(requestPlaylist, resposePlaylist);

    }


    @Link(name = "allure", value = "Google")
    @Description("Able To Update A Playlist")
    @Test(description = "Able To Update A Playlist")
    public void shouldBeAbleToUpdatePlaylist() {

        Playlist requestPlaylist = playlistBuilder("Update Pojo Playlist 1", "Update Pojo playlist description",false);
        Response response = PlaylistApi.put(requestPlaylist, DataLoader.getInstance().getPlaylistId());
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));

    }


    @TmsLink("test1")
    @Description("Should Not Create A Playlist Without Name")
    @Test(description = "Should Not Create A Playlist Without Name")
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {

        Playlist requestPlaylist = playlistBuilder("", "Update Pojo playlist description",false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_400.getCode()));

        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertError(errorRoot, StatusCode.CODE_400.getCode(), StatusCode.CODE_400.getMsg());


    }


    @Description("Should Not Create A Playlist With Invalid Token")
    @Test(description = "Should Not Create A Playlist With Invalid Token")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldNotBeAbleToCreatePlaylistWithInvalidToken() {
        String access_token = "1234";

        Playlist requestPlaylist = playlistBuilder("Pojo Playlist 1", "Pojo playlist description",false);

        Response response = PlaylistApi.post(requestPlaylist, access_token);
        assertThat(response.statusCode(), equalTo(401));

        ErrorRoot errorRoot =response.as(ErrorRoot.class);
        assertError(errorRoot, StatusCode.CODE_401.getCode(), StatusCode.CODE_401.getMsg());


    }
}
