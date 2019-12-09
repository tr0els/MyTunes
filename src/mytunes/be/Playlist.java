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

/**
 *
 * @author Troels Klein
 */
public class Playlist {

    private int id;
    private SimpleStringProperty name;
    private List<Media> medias = new ArrayList();

    public Playlist(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
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

    public List<Media> getMedias() {
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
        
        // need seperate convert sec to time and back methods.. 
        // if put in a util, that could be imported everywhere needed                        
        // needs more refinement, see assignment screenshot for check on hours / mins
        int totalHours = totalTime / 60 / 60;
        int totalMins = (totalTime - totalHours * 60 * 60) / 60;
        int totalSecs = totalTime - (totalHours * 60 * 60) - (totalMins * 60);
        
        String timeFormatted = String.format("%02d:%02d:%02d", totalHours, totalMins, totalSecs);
        
        return new SimpleStringProperty(timeFormatted);
    } 
}
