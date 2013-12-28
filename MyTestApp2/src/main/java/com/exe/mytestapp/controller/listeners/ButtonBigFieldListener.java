//package com.exe.mytestapp.controller.listeners;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.view.View;
//import android.widget.EditText;
//
//import com.exe.mytestapp.model.Player;
//import com.exe.mytestapp.view.MyActivity;
//
///**
// * Created by Nikita on 03.12.13.
// */
//public class ButtonBigFieldListener implements View.OnClickListener {
//    private Activity context;
//
//    public ButtonBigFieldListener(Activity context){
//        this.context = context;
//    }


//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(context, BigField.class);
//        context.startActivity(intent);
//    }
//}



//
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(context, MyActivity.class);
//
//        Player player1 = new Player();
//        player1.setName(player1Name.getText().toString());
//        Player player2 = new Player();
//        player2.setName(player2Name.getText().toString());
//        intent.putExtra("PLAYER_1", player1);
//        intent.putExtra("PLAYER_2", player2);
//        context.startActivity(intent);
//    }