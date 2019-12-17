/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.gui.model.DataModel;

/**
 * FXML Controller class
 *
 * @author Mikkel H
 */
public class PlaylistPopupController implements Initializable
{

    @FXML
    private Label titleLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private Button createButton;
    
    DataModel dataModel; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    //creates a new playlist
    @FXML
    private void createPlaylist(ActionEvent event) throws Exception
    {
        handleCreatePlaylist();
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }
    //handels createplaylsit
    public void handleCreatePlaylist() throws Exception 
    {   
          dataModel.createPlaylist(titleTextField.getText());
    }
    //transferre datamodel from MTcrontroller to this. 
    public void transfer(DataModel model)
    {
        dataModel = model;
    }
}
