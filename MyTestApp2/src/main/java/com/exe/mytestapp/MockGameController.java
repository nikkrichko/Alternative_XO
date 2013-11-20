package com.exe.mytestapp;

/**
 * Created by Nikita on 19.11.13.
 */
public class MockGameController implements GameConroller {

    public PlayersXO currentField = PlayersXO.O;
    private PlayersXO[][] field = new PlayersXO[3][3];

    @Override
    public void move(int x, int y) {

        field[x][y] = currentField;
        if (currentField == PlayersXO.O){
            currentField = PlayersXO.X;
            } else {
            currentField = PlayersXO.O;
        }


    }

    @Override
    public PlayersXO currentPlayer() {
       return currentField;
    }

    @Override
    public int fieldSize() {
        return 3;
    }
}
