package com.example.ayush.qapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ThemeLibrary extends AppCompatActivity {
    GridView mThemeLibrary;
    int theme[] = {

    R.drawable.android_beach_two,R.drawable.android_five,R.drawable.android_four,R.drawable.android_three,R.drawable.android_stars,
    R.drawable.android_two,R.drawable.android_one,R.drawable.android_six,R.drawable.iii,R.drawable.android_leaves,
    R.drawable.android_beach


};
@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theme_library2);
                mThemeLibrary=findViewById(R.id.grid_view_themeLibrary);
                float density=this.getResources().getDisplayMetrics().density;
                mThemeLibrary.setAdapter(new GridViewImageAdapter(this,theme));

                setContentView(R.layout.activity_theme_library);
                mThemeLibrary=findViewById(R.id.grid_view_themeLibrary);
                mThemeLibrary.setAdapter(new GridViewAdapter(this,theme));
                mThemeLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener(){
@Override
public void onItemClick(AdapterView<?> parent,View view,int position,long id){
        Settings.backgroundId=theme[position];
        Toast.makeText(getApplicationContext(),"Theme Changed",Toast.LENGTH_SHORT).show();
        }
        });
        }

public void saveData(){
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putInt("backgroundId",Settings.backgroundId);
        editor.apply();
        }

@Override
protected void onPause(){
        super.onPause();
        saveData();
        }

@Override
protected void onDestroy(){
        super.onDestroy();
        saveData();
        }
}

