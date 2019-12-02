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
        Media m1 = new Media(1, "1.mp3", "Michael Jackson", "Fly Away", 1983, 13, 3*60+34);
        Media m2 = new Media(2, "2.mp3", "Michael Jackson", "Goon Too Soon", 1991, 13, 4*60+01);
        Media m3 = new Media(3, "3.mp3", "Springsteen", "Fire", 1987, 17, 2*60+42);
        Media m4 = new Media(4, "4.mp3", "Adele", "Hello", 2015, 13, 6*60+31);
        Media m5 = new Media(5, "5.mp3", "SaveUs", "Levitate Me", 2015, 18, 4*60+54);
        Media m6 = new Media(6, "6.mp3", "Momford", "Wilder Mind", 2015, 17, 5*60+12);
        Media m7 = new Media(7, "7.mp3", "Snow Patrol", "Chasing Cars", 2016, 13, 3*60+34);
        Media m8 = new Media(8, "8.mp3", "Wiz Khalifa", "The Thrill", 2018, 13, 3*60+56);
        Media m9 = new Media(9, "9.mp3", "Seinabo Say", "Hard Time", 2014, 13, 4*60+31);
        Media m10 = new Media(10, "10.mp3", "Phil Collins", "In The Air", 1981, 13, 5*60+23);
        Media m11 = new Media(11, "11.mp3", "Springsteen", "Cover Me", 1984, 17, 4*60+8);
        Media m12 = new Media(12, "12.mp3", "Rolling Stones", "Under My Thumb", 1966, 17, 6*60+15);
        Media m13 = new Media(13, "13.mp3", "Guetta", "Titanium", 2011, 18, 3*60+32);

        // New list of media
        ArrayList<Media> medias = new ArrayList();

        // Add media to list
        medias.add(m1);
        medias.add(m2);
        medias.add(m3);
        medias.add(m4);
        medias.add(m5);

        return medias;
    }
}
