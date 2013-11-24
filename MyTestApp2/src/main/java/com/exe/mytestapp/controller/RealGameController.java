package com.exe.mytestapp.controller;

import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.model.PlayersXO;

/**
 * Created by Nikita on 19.11.13.
 */
public class RealGameController implements GameConroller {

//    public PlayersXO currentPlayer = PlayersXO.O;
    private PlayersXO[][] field = new PlayersXO[3][3];

    public Player player1;
    public Player player2;

    public Player currentPlayer;

    private GameListener gameListener;

    public RealGameController(Player player1, Player player2, GameListener gameListener) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = this.player1;
        this.gameListener = gameListener;
    }





    @Override
    public boolean move(int x, int y) {

        if (field[x][y] == null){
            field[x][y] = currentPlayer.getFigure();
            Player previosPlayer = currentPlayer;
            if (currentPlayer.getFigure() == player2.getFigure()){
                currentPlayer = player1;
                } else {
                currentPlayer = player2;

            }
            gameListener.onMoveFinished(x, y, previosPlayer);
            return true;
        }
        return false;
    }




    @Override
    public Player getCurrentPlayer() {
       return currentPlayer;
    }

    @Override
    public int getCurrentPlayerImage() {
        return currentPlayer.getImage();
    }


    @Override
    public int fieldSize() {
        return 3;
    }

    @Override
    public void reset() {
        for(int i = 0; i<fieldSize(); i++ ){
            for(int j = 0; j<fieldSize(); j++ ) {

                field[i][j] = null;

            }
        }
    }


}
