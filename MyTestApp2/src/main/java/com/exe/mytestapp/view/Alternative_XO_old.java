package com.exe.mytestapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.listeners.ButtonStartClickListener;
import com.exe.mytestapp.controller.listeners.ExitClickListener;

public class Alternative_XO_old extends Activity {

    private Button btnStart;
    private Button btnExit;
    private RadioGroup radioGroup;
    private RadioButton rbBlock;
    private RadioButton rbUnBlock;
    private RadioButton rbFirstWin;

    private static final String NAME_PLAYER_1 = "player1";

    private static final String NAME_PLAYER_2 = "player2";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction);

        radioGroup = (RadioGroup)findViewById(R.id.radio_group_introdaction);




        EditText player1name = (EditText)findViewById(R.id.player_1_name);

        EditText player2name = (EditText)findViewById(R.id.player_2_name);
        ButtonStartClickListener buttonStartClickListener = new ButtonStartClickListener(this, player1name, player2name);
        radioGroup.setOnCheckedChangeListener(buttonStartClickListener);

        btnStart = (Button)findViewById(R.id.BTN_start);
        btnExit = (Button)findViewById(R.id.BTN_cancel);

        btnStart.setOnClickListener(buttonStartClickListener);
        btnExit.setOnClickListener(new ExitClickListener(this));


    }


}
