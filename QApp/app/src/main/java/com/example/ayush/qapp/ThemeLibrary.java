package com.example.ayush.qapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class ThemeLibrary extends AppCompatActivity {
    GridView mThemeLibrary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_library);
        mThemeLibrary = findViewById(R.id.grid_view_themeLibrary);
    }
}
