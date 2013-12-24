package com.exe.mytestapp.controller;

import com.exe.mytestapp.model.GrandFieldAdapter;
import com.exe.mytestapp.model.Player;

/**
 * Created by Nikita on 24.12.13.
 */
public class MoveController {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    GrandFieldAdapter adapter;
    Object[][] object = new Object[3][3];

    public MoveController(GrandFieldAdapter adapter, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.adapter = adapter;

    }


    public boolean wivCheck(){
        if        ((object[0][0] == object[0][1] == object[0][2])
                || (object[1][0] == object[1][1] == object[1][2])
                || (object[2][0] == object[2][1] == object[2][2])

                || (object[0][0] == object[1][0] == object[2][0])
                || (object[0][1] == object[1][1] == object[2][1])
                || (object[0][2] == object[1][2] == object[2][2])

                || (object[0][0] == object[1][1] == object[2][2])
                || (object[0][2] == object[1][1] == object[2][0])){
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
