/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll.util;

import java.util.List;

/**
 *
 * @author Troels Klein
 */
public class ConvertCategory { 

    public static String categoryIdToName(List<String> allCategories,  int id) throws Exception {
        return allCategories.get(id);
    }
    
    public static int categoryNameToId(List<String> allCategories, String name) throws Exception {
        return allCategories.indexOf(name);
    }
}
