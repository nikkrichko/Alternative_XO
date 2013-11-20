package com.exe.mytestapp.ClickListeners;

import android.view.View;

import com.exe.mytestapp.MyActivity;

/**
 * Created by Nikita on 20.11.13.
 */
public class OnResetListener implements View.OnClickListener {

    private MyActivity mMyActivity;

    public OnResetListener(MyActivity myActivity) {
        mMyActivity = myActivity;
    }

    @Override
    public void onClick(View view) {
        mMyActivity.resetActivity();
    }
}
