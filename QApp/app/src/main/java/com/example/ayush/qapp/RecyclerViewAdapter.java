package com.example.ayush.qapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    int len;
    String data[];
    Context context;
    int lastPosition=-1;
    public RecyclerViewAdapter(String[] data, Context context){
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        holder.textView.setText(data[position]);
        final String q = data[position];
        if(!Favorite_Quotes.FavoriteQuotes.contains(q)){
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }else
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite_red_24dp);

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,data[position]);
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent,"Share this Quote with"));
            }
        });

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Favorite_Quotes.FavoriteQuotes.contains(q)){
                    Favorite_Quotes.FavoriteQuotes.addLast(q);
                    holder.favoriteButton.animate().rotation(360).start();
                    holder.favoriteButton.setImageResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(context,"Added To Favorites",Toast.LENGTH_SHORT).show();
                }else {
                    Favorite_Quotes.FavoriteQuotes.remove(q);
                    holder.favoriteButton.animate().rotation(-360).start();
                    holder.favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(context,"Deleted From Favorites",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(position >lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycelerview_animation);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageButton shareButton;
        ImageButton favoriteButton;
        TextView textView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            shareButton = itemView.findViewById(R.id.shareButton);
            textView = itemView.findViewById(R.id.textView);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}
