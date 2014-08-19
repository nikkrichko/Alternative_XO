package com.exe.mytestapp.controller.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import com.exe.mytestapp.R;
import com.exe.mytestapp.model.GrandFieldAdapter;

/**
 * Created by никита on 07.02.14.
 */
public class RulesClickListener implements View.OnClickListener {

    private GrandFieldAdapter adapter;
    private Activity activity;

    public RulesClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        new  AlertDialog.Builder(activity)
                .setMessage(R.string.rules)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();

    }
}
