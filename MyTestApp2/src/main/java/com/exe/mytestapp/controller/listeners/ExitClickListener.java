package com.exe.mytestapp.controller.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

/**
 * Created by Nikita on 30.12.13.
 */
public class ExitClickListener implements DialogInterface.OnClickListener, View.OnClickListener {

    Context activity;

    public ExitClickListener(Context activity) {
        this.activity = activity;
    }


//    public ExitClickListener(MyActivity activity){
//        this.activity = activity;
//    }



    @Override
    public void onClick(DialogInterface dialogInterface, int i) {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        onClick(null, 0);
    }
}
