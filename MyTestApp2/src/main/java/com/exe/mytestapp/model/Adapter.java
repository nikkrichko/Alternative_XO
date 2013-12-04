package com.exe.mytestapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.GameConroller;
import com.exe.mytestapp.controller.GameListener;
import com.exe.mytestapp.controller.listeners.ButtonXOClickListener;

/**
 * Created by Nikita on 22.11.13.
 */
public class Adapter extends ArrayAdapter<Player> implements GameConroller {

//    private final GameConroller gameController;


//    -----------------------------------------------------
private PlayersXO[][] field = new PlayersXO[3][3];

    public Player player1;
    public Player player2;

    public Player currentPlayer;

    private GameListener gameListener;
//    -----------------------------------------------------

    public Adapter(Context context, int resource, /*GameConroller gameController,*/ Player player1, Player player2, GameListener gameListener) {
        super(context, resource);
//        this.gameController = gameController;
        initField();

//        -----------------------------------------------------

        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = this.player1;
        this.gameListener = gameListener;



//                ------------------------------------------------------
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton = (ImageButton) LayoutInflater.from(getContext()).inflate(R.layout.field_item, null);
        imageButton.setImageResource(R.drawable.nulls);

        Player tmpPlayer = getItem(position);
        int x = position % /*gameController.*/fieldSize();
        int y = ((position - x) / /*gameController.*/fieldSize());
        imageButton.setOnClickListener(new ButtonXOClickListener(/*gameController,*/this, getContext(), x, y));
        if(tmpPlayer == null)
            return imageButton;
        imageButton.setImageResource(tmpPlayer.getImage());

        return imageButton;
    }

    public void setPlayerInPosition(int x, int y, Player player){
        int position = x * /*gameController.*/fieldSize() + y;
        insert(player, position);
        notifyDataSetInvalidated();
    }

    @Override
    public void clear(){
        super.clear();
        initField();
        notifyDataSetInvalidated();
    }

    private void initField(){
        for (int i = 0; i < /*gameController.*/fieldSize() * /*gameController.*/fieldSize(); i++)
            add(null);
    }


//  ------------------------------------------------------
    @Override
    public boolean move(int x, int y) {

        int position = x * fieldSize() + y;
        Player player = getItem(position);
        if (player == null){
           setPlayerInPosition(x, y, currentPlayer); //TODO

            Player previosPlayer = currentPlayer;
            if (currentPlayer == player2){
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
//    ---------------------------------------------------------
}
