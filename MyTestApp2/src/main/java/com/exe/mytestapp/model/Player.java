package com.exe.mytestapp.model;

import java.io.Serializable;

/**
 * Created by Nikita on 21.11.13.
 */
public class Player implements Serializable{

    public String name;

    public int image;

    public PlayersXO figure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public PlayersXO getFigure() {
        return figure;
    }

    public void setFigure(PlayersXO figure) {
        this.figure = figure;
    }


}
