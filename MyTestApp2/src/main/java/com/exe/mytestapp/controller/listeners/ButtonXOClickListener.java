package com.exe.mytestapp.controller.listeners;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.exe.mytestapp.controller.MoveController;
import com.exe.mytestapp.model.BaseFieldAdapter;
import com.exe.mytestapp.R;


public class ButtonXOClickListener implements View.OnClickListener {

    private static final String TAG = ButtonXOClickListener.class.getCanonicalName();

    private BaseFieldAdapter adapter;
    private Context context;
    private int x;
    private int y;
    MoveController moveController;


    public ButtonXOClickListener(BaseFieldAdapter adapter, Context context, MoveController moveController, int x, int y){

        this.adapter = adapter;
        this.context = context;
        this.x = x;
        this.y = y;
        this.moveController = moveController;
//        this.moveController = moveController;
    }



    @Override
    public void onClick(View view) {
        moveController.setBfAdapter(adapter);
        Log.d(TAG, "in onClick");
        ImageButton iButton = (ImageButton)view;
//        adapter.changeAdapterActivityFalse();
        if (moveController.move(x,y)){
                iButton.setImageResource(adapter.getCurrentPlayerImage());
            moveController.setXY(x, y);
            moveController.changeCurrentPlayer();

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
