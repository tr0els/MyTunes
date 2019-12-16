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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
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
public class MtController implements Initializable {

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
    private TableColumn<Media, Integer> songsTimeColumn;

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

    public MtController() throws Exception {
        dataModel = new DataModel();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populatePlaylistsTable();
        populateSongsInPlaylistList();
        populateSongsTable();

        volumeSlider.setValue(50);
        volumeLabel.setText("50%");

//        mpModel.songList = mpModel.getAllSongs(dataModel.getAllSongs().get(int).getSource());
//        mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
    }

    /**
     * Stops the song if one is currently playing. Gets the source of the song
     * the user has picked. Plays the new song.
     *
     * @param event
     */
    @FXML
    private void handleSong(MouseEvent event) {
        if (mediaView.getMediaPlayer() == null || mediaView.getMediaPlayer().getStatus() == Status.UNKNOWN || mediaView.getMediaPlayer().getStatus() == Status.READY) {
            mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
        }

        if (mediaView.getMediaPlayer().getStatus() == Status.PLAYING) {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
            mediaView.getMediaPlayer().play();
        }

        if (mediaView.getMediaPlayer().getStatus() == Status.PAUSED) {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsTable.getSelectionModel().getSelectedItem().getSource()));
        }

        mpModel.overRideSongList(songsTable.getItems(), songsTable.getSelectionModel().getSelectedIndex());
        mpModel.handlePlaySong(mediaView, currentSongLabel, pauseButton, volumeSlider);
    }

    private void populatePlaylistsTable() {
        // initialize the columns
        playlistsNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playlistsNumSongsColumn.setCellValueFactory(cellData -> cellData.getValue().numSongsProperty());
        playlistsTimeTotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalTimeProperty());

        // add data to the table
        playlistsTable.setItems(dataModel.getAllPlaylists());
    }

    private void populateSongsInPlaylistList() {
        // custom rendering of the list cell
        songsFromPlaylist.setCellFactory(param -> new ListCell<Media>() {
            @Override
            protected void updateItem(Media item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText((this.getIndex() + 1) + ". " + item.getTitle());
                }
            }
        });

        // add data to listview
        songsFromPlaylist.setItems(dataModel.getSongsOnPlaylist());
    }

    private void populateSongsTable() {
        // initialize the columns
        songsTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        songsArtistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        songsCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        songsTimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        // custom rendering of the category table cell
        songsCategoryColumn.setCellFactory(column -> new TableCell<Media, Integer>() {

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item == -1) {
                    setText(null);
                } else {
                    setText(dataModel.getAllCategories().get(item));
                }
            }
        });        
        
        // custom rendering of the time table cell
        songsTimeColumn.setCellFactory(column -> new TableCell<Media, Integer>() {

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(dataModel.secToTime(item));
                }
            }
        });

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

    private void handlePlayAndPause(ActionEvent event) {
        mpModel.playAndPause(currentSong, pauseButton, mediaView);
    }

    /**
     * Button that skips to the next song when pressed.
     *
     * @param event
     */
    @FXML
    private void handleSkipForward(ActionEvent event) {
        if (mpModel.getCurrentSong() != mpModel.songList.size() - 1) {
            mpModel.handleSkip(1, mediaView, currentSongLabel, pauseButton, volumeSlider);
        }

        mediaView.getMediaPlayer().setVolume(volumeSlider.getValue());
    }

    /**
     * Button that skips to the song before when pressed.
     *
     * @param event
     */
    @FXML
    private void handleSkipBackwards(ActionEvent event) {
        if (mpModel.getCurrentSong() != 0) {
            mpModel.handleSkip(-1, mediaView, currentSongLabel, pauseButton, volumeSlider);
        }

        mediaView.getMediaPlayer().setVolume(volumeSlider.getValue());
    }

    /**
     * Slider that changes the volume of the song playing, when dragged. The
     * label changes when there is a change in the sliders value.
     *
     * @param event
     */
    @FXML
    private void handleMusicVolume() {
        mpModel.musicVolume(mediaView.getMediaPlayer(), volumeSlider, volumeLabel);

    }

    /**
     * Opens a new stage that lets you create a new song.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void openSongPopup(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/SongPopupView.fxml"));
        Parent root = loader.load();

        SongPopupController SongPopupController = loader.getController();
        SongPopupController.transfer(dataModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Opens a new stage that lets you create a new playlist.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void openPlaylistPopup(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/PlaylistPopupView.fxml"));
        Parent root = loader.load();

        PlaylistPopupController playlistPopupController = loader.getController();
        playlistPopupController.transfer(dataModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Opens a new stage that lets you edit a song.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void handleEditSong(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditSongPopUp.fxml"));
        Parent root = loader.load();

        if (songsTable.getSelectionModel().getSelectedItem() != null) {
            EditSongPopUpController EditSongPopUpController = loader.getController();
            EditSongPopUpController.transferMedia(songsTable.getSelectionModel().getSelectedItem(), dataModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /**
     * A button that adds a chosen song to the currently picked playlist.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void addSongButton(ActionEvent event) throws Exception {
        dataModel.addSongToPlaylist(songsTable.getSelectionModel().getSelectedItem());
    }

    /**
     * Deletes a chosen song from the currently picked playlist. Opens a
     * dialogbox that asks if you're sure.
     *
     * @param list
     * @throws Exception
     */
