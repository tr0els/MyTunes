/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import mytunes.gui.MediaPlayerModel;

/**
 *
 * @author Troels Klein
 */
public class MtController implements Initializable {
    
    @FXML
    private TableView<?> playlistTable;
    @FXML
    private TableView<?> songTable;
    @FXML
    private TableView<?> playlistContentTable;
    @FXML
    private Button removeSongButton;
    @FXML
    private Button closeProgram;
    @FXML
    private Button newPlaylistButton;
    @FXML
    private Button editPlaylistButton;
    @FXML
    private Button deletePlaylistButton;
    @FXML
    private Button newSongButton;
    @FXML
    private Button editSongButton;
    @FXML
    private Button deleteSongButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button nextSong;
    @FXML
    private Button previousSong;
    @FXML
    private Button transferSongButton;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchLabel;
    @FXML
    private TextField searchField;
    @FXML
    private MediaView mediaViewMain;
    @FXML
    private Label currentSongLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Button swapSongUp;
    @FXML
    private Button swapSongDown;
    
    MediaView mv = new MediaView();

    private int currentSong = 0;

    MediaPlayerModel mpModel = new MediaPlayerModel();
    
    
    
    @FXML
    private void openSongPopup(ActionEvent event) throws Exception {
        
        Stage stage = new Stage();
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/mytunes/gui/view/SongPopupView.fxml")));
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void openPlaylistPopup(ActionEvent event) throws Exception {
        
        Stage stage = new Stage();
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/mytunes/gui/view/PlaylistPopupView.fxml")));
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mpModel.songList = mpModel.getAllSongs();
        mv.setMediaPlayer(mpModel.songList.get(currentSong));
        getMetadata();

    }
    
    private void getMetadata()
    {
        mv.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> ch) ->
        {
            if (ch.wasAdded())
            {
                handleMetadata(ch.getKey(), ch.getValueAdded());
            }

        });

    }

    public void handleMetadata(String key, Object value)
    {
        if (key.equals("title"))
        {
            currentSongLabel.setText(value.toString() + " ... is playing");
        }
    
    }

    @FXML
    private void handlePlayAndPause(ActionEvent event)
    {
        mpModel.playAndPause(currentSong, pauseButton);

    }

    @FXML
    private void handleSkipForward(ActionEvent event)
    {
        if (currentSong != mpModel.songList.size() - 1)
        {
            int newNumber = currentSong + 1; 
            
            mpModel.playNewSong(newNumber, currentSongLabel, currentSong, pauseButton);
            currentSong++;
        } 
        
    }

    @FXML
    private void handleSkipBackwards(ActionEvent event)
    {
        if (currentSong != 0)
        {
            int newNumber = currentSong - 1;
            mpModel.playNewSong(newNumber, currentSongLabel, currentSong, pauseButton);
            currentSong--;
        } 
    }

    @FXML
    private void handleMusicVolume(MouseEvent event)
    {
        volumeSlider.valueProperty().bindBidirectional(mv.getMediaPlayer().volumeProperty());
        mv.getMediaPlayer().setVolume(volumeSlider.getValue());
    }
}