/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Media;
import mytunes.be.Playlist;

/**
 *
 * @author zilot
 */
public class PlaylistDBDAO
{

    private DatabaseConnector dbCon;

    public PlaylistDBDAO() throws IOException
    { 
        dbCon = new DatabaseConnector();
 
    }

                 
    //opretter en ny playlist 
    public Playlist createPlaylist(String name) throws Exception
    {
        Connection con = dbCon.getConnection();

        String sql = "INSERT INTO playlist VALUES (?);";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 1)
        {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                int id = rs.getInt(1);
                Playlist playlist = new Playlist(id, name);
                return playlist;
            }

        }
        return null;
    }

    //retunere alle playlister 
    public ObservableList<Playlist> getAllPlaylist() throws Exception
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM playlist;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            // new empty list to hold all playlists
            ObservableList<Playlist> allPlaylist = FXCollections.observableArrayList();
            
            // get all playlists from database and put them in the list of playlists
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                // save the playlist data in a new playlist object
                Playlist playlist = new Playlist(id, name);
                
                // get songs in the playlist and put them in the playlists songlist
                for (Media media : getPlaylist(playlist)) {
                    playlist.getMedias().add(media);
                }
                
                // add the complete playlist to the list of playlists
                allPlaylist.add(playlist);
            }
            
            return allPlaylist;
        }
    }

    // retunere én playliste med dens sange
    public List<Media> getPlaylist(Playlist playlist) throws Exception
    {
        Connection con = dbCon.getConnection();
         int p_id = playlist.getId();

        String sql = "select s.id, s.source , s.artist, s.title , s.time, s.year, s.category  from songs_2 s, playlist_content_table pc where s.id  = pc.song_id  and pc.playlist_id ="+ p_id +";";
        
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        ArrayList<Media> playlistWithSongs = new ArrayList<>();
        while (rs.next())
        {
            int id = rs.getInt("id");
            String source = rs.getString("source");
            String artist = rs.getString("artist");
            String title = rs.getString("title");
            int time = rs.getInt("time");
            int year = rs.getInt("year");
            int category = rs.getInt("category");

            Media med = new Media(id, source, artist, title, time, year, category);

            playlistWithSongs.add(med);

        }
        return playlistWithSongs;

    }

    //sletter en playliste 
    public void deletePlaylist(Playlist playlist) throws Exception
    {
        Connection con = dbCon.getConnection();
        int pId = playlist.getId();

        String sqlPt = "DELETE FROM playlist_content_table where playlist_id = (?); ";
        String sqlP = "DELETE FORM playlist where id=(?);";

        PreparedStatement ps1 = con.prepareStatement(sqlPt);
        PreparedStatement ps2 = con.prepareStatement(sqlP);

        ps1.setInt(1, pId);
        ps2.setInt(1, pId);
        ps1.executeUpdate();
        ps2.executeUpdate();
        ps1.close();
        ps2.close();

    }

    //tilføjer en sang til en playliste 
    public void addToPlaylist(Playlist playlist, Media media) throws Exception
    {

        Connection con = dbCon.getConnection();
        int pId = playlist.getId();
        int meId = media.getId();

        String sql = "insert into playlist_content_table (playlist_id , song_id)values ((?), (?)); ";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, pId);
        pst.setInt(2, meId);

        pst.executeUpdate();
        pst.close();

    }

    //fjerner en sang fra en playliste 
    public void removeFromPlaylist(Playlist playlist, Media media) throws Exception
    {
        Connection con = dbCon.getConnection();
        int pId = playlist.getId();
        int meId = media.getId();

        String sql = "Delete From playlist_content_table where playlist_id = (?) and song_id=(?); ";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, pId);
        pst.setInt(2, meId);

        pst.executeUpdate();
        pst.close();

    }

    //opdatere en playliste med navn 
    public void updatePlaylist(Playlist playlist, String name) throws Exception
    {
        Connection con = dbCon.getConnection();
        int pId = playlist.getId();

        String sql = "Update playlist set name = (?) where playlist_id = (?);";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setInt(2, pId);

        pst.executeUpdate();
        pst.close();

    }

}
