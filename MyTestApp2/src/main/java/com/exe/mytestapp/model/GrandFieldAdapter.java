package com.exe.mytestapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.exe.mytestapp.R;
import com.exe.mytestapp.controller.listeners.ButtonXOClickListener;

/**
 * Created by Nikita on 23.12.13.
 */
public class GrandFieldAdapter extends BaseAdapter {
    private final int FIELD_SIZE = 3;
    private PositionConverter converter = new PositionConverter();
    private Context context;
    private Player player1;
    private Player player2;


    private MyBaseAdapter[][] grandField = new MyBaseAdapter[FIELD_SIZE][FIELD_SIZE];

    public GrandFieldAdapter(Context context, Player player1, Player player2){
        this.context = context;
        this.player1 = player1;
        this.player2 = player2;
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
//        GridView gridView = (GridView) LayoutInflater.from(context).inflate(R.layout.grid_view_item , null);
        converter.converterPositionToXY(position);
        int x = converter.getX();
        int y = converter.getY();


        if(grandField[x][y] == null)
            grandField[x][y] = new MyBaseAdapter(context, player1, player2);

        gridView.setAdapter(grandField[x][y]);
        grandField[x][y].notifyDataSetInvalidated();

        return linearLayout;
    }
}
