/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import javafx.util.Duration;

/**
 *
 * @author Troels Klein
 */
public class Media {

    private final int id;
    private String source;
    private String artist;
    private String title;
    private int time;
    private int year;
    private int category;
    private int plays;

    public Media(int id, String source) {
        this.id = id;
        this.source = source;
    }
    
    public Media(int id, String source, String artist, String title, int time, int year, int category) {
        this.id = id;
        this.source = source;
        this.artist = artist;
        this.title = title;
        this.time = time;
        this.year = year;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Media other = (Media) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
