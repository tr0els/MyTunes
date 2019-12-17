/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import mytunes.gui.model.DataModel;

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
    private DataModel dataModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    /**
     * Gets information about the chosen song from the main view. 
     * Shows the songs information in the pop up. 
     * @param media
     * @param dataModel
     * @throws Exception 
     */
    public void transferMedia(Media media, DataModel dataModel) throws Exception
    {
        editTitle.setText(media.getTitle());
        editArtist.setText(media.getArtist());
        editYear.setText(media.getYear() + "");
        editTime.setText(CT.secToTime(media.getTime()));
        
        this.media = media;
        this.dataModel = dataModel;
        
        categories(media);    
    }

    /**
     * Fills the comboBox with the categories from the database. 
     * @param media
     * @throws Exception 
     */
    private void categories(Media media) throws Exception
    {
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll(dataModel.getAllCategories());
        
        comboCategory.setItems(categories);
        comboCategory.getSelectionModel().select(media.getCategory()); // use when category is int
        //comboCategory.getSelectionModel().select(dataModel.categoryNameToId(media.getCategory())); // use when category is string
    }

    /**
     * Gets the edits made to the song and saves them.
     * @param event 
     */
    @FXML
    private void handleEditFile(ActionEvent event)
    {
        media.setTitle(editTitle.getText());
        media.setArtist(editArtist.getText());
        media.setYear(Integer.parseInt(editYear.getText()));
        media.setTime(CT.timeToSec(editTime.getText()));
        media.setCategory(comboCategory.getSelectionModel().getSelectedIndex());

        dataModel.updateSong(media);
        
        Stage stage = (Stage) editFileButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCategory(ActionEvent event)
    {
    }
}
