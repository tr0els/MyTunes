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
    private TextField fileTextField;
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
    }

    @FXML
    private void createMedia(ActionEvent event)
    {
    }
    
}
