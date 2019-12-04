/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

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
    MediaView mv = new MediaView();
    
    private Media media;

    public ArrayList<MediaPlayer> songList;
    
    
    
    public ArrayList<MediaPlayer> getAllSongs()
    {
        ArrayList<MediaPlayer> allSongs = new ArrayList<>();

        Media media1 = new Media("http://traffic.libsyn.com/dickwall/JavaPosse373.mp3");
        Media media2 = new Media(new File("C:\\Users\\Christina\\Music\\Uncle-Kracker-Follow-Me.mp3").toURI().toString());
        Media media3 = new Media(new File("C:\\Users\\Christina\\Music\\8-bit-music.mp3").toURI().toString());

        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
        MediaPlayer mediaPlayer3 = new MediaPlayer(media3);

        allSongs.add(mediaPlayer1);
        allSongs.add(mediaPlayer2);
        allSongs.add(mediaPlayer3);

        return allSongs;
    }
    
    
    
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

    

    public void playNewSong(int number, Label songLabel, int currentSong, Button playAndPauseButton)
    {
        songList.get(currentSong).stop();
        mv.setMediaPlayer(songList.get(number));
        songList.get(number).play();
        playAndPauseButton.setText("Pause");
        getNameLabel(number, songLabel);
        
    }
    
    
    
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

    
    
   
        
        
    
    
    
    
    
    
    


