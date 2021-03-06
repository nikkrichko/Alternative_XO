package com.exe.mytestapp.controller.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.exe.mytestapp.R;
import com.exe.mytestapp.model.GrandFieldAdapter;

/**
* Created by Nikita on 20.11.13.
*/
public class OnResetListener implements View.OnClickListener {

    private GrandFieldAdapter adapter;
    private Activity activity;

    public OnResetListener(GrandFieldAdapter adapter, Activity activity) {
        this.adapter = adapter;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        new  AlertDialog.Builder(activity)
                .setMessage(R.string.Want_reset)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.resetField();
                    }
                })

                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                }).show();

    }
}
