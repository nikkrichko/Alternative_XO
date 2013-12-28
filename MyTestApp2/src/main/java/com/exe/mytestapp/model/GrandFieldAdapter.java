package com.exe.mytestapp.model;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.MoveController;

/**
 * Created by Nikita on 23.12.13.
 */
public class GrandFieldAdapter extends BaseAdapter {
    private final int FIELD_SIZE = 3;
    private PositionConverter converter = new PositionConverter();
    private Context context;
    private Player player1;
    private Player player2;
    private MoveController moveController;
    private GridView[][] gridViews = new GridView[FIELD_SIZE][FIELD_SIZE];
    private BaseFieldAdapter[][] baseField = new BaseFieldAdapter[FIELD_SIZE][FIELD_SIZE];



    private Player[][] baseFieldType = new Player[FIELD_SIZE][FIELD_SIZE];

    public GrandFieldAdapter(Context context, Player player1, Player player2){
        this.context = context;
        this.player1 = player1;
        this.player2 = player2;
        moveController = new MoveController(this, this.player1, this.player2);
    }

    public GrandFieldAdapter(Context context, MoveController moveController) {
        this.context = context;
        this.moveController = moveController;
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
        LinearLayout linearLayout = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.grid_view_item, null);
        GridView gridView = (GridView) linearLayout.findViewById(R.id.grid_in_big);
        converter.converterPositionToXY(position);
        int x = converter.getX();
        int y = converter.getY();

        gridViews[x][y] = gridView;
        if(baseField[x][y] == null)
            baseField[x][y] = new BaseFieldAdapter(context, moveController);


        checkAndCHangeColor(x, y);
        gridView.setAdapter(baseField[x][y]);

        baseField[x][y].notifyDataSetInvalidated();


        return linearLayout;
    }

    public void setGridViewsBachgroundToDarkGrey(){
        for (int i=0; i<3; i++){
            for (int j=0;j<3; j++){
                gridViews[i][j].setBackgroundResource(R.color.soft_blue);
            }
        }
        notifyDataSetInvalidated();
    }

    public void changegridViewsColorToSoftBlue(int x, int y){
        gridViews[x][y].setBackgroundResource(R.color.soft_blue);
    }

    public void changegridViewsColorToSoftgray(int x, int y){
        gridViews[x][y].setBackgroundResource(R.color.dark_grey);
    }

    private void checkAndCHangeColor(int x, int y){
        if (baseField[x][y].isAdapterNowActivity()){
            changegridViewsColorToSoftBlue(x, y);
        } else {
            changegridViewsColorToSoftgray(x, y);
        }
    }

    public void changeColorInOneGridView(int x, int y){
        setGridViewsBachgroundToDarkGrey();
        changegridViewsColorToSoftBlue(x, y);
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
        Toast toast = Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
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
