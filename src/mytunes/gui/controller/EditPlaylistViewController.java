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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.gui.model.DataModel;
import mytunes.gui.model.PlaylistModel;

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
    
    private DataModel dataModel;
    
    private Playlist pList;
    
    PlaylistModel playlistModel; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    public void transferPlaylist(Playlist pl)
    {
        titleTextField.setText(pl.getName());
        pList = pl;
    }
    
    @FXML
    private void savePlaylist(ActionEvent event) throws Exception 
    {
        
        handelEditPlaylist(); 
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
    public void handelEditPlaylist () throws Exception 
    {
        dataModel.updatePlaylist(pList,titleTextField.getText());
    }
   
    
}
