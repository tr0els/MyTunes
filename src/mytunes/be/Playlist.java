/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.bll.util.ConvertTime;

/**
 *
 * @author Troels Klein
 */
public class Playlist {

    private int id;
    private SimpleStringProperty name;
    private ObservableList<Media> medias;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.medias = FXCollections.observableArrayList();
    }
 
    public int getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObservableList<Media> getMedias() {
        return medias;
    }

    // getMedias().add can also be called instead
    // not sure how this exactly will work yet.. but this seems like the best aproach
    public void addMedia(Media media) {
        medias.add(media);
    }
    
    public void deleteMedia(Media media) {
        medias.remove(media); // or use int?
    }
    
    public ObservableValue<Integer> numSongsProperty() {
        return new SimpleIntegerProperty(medias.size()).asObject(); // don't ask
    }
    
    public StringProperty totalTimeProperty() {
        int totalTime = 0;
        for (Media media : medias) {
            totalTime += media.getTime();
        }
        
        ConvertTime convertTime = new ConvertTime();
        String formattedTime = convertTime.secToTime(totalTime);
        
        return new SimpleStringProperty(formattedTime);
    } 
}
