package com.exe.mytestapp.controller;

import com.exe.mytestapp.model.Player;


public interface GameConroller {

    public boolean move(int x, int y);

    public Player getCurrentPlayer();

    public int getCurrentPlayerImage();

    public int fieldSize();

    public void reset();



}
