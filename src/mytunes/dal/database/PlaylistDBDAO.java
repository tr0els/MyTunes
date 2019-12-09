/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Media;
import mytunes.be.Playlist;

/**
 *
 * @author zilot
 */
public class PlaylistDBDAO
{
    
     private DatabaseConnector dbCon;
    
     public PlaylistDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }
             
     //opretter en ny playlist 
     public Playlist createPlaylist(String name) throws Exception 
     {
            Connection con = dbCon.getConnection();
        
            String sql = "INSERT INTO playlist VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,name);
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
     public List<Playlist> getAllPlaylist() throws Exception 
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM playlist;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Playlist> allPlaylist = new ArrayList<>();
            while (rs.next())
            {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            
            Playlist playlist = new Playlist( id,  name);
            allPlaylist.add(playlist);
            
            }
            return allPlaylist;
        
        }
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
         
         PreparedStatement pst =  con.prepareStatement(sql);
         
        pst.setInt(1, pId);
        pst.setInt(2, meId);
     
        pst.executeUpdate();
        pst.close();
     
     }
     
     //opdatere en playliste med navn 
     public void updatePlaylist (Playlist playlist, String name) throws Exception 
     {
         Connection con = dbCon.getConnection();
         int pId = playlist.getId();
         
         
         String sql = "Update playlist set name = (?) where playlist_id = (?);";
         
        PreparedStatement pst =  con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setInt(2, pId);
        
        pst.executeUpdate();
        pst.close();
        
     
     }
     
     
     
     
     
}
