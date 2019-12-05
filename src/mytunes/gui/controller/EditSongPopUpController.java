/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mytunes.be.Media;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class EditSongPopUpController implements Initializable
{
    
    @FXML
    private Label titleLabel;
    @FXML
    private TextField editTitle;
    @FXML
    private Label artistLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField editArtist;
    @FXML
    private TextField editTime;
    @FXML
    private Button editFileButton;
    @FXML
    private ComboBox comboCategory;
    @FXML
    private Label categoryLabel;
    @FXML
    private TextField editYear;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }    
    
    public void transferMedia(Media media)
    {
        editTitle.setText(media.getTitle());
        editArtist.setText(media.getArtist());
        editYear.setText(media.getYear() + "");
        time(media.getTime());
        categories(media);
        
    }
    
    private void time(int time)
    {
        int minutes = time/60;
        int hours = time/60/60;
        int seconds = time-hours*60*60-minutes*60;
        
        
        
        if(hours == 0)
        {
            editTime.setText(minutes + ":" + seconds);
        }
        else
        {
            editTime.setText(hours + ":" + minutes + ":" + seconds);
        }
        
    }
    
    
    
    
    private void categories(Media media)
    {
        comboCategory.setItems(FXCollections.observableArrayList(
                "Blues", 
                "Classic Rock", 
                "Country", 
                "Dance", 
                "Disco", 
                "Funk", 
                "Grunge",
                "Hip-Hop",
                "Jazz",
                "Metal",
                "New Age",
                "Oldies",
                "Other",
                "Pop",
                "Rhythem and Blues",
                "Rap",
                "Reggae",
                "Rock",
                "Techno",
                "Industrial",
                "Alternative")
        );
        
        comboCategory.getSelectionModel().select(media.getCategory());

    }
    
    
    

    @FXML
    private void handleEditFile(ActionEvent event)
    {
        
    }

    @FXML
    private void handleCategory(ActionEvent event)
    {
    }
    
}
