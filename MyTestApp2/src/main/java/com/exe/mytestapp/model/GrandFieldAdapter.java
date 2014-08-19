package com.exe.mytestapp.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.view.*;
import android.widget.*;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.MoveController;
import com.exe.mytestapp.controller.listeners.ExitClickListener;
import com.exe.mytestapp.view.MyActivity;

/**
 * Created by Nikita on 23.12.13.
 */
public class GrandFieldAdapter extends BaseAdapter {
    private final int FIELD_SIZE = 3;
    private PositionConverter converter = new PositionConverter();
    private Context context;
    private MoveController moveController;
    private GridView[][] gridViews = new GridView[FIELD_SIZE][FIELD_SIZE];
    private BaseFieldAdapter[][] baseField = new BaseFieldAdapter[FIELD_SIZE][FIELD_SIZE];
    private Display display;
    private int bothSides;
    private MyActivity myActivity;
    Rules rules;


    private Player[][] baseFieldType = new Player[FIELD_SIZE][FIELD_SIZE];

    public GrandFieldAdapter(Context context, Player player1, Player player2, MoveController.IMoveListener moveListener, Display display, MyActivity myActivity){
        this.context = context;
        this.myActivity = myActivity;
        this.rules = myActivity.getRules();
        moveController = new MoveController(this, player1, player2, moveListener, rules);
        this.display = display;

    }

    @Override
    public int getCount() {
        return FIELD_SIZE*FIELD_SIZE;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout = new LinearLayout(context);
        GridView gridView = new GridView(context);
        gridView.setNumColumns(3);
        linearLayout.addView(gridView, getGridBothSides(), getGridBothSides());
        converter.converterPositionToXY(position);
        int x = converter.getX();
        int y = converter.getY();
        gridViews[x][y] = gridView;
        if(baseField[x][y] == null)
            baseField[x][y] = new BaseFieldAdapter(context, moveController, display, rules);
        checkAndCHangeColor(x, y);
        checkWinFieldAndChangeWinnerCollors(x, y);
        gridView.setAdapter(baseField[x][y]);
        baseField[x][y].notifyDataSetInvalidated();
        return linearLayout;
    }

    private int getGridBothSides(){
        Point size = new Point();
        display.getSize(size);
        int x = size.x;
        bothSides = x / 3 - 5;
        return bothSides;
    }

    public void setGridViewsBachgroundToDarkGrey(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                gridViews[i][j].setBackgroundResource(R.color.soft_blue);
            }
        }
        notifyDataSetInvalidated();
    }

    public void changegridViewsColorToPlayerColor(int x, int y){
        if (moveController.getCurrentPlayer().getFigure() == PlayersXO.O){
            gridViews[x][y].setBackgroundResource(R.color.blue);
        }
        else {
            gridViews[x][y].setBackgroundResource(R.color.red);
        }

    }

    public void changegridViewsColorToSoftgray(int x, int y){
         gridViews[x][y].setBackgroundResource(R.color.dark_grey);
    }

    private void setBlueBackgroundForWinner(int x, int y) {
        gridViews[x][y].setBackgroundResource(R.color.blue);
    }

    private void setRedBackgroundForWinner(int x, int y) {
        gridViews[x][y].setBackgroundResource(R.color.red);
    }

    private void checkWinFieldAndChangeWinnerCollors(int x, int y){
        if (baseField[x][y].getFieldType() != null){
            if (baseField[x][y].getFieldType().getFigure() == PlayersXO.O){
                setBlueBackgroundForWinner(x, y);
            }
            if (baseField[x][y].getFieldType().getFigure() == PlayersXO.X){
                setRedBackgroundForWinner(x, y);
            }
        }
    }

    private void checkAndCHangeColor(int x, int y){
        if (baseField[x][y].isAdapterNowActivity()){
            changegridViewsColorToPlayerColor(x, y);
        }

        else {
            changegridViewsColorToSoftgray(x, y);
        }
    }

    public void changeColorInOneGridView(int x, int y){
        setGridViewsBachgroundToDarkGrey();
        changegridViewsColorToPlayerColor(x, y);
    }

    public void changeBaseFieldActivity(int x, int y){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                this.baseField[i][j].changeAdapterActivityFalse();
            }
        }
        this.baseField[x][y].changeAdapterActivityTrue();
        notifyDataSetInvalidated();
    }

    public void changeBaseFieldActivityTrueToFree(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                if (this.baseField[i][j].getFieldType() == null)
                    this.baseField[i][j].changeAdapterActivityTrue();
            }
        }
    }

    public void changeBaseFieldActivityFalseToALL(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                this.baseField[i][j].changeAdapterActivityFalse();
            }
        }
        noteWindowGameOver();
    }

    public void resetField(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                baseField[i][j].resetField();
            }
        }
        notifyDataSetInvalidated();
    }

    public BaseFieldAdapter[][] getBaseField() {
        return baseField;
    }

    private boolean checkForReset(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                baseField[i][j] = null;
                return false;
            }
        }
        return true;
    }

    private void noteWindowGameOver(){

            new  AlertDialog.Builder(context).setTitle(moveController.getCurrentPlayer().getName()
                    + " " + context.getString(R.string.Are_win))
                    .setIcon(moveController.getCurrentPlayer().getImage())
                    .setMessage( context.getString(R.string.Game_over) + "\n\n" + context.getString(R.string.Play_again))
                    .setNegativeButton(R.string.No, (DialogInterface.OnClickListener) new ExitClickListener(context))
                    .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            resetField();
                            dialogInterface.cancel();
                        }
                    })
                    .setCancelable(false).show();

    }

    public BaseFieldAdapter getBaseFieldFromGrand(){
        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){
                if (baseField[x][y].isAdapterNowActivity())
                return baseField[x][y];
            }
        }
        return null;
    }

    public MoveController getMoveController() {
        return moveController;
    }

    public Player[][] getBaseFieldType() {
        return baseFieldType;
    }

    public void setBaseFieldType() {
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                baseFieldType[i][j] = baseField[i][j].getFieldType();
            }
        }
    }

    public void changeColor(int x, int y){

    }
}
