/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll.util;

import mytunes.bll.BLLManager;

/**
 *
 * @author Troels Klein
 */
public class ConvertCategory {

    private BLLManager bll = new BLLManager();

    public String idToString(int id) {
        return bll.getAllCategories().get(id);
    }
}
