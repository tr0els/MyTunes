/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Christina
 */
public class MediaPlayerModel
{
    
    private Media media;

    public ArrayList<MediaPlayer> songList;
    
    
    /**
     * Makes a list of songs.
     * 
     * @return 
     */
    public ArrayList<MediaPlayer> getAllSongs()
    {
        ArrayList<MediaPlayer> allSongs = new ArrayList<>();

        Media media1 = new Media("http://traffic.libsyn.com/dickwall/JavaPosse373.mp3");
        Media media2 = new Media(new File("songs/Uncle-Kracker-Follow-Me.mp3").toURI().toString());
        Media media3 = new Media(new File("songs/8-bit-music.mp3").toURI().toString());

        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
        MediaPlayer mediaPlayer3 = new MediaPlayer(media3);

        allSongs.add(mediaPlayer1);
        allSongs.add(mediaPlayer2);
        allSongs.add(mediaPlayer3);

        return allSongs;
    }
    
    
    /**
     * Checks if the current song is playing, paused or ready.
     * Sets the button text to Play or Pause, depending on if the song is playing or paused. 
     * 
     * @param currentSong
     * @param button 
     */
    public void playAndPause(int currentSong, Button button)
    {
        if (songList.get(currentSong).getStatus() == MediaPlayer.Status.PLAYING)
        {
            songList.get(currentSong).pause();
            button.setText("Play");
            

        } 
        else if (songList.get(currentSong).getStatus() == MediaPlayer.Status.PAUSED || songList.get(currentSong).getStatus() == MediaPlayer.Status.READY)
        {
            songList.get(currentSong).play();
            button.setText("Pause");
            
        }
    }

    
    /**
     * Stops the song currently playing and 
     * changes it to the next song in the list.
     * 
     * @param number
     * @param songLabel
     * @param currentSong
     * @param playAndPauseButton 
     */
    public void playNewSong(int number, Label songLabel, int currentSong, Button playAndPauseButton, MediaView mv)
    {
        songList.get(currentSong).stop();
        mv.setMediaPlayer(songList.get(number));
        songList.get(number).play();
        playAndPauseButton.setText("Pause");
        getNameLabel(number, songLabel);
        mv.getMediaPlayer().setVolume(0.5);
        
    }
    
    
    /**
     * Gets the title of the song playing and sets the label to display the title.
     * If the song does not have a title, it displays the song title as unknown.
     * 
     * @param number
     * @param songLabel 
     */
    public void getNameLabel(int number, Label songLabel)
    {
        if (songList.get(number).getMedia().getMetadata().get("title") == null)
        {
            songLabel.setText("Unknown ... is playing");
        }
        else
        {
            songLabel.setText(songList.get(number).getMedia().getMetadata().get("title").toString() + " ... is playing");
        }
    }

}

    
    
   
        
        
    
    
    
    
    
    
    


