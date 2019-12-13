/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll.util;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Media;

/**
 *
 * @author Troels Klein
 */
public class SearchSongs {

    public static List<Media> search(List<Media> searchBase, String query) {
        List<Media> output = new ArrayList<>();

        for (Media song : searchBase) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())
                    || song.getArtist().toLowerCase().contains(query.toLowerCase())) {
                output.add(song);
            }
        }

        return output;
    }
}
