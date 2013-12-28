package com.exe.mytestapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.exe.mytestapp.controller.GameListener;
//import com.exe.mytestapp.controller.listeners.OnResetListener;
import com.exe.mytestapp.R;
//import com.exe.mytestapp.controller.RealGameController;
import com.exe.mytestapp.controller.MoveController;
import com.exe.mytestapp.controller.listeners.OnResetListener;
import com.exe.mytestapp.controller.listeners.dialog.ResumeClickListener;

import com.exe.mytestapp.model.BaseFieldAdapter;
import com.exe.mytestapp.model.GrandFieldAdapter;
import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.model.PlayersXO;

import android.view.Menu;


public class MyActivity extends Activity implements GameListener {

    private Button mResetButton;
    private Button mBigFieldButton;
    private Player player1 = new Player();
    private Player player2 = new Player();
//    private RealGameController gameController;
    private TextView mTextTurnOn;
    private GridView gridView;
    private BaseFieldAdapter fieldAdapter;
    private GrandFieldAdapter grandFieldAdapter;
    private MoveController moveController;

    private static final int IDM_NEW = 101;
    private static final int IDM_RESET = 102;

    private boolean firstStart = true;
    ///////////////////////
    private Intent tmpIntent;
////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////
        Intent tmpIntent = getIntent();
        extractData(tmpIntent);
        ///////////////////////
        initPlayer();
        mTextTurnOn = (TextView) findViewById(R.id.move_on);
        mResetButton = (Button)findViewById(R.id.reset);
        grandFieldAdapter = new GrandFieldAdapter(this, player1, player2);
        gridView = (GridView)findViewById(R.id.grid_view_on_main);
        gridView.setAdapter(grandFieldAdapter);

        ///////////////////////////////////////////
//        mTextTurnOn.setText(grandFieldAdapter.getMoveController().getCurrentPlayer().getName());
        /////////////////////////////////////////////
        mResetButton.setOnClickListener(new OnResetListener(grandFieldAdapter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE, IDM_NEW, Menu.NONE, "NEW");
        menu.add(Menu.NONE, IDM_RESET, Menu.NONE, "RESET");
        return (super.onCreateOptionsMenu(menu));
    }


    @Override
    protected void onResume() {
        super.onResume();
        // TODO
        if (!firstStart){
        new  AlertDialog.Builder(this)
        .setMessage("Do you want continue?")
                .setPositiveButton("YES", new ResumeClickListener())
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MyActivity.this, IntrodatcionActivity.class);
                        MyActivity.this.startActivity(intent);
                        MyActivity.this.finish();
                    }

                }).show();
        }
        firstStart = false;
    }


    private void initPlayer(){
        ///////////////////////////////
        extractData(getIntent());
        ////////////////////////////////////////

//        player1.setName("NIKITA");
        player1.setImage(R.drawable.cross);
        player1.setFigure(PlayersXO.X);
//        player2.setName("SLAVA");
        player2.setImage(R.drawable.zerro);
        player2.setFigure(PlayersXO.O);





    }

    @Override
    public void onMoveFinished(int x, int y, Player player) {
        mTextTurnOn.setText(getString(R.string.who_move_now) + player.getName());
    }

    private void extractData(Intent intent){
        player1 = (Player)intent.getSerializableExtra("PLAYER_1");
        player2 = (Player)intent.getSerializableExtra("PLAYER_2");

    }




}
