/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Media;
import mytunes.be.Playlist;
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

    public void updateMedia(Media media)
    {
        
        try
        {
          mediaDB.updateMedia(media);
        } catch (Exception ex)
        {
            Logger.getLogger(BLLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateSong(Media media) throws Exception {
        mediaDB.updateMedia(media);
    }
    
    public void deleteSong(Media media) throws Exception {
        mediaDB.deleteMedia(media);
    }

    // search in allSongs list
    public List<Media> searchMedias(String query)
    {
        return null;
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

    public List<Media> search(String query) throws Exception
    {

        List<Media> searchBase = mediaDB.getAllMedias();
        List<Media> output = new ArrayList<>();

        for (Media song : searchBase)
        {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())
                    || song.getArtist().toLowerCase().contains(query.toLowerCase()))
            {
                output.add(song);
            }
        }

        return output;
    }
}
