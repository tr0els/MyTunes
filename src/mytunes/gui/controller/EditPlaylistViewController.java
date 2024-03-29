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
import mytunes.be.Playlist;
import mytunes.gui.model.DataModel;


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
    
    
    DataModel dataModel; 
    
    Playlist pList;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    /**
     * Overrides pList with the chosen playlist.
     * Sets playlist name in the textfield in the pop up. 
     * @param pl 
     */
    // transport a playlist frm MTcontroller to this controller
    //@para playlist
    public void transferPlaylist(Playlist pl)
    {   
        pList = pl;
        titleTextField.setText(pl.getName());
    }
    
    //saves update on playlist
    @FXML
    private void savePlaylist(ActionEvent event) throws Exception 
    {
        
        handelEditPlaylist(); 
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
    //handels update to playlist 
    public void handelEditPlaylist() throws Exception 
    {  
        pList.setName(titleTextField.getText());
        dataModel.updatePlaylist(pList);
    }
    
    //tranfere Datamodel from MTcontroller to this
    public void  tranferDatamodel(DataModel model)
    {
        dataModel = model;
    }
            
   
    
}
