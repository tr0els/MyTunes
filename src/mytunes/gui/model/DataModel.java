/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.Collections;
import javafx.beans.property.SimpleObjectProperty;
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

    // observable lists to hold data
    private ObservableList<Playlist> allPlaylists; // list of playlist
    private ObservableList<Media> songsOnSelectedPlaylist; // list of songs on currently selected playlist
    private ObservableList<Media> allSongs; // list of all songs

    // keep track of currently selected playlist and songlist (unused so far)
    private SimpleObjectProperty<Playlist> selectedPlaylist; // reference to currently selected playlist
    private ObservableList<Media> selectedSonglist; // maybe for mediaplayer? reference to selected list of songs (songsInPlaylist or allSongs) --> should be set whenever songsInplaylist or allSongs are selected??        
    
    public DataModel() throws Exception {
        bll = new BLLManager();
        
        allPlaylists = FXCollections.observableArrayList();
        songsOnSelectedPlaylist = null; // or null and make a new obslist everytime its set? this is only given an already existing obslist so no need for a new right?
        allSongs = FXCollections.observableArrayList();
        selectedPlaylist = new SimpleObjectProperty<>();

        // populate playlist table and all songs table
        allPlaylists.addAll(bll.getAllPlaylists());
        allSongs.addAll(bll.getAllMedias());
    }
    
    /**
     * methods currently selected playlist and songlist
     */
   
    // updates which playlist is currently selected and puts all songs in the playlist in songsOnPlaylist
    // Playlist or ObservableList<Playlist>??
    public void setSelectedPlaylist(Playlist currentSelectedPlaylist) {
        selectedPlaylist.set(currentSelectedPlaylist); // or use set(newSelectedPlaylist); ??
        songsOnSelectedPlaylist.setAll(currentSelectedPlaylist.getMedias());
    }    
    
    // sets the currently selected songlist (from either a playlist or all songs)
    public void setSelectedSonglist(ObservableList<Media> songlist) {
        selectedSonglist = songlist; // or use set?
    }
    
    /**
    * methods for playlists
    */
    
    // returns an observable list of all playlists
    public ObservableList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }
    
    public void createPlaylist(String name) throws Exception {
        Playlist playlist = bll.createPlaylist(name);
        allPlaylists.add(playlist);
    }
    
    // sends to bll the already edited playlist
    public void updatePlaylist(Playlist playlist, String name) throws Exception {
        bll.editPlaylist(playlist , name);
    }
    
    public void deletePlaylist(Playlist playlist) throws Exception {
        //bll.deletePlaylist(playlist);
    }
    
    // NOT DONE YET
    // figure out how to implement this.
    // one way would be to have a property in each playlist, so it works for each playlist
    // the playlists add / remove song  methods must be used to update the count then
    public void updateSongsTotalOnSelectedPlaylist() {
        //selectedPlaylist.getValue().setNumSongs(songsOnSelectedPlaylist.size());
    }

    // NOT DONE YET
    public void updateTimeTotalOnSelectedPlaylist() {
        //selectedPlaylist.getValue().setNumSongs(songsOnPlaylist.size());
    }
    
    /**
    * methods for songlists
    */
    
    // returns a list of the songs in the currently selected playlist
    public ObservableList<Media> getSongsOnPlaylist() {
        return songsOnSelectedPlaylist;
    }
    
    // sets the songlist of the selected playlist as the content for the listview 
    // (should be called from controller when a playlist is selected, the listview should hopefulle update automatically since it should run on the songsOnSelectedPlaylist)
    public void displaySongsInPlaylist(Playlist playlist) { // or just Media?
        songsOnSelectedPlaylist = playlist.getMedias();
    }
    
    // adds song to the currently selected playlist
    public void addSongToPlaylist(Media media) {
        //bll.addSongToPlaylist(media);
        songsOnSelectedPlaylist.add(media);
        //selectedPlaylist.getValue().setNumSongs(songsOnPlaylist.size());
    }

    // remove song from the currently selected playlist
    public void deleteSongFromPlaylist(Media media) {
        //bll.removeSongFromPlaylist(Media media);
        songsOnSelectedPlaylist.remove(media);
    }

    // change order of a song on the currently selected playlist
    public void swapSongsInPlaylist(int i, int j) {
        Collections.swap(songsOnSelectedPlaylist, i, j);
        //bll.swapSongsInPlaylist(selectedPlaylist.getValue().getId(), songsOnPlaylist.get(i).getId(), songsOnPlaylist.get(j).getId());
    }

    
    


    
    // methods for all songs
    
    // return the observable list containing all songs
    public ObservableList<Media> getAllSongs() {
        return allSongs;
    }
    
    public void createSong(String source, String artist, String title, int time, int year, int category) throws Exception {
        Media media = bll.createMedia(source, artist, title, time, year, category);
        allSongs.add(media); // add to list here or in controller?
    }
    
    // sends to bll the already edited media
    public void editSong(Media media) {
        // list is already updated so just pass object to bll ??
        //bll.editSong(media);
    }
    
    public void deleteSong(Media media) {
        allSongs.remove(media);
        //bll.deleteSong(media);
    }
}
