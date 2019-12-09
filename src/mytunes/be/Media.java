/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Troels Klein
 */
public class Media {

    private final int id;
    private StringProperty source;
    private StringProperty artist;
    private StringProperty title;
    private IntegerProperty time;
    private IntegerProperty year;
    private IntegerProperty category;
    private IntegerProperty numPlays;

    public Media(int id, String source) {
        this.id = id;
        this.source = new SimpleStringProperty(source);
    }
    
    public Media(int id, String source, String artist, String title, int time, int year, int category) {
        this.id = id;
        this.source = new SimpleStringProperty(source);
        this.artist = new SimpleStringProperty(artist);
        this.title = new SimpleStringProperty(title);
        this.time = new SimpleIntegerProperty(time);
        this.year = new SimpleIntegerProperty(year);
        this.category = new SimpleIntegerProperty(category);
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source.get();
    }

    public void setSource(String source) {
        this.source.set(source);
    }
    
    public StringProperty sourceProperty() {
        return source;
    }
    
    public String getArtist() {
        return artist.get();
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public StringProperty artistProperty() {
        return artist;
    }
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }    
    
    public int getTime() {
        return time.get();
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public ObservableValue<Integer> timeProperty() {
        return time.asObject();
    }
    
    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }
    
    public ObservableValue<Integer> yearProperty() {
        return year.asObject();
    }

    public int getCategory() {
        return category.get();
    }

    public void setCategory(int category) {
        this.category.set(category);
    }
    
    public ObservableValue<Integer> categoryProperty() {
        return category.asObject();
    }
    
    public int getNumPlays() {
        return numPlays.get();
    }

    public void setNumPlays(int numPlays) {
        this.numPlays.set(numPlays);
    }
    
    public IntegerProperty numPlaysProperty() {
        return numPlays;
    }

    // not using this yet, so remember to remove later if still unused
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
