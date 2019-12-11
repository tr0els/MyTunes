/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.dal.MockManager;
import mytunes.dal.database.MediaDBDAO;
import mytunes.dal.database.PlaylistDBDAO;

/**
 *
 * @author Troels Klein
 */
public class BLLManager {

    // connect to dal layer (mock for now)
  // private final MockManager mock = new MockManager();
    private final MediaDBDAO mediaDB; 
    private final PlaylistDBDAO playlistDB;

    // keep track of active playlist and media
    private Playlist currentPlaylist;
    private Media currentMedia;
    // private int index needed?

    public BLLManager()throws Exception
    {
        mediaDB = new MediaDBDAO();
        playlistDB = new PlaylistDBDAO();
    }

    public List<Media> getAllMedias() throws Exception{
        return mediaDB.getAllMedias();
    }

    public Media createMedia(String source, String artist, String title, int time, int year, int category) throws Exception
    {   int numplays = 0; 
        Media media = mediaDB.createMedia(source, artist, title, time, year, category, numplays);
        return media;
        //return null;
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

    public List<Playlist> getAllPlaylists() throws Exception {
        return playlistDB.getAllPlaylist();
    }
    
    public Playlist createPlaylist(String name) throws Exception 
    {
       Playlist playlist = playlistDB.createPlaylist(name);
       return playlist;
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

    public List<String> getAllCategories() {
        // return dal.getAllCategories(); <-- uncomment when getAllCategories is complete in dal
        return null;
    }
    
    public List<Media> search(String query) throws Exception
    {
        
        List<Media> searchBase = mediaDB.getAllMedias();
        List<Media> output = new ArrayList<>();

        for (Media song : searchBase)
        {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())||
                    song.getArtist().toLowerCase().contains(query.toLowerCase()))
            {
                output.add(song);
            }
        }
        
        return output;
    }
}
