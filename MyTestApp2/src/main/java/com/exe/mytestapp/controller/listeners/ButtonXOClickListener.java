package com.exe.mytestapp.controller.listeners;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.exe.mytestapp.controller.GameConroller;
import com.exe.mytestapp.model.Adapter;
import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.R;


public class ButtonXOClickListener implements View.OnClickListener {

    private static final String TAG = ButtonXOClickListener.class.getCanonicalName();
//    private GameConroller gameConroller;
    private Adapter adapter;
    private Context context;
    private int x;
    private int y;


    public ButtonXOClickListener(/*GameConroller gc,*/ Adapter adapter, Context context, int x, int y){
//        this.gameConroller = gc;
        this.adapter = adapter;
        this.context = context;
        this.x = x;
        this.y = y;
    }



    @Override
    public void onClick(View view) {
        Log.d(TAG, "in onClick");
        Player currentPlayer = /*gameConroller*/adapter.getCurrentPlayer();
        ImageButton iButton = (ImageButton)view;

        if (/*gameConroller*/adapter.move(x,y)){
                iButton.setImageResource(/*gameConroller*/adapter.getCurrentPlayerImage());
        } else {
                    noteWindow();
        }

    }

    private void noteWindow(){
        Toast toast = Toast.makeText(context, R.string.alredy_pressed, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
