package com.example.ayush.qapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class About_US extends AppCompatActivity {

    TextView textView;
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);
        textView = findViewById(R.id.textView3);
        textView.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
    }
}
