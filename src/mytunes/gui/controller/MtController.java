/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.gui.model.MediaPlayerModel;
import mytunes.gui.model.DataModel;

/**
 *
 * @author Troels Klein
 */
public class MtController implements Initializable
{

    @FXML
    private TableView<Playlist> playlistsTable;
    @FXML
    private TableColumn<Playlist, String> playlistsNameColumn;
    @FXML
    private TableColumn<Playlist, Integer> playlistsNumSongsColumn;
    @FXML
    private TableColumn<Playlist, String> playlistsTimeTotalColumn;

    @FXML
    private ListView<Media> songsFromPlaylist;    
    
    @FXML
    private TableView<Media> songsTable;
    @FXML
    private TableColumn<Media, String> songsTitleColumn;
    @FXML
    private TableColumn<Media, String> songsArtistColumn;
    @FXML
    private TableColumn<Media, Integer> songsCategoryColumn;
    @FXML
    private TableColumn<Media, String> songsTimeColumn;    
    
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
    private Label searchLabel;
    @FXML
    private TextField searchField;
    @FXML
    private MediaView mediaView;
    @FXML
    private Label currentSongLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Button swapSongUp;
    @FXML
    private Button swapSongDown;
    @FXML
    private Button deletePlaylistSongButton;

    public DataModel dataModel;
    
    private MediaPlayerModel mpModel = new MediaPlayerModel();
    private int currentSong = 0;
    private int countId = 0;

    public MtController() throws Exception
    {
        dataModel = new DataModel();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populatePlaylistsTable();
        populateSongsInPlaylistList();
        populateSongsTable();
        
        volumeSlider.setValue(50);
        volumeLabel.setText("50%");

//        mpModel.songList = mpModel.getAllSongs(dataModel.getAllSongs().get(int).getSource());
//        mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
    }

    @FXML
    private void handleSong(MouseEvent event)
    {
        if (mediaView.getMediaPlayer() == null || mediaView.getMediaPlayer().getStatus() == Status.UNKNOWN)
        {
            mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
        }
        
        if (mediaView.getMediaPlayer().getStatus() == Status.PLAYING)
        {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
            mediaView.getMediaPlayer().play();
        }
        
        if (mediaView.getMediaPlayer().getStatus() == Status.PAUSED)
        {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
        }
            
        mpModel.overRideSongList(songsTable.getItems(), songsTable.getSelectionModel().getSelectedIndex());
        mpModel.handlePlaySong(mediaView, currentSongLabel, pauseButton, volumeSlider);
    }

    private void populatePlaylistsTable()
    {
        // initialize the columns
        playlistsNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playlistsNumSongsColumn.setCellValueFactory(cellData -> cellData.getValue().numSongsProperty());
        playlistsTimeTotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalTimeProperty());

