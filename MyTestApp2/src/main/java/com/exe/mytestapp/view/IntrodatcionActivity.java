package com.exe.mytestapp.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.R.*;
import android.widget.Button;
import android.widget.EditText;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.listeners.ButtonStartClickListener;

public class IntrodatcionActivity extends Activity {

    private Button btnStart;
    private Button btnCancel;

    private static final String NAME_PLAYER_1 = "player1";

    private static final String NAME_PLAYER_2 = "player2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction);

        EditText player1name = (EditText)findViewById(R.id.player_1_name);

        EditText player2name = (EditText)findViewById(R.id.player_2_name);

        btnStart = (Button)findViewById(R.id.BTN_start);
        btnCancel= (Button)findViewById(R.id.BTN_cancel);

        btnStart.setOnClickListener(new ButtonStartClickListener(this, player1name, player2name));


    }



}
