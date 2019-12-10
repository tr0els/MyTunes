/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import mytunes.be.Media;
import mytunes.be.Playlist;
import mytunes.gui.model.MediaPlayerModel;
import mytunes.dal.MockManager;
import mytunes.gui.model.MediaModel;
import mytunes.gui.model.PlaylistModel;

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
    @FXML
    private ListView songsFromPlaylist;

    
    private MediaModel mediaModel;
    private PlaylistModel playlistModel;

    private MediaPlayerModel mpModel = new MediaPlayerModel();

    private int currentSong = 0;

    ObservableList<Media> songLists = null;

    private int countId = 0;

    
    
    public MtController() throws Exception
    {
     mediaModel = new MediaModel();
     playlistModel = new PlaylistModel();
    }
    

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populatePlaylistsTable();
        //populatePlaylistMediaList();
        populateSongsTable();

        mpModel.songList = mpModel.getAllSongs();
        mediaView.setMediaPlayer(mpModel.songList.get(currentSong));
        getMetadata();
        mediaView.getMediaPlayer().setVolume(0.5);
    }

    private void populatePlaylistsTable()
    {
        // initialize the columns
        playlistsNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playlistsNumSongsColumn.setCellValueFactory(cellData -> cellData.getValue().numSongsProperty());
        playlistsTimeTotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalTimeProperty());

        // add data to the table
        playlistsTable.setItems(playlistModel.getAllPlaylists());
    }

    private void populateSongsInPlaylistList()
    {
        //change to list view        
        //playlistTable.setItems(playlistModel.getAllPlaylists());
    }

    private void populateSongsTable()
    {
        // initialize the columns
        songsTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        songsArtistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        songsCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        songsTimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        // add data to the table
        songsTable.setItems(mediaModel.getAllMedias());
    }

    /**
     * Gets the MetaData from the mp3 file, by seeing if there is a change to
     * the MetaData map. This map doesn't load in instantly, therefore we have
     * to check if something changes by using a listener.
     */
    private void getMetadata()
    {
        mediaView.getMediaPlayer().getMedia().getMetadata().addListener((
                MapChangeListener.Change<? extends String, ? extends Object> ch)
                ->
        {
            if (ch.wasAdded())
            {
                handleMetadata(ch.getKey(), ch.getValueAdded());
            }
        });
    }

    /**
     * Checks if the change made to the MetaData is the title. If so, it sets
     * the text of the currentSongLabel to the value of the title.
     *
     * @param key
     * @param value
     */
    public void handleMetadata(String key, Object value)
    {
        if (key.equals("title"))
        {
            currentSongLabel.setText(value.toString() + " ... is playing");
        }
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
        mpModel.playAndPause(currentSong, pauseButton);
    }

    /**
     * Button that skips to the next song when pressed.
     *
     * @param event
     */
    @FXML
    private void handleSkipForward(ActionEvent event)
    {
        if (currentSong != mpModel.songList.size() - 1)
        {
            int newNumber = currentSong + 1;

            mpModel.playNewSong(newNumber, currentSongLabel, currentSong, pauseButton, mediaView);
            currentSong++;
            musicVolume(mediaView.getMediaPlayer());
        }
    }

    /**
     * Button that skips to the song before when pressed.
     *
     * @param event
     */
    @FXML
    private void handleSkipBackwards(ActionEvent event)
    {
        if (currentSong != 0)
        {
            int newNumber = currentSong - 1;
            mpModel.playNewSong(newNumber, currentSongLabel, currentSong, pauseButton, mediaView);
            currentSong--;
            musicVolume(mediaView.getMediaPlayer());
        }
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
        musicVolume(mediaView.getMediaPlayer());

    }

    private void musicVolume(MediaPlayer currSong)
    {
        volumeSlider.setValue(currSong.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                currSong.setVolume(volumeSlider.getValue() / 100);
            }
        });

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue)
            {
                volumeLabel.textProperty().setValue(
                        String.valueOf(newValue.intValue() + "%"));
            }
        });

    }

    @FXML
    private void openSongPopup(ActionEvent event) throws Exception
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/SongPopupView.fxml"));
        Parent root = loader.load();

        SongPopupController SongPopupController = loader.getController();
        SongPopupController.transfer(mediaModel);
        //SongPopupController.createSong(mediaModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openPlaylistPopup(ActionEvent event) throws Exception
    {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/mytunes/gui/view/PlaylistPopupView.fxml")));

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleEditSong(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditSongPopUp.fxml"));
        Parent root = loader.load();

        if (songsTable.getSelectionModel().getSelectedItem() != null)
        {
            EditSongPopUpController EditSongPopUpController = loader.getController();
            EditSongPopUpController.transferMedia(songsTable.getSelectionModel().getSelectedItem());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void searchSong(KeyEvent event) throws Exception 
    {
        String input = searchField.getText();
        ObservableList<Media> result = mediaModel.getSearchResult(input);
        songsTable.setItems(result);
    }

    public ObservableList<Media> search(String query)
    {
        MockManager mM = new MockManager();
        List<Media> searchBase = mM.getAllMedias();
        List<Media> filter = new ArrayList<>();

        for (Media song : searchBase)
        {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())
                    || song.getArtist().toLowerCase().contains(query.toLowerCase()))
            {
                filter.add(song);
            }
        }

        ObservableList<Media> result = FXCollections.observableList(filter);

        return result;
    }

    @FXML
    private void addSongButton(ActionEvent event)
    {
        addSongToPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
    }

    //WORKS
    private void addSongToPlaylist(Playlist list)
    {
        songLists = FXCollections.observableArrayList(list.getMedias());
        Media selectedMedia = songsTable.getSelectionModel().getSelectedItem();
        list.addMedia(selectedMedia);
        countId++;
        songsFromPlaylist.getItems().add(countId + ": " + selectedMedia.getTitle());
    }

    // WORKS
    private void displaySongsFromPlaylist(Playlist list)
    {
        songLists = FXCollections.observableArrayList(list.getMedias());
        for (Media song : songLists)
        {
            countId++;
            songsFromPlaylist.getItems().add(countId + ": " + song.getTitle());
        }
    }

    //WORKS
    private void deleteSongFromPlaylist(Playlist list)
    {
        int input = JOptionPane.showConfirmDialog(null,"Delete the song from the playlist?", "Select an Option...", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        
        // 0=yes, 1=no.
        
        if (input == JOptionPane.YES_OPTION)
        {
            songLists = FXCollections.observableArrayList(list.getMedias());
            Media selectedMedia = songLists.get(songsFromPlaylist.getSelectionModel().getSelectedIndex());
            list.deleteMedia(selectedMedia);
            songsFromPlaylist.getItems().remove(songsFromPlaylist.getSelectionModel().getSelectedItem());
        }

    }

    //WORKS
    @FXML
    private void handleSongsFromPlayList(MouseEvent event)
    {
        songsFromPlaylist.getItems().clear();
        countId = 0;
        displaySongsFromPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleDeleteSongFromPlaylist(ActionEvent event)
    {
        if(songsFromPlaylist.getSelectionModel().getSelectedItem() != null)
        {
            deleteSongFromPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void moveSongUpInPlaylist(ActionEvent event)
    {
        changeOrderInPlaylist(-1);
    }

    @FXML
    private void moveSongDownInPlaylist(ActionEvent event)
    {
        changeOrderInPlaylist(+1);
    }

    private void changeOrderInPlaylist(int upOrDown)
    {
        Collections.swap(playlistsTable.getSelectionModel().getSelectedItem().getMedias(), songsFromPlaylist.getSelectionModel().getSelectedIndex(),
                songsFromPlaylist.getSelectionModel().getSelectedIndex() + upOrDown);

        songsFromPlaylist.getItems().clear();
        countId = 0;
        displaySongsFromPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void editPlaylist(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditPlaylistView.fxml"));
        Parent root = loader.load();

        if (playlistsTable.getSelectionModel().getSelectedItem() != null)
        {
            EditPlaylistViewController EditPlaylistViewController = loader.getController();
            EditPlaylistViewController.transferMedia(playlistsTable.getSelectionModel().getSelectedItem());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void deletePlaylist(ActionEvent event)
    {
        if(playlistsTable.getSelectionModel().getSelectedItem() != null)
        {
            int input = JOptionPane.showConfirmDialog(null,"Permanently delete the playlist?", "Select an Option...", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        
            // 0=yes, 1=no.
        
            if (input == JOptionPane.YES_OPTION)
            {
                playlistsTable.getItems().remove(playlistsTable.getSelectionModel().getSelectedItem());
            } 
        }
    }

    @FXML
    private void handleDeleteSong(ActionEvent event)
    {
        //songLists = FXCollections.observableArrayList(mediaModel.getAllMedias());
        //Media selectedMedia = songLists.get(songsTable.getSelectionModel().getSelectedIndex());
        // delete song from model.
        if(songsTable.getSelectionModel().getSelectedItem() != null)
        {
            int input = JOptionPane.showConfirmDialog(null,"Permanently delete the song?", "Select an Option...", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        
            // 0=yes, 1=no.
        
            if (input == JOptionPane.YES_OPTION)
            {
                songsTable.getItems().remove(songsTable.getSelectionModel().getSelectedItem());
            } 
        }
    }
}
