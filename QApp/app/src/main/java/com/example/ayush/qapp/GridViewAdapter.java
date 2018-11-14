package com.example.ayush.qapp;

import android.content.Context;
<<<<<<< HEAD
import android.content.res.Resources;
import android.util.TypedValue;
=======
>>>>>>> master
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
<<<<<<< HEAD
            float density = context.getResources().getDisplayMetrics().density;
            if (density >= 4.0) {
                linearLayout.setLayoutParams(new GridView.LayoutParams(434,600));
            }
            else if (density >= 3.0) {
                linearLayout.setLayoutParams(new GridView.LayoutParams(325,450));
            }
            else {
                linearLayout.setLayoutParams(new GridView.LayoutParams(217,300));
            }
=======
            linearLayout.setLayoutParams(new GridView.LayoutParams((int)context.getResources().getDimension(R.dimen.grid_width),(int)context.getResources().getDimension(R.dimen.grid_heigth)));
            linearLayout.setPadding(8,8,8,8);
>>>>>>> master
        }else{
            linearLayout = (LinearLayout)convertView;
        }
        linearLayout.setBackgroundResource(background[position]);
        return linearLayout;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> master
