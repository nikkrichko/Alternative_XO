package com.exe.mytestapp.controller.listeners;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.exe.mytestapp.controller.GameConroller;
import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.R;

/**
 * Created by Nikita on 19.11.13.
 */
public class ButtonXOClickListener implements View.OnClickListener {

    private static final String TAG = ButtonXOClickListener.class.getCanonicalName();

    private GameConroller gameConroller;
    private Context context;
    private int x;
    private int y;
//    private TextView mTextTurnOn;

    public ButtonXOClickListener(GameConroller gc, Context context, int x, int y){
        this.gameConroller = gc;
        this.context = context;
        this.x = x;
        this.y = y;
//        this.gameConroller.initPlayer();
//        this.mTextTurnOn = mTextTurnOn;

    }



    @Override
    public void onClick(View view) {
        Log.d(TAG, "in onClick");
//        PlayersXO cp = gameConroller.getCurrentPlayer();
        Player currentPlayer = gameConroller.getCurrentPlayer();
        ImageButton iButton = (ImageButton)view;
//            if (iButton.isActivated()){
//
//            } else {
//                if (cp == PlayersXO.O){
//                    iButton.setImageResource(R.drawable.abc_ab_solid_dark_holo);
//                    iButton.setActivated(true);
//                } else {
//                    iButton.setImageResource(R.drawable.ic_launcher);
//                    iButton.setActivated(true);
////                    iButton.setClickable(false);
////                    iButton.setEnabled(false);
//            }
//        }

        if (gameConroller.move(x,y)){
            //iButton.setImageResource(R.drawable.ic_launcher);
                iButton.setImageResource(gameConroller.getCurrentPlayerImage());
//                mTextTurnOn.setText(gameConroller.getCurrentPlayer().getName());
            }
                else {
//            (iButton.isActivated();
            noteWindow();
        }
    }



    private void noteWindow(){

        Toast toast = Toast.makeText(context, R.string.alredy_pressed, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
