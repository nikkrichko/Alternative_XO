package com.exe.mytestapp.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.exe.mytestapp.controller.GameConroller;
import com.exe.mytestapp.controller.GameListener;
import com.exe.mytestapp.controller.listeners.ButtonXOClickListener;

/**
 * Created by Nikita on 22.11.13.
 */
public class Adapter extends ArrayAdapter<Player> {

    private final GameConroller gameController;

    public Adapter(Context context, int resource, GameConroller gameController) {
        super(context, resource);
        this.gameController = gameController;
        initField();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton = new ImageButton(getContext());

//        ViewGroup.LayoutParams layoutParams = imageButton.getLayoutParams();
//        layoutParams.width = 30;
//        layoutParams.height = 30;
//
//        imageButton.setLayoutParams(layoutParams);

        Player tmpPlayer = getItem(position);
        int x = position % gameController.fieldSize();
        int y = ((position - x) / gameController.fieldSize());
        imageButton.setOnClickListener(new ButtonXOClickListener(gameController, getContext(), x, y));
        if(tmpPlayer == null)
            return imageButton;
        imageButton.setImageResource(tmpPlayer.getImage());






        return imageButton;
    }

    public void setPlayerInPosition(int x, int y, Player player){
        int position = x * gameController.fieldSize() + y;
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
        for (int i = 0; i < gameController.fieldSize() * gameController.fieldSize(); i++)
            add(null);
    }

}
