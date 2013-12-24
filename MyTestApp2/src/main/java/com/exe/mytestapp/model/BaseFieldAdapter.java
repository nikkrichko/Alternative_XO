package com.exe.mytestapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.listeners.ButtonXOClickListener;

/**
 * Created by Nikita on 17.12.13.
 */
public class BaseFieldAdapter extends BaseAdapter {

    private int FIELD_SIZE = 3;
    private Player[][] field = new Player[FIELD_SIZE][FIELD_SIZE];
    private PositionConverter converter = new PositionConverter();
    private Context context;
    Player player1;
    Player player2;
    Player currentPlayer;

    public BaseFieldAdapter(Context context, Player player1, Player player2) {
        this.context = context;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = this.player1;
    }

    @Override
    public int getCount() {
        return FIELD_SIZE*FIELD_SIZE;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageButton imageButton = (ImageButton) LayoutInflater.from(context).inflate(R.layout.field_item_button, null);
        imageButton.setImageResource(R.drawable.nulls);
        converter.converterPositionToXY(position);
        int x = converter.getX();
        int y = converter.getY();
        imageButton.setOnClickListener(new ButtonXOClickListener(this, context, x, y));
        if(field[x][y] != null)
            imageButton.setImageResource(field[x][y].getImage());

        return imageButton;
    }


    public void setPlayerInPosition(int x, int y, Player player){
        field[x][y] = player;

    }

    public boolean move(int x, int y){

        if (field[x][y] == null){
            setPlayerInPosition(x, y, currentPlayer);
            notifyDataSetInvalidated();
            if (currentPlayer == player2){
                currentPlayer = player1;
            } else {
                currentPlayer = player2;
            }
            return true;
        }
        return false;
    }



    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public int getCurrentPlayerImage() {
        return currentPlayer.getImage();
    }

    private boolean checkForReset(){
        for (int i=0; i<3; i++){
            for (int j=0;j<0; j++){
                field[i][j] = null;
                return false;
            }
        }
        return true;
    }

    public void resetField(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                field[i][j] = null;
            }
        }
        notifyDataSetInvalidated();
    }

}
