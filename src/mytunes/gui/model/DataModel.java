/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.bll.BLLManager;
import mytunes.dal.MockManager;

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
    private Playlist selectedPlaylist; // reference to currently selected playlist
    private ObservableList<Media> selectedSonglist; // maybe for mediaplayer? reference to selected list of songs (songsInPlaylist or allSongs) --> should be set whenever songsInplaylist or allSongs are selected??        
    
    public DataModel() throws Exception {
        bll = new BLLManager();
        
        allPlaylists = FXCollections.observableArrayList();
        songsOnSelectedPlaylist = null; // or null and make a new obslist everytime its set? this is only given an already existing obslist so no need for a new right?
        allSongs = FXCollections.observableArrayList();
        selectedPlaylist = null;

        // populate playlist table and all songs table
        allPlaylists.addAll(bll.getAllPlaylists());
        allSongs.addAll(bll.getAllMedias());
    }
    
    /**
     * methods currently selected playlist and songlist
     */
   
    // sets references to the currently selected playlist and also the associated list of songs
    public void setSelectedPlaylist(Playlist playlist) {
        songsOnSelectedPlaylist = playlist.getMedias();
        selectedPlaylist = playlist;
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
    public void updatePlaylist(Playlist playlist) throws Exception {
        //bll.editPlaylist(playlist);
    }
    
    public void deletePlaylist() throws Exception {
        bll.deletePlaylist(selectedPlaylist); // a try with catch would be a good idea here
        allPlaylists.remove(selectedPlaylist);
        songsOnSelectedPlaylist.clear();
        selectedPlaylist = null;
        songsOnSelectedPlaylist = null;
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
    * methods for songs on a playlist
    */
    
    // returns a list of the songs in the currently selected playlist
    public ObservableList<Media> getSongsOnPlaylist() {
        return songsOnSelectedPlaylist;
    }
    
    // adds song to the currently selected playlist
    public void addSongToPlaylist(Media media) throws Exception {
        bll.addSongToPlaylist(selectedPlaylist, media);
        songsOnSelectedPlaylist.add(media);
        //selectedPlaylist.numSongsProperty().getValue() = "323".asObject();
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
    
    public ObservableList<Media> search(String query)
    {
        MockManager mM = new MockManager();
        List<Media> searchBase = mM.getAllMedias();
        List<Media> filter = new ArrayList<>();

        for (Media song : searchBase)
        {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())
                    || song.getArtist().toLowerCase().contains(query.toLowerCase()))
            {
                filter.add(song);
            }
        }

        ObservableList<Media> result = FXCollections.observableList(filter);

        return result;
    }
}
