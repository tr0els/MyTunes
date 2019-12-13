/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll.util;

import java.util.List;
import mytunes.bll.BLLManager;

/**
 *
 * @author Troels Klein
 */
public class ConvertCategory {

    private final BLLManager bll; 
          
    public ConvertCategory() throws Exception
    {
         bll = new BLLManager();
    }

    public String categoryIdToName(int id) throws Exception {
        List<String> allCategories = bll.getAllCategories();
        
        return allCategories.get(id);
    }
    
    public int categoryNameToId(String name) throws Exception {
        List<String> allCategories = bll.getAllCategories();
        
        return allCategories.indexOf(name);
    }
}
