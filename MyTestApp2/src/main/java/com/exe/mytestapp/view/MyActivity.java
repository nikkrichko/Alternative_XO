package com.exe.mytestapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.exe.mytestapp.controller.GameListener;
import com.exe.mytestapp.controller.listeners.ButtonBigFieldListener;
import com.exe.mytestapp.controller.listeners.ButtonClickListener;
import com.exe.mytestapp.controller.listeners.OnResetListener;
import com.exe.mytestapp.R;
//import com.exe.mytestapp.controller.RealGameController;
import com.exe.mytestapp.controller.listeners.dialog.ResumeClickListener;
import com.exe.mytestapp.model.Adapter;
import com.exe.mytestapp.model.Player;
import com.exe.mytestapp.model.PlayersXO;

import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity implements GameListener {

    private Button mResetButton;
    private Button mBigFieldButton;
    private Player player1 = new Player();
    private Player player2 = new Player();
//    private RealGameController gameController;
    private TextView mTextTurnOn;
//    private GridView gridView;
    private Adapter fieldAdapter;

    private static final int IDM_NEW = 101;
    private static final int IDM_RESET = 102;

    private final boolean firstStart = true;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent tmpIntent = getIntent();
        extractData(tmpIntent);
        initPlayer();
//        gameController = new RealGameController(player1, player2, this);
        final TextView mtext = (TextView) findViewById(R.id.hello_world);
        mTextTurnOn = (TextView) findViewById(R.id.move_on);
        mResetButton = (Button)findViewById(R.id.reset);
//        gridView = (GridView)findViewById(R.id.main_grid);
//        gridView = (GridView)findViewById(R.id.grid_litle_filed);
        mBigFieldButton = (Button) findViewById(R.id.btnBigField);





        Context context = getApplicationContext();


        fieldAdapter = new Adapter(context, 0, /*gameController,*/ player1, player2, this);
//        gridView.setAdapter(fieldAdapter);
        ButtonClickListener tText = new ButtonClickListener(mtext);
        mResetButton.setOnClickListener(new OnResetListener(this));
        mTextTurnOn.setText(player1.getName());
        mBigFieldButton.setOnClickListener(new ButtonBigFieldListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE, IDM_NEW, Menu.NONE, "NEW");
        menu.add(Menu.NONE, IDM_RESET, Menu.NONE, "RESET");
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case IDM_RESET:
//                gameController.reset();
                fieldAdapter.reset();
                item.setChecked(true);
                break;
            case IDM_NEW:
                mTextTurnOn.setText("NEW GAME STARTED");
                break;
            default:
            return false;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
    }

    public void resetActivity(){
        fieldAdapter.reset();
//            gameController.reset();
            fieldAdapter.clear();

    }

    private void initPlayer(){

        player1.setImage(R.drawable.cross);
        player1.setFigure(PlayersXO.O);

        player2.setImage(R.drawable.zerro);
        player2.setFigure(PlayersXO.X);
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
