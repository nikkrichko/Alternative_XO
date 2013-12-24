package com.exe.mytestapp.model;

/**
 * Created by Nikita on 17.12.13.
 */
public class PositionConverter {
    private int FIELD_SIZE = 3;
    private int position;
    private int x;
    private int y;

    PositionConverter(){}

    PositionConverter(int position){
        this.position = position;
    }

    PositionConverter(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void converterXYtoPosition(int x, int y){
        this.position = x * FIELD_SIZE + y;
    }

    public void converterPositionToXY(int position){
        this.x = position % FIELD_SIZE;
        this.y = ((position - x) / FIELD_SIZE);
    }

    public int getPosition() {
        return position;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

// TODO tests for convertors
}
