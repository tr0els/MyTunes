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
    private Playlist currentPlaylist;
    private Media currentMedia;
    // private int index needed?

    public List<Media> getAllMedias() {
        return null;
    }

    public Media createMedia(String source, String artist, String title, int time, int year, int category) {
        return null;
    }

    public void updateMedia(Media media) {

    }

    public void deleteMedia(Media media) {

    }

    // search in allSongs list
    public List<Media> searchMedias(String query) {
        return null;
    }

    public Media getCurrentMedia() {
        return null;
    }

    public void setCurrentMedia(Media media) {

    }

    public List<Playlist> getAllPlaylists() {
        return null;
    }

    // think this functionality will be needed, maybe use with listener
    Playlist getPlaylist(int id) { // id or index?
        return null;
    }

    public Playlist getCurrentPlaylist() {
        return null;
    }

    public void setCurrentPlaylist(Playlist playlist) {

    }

    // handle prev/next songs in controller instead? seems simpler
    public Media getNextMediaInPlaylist() {
        return null;
    }

    public List<Category> getAllCategories() {
        return null;
    }
}
