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
     
    // set a reference to currently selected playlist and the playlists associated songlist
    public void setSelectedPlaylist(Playlist playlist) {
        selectedPlaylist = playlist;
        songsOnSelectedPlaylist = playlist.getMedias();
    }    
   
    // returns an observable list of all playlists
    public ObservableList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }
    
    public void createPlaylist(String name) throws Exception {
        Playlist playlist = bll.createPlaylist(name);
        allPlaylists.add(playlist);
    }
    
    // sends to bll the already edited playlist
    public void updatePlaylist(Playlist playlist) throws Exception {
        bll.updatePlaylist(playlist);
    }
    
    public void deletePlaylist() throws Exception {
        bll.deletePlaylist(selectedPlaylist); // a try with catch would be a good idea here
        allPlaylists.remove(selectedPlaylist);
        songsOnSelectedPlaylist.clear();
        selectedPlaylist = null;
        songsOnSelectedPlaylist = null;
    }
          
    // returns a list of the songs in the currently selected playlist
    public ObservableList<Media> getSongsOnPlaylist() {
        return songsOnSelectedPlaylist;
    }
    
    // add song to the currently selected playlist
    public void addSongToPlaylist(Media media) throws Exception {
        bll.addSongToPlaylist(selectedPlaylist, media);
        songsOnSelectedPlaylist.add(media);
        // todo: update numSongs + time
    }

    // remove song from the currently selected playlist
    public void deleteSongFromPlaylist(Media media) throws Exception {
        bll.deleteSongFromPlaylist(selectedPlaylist, media);
        songsOnSelectedPlaylist.remove(media);
        // todo: update numSongs + time
    }

    // change order of a song on the currently selected playlist
    public void swapSongsInPlaylist(int i, int j) throws Exception {
        Collections.swap(songsOnSelectedPlaylist, i, j);
        //bll.swapSongsInPlaylist(selectedPlaylist.getValue().getId(), songsOnPlaylist.get(i).getId(), songsOnPlaylist.get(j).getId()); // todo
    }
    
    // todo - figure out how to implement these in a smartish way
    public void updateSongsTotalOnSelectedPlaylist() {
    }

    // todo
    public void updateTimeTotalOnSelectedPlaylist() {
    }

    // return the observable list containing all songs
    public ObservableList<Media> getAllSongs() {
        return allSongs;
    }
    
    public void createSong(String source, String artist, String title, int time, int year, int category) throws Exception {
        Media media = bll.createMedia(source, artist, title, time, year, category);
        allSongs.add(media);
    }
    
    // pass updated object to bll, view is already updated (observable)
    public void updateSong(Media media) {
        bll.updateSong(media);
    }
    
    public void deleteSong(Media media) throws Exception {
        allSongs.remove(media);
        bll.deleteSong(media);
    }
    

    public List<String> getAllCategories()
    {
        return allCategories;
    }
    
    public String categoryIdToName(int id) throws Exception {
        return bll.categoryIdToName(id);
    }
    
    public int categoryNameToId(String name) throws Exception {
        return bll.categoryNameToId(name);
    }
    
    public String secToTime(int sec) {
        return bll.secToTime(sec);
    }
    
    public int timeToSec(String time) {
        return bll.timeToSec(time);
    }    
    
    public ObservableList<Media> getSearchResult(String input) throws Exception
    {
        List<Media> filter = bll.search(input);
        ObservableList<Media> output = FXCollections.observableList(filter);
        
        return output;
    }
}