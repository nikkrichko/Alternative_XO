package com.exe.mytestapp.ClickListeners;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.exe.mytestapp.GameConroller;
import com.exe.mytestapp.PlayersXO;
import com.exe.mytestapp.R;

/**
 * Created by Nikita on 19.11.13.
 */
public class ButtonXOClickListener implements View.OnClickListener {
    private GameConroller gameConroller;
    private Context context;
    private int x;
    private int y;

    public ButtonXOClickListener(GameConroller gc, Context context, int x, int y){
        this.gameConroller = gc;
        this.context = context;
        this.x = x;
        this.y = y;
    }



    @Override
    public void onClick(View view) {
        PlayersXO cp = gameConroller.currentPlayer();
        gameConroller.move(x, y);
        ImageButton iButton = (ImageButton)view;
            if (iButton.isActivated()){
                noteWindow();
            } else {
                if (cp == PlayersXO.O){
                    iButton.setImageResource(R.drawable.abc_ab_solid_dark_holo);
                    iButton.setActivated(true);
                } else {
                    iButton.setImageResource(R.drawable.ic_launcher);
                    iButton.setActivated(true);
//                    iButton.setClickable(false);
//                    iButton.setEnabled(false);
            }
        }
    }

    private void noteWindow(){

        Toast toast = Toast.makeText(context, R.string.alredy_pressed, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
