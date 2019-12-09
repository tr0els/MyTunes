/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 *
 * @author zilot
 */
public class CategoryDBDAO
{
    
    private DatabaseConnector dbCon;
    
    public CategoryDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }
    

    
     public List<String> getAllCategory() throws Exception 
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT name FROM CATEGORY;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<String> allCategory = new ArrayList<>();
            while (rs.next())
            {
          
            String name = rs.getString("name");
            
           
            allCategory.add(name);
            
            }
         
            return allCategory;
         }
    }
     
}
    
    
    
    
    

