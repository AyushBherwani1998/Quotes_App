package com.example.ayush.qapp;

import android.annotation.SuppressLint;


import android.content.Intent;


import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;


import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Favorite_Quotes extends AppCompatActivity {
    int randomCategory;
    public  static Button mExploreButton;
    public static LinkedList<String> FavoriteQuotes;
    ConstraintLayout constraintLayout;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
        mExploreButton = findViewById(R.id.exploreButton);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundResource(Settings.backgroundId);
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapterFavortieQuotes( FavoriteQuotes,this));

        if(FavoriteQuotes.isEmpty()){
            mExploreButton.setVisibility(View.VISIBLE);
        }

        mExploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomCategory = (int) (Math.random()*7);
                switch (randomCategory){
                    case 0:
                        startActivity(new Intent(Favorite_Quotes.this,Inspiration_Quotes.class));
                        finish();
                        break;
                    case 1:
                        startActivity(new Intent(Favorite_Quotes.this,Motivational_Quotes.class));
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(Favorite_Quotes.this,Love_Quotes.class));
                        finish();
                        break;
                    case 3:
                        startActivity(new Intent(Favorite_Quotes.this,Friendship_Quotes.class));
                        finish();
                        break;
                    case 4:
                        startActivity(new Intent(Favorite_Quotes.this,Breakup_Quotes.class));
                        finish();
                        break;
                    case 5:
                        startActivity(new Intent(Favorite_Quotes.this,Failure_Quotes.class));
                        finish();
                        break;
                    case 6:
                        startActivity(new Intent(Favorite_Quotes.this,Witty_Quotes.class));
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            return true;
        }


        if(id == R.id.action_help){
            startActivity(new Intent(this,Help.class));
            return true;
        }
        if(id == R.id.action_settings){
            startActivity(new Intent(Favorite_Quotes.this,Settings.class));
            finish();
            return true;
        }
        if(id == R.id.action_feedback){
            String subject = "Feedback";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ayush.bherwani1998@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,"");
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"No Email App Detected",Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        if(id == R.id.action_rateus){
            startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ayushbherwani.ayush.qapp")));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        ArrayList<String> arrayList = new ArrayList<String>(FavoriteQuotes);
        String json = gson.toJson(arrayList);
        editor.putString("favoriteQuotes",json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        Settings.backgroundId = preferences.getInt("backgroundId",R.color.default_color);
        Settings.textSize = preferences.getInt("textSize",14);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoriteQuotes", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList;
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            FavoriteQuotes = new LinkedList<>();
        } else {
            FavoriteQuotes = new LinkedList<>(arrayList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}



