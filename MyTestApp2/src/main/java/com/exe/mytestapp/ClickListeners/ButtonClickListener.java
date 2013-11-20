package com.exe.mytestapp.ClickListeners;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.exe.mytestapp.R;

import org.w3c.dom.Text;

/**
 * Created by Nikita on 09.11.13.
 */
public class ButtonClickListener implements View.OnClickListener{

    private TextView mText;

    public ButtonClickListener(final TextView mText) {
        this.mText = mText;
    }




    @Override
    public void onClick(View v){

        Button button = (Button)v;
        mText.setText("other" + button.getId());



//        Button button = (Button)v;
//        button.setText("click");

    }
}
