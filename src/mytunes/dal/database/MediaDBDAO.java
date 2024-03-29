/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Media;
/**
 *
 * @author zilot
 */
public class MediaDBDAO
{
    private DatabaseConnector dbCon;
    
    public MediaDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }
        
    /* Creates a song to be committet in the database
     * @param source 
     * @param artist
     * @param title
     * @param time
     * @param year
     * @param category
     * @param numplays
     * @return Media
     */
    public Media createMedia(String source, String artist, String title, int time, int year, int category, int numplays) throws Exception
    {
         Connection con = dbCon.getConnection();
        
            String sql = "INSERT INTO songs_2 VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, source);
            ps.setString(2, artist);
            ps.setString(3, title);
            ps.setInt(4, time);
            ps.setInt(5, year);
            ps.setInt(6, category);
            ps.setInt(7, numplays);
           int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) 
                {
                    int id = rs.getInt(1);
                    Media med = new Media(id, source, artist, title, time, year, category);
                    return med;
                }
        
            }              
     return null;
    }

    
    // returns a List with all the Medias from the Songs_2 tabel
    public List<Media> getAllMedias() throws Exception 
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM songs_2;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Media> allMedia = new ArrayList<>();
            while (rs.next())
            {
            int id = rs.getInt("id");
            String source = rs.getString("source");
            String artist = rs.getString("artist");
            String title = rs.getString("title");
            int time = rs.getInt("time");
            int year = rs.getInt("year");
            int category = rs.getInt("category");
         
            Media med = new Media( id,  source,  artist,  title,  time,  year,  category);
            allMedia.add(med);
            
            }
            return allMedia;
        }
    }
     
    //deletes a specific media form the table songs_2
    //@param Media 
    public void deleteMedia(Media media) throws Exception
    {
        Connection con = dbCon.getConnection();
        
        int id = media.getId();
        String sql = "DELETE FROM songs_2 WHERE id=?;";
        String sql2 = "delete from playlist_content_table where song_id =(?)";
        PreparedStatement ps = con.prepareStatement(sql);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps.setInt(1, id);
        ps2.setInt(1, id);
        
        ps2.executeUpdate();
        ps.executeUpdate();
      
    }
    
    //updates a media with new data to be saved to the database
    //@param Media
    public void updateMedia(Media media) throws Exception
    {
        Connection con = dbCon.getConnection();
        int id = media.getId();
        String sql = "UPDATE songs_2 set source = ?, artist = ? ,title=?, time=?, year=?,category=? WHERE id="+id+";";
         PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, media.getSource());
            ps.setString(2, media.getArtist());
            ps.setString(3, media.getTitle());
            ps.setInt(4, media.getTime());
            ps.setInt(5, media.getYear());
            ps.setInt(6, media.getCategory());
            //ps.setInt(7, media.getNumPlays());
        
        ps.executeUpdate();
        ps.close();
    
    }
    
    
}
    
        
        
        
        
        
        
        
        
        
        
    








