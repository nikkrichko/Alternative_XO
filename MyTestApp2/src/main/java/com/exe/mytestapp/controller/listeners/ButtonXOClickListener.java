package com.exe.mytestapp.controller.listeners;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.exe.mytestapp.model.BaseFieldAdapter;
import com.exe.mytestapp.R;


public class ButtonXOClickListener implements View.OnClickListener {

    private static final String TAG = ButtonXOClickListener.class.getCanonicalName();

    private BaseFieldAdapter adapter;
    private Context context;
    private int x;
    private int y;


    public ButtonXOClickListener(BaseFieldAdapter adapter, Context context, int x, int y){

        this.adapter = adapter;
        this.context = context;
        this.x = x;
        this.y = y;
    }



    @Override
    public void onClick(View view) {
        Log.d(TAG, "in onClick");
//        Player currentPlayer = adapter.getCurrentPlayer();
        ImageButton iButton = (ImageButton)view;

        if (adapter.move(x,y)){
                iButton.setImageResource(adapter.getCurrentPlayerImage());
        } else {
                    noteWindow();


        }
        adapter.notifyDataSetInvalidated();

    }

    private void noteWindow(){
        Toast toast = Toast.makeText(context, R.string.alredy_pressed, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
