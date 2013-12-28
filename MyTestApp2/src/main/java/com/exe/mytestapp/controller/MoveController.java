package com.exe.mytestapp.controller;

import com.exe.mytestapp.model.BaseFieldAdapter;
import com.exe.mytestapp.model.GrandFieldAdapter;
import com.exe.mytestapp.model.Player;

/**
 * Created by Nikita on 24.12.13.
 */
public class MoveController {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    GrandFieldAdapter gfAdapter;
    BaseFieldAdapter bfAdapter;



    int x;
    int y;

    public void setBfAdapter(BaseFieldAdapter bfAdapter) {
        this.bfAdapter = bfAdapter;
    }

    public MoveController(GrandFieldAdapter gfAdapter, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gfAdapter = gfAdapter;

    }

    public MoveController(BaseFieldAdapter adapter, Player currentPlayer){
        this.bfAdapter = adapter;
        this.currentPlayer = currentPlayer;
    }


    public boolean move(int x, int y){
        BaseFieldAdapter baseField = gfAdapter.getBaseFieldFromGrand();

                if (bfAdapter.getField()[x][y] == null){
//                    standartMove(bfAdapter, gfAdapter);
                    bfAdapter.setPlayerInPosition(x, y, currentPlayer);
                    bfAdapter.notifyDataSetInvalidated();
                    bfAdapter.changeAdapterActivityFalse();
                    gfAdapter.changeBaseFieldActivity(x, y);
                    bfAdapter.notifyDataSetInvalidated();
                    gfAdapter.notifyDataSetInvalidated();

                    if (checkForWin(bfAdapter.getField(), currentPlayer)){
                        bfAdapter.setBigFigure();

                        bfAdapter.noteWindowWIN();
                    }
                        gfAdapter.setBaseFieldType();
                    if (isMovePossible(gfAdapter.getBaseField()[x][y])){
                        gfAdapter.changeBaseFieldActivityTrueToFree();
                    }
                    if (checkForWin(gfAdapter.getBaseFieldType(), currentPlayer)){
                        gameOver(gfAdapter);
                    }
                    return true;
                }



        return false;
    }



    private void blockedMove(BaseFieldAdapter bfAdapter, GrandFieldAdapter gfAdapter){

    }

    private boolean isMovePossible(BaseFieldAdapter field){
        if (field.getFieldType() !=null){
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void gameOver(GrandFieldAdapter gfAdapter){
        gfAdapter.changeBaseFieldActivityFalseToALL();


    }

    public boolean checkForWin(Player[][] field, Player currentPlayer){
        if (    checkHorisontal(field, currentPlayer) ||
                checkVertical(field, currentPlayer) ||
                checkDiagonal_1(field, currentPlayer) ||
                checkDiagonal_2(field, currentPlayer)){
            return true;
        }
        return false;
    }

    public void changeCurrentPlayer(){
        if (currentPlayer == player2){
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }

    }

    private boolean checkHorisontal(Player[][] field, Player currentPlayer){
        int fieldSize = 3;
        int counter = 0;
        for (int x = 0; x < fieldSize; x++ ){
            for (int y = 0; y < fieldSize; y++ ){
                if (field[x][y] == currentPlayer){
                    counter++;
                }
            }
            if (counter == fieldSize){
                return true;
            }
            else {
                counter = 0;
            }
        }
        return false;
    }

    private boolean checkVertical(Player[][] field, Player currentPlayer){
        int fieldSize = 3;
        int counter = 0;
        for (int y = 0; y < fieldSize; y++ ){
            for (int x = 0; x < fieldSize; x++ ){
                if (field[x][y] == currentPlayer){
                    counter++;
                }
            }
            if (counter == fieldSize){
                return true;
            }
            else {
                counter = 0;
            }
        }
        return false;
    }

    private boolean checkDiagonal_1(Player[][] field, Player currentPlayer){
        int fieldSize = 3;
        int counter = 0;
        for (int i = 0; i<fieldSize; i++){
            if (field[i][i] == currentPlayer){
                counter++;
            }
        }
        if (counter == fieldSize){
            return true;
        } else {
            counter = 0;
        }

        return false;
    }

    private boolean checkDiagonal_2(Player[][] field, Player currentPlayer){
        int fieldSize = 3;
        int counter = 0;
        int y = fieldSize-1;
        for (int x = 0; x < fieldSize; x++){

            if (field[x][y] == currentPlayer){
                counter++;
                y--;
            } else {
                y--;
            }
        }
        if (counter == fieldSize){
            return true;
        } else {
            counter = 0;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }




}
