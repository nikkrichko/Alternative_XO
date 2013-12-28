//package com.exe.mytestapp.view;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.GridView;
//
//import com.exe.mytestapp.R;
//import com.exe.mytestapp.controller.GameListener;
//import com.exe.mytestapp.controller.MoveController;
//import com.exe.mytestapp.model.BaseFieldAdapter;
//import com.exe.mytestapp.model.Player;
//import com.exe.mytestapp.model.PlayersXO;
//
///**
// * Created by Nikita on 03.12.13.
// */
//public class BigField extends Activity implements GameListener {
//    Player player1 = new Player();
//    Player player2 = new Player();
//    GridView gridView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.big_field);
//
//        initPlayer();
//        gridView = (GridView)findViewById(R.id.grid_litle_filed);
//        gridView.setAdapter(new BaseFieldAdapter(this, new MoveController()));
//    }
//
//    private void initPlayer(){
//        player1.setName("nik");
//        player1.setImage(R.drawable.cross);
//        player1.setFigure(PlayersXO.O);
//        player2.setName("Slava");
//        player2.setImage(R.drawable.zerro);
//        player2.setFigure(PlayersXO.X);
//    }
//
//    @Override
//    public void onMoveFinished(int x, int y, Player player) {
//
//    }
//}
