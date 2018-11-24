package com.example.ayush.qapp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int background[];

    public GridViewAdapter(Context context,int background[]){
        this.context = context;
        this.background = background;
    }

    @Override
    public int getCount() {
        return background.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            linearLayout = new LinearLayout(context);

            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels/3 - 10;
            int height = (int)(width*1.5);
            linearLayout.setLayoutParams(new GridView.LayoutParams(width,height));

        }else{
            linearLayout = (LinearLayout)convertView;
        }
        linearLayout.setBackgroundResource(background[position]);
        return linearLayout;
    }

}

