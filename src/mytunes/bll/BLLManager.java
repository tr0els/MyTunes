/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.List;
import mytunes.be.Category;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.dal.MockManager;

/**
 *
 * @author Troels Klein
 */
public class BLLManager {

    // connect to dal layer (mock for now)
    private MockManager dal = new MockManager();

    // keep track of active playlist and media
    private int currentPlaylistIndex;
    private int currentMediaIndex;

    public List<Media> getAllsMedias() {
        return null;
    }

    public Media createMedia(String source, String artist, String title, int time, int year, int category) {
        return null;
    }

    public void updateMedia(Media media) {

    }

    public void deleteMedia(Media media) {

    }

    List<Media> searchMedias(String query) {
        return null;
    }

    List<Playlist> getAllPlaylists() {
        return null;
    }

    Playlist getPlaylist(int id) { // id or index?
        // think this functionality will be needed, maybe use with listener
        return null;
    }

    List<Category> getAllCategories() {
        return null;
    }
}
