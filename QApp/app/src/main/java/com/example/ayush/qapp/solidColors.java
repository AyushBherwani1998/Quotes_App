package com.example.ayush.qapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class solidColors extends AppCompatActivity {
    GridView mSolidColorGridView;
    int solidColors[] = {
            R.color.color1,  R.color.color2,  R.color.color3 ,R.color.color4,  R.color.color5,  R.color.color6,
            R.color.color7,  R.color.color8,  R.color.color9,  R.color.color10,  R.color.color11, R.color.color12,
            R.color.color13,  R.color.color14,  R.color.color15,  R.color.color16, R.color.color17, R.color.color18,
            R.color.color19
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_colors2);
        mSolidColorGridView = findViewById(R.id.grid_view_solidColors);
        mSolidColorGridView.setAdapter(new GridViewAdapter(this,solidColors));
        mSolidColorGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Settings.backgroundId = solidColors[position];
                Toast.makeText(getApplicationContext(),"Theme Changed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveData(){
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putInt("backgroundId",Settings.backgroundId);
        editor.apply();
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
