/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;

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
    private TextField categoryTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextArea fileTextField;
    @FXML
    private Button selectFileButton;
    @FXML
    private Button createButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
        
    }

    @FXML
    private void createMedia(ActionEvent event)
    {
        
        
        
    }
    
}
