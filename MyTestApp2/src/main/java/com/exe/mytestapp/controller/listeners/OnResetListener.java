package com.exe.mytestapp.controller.listeners;

import android.view.View;

import com.exe.mytestapp.model.GrandFieldAdapter;
import com.exe.mytestapp.view.MyActivity;

/**
* Created by Nikita on 20.11.13.
*/
public class OnResetListener implements View.OnClickListener {

    private GrandFieldAdapter adapter;

    public OnResetListener(GrandFieldAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onClick(View view) {
        adapter.resetField();
    }
}
