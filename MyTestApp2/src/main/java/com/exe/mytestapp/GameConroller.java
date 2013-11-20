package com.exe.mytestapp;

/**
 * Created by Nikita on 19.11.13.
 */
public interface GameConroller {

    public void move(int x, int y);

    public PlayersXO currentPlayer();

    public int fieldSize();

}
