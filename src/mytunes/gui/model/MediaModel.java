/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Media;
import mytunes.bll.BLLManager;

/**
 *
 * @author Troels Klein
 */
public class MediaModel {

    private BLLManager bll;
    private ObservableList<Media> medias;

    public MediaModel() {
        bll = new BLLManager();
        medias = FXCollections.observableArrayList();
        medias.addAll(bll.getAllMedias());
    }

    public ObservableList<Media> getMovies() {
        return medias;
    }

    public void createMovie(String source, String artist, String title, int time, int year, int category) {
        Media media = bll.createMedia(source, artist, title, time, year, category);
        medias.add(media);
    }
}