/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import mytunes.be.Media;
import java.sql.ResultSet;
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
    
    public Media createSong(String source, String artist, String title, int time, int year, int category) throws Exception
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO MyTunes_2 VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,source);
            ps.setString(2, artist);
            ps.setString(3, title);
            ps.setInt(4, time);
            ps.setInt(5, year);
            ps.setInt(6, category);
           int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) // <-- Remember to do this!!
                {
                    int id = rs.getInt(1);
                    Media med = new Media(id, source, artist, title, time, year, category);
                    return med;
                }
        
            }
          
        
            
            
        } 
              
     return null;
    }
        
}