        // add data to the table
        playlistsTable.setItems(dataModel.getAllPlaylists());
    }

    private void populateSongsInPlaylistList()
    {
        // add data to listview
        songsFromPlaylist.setItems(dataModel.getSongsOnPlaylist());
    }

    private void populateSongsTable()
    {
        // initialize the columns
        songsTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        songsArtistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        songsCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        songsTimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        // add data to the table
        songsTable.setItems(dataModel.getAllSongs());
    }

    /**
     * Button that plays and pauses the song, depending on if the song is
     * already playing or paused, when pressed.
     *
     * @param event
     */
    @FXML
    private void handlePlayAndPause(ActionEvent event)
    {
        mpModel.playAndPause(currentSong, pauseButton, mediaView);
    }

    /**
     * Button that skips to the next song when pressed.
     *
     * @param event
     */
    @FXML
    private void handleSkipForward(ActionEvent event)
    {
        if (mpModel.getCurrentSong() != mpModel.songList.size() - 1)
        {
            mpModel.handleSkip(1, mediaView, currentSongLabel, pauseButton, volumeSlider);
        }
        
        mpModel.musicVolume(mediaView.getMediaPlayer(), volumeSlider, volumeLabel);
    }

    /**
     * Button that skips to the song before when pressed.
     *
     * @param event
     */
    @FXML
    private void handleSkipBackwards(ActionEvent event)
    {
        if(mpModel.getCurrentSong() != 0)
        {
            mpModel.handleSkip(-1, mediaView, currentSongLabel, pauseButton, volumeSlider);
        }
        mpModel.musicVolume(mediaView.getMediaPlayer(), volumeSlider, volumeLabel);
    }

    /**
     * Slider that changes the volume of the song playing, when dragged. The
     * label changes when there is a change in the sliders value.
     *
     * @param event
     */
    @FXML
    private void handleMusicVolume()
    {
        mpModel.musicVolume(mediaView.getMediaPlayer(), volumeSlider, volumeLabel);

    }

    @FXML
    private void openSongPopup(ActionEvent event) throws Exception
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/SongPopupView.fxml"));
        Parent root = loader.load();

        SongPopupController SongPopupController = loader.getController();
        SongPopupController.transfer(dataModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openPlaylistPopup(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/PlaylistPopupView.fxml"));
        Parent root = loader.load();

        PlaylistPopupController playlistPopupController = loader.getController();
        playlistPopupController.transfer(dataModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleEditSong(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditSongPopUp.fxml"));
        Parent root = loader.load();

        if (songsTable.getSelectionModel().getSelectedItem() != null)
        {
            EditSongPopUpController EditSongPopUpController = loader.getController();
            EditSongPopUpController.transferMedia(songsTable.getSelectionModel().getSelectedItem(), dataModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void searchSong(KeyEvent event) throws Exception
    {
        String input = searchField.getText();
        ObservableList<Media> result = dataModel.getSearchResult(input);
        songsTable.setItems(result);
    }

    @FXML
    private void addSongButton(ActionEvent event) throws Exception
    {
        dataModel.addSongToPlaylist(songsTable.getSelectionModel().getSelectedItem());
    }

    //WORKS
    private void deleteSongFromPlaylist(Playlist list) throws Exception
    {
        int input = JOptionPane.showConfirmDialog(null, "Delete the song from the playlist?", "Select an Option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

        // 0=yes, 1=no.
        if (input == JOptionPane.YES_OPTION)
        {
            dataModel.deleteSongFromPlaylist(songsFromPlaylist.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    private void handleDeleteSongFromPlaylist(ActionEvent event) throws Exception
    {
        if (songsFromPlaylist.getSelectionModel().getSelectedItem() != null)
        {
            int input = JOptionPane.showConfirmDialog(null, "Delete the song from the playlist?", "Select an Option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            // 0=yes, 1=no.
            if (input == JOptionPane.YES_OPTION)
            {
                dataModel.deleteSongFromPlaylist(songsFromPlaylist.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    private void moveSongUpInPlaylist(ActionEvent event) throws Exception
    {
        changeOrderInPlaylist(-1);
    }

    @FXML
    private void moveSongDownInPlaylist(ActionEvent event) throws Exception
    {
        changeOrderInPlaylist(+1);
    }

    private void changeOrderInPlaylist(int upOrDown) throws Exception
    {
        dataModel.swapSongsInPlaylist(songsFromPlaylist.getSelectionModel().getSelectedIndex(),
                songsFromPlaylist.getSelectionModel().getSelectedIndex() + upOrDown);
    }

    @FXML
    private void editPlaylist(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditPlaylistView.fxml"));
        Parent root = loader.load();

        if (playlistsTable.getSelectionModel().getSelectedItem() != null)
        {
            EditPlaylistViewController EditPlaylistViewController = loader.getController();
            EditPlaylistViewController.transferPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
            EditPlaylistViewController.tranferDatamodel(dataModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void deletePlaylist(ActionEvent event) throws Exception
    {
        if (playlistsTable.getSelectionModel().getSelectedItem() != null)
        {
            int input = JOptionPane.showConfirmDialog(null, "Permanently delete the playlist?", "Select an Option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            // 0=yes, 1=no.
            if (input == JOptionPane.YES_OPTION)
            {
                dataModel.deletePlaylist();
            }
        }
    }

    @FXML
    private void handleDeleteSong(ActionEvent event) throws Exception
    {
        if (songsTable.getSelectionModel().getSelectedItem() != null)
        {
            int input = JOptionPane.showConfirmDialog(null, "Permanently delete the song?", "Select an Option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            // 0=yes, 1=no.
            if (input == JOptionPane.YES_OPTION)
            {
                dataModel.deleteSong(songsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    private void handlePlaySongFromPlaylist(MouseEvent event)
    {
       if (mediaView.getMediaPlayer() == null || mediaView.getMediaPlayer().getStatus() == Status.UNKNOWN)
        {
            mediaView.setMediaPlayer(mpModel.getSong(songsFromPlaylist.getSelectionModel().getSelectedItem().getSource()));
            
        }
        
        if (mediaView.getMediaPlayer().getStatus() == Status.PLAYING)
        {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsFromPlaylist.getSelectionModel().getSelectedItem().getSource()));
            mediaView.getMediaPlayer().play();
        }
        
        if (mediaView.getMediaPlayer().getStatus() == Status.PAUSED)
        {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsFromPlaylist.getSelectionModel().getSelectedItem().getSource()));
        }
            
        mpModel.overRideSongList(songsFromPlaylist.getItems(), songsFromPlaylist.getSelectionModel().getSelectedIndex());
        mpModel.handlePlaySong(mediaView, currentSongLabel, pauseButton, volumeSlider);
    }

    @FXML
    private void handleSongsFromPlaylist(MouseEvent event)
    {
        
        dataModel.setSelectedPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
        populateSongsInPlaylistList(); // calls setItems again
    }
}
