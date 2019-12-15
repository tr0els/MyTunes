/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.bll.util.ConvertCategory;
import mytunes.bll.util.ConvertTime;
import mytunes.bll.util.SearchSongs;
import mytunes.dal.database.CategoryDBDAO;
import mytunes.dal.database.MediaDBDAO;
import mytunes.dal.database.PlaylistDBDAO;

/**
 *
 * @author Troels Klein
 */
public class BLLManager
{

    // connect to dal layer (mock for now)
    // private final MockManager mock = new MockManager();
    private final MediaDBDAO mediaDB;
    private final PlaylistDBDAO playlistDB;
    private final CategoryDBDAO categoryDB;

    public BLLManager() throws Exception
    {
        mediaDB = new MediaDBDAO();
        playlistDB = new PlaylistDBDAO();
        categoryDB = new CategoryDBDAO();
    }

    public List<Media> getAllMedias() throws Exception
    {
        return mediaDB.getAllMedias();
    }

    public Media createMedia(String source, String artist, String title, int time, int year, int category) throws Exception
    {
        int numplays = 0;
        Media media = mediaDB.createMedia(source, artist, title, time, year, category, numplays);
        return media;
        //return null;
    }

    public void updateSong(Media media)
    {
        
        try
        {
          mediaDB.updateMedia(media);
        } catch (Exception ex)
        {
            Logger.getLogger(BLLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteSong(Media media) throws Exception {
        mediaDB.deleteMedia(media);
    }

    public List<Playlist> getAllPlaylists() throws Exception
    {
        return playlistDB.getAllPlaylist();
    }

    public Playlist createPlaylist(String name) throws Exception
    {
        Playlist playlist = playlistDB.createPlaylist(name);
        return playlist;
    }

    public void updatePlaylist(Playlist playlist) throws Exception 
    {
     playlistDB.updatePlaylist(playlist);
    }

    public void deletePlaylist(Playlist playlist) throws Exception
    {
        playlistDB.deletePlaylist(playlist);  
    }
    
    public void addSongToPlaylist(Playlist playlist, Media media) throws Exception
    {
        playlistDB.addToPlaylist(playlist, media);
    }

    public void deleteSongFromPlaylist(Playlist playlist, Media media) throws Exception 
    {
        playlistDB.removeFromPlaylist(playlist, media);
    }

    public List<String> getAllCategories() throws Exception
    {
        return categoryDB.getAllCategories();
    }
    
    public String categoryIdToName(int id) throws Exception {
        List<String> allCategories = getAllCategories();
        return ConvertCategory.categoryIdToName(allCategories, id);
    }
    
    public int categoryNameToId(String name) throws Exception {
        List<String> allCategories = getAllCategories();
        return ConvertCategory.categoryNameToId(allCategories, name);
    }
    
    public String secToTime(int sec) {
        return ConvertTime.secToTime(sec);
    }
    
    public int timeToSec(String time) {
        return ConvertTime.timeToSec(time);
    }    

    //search the songs table for the query input 
    //@param query
    //@returns list of media 
    public List<Media> search(String query) throws Exception
    {
        List<Media> searchBase = mediaDB.getAllMedias();
        searchBase = SearchSongs.search(searchBase, query);
        
        return searchBase;
    }
}
