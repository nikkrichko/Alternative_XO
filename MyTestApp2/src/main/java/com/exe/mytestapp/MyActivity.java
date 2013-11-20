package com.exe.mytestapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.exe.mytestapp.ClickListeners.ButtonClickListener;
import com.exe.mytestapp.ClickListeners.ButtonXOClickListener;
import com.exe.mytestapp.ClickListeners.OnResetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 09.11.13.
 */
public class MyActivity extends Activity {

    private List<ImageButton> mButtons = new ArrayList<ImageButton>(9);

    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView mtext = (TextView) findViewById(R.id.hello_world);

        mButtons.add((ImageButton) findViewById(R.id.b1));
        mButtons.add((ImageButton)findViewById(R.id.b2));
        mButtons.add((ImageButton) findViewById(R.id.b3));
        mButtons.add((ImageButton) findViewById(R.id.b4));
        mButtons.add((ImageButton)findViewById(R.id.b5));
        mButtons.add((ImageButton) findViewById(R.id.b6));
        mButtons.add( (ImageButton)findViewById(R.id.b7));
        mButtons.add((ImageButton)findViewById(R.id.b8));
        mButtons.add((ImageButton)findViewById(R.id.b9));
        mResetButton = (Button)findViewById(R.id.reset);


        Context context = getApplicationContext();
        ButtonClickListener tText = new ButtonClickListener(mtext);
//        ButtonXOClickListener iButton = new ButtonXOClickListener(new MockGameController(), context);
        MockGameController gameController = new MockGameController();

        for (int i = 0; i < mButtons.size(); i++) {
            int x = i % gameController.fieldSize();
            int y = ((i - x) / gameController.fieldSize());
            mButtons.get(i).setOnClickListener(new ButtonXOClickListener(gameController, context, x, y));
        }

        mResetButton.setOnClickListener(new OnResetListener(this));


    }




    public void resetActivity(){
        for (ImageButton button : mButtons) {
            button.setImageDrawable(null);
            button.setActivated(false);
        }
    }








}
