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

    /**
     * Gets a list of all songs
     * 
     * @return list of Media objects
     * @throws Exception 
     */
    public List<Media> getAllMedias() throws Exception
    {
        return mediaDB.getAllMedias();
    }

    /**
     * Creates a new song and adds it to the storage
     * 
     * @param source is the path + filename of the source song
     * @param artist is the song artist
     * @param title is the song title
     * @param time is the song length in seconds 
     * @param year is is the song year
     * @param category is the song category id
     * @return the created Media object 
     * @throws Exception 
     */
    public Media createMedia(String source, String artist, String title, int time, int year, int category) throws Exception
    {
        int numplays = 0; // not implemented yet
        Media media = mediaDB.createMedia(source, artist, title, time, year, category, numplays);
        
        return media;
    }

    /***
     * Update song in the storage
     * 
     * @param media is the updated song object
     */
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
    
    /**
     * Remove a song from the currently selected playlist
     * 
     * @param media is the song object
     * @throws Exception 
     */
    public void deleteSong(Media media) throws Exception {
        mediaDB.deleteMedia(media);
    }

    /**
     * Gets a list of all playlists from the storage
     * 
     * @return list of Playlists
     * @throws Exception 
     */
    public List<Playlist> getAllPlaylists() throws Exception
    {
        return playlistDB.getAllPlaylist();
    }

    /**
     * Create playlist in the storage
     * 
     * @param name of the playlist
     * @return from storage the newly created Playlist from 
     * @throws Exception 
     */
    public Playlist createPlaylist(String name) throws Exception
    {
        Playlist playlist = playlistDB.createPlaylist(name);
        return playlist;
    }

    /**
     * Update playlist in storage
     * 
     * @param update playlist object
     * @throws Exception 
     */
    public void updatePlaylist(Playlist playlist) throws Exception 
    {
        playlistDB.updatePlaylist(playlist);
    }

    /**
     * Delete playlist from storage
     * 
     * @param playlist object to be deleted
     * @throws Exception 
     */
    public void deletePlaylist(Playlist playlist) throws Exception
    {
        playlistDB.deletePlaylist(playlist);  
    }
    
    /**
     * Add song to a playlist
     * 
     * @param playlist is the playlist to add song to
     * @param media is the song that should be added
     * @throws Exception 
     */
    public void addSongToPlaylist(Playlist playlist, Media media) throws Exception
    {
        playlistDB.addToPlaylist(playlist, media);
    }

    /**
     * Remove a song from the currently selected playlist
     * 
     * @param playlist is the playlist to remove song from
     * @param media is the song that should be removed
     * @throws Exception 
     */
    public void deleteSongFromPlaylist(Playlist playlist, Media media) throws Exception 
    {
        playlistDB.removeFromPlaylist(playlist, media);
    }

    /**
     * Gets all categories from storage
     * 
     * @return list is the List containing all the category Strings
     * @throws Exception 
     */
    public List<String> getAllCategories() throws Exception
    {
        return categoryDB.getAllCategories();
    }
    
    /**
     * Converts category id to its text representation
     * 
     * @param id is the category id
     * @return category text as a String
     * @throws Exception 
     */
    public String categoryIdToName(int id) throws Exception {
        List<String> allCategories = getAllCategories();
        return ConvertCategory.categoryIdToName(allCategories, id);
    }
    
     /**
     * Converts category name to its id representation
     * 
     * @param name  is the category text name
     * @return category id as an int
     * @throws Exception 
     */
    public int categoryNameToId(String name) throws Exception {
        List<String> allCategories = getAllCategories();
        return ConvertCategory.categoryNameToId(allCategories, name);
    }
    
    /**
     * Converts seconds to a textual time representation
     * 
     * @param sec is the amount of seconds
     * @return the formatted text (like hh:mm:ss)
     */
    public String secToTime(int sec) {
        return ConvertTime.secToTime(sec);
    }
    
    /**
     * Converts textual time representation into seconds
     * 
     * @param time the textual representation
     * @return amount of seconds 
     */
    public int timeToSec(String time) {
        return ConvertTime.timeToSec(time);
    }    

    /**
     * Search the songs table for the query input
     * 
     * @param query is the search query text
     * @return the search result as a List of songs (Media objects)
     * @throws Exception 
     */
    public List<Media> search(String query) throws Exception
    {
        List<Media> searchBase = mediaDB.getAllMedias();
        searchBase = SearchSongs.search(searchBase, query);
        
        return searchBase;
    }
}