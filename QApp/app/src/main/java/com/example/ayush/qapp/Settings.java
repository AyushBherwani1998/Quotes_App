package com.example.ayush.qapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    int spinnerPosition;
    public static int textSize;
    public static int backgroundId;
    TextView mSolidColors;
    TextView mSolidColorsSub;
    TextView mThemeLibrarySub;
    TextView mThemeLibrary;
    TextView mDefaultTheme;
    TextView mDefaultThemeSub;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);


        spinner = findViewById(R.id.spinner);
        mSolidColors = findViewById(R.id.solidColor);
        mThemeLibrary = findViewById(R.id.ThemeLibrary);
        mSolidColorsSub = findViewById(R.id.textView14);
        mThemeLibrarySub = findViewById(R.id.textView16);
        mDefaultTheme = findViewById(R.id.defaulTheme);
        mDefaultThemeSub = findViewById(R.id.textView20);

        loadData();

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.textsize_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(spinnerPosition);

        mSolidColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,solidColors.class));
            }
        });

        mSolidColorsSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,solidColors.class));
            }
        });

        mThemeLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,ThemeLibrary.class));
                Toast.makeText(getApplicationContext(),"Loading Themes",Toast.LENGTH_SHORT).show();

            }
        });

        mThemeLibrarySub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,ThemeLibrary.class));
                Toast.makeText(getApplicationContext(),"Loading Themes",Toast.LENGTH_SHORT).show();


            }
        });

        mDefaultTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundId = R.color.default_color;
                textSize = 14;
                spinnerPosition = 0;
                spinner.setSelection(spinnerPosition);
                Toast.makeText(getApplicationContext(),"Default Theme Applied",Toast.LENGTH_SHORT).show();
            }
        });

        mDefaultThemeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundId = R.color.default_color;
                textSize = 14;
                spinnerPosition = 0;
                spinner.setSelection(spinnerPosition);
                Toast.makeText(getApplicationContext(),"Default Theme Applied",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String temp = parent.getItemAtPosition(position).toString();
        if(temp.equals("default")){
            textSize = 14;
        }else{
            textSize = Integer.parseInt(temp);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void loadData(){
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        spinnerPosition = preferences.getInt("spinnerPos",0);
        textSize = preferences.getInt("textSize",textSize);
        backgroundId = preferences.getInt("backgroundId",R.color.default_color);
    }

    public void saveData(){
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putInt("spinnerPos",spinner.getSelectedItemPosition());
        editor.putInt("textSize",textSize);
        editor.putInt("backgroundId",backgroundId);
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

