package com.exe.mytestapp.controller.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;


import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.view.MyActivity;

/**
 * Created by Nikita on 24.11.13.
 */
public class ButtonStartClickListener implements View.OnClickListener {
    Activity context;

    EditText player1Name;
    EditText player2Name;



    public ButtonStartClickListener(Activity context, EditText player1Name, EditText player2Name) {

        this.context = context;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MyActivity.class);


        Player player1 = new Player();
        player1.setName(player1Name.getText().toString());

        Player player2 = new Player();
        player2.setName(player2Name.getText().toString());

        intent.putExtra("PLAYER_1", player1);
        intent.putExtra("PLAYER_2", player2);
        context.startActivity(intent);
    }
}
