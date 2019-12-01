/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Category;
import mytunes.be.Media;

/**
 *
 * @author Troels Klein
 */
public class MockManager {

    public List<Media> getAllMedias() {
        
        // Create mock media
        Media m1 = new Media(1, "Michael Jackson - Fly Away.mp3", "Michael Jackson", "Fly Away", 1983, 13, 3*60+34);
        Media m2 = new Media(2, "Michael Jackson - Goon To Soon.mp3", "Michael Jackson", "Goon Too Soon", 1991, 13, 4*60+01);
        Media m3 = new Media(3, "Springsteen - Fire.mp3", "Springsteen", "Fire", 1987, 17, 2*60+42);
        Media m4 = new Media(4, "Adele - Hello.mp3", "Adele", "Hello", 2015, 13, 6*60+31);
        Media m5 = new Media(5, "SaveUs - Levitate Me.mp3", "Adele", "Hello", 2015, 18, 4*60+54);

        // New list of media
        ArrayList<Media> medias = new ArrayList();

        // Add mock media to list
        medias.add(m1);
        medias.add(m2);
        medias.add(m3);
        medias.add(m4);
        medias.add(m5);

        return medias;
    }
}
