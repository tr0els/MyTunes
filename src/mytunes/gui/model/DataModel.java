/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.bll.BLLManager;

/**
 *
 * @author Troels Klein
 */
public class DataModel {

    private BLLManager bll;

    private ObservableList<Playlist> allPlaylists; // list of playlist
    private ObservableList<Media> songsOnSelectedPlaylist; // list of songs on currently selected playlist
    private ObservableList<Media> allSongs; // list of all songs
    private Playlist selectedPlaylist; // reference to currently selected playlist
    private List<String> allCategories;
    
    public DataModel() throws Exception {
        bll = new BLLManager();
        
        // initialize
        allPlaylists = FXCollections.observableArrayList();
        songsOnSelectedPlaylist = null;
        allSongs = FXCollections.observableArrayList();
        selectedPlaylist = null;

        // populate playlist and all songs lists
        allPlaylists.addAll(bll.getAllPlaylists());
        allSongs.addAll(bll.getAllMedias());
        
        // list of all categories
        allCategories = bll.getAllCategories();
    }
    
    /**
     * sets the references to currently selected playlist 
     * and the playlists associated songlist
     */
    public void setSelectedPlaylist(Playlist playlist) {
        selectedPlaylist = playlist;
        songsOnSelectedPlaylist = playlist.getMedias();
    }    
   
    /**
    * gets a reference to the observable list of all playlists
    * 
    * @return a list of all playlists
    */
    public ObservableList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }
    
    /**
     * Creates a playlist by adding it to the storage,
     * and adding it to the list of all playlists.
     * 
     * @param name is the name of the new playlist
     * @throws Exception 
     */
    public void createPlaylist(String name) throws Exception {
        Playlist playlist = bll.createPlaylist(name);
        allPlaylists.add(playlist);
    }
    
    /**
     * Updates the edited playlist in the storage
     * 
     * @param playlist is the playlist object to be updated
     * @throws Exception 
     */
    public void updatePlaylist(Playlist playlist) throws Exception {
        bll.updatePlaylist(playlist);
    }
    
    /**
     * Deletes the playlist from storage.
     * It is also removed from the list of all playlists, 
     * and the playlists list of songs view is cleared.
     * References to the playlist and its list of songs are both set to null.
     * 
     * @throws Exception 
     */
    public void deletePlaylist() throws Exception {
        bll.deletePlaylist(selectedPlaylist); // a try with catch would be a good idea here
        allPlaylists.remove(selectedPlaylist);
        songsOnSelectedPlaylist.clear();
        selectedPlaylist = null;
        songsOnSelectedPlaylist = null;
    }
          
    /**
     * Gets a reference to the list of the songs in the currently selected playlist
     * 
     * @return the playlists list of songs
     */
    public ObservableList<Media> getSongsOnPlaylist() {
        return songsOnSelectedPlaylist;
    }
    
    /**
     * Add a song to the currently selected playlist
     * 
     * @param media is the selected song
     * @throws Exception 
     */
    public void addSongToPlaylist(Media media) throws Exception {
        bll.addSongToPlaylist(selectedPlaylist, media);
        songsOnSelectedPlaylist.add(media);
        // todo: update numSongs + total time
    }

    /**
     * Remove a song from the currently selected playlist
     * 
     * @param media is the selected song
     * @throws Exception 
     */
    public void deleteSongFromPlaylist(Media media) throws Exception {
        bll.deleteSongFromPlaylist(selectedPlaylist, media);
        songsOnSelectedPlaylist.remove(media);
        // todo: update numSongs + total time
    }

    /**
     * Change order of a song on the currently selected playlist
     * 
     * @param i is the index of the first selected song in the listview
     * @param j is the index of the second selected song in the listview
     * @throws Exception 
     */
    public void swapSongsInPlaylist(int i, int j) throws Exception {
        Collections.swap(songsOnSelectedPlaylist, i, j);
        //bll.swapSongsInPlaylist(selectedPlaylist.getValue().getId(), songsOnPlaylist.get(i).getId(), songsOnPlaylist.get(j).getId()); // todo
    }
    
    // todo
    public void updateSongsTotalOnSelectedPlaylist() {
    }

    // todo
    public void updateTimeTotalOnSelectedPlaylist() {
    }

    /**
     * Gets a reference to the observable list containing all songs
     * 
     * @return list of all songs
     */
    public ObservableList<Media> getAllSongs() {
        return allSongs;
    }
    
    /**
     * Creates a new song and adds it to the storage.
     * The returned object is added the all songs tableview
     * 
     * @param source is the path + filename of the source song
     * @param artist is the song artist
     * @param title is the song title
     * @param time is the song length in seconds 
     * @param year is is the song year
     * @param category is the song category id
     * @throws Exception 
     */
    public void createSong(String source, String artist, String title, int time, int year, int category) throws Exception {
        Media media = bll.createMedia(source, artist, title, time, year, category);
        allSongs.add(media);
    }
    
    /**
     * Update song by in the storage
     * The view is already updated (observable)
     * 
     * @param media is the updated song object 
     */
    public void updateSong(Media media) {
        bll.updateSong(media);
    }
    
    /**
     * Remove a song from the currently selected playlist
     * the song is also removed from the view
     * 
     * @param media is the song object
     * @throws Exception 
     */
    public void deleteSong(Media media) throws Exception {
        allSongs.remove(media);
        bll.deleteSong(media);
    }
    
    /**
     * Gets a reference to the list containing all categories as strings
     * @return 
     */
    public List<String> getAllCategories()
    {
        return allCategories;
    }
    
    /**
     * Converts category id to its text representation
     * 
     * @param id is the category id
     * @return category text as a String
     * @throws Exception 
     */
    public String categoryIdToName(int id) throws Exception {
        return bll.categoryIdToName(id);
    }
    
     /**
     * Converts category name to its id representation
     * 
     * @param name  is the category text name
     * @return category id as an int
     * @throws Exception 
     */
    public int categoryNameToId(String name) throws Exception {
        return bll.categoryNameToId(name);
    }
    
    /**
     * Converts seconds to a textual time representation
     * 
     * @param sec is the amount of seconds
     * @return the formatted text (like hh:mm:ss)
     */
    public String secToTime(int sec) {
        return bll.secToTime(sec);
    }
    
    /**
     * Converts textual time representation into seconds
     * 
     * @param time the textual representation
     * @return amount of seconds 
     */
    public int timeToSec(String time) {
        return bll.timeToSec(time);
    }    
    
    /**
     * Gets a list with the search result matching the search query
     * 
     * @param input search query
     * @return a list of matching songs
     * @throws Exception 
     */
    public ObservableList<Media> getSearchResult(String input) throws Exception
    {
        List<Media> filter = bll.search(input);
        ObservableList<Media> output = FXCollections.observableList(filter);
        
        return output;
    }
}