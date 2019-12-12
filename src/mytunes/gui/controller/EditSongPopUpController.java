/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.be.Media;
import mytunes.bll.util.ConvertTime;

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

    private Media media;
    
    private ConvertTime CT = new ConvertTime();

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
        editTime.setText(CT.secToTime(media.getTime()));
        categories(media);
        this.media = media;
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
        this.media.setTitle(editTitle.getText());
        this.media.setArtist(editArtist.getText());
        this.media.setYear(Integer.parseInt(editYear.getText()));
        this.media.setTime(CT.timeToSec(editTime.getText()));
        Stage stage = (Stage) editFileButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void handleCategory(ActionEvent event)
    {
    }
    
   

}
