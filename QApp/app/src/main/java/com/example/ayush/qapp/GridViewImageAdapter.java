package com.example.ayush.qapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GridViewImageAdapter extends BaseAdapter {
    private Context context;
    private int background[];

    public GridViewImageAdapter(Context context,int background[]){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView==null){
            imageView = new ImageView(context);

            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels/3 - 10;
            int height = (int)(width*1.5);
        imageView.setLayoutParams(new GridView.LayoutParams(width,height));
        }else{
            imageView = (ImageView) convertView;
        }
        Glide.with(context).load(background[position]).apply(new RequestOptions().centerCrop()).into(imageView);
        return imageView;
    }


}
