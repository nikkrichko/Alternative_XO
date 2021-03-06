package com.exe.mytestapp.model;

import android.content.Context;
import android.graphics.Point;
import android.view.*;
import android.widget.*;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.MoveController;
import com.exe.mytestapp.controller.listeners.ButtonXOClickListener;

/**
 * Created by Nikita on 17.12.13.
 */
public class BaseFieldAdapter extends BaseAdapter {

    private int FIELD_SIZE = 3;


    private Player fieldType;
    private Player[][] field = new Player[FIELD_SIZE][FIELD_SIZE];
    private PositionConverter converter = new PositionConverter();
    private Context context;
    private Display display;
    Player currentPlayer;
    private boolean adapterNowActivity = true;
    private MoveController moveController;
    private int bothSides;
    Rules rules;

    public BaseFieldAdapter(Context context, MoveController moveController, Display display, Rules rules) {
        this.context = context;
        this.moveController = moveController;
        this.currentPlayer = this.moveController.getCurrentPlayer();
        this.display = display;
        this.rules = rules;
    }


    @Override
    public int getCount() {
        return FIELD_SIZE * FIELD_SIZE;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(17);
        ImageButton imageButton = new ImageButton(context);
        converter.converterPositionToXY(position);
        int x = converter.getX();
        int y = converter.getY();
        imageButton.setOnClickListener(new ButtonXOClickListener(this, context, moveController, x, y));
        activeDeactivate(imageButton);
        checkAndDeactivetedImageButton(imageButton);
        if (field[x][y] != null)
            imageButton.setImageResource(field[x][y].getImage());
        setBackgroundIfWinOnField(imageButton);
        linearLayout.addView(imageButton, getGridBothSides(), getGridBothSides());
        return linearLayout;
    }


    private int getGridBothSides() {
        Point size = new Point();
        display.getSize(size);
        int x = size.x;
        bothSides = (x / 3 - 5) / 3;
        return bothSides;
    }

    public Player[][] getField() {
        return field;
    }

    public void setPlayerInPosition(int x, int y, Player player) {
        field[x][y] = player;

    }

    private void activeDeactivate(ImageButton imageButton) {
        if (isAdapterNowActivity() == true) {
            imageButton.setClickable(true);
            imageButton.setActivated(true);
        }
        if (isAdapterNowActivity() == false) {
            imageButton.setClickable(false);
            imageButton.setActivated(false);
        }
    }

    private void setDarkBackgroundForButton(ImageButton imageButton) {
        imageButton.setBackgroundColor(R.color.dark_grey);
    }

    private void setBlueBackgroundForButton(ImageButton imageButton) {
        imageButton.setBackgroundColor(R.color.blue);
    }

    private void setRedBackgroundForButton(ImageButton imageButton) {
        imageButton.setBackgroundColor(R.color.red);
    }

    private void setBackgroundIfWinOnField(ImageButton imageButton) {
        if (fieldType != null){
            if (fieldType.getFigure() == PlayersXO.O) {
                setBlueBackgroundForButton(imageButton);
            }
            if (fieldType.getFigure() == PlayersXO.X){
                setRedBackgroundForButton(imageButton);
            }
        }
    }

    public void setBigFigure() {
        resetField();
        setAllField();
        if (moveController.getCurrentPlayer().getFigure() == PlayersXO.O) {
            setZero();
            setFieldType();
        }
        if (moveController.getCurrentPlayer().getFigure() == PlayersXO.X) {
            setCross();
            setFieldType();
        }
        changeAdapterActivityFalse();
        notifyDataSetInvalidated();
    }

    private void checkAndDeactivetedImageButton(ImageButton imageButton) {
        if (fieldType != null) {
            imageButton.setClickable(false);
            imageButton.setActivated(false);
        }
    }

    private void setAllField() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                field[x][y] = moveController.getCurrentPlayer();
            }
        }

    }

    private void setZero() {
        field[1][1] = null;
    }

    private void setCross() {
        field[1][0] = null;
        field[0][1] = null;
        field[2][1] = null;
        field[1][2] = null;
    }

    public int getCurrentPlayerImage() {
        return currentPlayer.getImage();
    }

    private boolean checkForReset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 0; j++) {
                field[i][j] = null;
                return false;
            }
        }
        return true;
    }

    public void resetField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = null;
            }
        }
        adapterNowActivity = true;
        fieldType = null;
        notifyDataSetInvalidated();
    }

    public void changeAdapterActivityFalse() {
        this.adapterNowActivity = false;
    }

    public void changeAdapterActivityTrue() {
        this.adapterNowActivity = true;
    }

    public boolean isAdapterNowActivity() {
        return adapterNowActivity;
    }

    public Player getFieldType() {
        return fieldType;
    }

    public void setFieldType() {
        this.fieldType = moveController.getCurrentPlayer();
    }

    public void noteWindowWIN() {
        Toast toast = Toast.makeText(context, moveController.getCurrentPlayer().getName() + " " + context.getString(R.string.little_win), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }


}