//    private void deleteSongFromPlaylist(Playlist list) throws Exception
//    {
//        int input = JOptionPane.showConfirmDialog(null, "Delete the song from the playlist?", "Select an Option...",
//                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
//
//        // 0=yes, 1=no.
//        if (input == JOptionPane.YES_OPTION)
//        {
//            dataModel.deleteSongFromPlaylist(songsFromPlaylist.getSelectionModel().getSelectedItem());
//        }
//
//    }
    /**
     * A button that deletes a chosen song from the currently picked playlist.
     * Opens a dialogbox that asks if you're sure.
     *
     * @param list
     * @throws Exception
     */
    @FXML
    private void handleDeleteSongFromPlaylist(ActionEvent event) throws Exception {
        if (songsFromPlaylist.getSelectionModel().getSelectedItem() != null) {
            int input = JOptionPane.showConfirmDialog(null, "Delete the song from the playlist?", "Select an Option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            // 0=yes, 1=no.
            if (input == JOptionPane.YES_OPTION) {
                dataModel.deleteSongFromPlaylist(songsFromPlaylist.getSelectionModel().getSelectedItem());
            }
        }
    }

    /**
     * A button that switches two chosen songs in the current playlist. This
     * button puts the chosen song 1 line down.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void moveSongUpInPlaylist(ActionEvent event) throws Exception {
        changeOrderInPlaylist(-1);
    }

    /**
     * A button that swaps one chosen song with the one above or below. This
     * button puts the chosen song 1 line up.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void moveSongDownInPlaylist(ActionEvent event) throws Exception {
        changeOrderInPlaylist(+1);
    }

    /**
     * Swaps one chosen song with the one above or below.
     *
     * @param upOrDown
     * @throws Exception
     */
    private void changeOrderInPlaylist(int upOrDown) throws Exception {
        dataModel.swapSongsInPlaylist(songsFromPlaylist.getSelectionModel().getSelectedIndex(),
                songsFromPlaylist.getSelectionModel().getSelectedIndex() + upOrDown);
    }

    /**
     * A button that opens a new stage where you can edit the information about
     * the chosen song.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void editPlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditPlaylistView.fxml"));
        Parent root = loader.load();

        if (playlistsTable.getSelectionModel().getSelectedItem() != null) {
            EditPlaylistViewController EditPlaylistViewController = loader.getController();
            EditPlaylistViewController.transferPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
            EditPlaylistViewController.tranferDatamodel(dataModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /**
     * A button that deletes a chosen playlist. Opens a dialogbox that asks if
     * you're sure.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void deletePlaylist(ActionEvent event) throws Exception {
        if (playlistsTable.getSelectionModel().getSelectedItem() != null) {
            int input = JOptionPane.showConfirmDialog(null, "Permanently delete the playlist?", "Select an Option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            // 0=yes, 1=no.
            if (input == JOptionPane.YES_OPTION) {
                dataModel.deletePlaylist();
            }
        }
    }

    /**
     * Method that deletes a chosen song. Opens a dialogbox that asks if you're
     * sure.
     *
     * @param event
     * @throws Exception
     */
    private void handleDeleteSong(ActionEvent event) throws Exception {
        if (songsTable.getSelectionModel().getSelectedItem() != null) {
            int input = JOptionPane.showConfirmDialog(null, "Permanently delete the song?", "Select an Option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            // 0=yes, 1=no.
            if (input == JOptionPane.YES_OPTION) {
                dataModel.deleteSong(songsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    /**
     * Stops the song if one is currently playing. Gets the source of the song
     * the user has picked in the current playlist. Plays the new song.
     *
     * @param event
     */
    private void handlePlaySongFromPlaylist(MouseEvent event) {
        if (mediaView.getMediaPlayer() == null || mediaView.getMediaPlayer().getStatus() == Status.UNKNOWN || mediaView.getMediaPlayer().getStatus() == Status.READY) {
            mediaView.setMediaPlayer(mpModel.getSong(songsFromPlaylist.getSelectionModel().getSelectedItem().getSource()));

        }

        if (mediaView.getMediaPlayer().getStatus() == Status.PLAYING) {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsFromPlaylist.getSelectionModel().getSelectedItem().getSource()));
            mediaView.getMediaPlayer().play();
        }

        if (mediaView.getMediaPlayer().getStatus() == Status.PAUSED) {
            mediaView.getMediaPlayer().stop();
            mediaView.setMediaPlayer(mpModel.getSong(songsFromPlaylist.getSelectionModel().getSelectedItem().getSource()));
        }

        mpModel.overRideSongList(songsFromPlaylist.getItems(), songsFromPlaylist.getSelectionModel().getSelectedIndex());
        mpModel.handlePlaySong(mediaView, currentSongLabel, pauseButton, volumeSlider);
    }

    /**
     * Displays the songs from the chosen playlist.
     *
     * @param event
     */
    @FXML
    private void handleSongsFromPlaylist(MouseEvent event) {
        dataModel.setSelectedPlaylist(playlistsTable.getSelectionModel().getSelectedItem());
        populateSongsInPlaylistList(); // calls setItems again
    }

    /**
     * Closes the program.
     *
     * @param event
     */
    @FXML
    private void handleCloseProgram(ActionEvent event) {
        Stage stage = (Stage) closeProgram.getScene().getWindow();
        stage.close();
    }

    /**
     * Uses the keys pressed to search between the songs.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void searchSong(KeyEvent event) throws Exception {
        String input = searchField.getText();
        ObservableList<Media> result = dataModel.getSearchResult(input);
        songsTable.setItems(result);
    }
}
