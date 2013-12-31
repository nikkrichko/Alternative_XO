package com.exe.mytestapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exe.mytestapp.controller.GameListener;
//import com.exe.mytestapp.controller.listeners.OnResetListener;
import com.exe.mytestapp.R;
//import com.exe.mytestapp.controller.RealGameController;
import com.exe.mytestapp.controller.MoveController;
import com.exe.mytestapp.controller.listeners.ExitClickListener;
import com.exe.mytestapp.controller.listeners.OnResetListener;
import com.exe.mytestapp.controller.listeners.dialog.ResumeClickListener;

import com.exe.mytestapp.model.*;

import android.view.Menu;


public class MyActivity extends Activity implements GameListener, MoveController.IMoveListener{

    private Button mResetButton;
    private Button mBigFieldButton;
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private Player player1 = new Player();
    private Player player2 = new Player();

    private TextView mTextTurnOn;
    private GridView gridView;
    private BaseFieldAdapter fieldAdapter;
    private GrandFieldAdapter grandFieldAdapter;
    private MoveController moveController;
    private Display display;
    private Rules rules;
    private static final int IDM_NEW = 101;
    private static final int IDM_RESET = 102;

    private boolean firstStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        Intent tmpIntent = getIntent();
        extractData(tmpIntent);
        initPlayer();
        mTextTurnOn = (TextView) findViewById(R.id.move_on);
        mResetButton = (Button)findViewById(R.id.reset);
        grandFieldAdapter = new GrandFieldAdapter(this, player1, player2, this, display, this);
        gridView = (GridView)findViewById(R.id.grid_view_on_main);
        gridView.setAdapter(grandFieldAdapter);

        imageButton1 = (ImageButton)findViewById(R.id.imageBTN1);
        imageButton2 = (ImageButton)findViewById(R.id.imageBTN2);

        ///////////////////////////////////////////
//        mTextTurnOn.setText(grandFieldAdapter.getMoveController().getCurrentPlayer().getName());
        /////////////////////////////////////////////
        mResetButton.setOnClickListener(new OnResetListener(grandFieldAdapter, this));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE, IDM_NEW, Menu.NONE, "NEW");
        menu.add(Menu.NONE, IDM_RESET, Menu.NONE, "RESET");
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case IDM_NEW:
                finish();
                break;
            case IDM_RESET:
                grandFieldAdapter.resetField();
                break;
            default:
                return false;
        }
        return true;
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new ExitClickListener(this))
                .create().show();
    }

    private void initPlayer(){
        extractData(getIntent());
        player1.setImage(R.drawable.cross);
        player1.setFigure(PlayersXO.X);
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
        rules = (Rules)intent.getSerializableExtra("RULES");

    }

    @Override
    public void beforeMove(Player player) {

//        mTextTurnOn.setText(rules.name().toString());
        mTextTurnOn.setText("NOW MOVE: " + player.getName());
        imageButton1.setBackgroundResource(player.getImage());
        imageButton2.setBackgroundResource(player.getImage());
    }

    public Rules getRules() {
        return rules;
    }
}
