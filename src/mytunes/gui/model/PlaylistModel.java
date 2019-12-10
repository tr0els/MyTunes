/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.bll.BLLManager;

/**
 *
 * @author Troels Klein
 */
public class PlaylistModel {

    private BLLManager bll;
    private ObservableList<Playlist> playlists;

    public PlaylistModel() throws Exception 
    {
        bll = new BLLManager();
        playlists = FXCollections.observableArrayList();
        playlists.addAll(bll.getAllPlaylists());
    }

    public ObservableList<Playlist> getAllPlaylists() {
        return playlists;
    }

    public void createPlaylist(String name) {
        Playlist playlist = bll.createPlaylist(name);
        playlists.add(playlist);
    }

    public static void main(String[] args) throws Exception 
    {
        BLLManager bll = new BLLManager();

        List<Playlist> playlists = bll.getAllPlaylists();

        for (Playlist playlist : playlists) {
            System.out.println("playlist name is: " + playlist.getName());
        }
    }
}
