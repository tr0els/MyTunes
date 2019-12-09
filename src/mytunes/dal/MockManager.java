/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Media;
import mytunes.be.Playlist;

/**
 *
 * @author Troels Klein
 */
public class MockManager {

    public List<Media> getAllMedias() {

        // create mock media
        Media m1 = new Media(1, "1.mp3", "Michael Jackson", "Fly Away", 3 * 60 + 34, 1983, 13);
        Media m2 = new Media(2, "2.mp3", "Michael Jackson", "Goon Too Soon", 4 * 60 + 01, 1991, 13);
        Media m3 = new Media(3, "3.mp3", "Springsteen", "Fire", 2 * 60 + 42, 1987, 17);
        Media m4 = new Media(4, "4.mp3", "Adele", "Hello", 6 * 60 + 31, 2015, 13);
        Media m5 = new Media(5, "5.mp3", "SaveUs", "Levitate Me", 4 * 60 + 54, 2015, 18);
        Media m6 = new Media(6, "6.mp3", "Momford", "Wilder Mind", 5 * 60 + 12, 2015, 17);
        Media m7 = new Media(7, "7.mp3", "Snow Patrol", "Chasing Cars", 3 * 60 + 34, 2016, 13);
        Media m8 = new Media(8, "8.mp3", "Wiz Khalifa", "The Thrill", 3 * 60 + 56, 2018, 13);
        Media m9 = new Media(9, "9.mp3", "Seinabo Say", "Hard Time", 4 * 60 + 31, 2014, 13);
        Media m10 = new Media(10, "10.mp3", "Phil Collins", "In The Air", 5 * 60 + 23, 1981, 13);
        Media m11 = new Media(11, "11.mp3", "Springsteen", "Cover Me", 4 * 60 + 8, 1984, 17);
        Media m12 = new Media(12, "12.mp3", "Rolling Stones", "Under My Thumb", 6 * 60 + 15, 1966, 17);
        Media m13 = new Media(13, "13.mp3", "Guetta", "Titanium", 3 * 60 + 32, 2011, 18);

        // new list of media
        ArrayList<Media> medias = new ArrayList();

        // add media to list
        medias.add(m1);
        medias.add(m2);
        medias.add(m3);
        medias.add(m4);
        medias.add(m5);
        medias.add(m6);
        medias.add(m7);
        medias.add(m8);
        medias.add(m9);
        medias.add(m10);
        medias.add(m11);
        medias.add(m12);
        medias.add(m13);

        return medias;
    }

    public List<Playlist> getAllPlaylists() {
        // new playlist
        Playlist party = new Playlist(1, "Party");

        // add medias to playlist
        party.getMedias().add(getAllMedias().get(0));
        party.getMedias().add(getAllMedias().get(1));
        party.getMedias().add(getAllMedias().get(2));
        
        // new playlist
        Playlist party2 = new Playlist(2, "Party2");

        // add medias to playlist
        party2.getMedias().add(getAllMedias().get(3));
        party2.getMedias().add(getAllMedias().get(4));
        party2.getMedias().add(getAllMedias().get(5));
        party2.getMedias().add(getAllMedias().get(6));
        party2.getMedias().add(getAllMedias().get(7));
        
        // new list of playlists
        List<Playlist> playlists = new ArrayList();
        
        // add playlists to list
        playlists.add(party);
        playlists.add(party2);
        
        return playlists;
    }

    /*
    public List<Category> getAllCategories() {
        
        List<Category> categories = new ArrayList();
    
        // categories based on: https://en.wikipedia.org/wiki/List_of_ID3v1_Genres
        categories.add(new Category(0, "Blues"));
        categories.add(new Category(1, "Classic Rock"));
        categories.add(new Category(2, "Country"));
        categories.add(new Category(3, "Dance"));
        categories.add(new Category(4, "Disco"));
        categories.add(new Category(5, "Funk"));
        categories.add(new Category(6, "Grunge"));
        categories.add(new Category(7, "Hip-Hop"));
        categories.add(new Category(8, "Jazz"));
        categories.add(new Category(9, "Metal"));
        categories.add(new Category(10, "New Age"));
        categories.add(new Category(11, "Oldies"));
        categories.add(new Category(12, "Other"));
        categories.add(new Category(13, "Pop"));
        categories.add(new Category(14, "Rhythm and Blues"));
        categories.add(new Category(15, "Rap"));
        categories.add(new Category(16, "Reggae"));
        categories.add(new Category(17, "Rock"));
        categories.add(new Category(18, "Techno"));
        categories.add(new Category(19, "Industrial"));
        categories.add(new Category(20, "Alternative"));
        
        return categories;
    }
    */

}