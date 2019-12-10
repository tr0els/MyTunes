/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mytunes.bll.util.ConvertTime;
import mytunes.gui.model.MediaModel;

/**
 * FXML Controller class
 *
 * @author Mikkel H
 */
public class SongPopupController implements Initializable
{

    @FXML
    private Label titleLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private Label artistLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label fileLabel;
    @FXML
    private TextField artistTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextArea fileTextField;
    @FXML
    private Button selectFileButton;
    @FXML
    private Button createButton;
    @FXML
    private TextField yearTextField;
    @FXML
    private ComboBox comboCategory;

    private Media mainMedia;
    private String title;
    private String artist;
    private int category = 21;
    private int time = 30;
    private int year = 30;
    @FXML
    private Label noMetaData;
    
    ConvertTime CT = new ConvertTime();
    
    MediaModel mediaModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        categories();
    }

    @FXML
    private void selectFile(ActionEvent event)
    {

        String path;

        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3"));
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        path = path.replace("\\", "/");
        fileTextField.setText(path);

        Media media = new Media(new File(path).toURI().toString());
        getMetaData(media);
    }

    public void getMetaData(Media med)
    {
        med.getMetadata().addListener((MapChangeListener<String, Object>) change ->
        {
            if (change.wasAdded())
            {
                handleMetadata(change.getKey(), change.getValueAdded());
            }

        });
    }

    public void handleMetadata(String key, Object value)
    {
        if (key.equals("title"))
        {
            title = value.toString();
        }
        if (key.equals("artist"))
        {
            artist = value.toString();
        }
        if (key.equals("category"))
        {
            category = Integer.parseInt(value.toString());
        }
        if (key.equals("time"))
        {
            time = Integer.parseInt(value.toString());
        }
        if (key.equals("year"))
        {
            year = Integer.parseInt(value.toString());
        }

    }

    @FXML
    private void createMedia(ActionEvent event) throws Exception
    {
        handleCreateSong();
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleInsertMetaData(ActionEvent event)
    {
        if (title != null)
        {
            titleTextField.setText(title);
        }

        if (artist != null)
        {
            artistTextField.setText(artist);
        }

        if (category != 21)
        {
            comboCategory.getSelectionModel().select(category);
        }

        if (time != 30)
        {
            CT.secToTime(time);
        }

        if (year != 30)
        {
            yearTextField.setText(year + "");
        }

        if (title == null && artist == null && category == 21 && time == 30 && year == 30)
        {
            noMetaData.setText("No metadata found.");
        }
    }

    private void categories()
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

    }

    public void handleCreateSong() throws Exception 
    {
        mediaModel.createMovie(fileTextField.getText(), artistTextField.getText(), titleTextField.getText(), 
                CT.timeToSec(timeTextField.getText()), Integer.parseInt(yearTextField.getText()), 
                comboCategory.getItems().indexOf(comboCategory.getSelectionModel().getSelectedItem()));
                
    }

    public void transfer(MediaModel model)
    {
        mediaModel = model;
    }

}   

