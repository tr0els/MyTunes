/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.io.File;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javax.xml.transform.Source;

/**
 *
 * @author Christina
 */
public class MediaPlayerModel
{
    
    private Media media;

    public ArrayList<MediaPlayer> songList;
    
    public MediaPlayer getSong(String source)
    {
        Media med = new Media (new File(source).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(med);
        return mediaPlayer;
    }
    
    
    /**
     * Checks if the current song is playing, paused or ready.
     * Sets the button text to Play or Pause, depending on if the song is playing or paused. 
     * 
     * @param currentSong
     * @param button 
     */
    public void playAndPause(int currentSong, Button button, MediaView mediaView)
    {
        if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaView.getMediaPlayer().pause();
            button.setText("Play");
            

        } 
        else if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED || mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.READY)
        {
            mediaView.getMediaPlayer().play();
            button.setText("Pause");
            
        }
    }

    public void playNextSong(MediaView mv, MediaModel mediaModel, TableView<mytunes.be.Media> media, Label songLabel, Button button)
    {
        mv.getMediaPlayer().setOnEndOfMedia(() ->
        {
            Media med = new Media (new File(mediaModel.getAllMedias().get(media.getSelectionModel()
                    .getSelectedIndex()+1).getSource()).toURI().toString());
           
            MediaPlayer mediaPlayer = new MediaPlayer(med);
            
            mv.setMediaPlayer(mediaPlayer);
            mv.getMediaPlayer().play();
            button.setText("Pause");
            
            songLabel.setText(media.getSelectionModel().getSelectedItem().getTitle() + "... is playing");
            
        });
    }
    
    public void playNextSongPL(MediaView mv, MediaModel mediaModel, ListView media, Label songLabel, Button button, String title, int chosenSong, DataModel dataModel)
    {
        mv.getMediaPlayer().setOnEndOfMedia(() ->
        {
            Media med = new Media (new File(dataModel.getSongsOnPlaylist().get(chosenSong + 1).getSource()).toURI().toString());
           
            MediaPlayer mediaPlayer = new MediaPlayer(med);
            
            mv.setMediaPlayer(mediaPlayer);
            mv.getMediaPlayer().play();
            button.setText("Pause");
            
            songLabel.setText(title + "... is playing");
            
        });
    }
}
