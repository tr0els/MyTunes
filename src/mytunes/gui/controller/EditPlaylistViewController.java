/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.be.Playlist;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class EditPlaylistViewController implements Initializable
{

    @FXML
    private Label titleLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private Button saveButton;
    
    private Playlist pList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    public void transferMedia(Playlist pl)
    {
        titleTextField.setText(pl.getName());
        pList = pl;
    }
    
    @FXML
    private void savePlaylist(ActionEvent event)
    {
        this.pList.setName(titleTextField.getText());
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
}
