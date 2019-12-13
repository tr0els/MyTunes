/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zilot
 */
public class CategoryDBDAO {

    private final DatabaseConnector dbCon;

    //connector to DB
    public CategoryDBDAO() throws Exception {
        dbCon = new DatabaseConnector();
    }

    //Get a list of all the Categorys 
    public List<String> getAllCategories() throws Exception {
        try (Connection con = dbCon.getConnection()) {
            String sql = "SELECT name FROM CATEGORY;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<String> allCategory = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                allCategory.add(name);
            }

            return allCategory;
        }
    }
}