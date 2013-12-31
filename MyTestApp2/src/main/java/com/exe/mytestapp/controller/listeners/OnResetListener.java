package com.exe.mytestapp.controller.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.view.View;

import com.exe.mytestapp.controller.listeners.dialog.ResumeClickListener;
import com.exe.mytestapp.model.GrandFieldAdapter;
import com.exe.mytestapp.view.IntrodatcionActivity;
import com.exe.mytestapp.view.MyActivity;

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
                .setMessage("Do you want reset?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.resetField();
                    }
                })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                }).show();

    }
}
