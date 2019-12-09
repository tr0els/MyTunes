/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mytunes.be.Media;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class EditSongPopUpController implements Initializable
{

    @FXML
    private Label titleLabel;
    @FXML
    private TextField editTitle;
    @FXML
    private Label artistLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField editArtist;
    @FXML
    private TextField editTime;
    @FXML
    private Button editFileButton;
    @FXML
    private ComboBox comboCategory;
    @FXML
    private Label categoryLabel;
    @FXML
    private TextField editYear;

    private Media media;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    public void transferMedia(Media media)
    {
        editTitle.setText(media.getTitle());
        editArtist.setText(media.getArtist());
        editYear.setText(media.getYear() + "");
        time(media.getTime());
        categories(media);
        this.media = media;
    }

    private void time(int time)
    {
        int minutes = time / 60;
        int hours = time / 60 / 60;
        int seconds = time - hours * 60 * 60 - minutes * 60;

        if (hours == 0)
        {
            editTime.setText(minutes + ":" + seconds);

            if (seconds < 10)
            {
                editTime.setText(minutes + ":" + "0" + seconds);
            }
        } else
        {
            editTime.setText(hours + ":" + minutes + ":" + seconds);
            if (minutes < 10)
            {
                editTime.setText(hours + ":" + "0" + minutes + ":" + seconds);
            } else if (seconds < 10)
            {
                editTime.setText(hours + ":" + minutes + ":" + "0" + seconds);
            } else if (minutes < 10 && seconds < 10)
            {
                editTime.setText(hours + ":" + "0" + minutes + ":" + "0" + seconds);
            }

        }

    }

    private int convertTime(String time)
    {
        
        String[] split = time.split(":");
        
        int count = split.length - 1;

        int total = 0;
        
        try
        {
            total += (Integer.parseInt(split[count]));
            count--;
        } catch (Exception e)
        {
            System.out.println("No seconds");
        }
        try
        {
            total += (Integer.parseInt(split[count])*60);
            count--;
        } catch (Exception e)
        {
            System.out.println("No minutes");
        }
        try
        {
            total += (Integer.parseInt(split[count])*60*60);
            count--;
        } catch (Exception e)
        {
            System.out.println("No hours");
        }
        
        return total;
    }

    private void categories(Media media)
    {
        comboCategory.setItems(FXCollections.observableArrayList(
                "Blues",
                "Classic Rock",
                "Country",
                "Dance",
                "Disco",
                "Funk",
                "Grunge",
                "Hip-Hop",
                "Jazz",
                "Metal",
                "New Age",
                "Oldies",
                "Other",
                "Pop",
                "Rhythem and Blues",
                "Rap",
                "Reggae",
                "Rock",
                "Techno",
                "Industrial",
                "Alternative")
        );

        comboCategory.getSelectionModel().select(media.getCategory());

    }

    @FXML
    private void handleEditFile(ActionEvent event)
    {
        this.media.setTitle(editTitle.getText());
        this.media.setArtist(editArtist.getText());
        this.media.setYear(Integer.parseInt(editYear.getText()));
        this.media.setTime(convertTime(editTime.getText()));

    }

    @FXML
    private void handleCategory(ActionEvent event)
    {
    }

}
