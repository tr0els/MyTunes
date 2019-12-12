/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.io.File;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

/**
 *
 * @author Christina
 */
public class MediaPlayerModel
{

    public ObservableList<mytunes.be.Media> songList;

    int currentSong = 0;

    public MediaPlayer getSong(String source)
    {
        Media med = new Media(new File(source).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(med);
        return mediaPlayer;
    }

    /**
     * Checks if the current song is playing, paused or ready. Sets the button
     * text to Play or Pause, depending on if the song is playing or paused.
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

        } else if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED
                || mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.READY)
        {
            mediaView.getMediaPlayer().play();
            button.setText("Pause");

        }
    }

    public void playNextSong(MediaView mv, Label songLabel, Button button)
    {
        mv.getMediaPlayer().setOnEndOfMedia(() ->
        {
            currentSong++;
            handleSongs(mv, songLabel, button, "Pause");
            mv.getMediaPlayer().play();
        });

    }

    public void musicVolume(MediaPlayer currSong, Slider volumeSlider, Label volumeLabel)
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

    public void overRideSongList(ObservableList<mytunes.be.Media> items, int media)
    {
        songList = items;
        currentSong = media;
    }

    public void handleSkip(int upOrDown, MediaView mv, Label currentSongLabel, Button pauseButton)
    {
        if (mv.getMediaPlayer().getStatus() == Status.PLAYING)
        {
            currentSong = currentSong + upOrDown;
            mv.getMediaPlayer().stop();
            handleSongs(mv, currentSongLabel, pauseButton, "Pause");
            mv.getMediaPlayer().play();
            
        } else if (mv.getMediaPlayer().getStatus() == Status.PAUSED || mv.getMediaPlayer().getStatus() == Status.STOPPED)
        {
            currentSong = currentSong + upOrDown;
            handleSongs(mv, currentSongLabel, pauseButton, "Play");
        }
    }

    public void handlePlaySong(MediaView mv, Label currentSongLabel, Button pauseButton)
    {
        if (mv.getMediaPlayer().getStatus() == Status.PLAYING)
        {
            mv.getMediaPlayer().stop();
            handleSongs(mv, currentSongLabel, pauseButton, "Pause");
            mv.getMediaPlayer().play();
        } else if (mv.getMediaPlayer().getStatus() == Status.PAUSED || mv.getMediaPlayer().getStatus() == Status.STOPPED)
        {
            handleSongs(mv, currentSongLabel, pauseButton, "Play");
        }
    }

    private void handleSongs(MediaView mv, Label currentSongLabel, Button pauseButton, String playOrPause)
    {
        mv.setMediaPlayer(getSong(songList.get(currentSong).getSource()));
        currentSongLabel.setText(songList.get(currentSong).getTitle() + "... is playing");
        pauseButton.setText(playOrPause);
        mv.getMediaPlayer().setVolume(0.5);
        playNextSong(mv, currentSongLabel, pauseButton);
    }
}
