package com.exe.mytestapp.controller.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.appcompat.R;
import android.view.View;
import android.widget.EditText;


import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.model.PlayersXO;
import com.exe.mytestapp.model.Rules;
import com.exe.mytestapp.view.IntrodatcionActivity;
import com.exe.mytestapp.view.MyActivity;

/**
 * Created by Nikita on 24.11.13.
 */
public class ButtonStartClickListener implements View.OnClickListener {
    private Activity context;

    private EditText player1Name;
    private EditText player2Name;
    Player player1 = new Player();
    Player player2 = new Player();

    Rules rules;
    IntrodatcionActivity introdatcionActivity;




    public ButtonStartClickListener(Activity context, EditText player1Name, EditText player2Name, IntrodatcionActivity introdatcionActivity) {
        this.context = context;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.introdatcionActivity = introdatcionActivity;

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MyActivity.class);


        rules = introdatcionActivity.rulesSelection();

        player1.setName(player1Name.getText().toString());
        if (player1.getName().isEmpty()) {
            player1.setName("PLAYER___1");
        }

        player2.setName(player2Name.getText().toString());
        if (player2.getName().isEmpty()) {
            player2.setName("PLAYER___2");
        }

        intent.putExtra("PLAYER_1", player1);
        intent.putExtra("PLAYER_2", player2);
        intent.putExtra("RULES", rules);

        context.startActivity(intent);
    }
}
