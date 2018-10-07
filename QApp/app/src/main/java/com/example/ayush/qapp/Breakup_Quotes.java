package com.example.ayush.qapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.MessageQueue;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Breakup_Quotes extends AppCompatActivity {

    private String BreakupQuotes[] = {
            "She was an incomplete human yet a complete catastrophe, one that I curse and crave.",
            "First best is falling in love. Second best is being in love. Least best is falling out of love. But any of it is better than never having been in love.",
            "Healing is a choice. It is not an easy one because it takes work to turn around your habits. But keep making the choice and shifts will happen",
            "The worst feeling when someone makes you feel special, then suddenly leaves you hanging and you have to act like you don’t care at all",
            "Never wish them pain. That’s not who you are. If they caused you pain they must have pain inside. Wish them healing",
            "We all go through phases in life. If you are not okay now, it’s okay. You will find your way",
            "Stop trying to change someone who doesn’t want to change. Stop giving chances to someone who abuses your forgiveness. Stop running back to the place where your heart ran from. Stop trusting their words and ignoring their actions. Stop breaking your own heart.",
            "It happens to everyone as they grow up. You find out who you are and what you want, and then you realize that people you’ve known forever don’t see things the way you do. So you keep the wonderful memories, but find yourself moving on.",
            "If you can’t do anything about it then let it go. Don’t be a prisoner to things you can’t change.",
            "Remember that the best relationship is one in which your love for each other exceeds your need for each other.",
            "The poison leaves bit by bit, not all at once. Be patient. You are healing.",
            "Distance doesn’t ruin a relationship. Doubts do.",
            "The past is a place of learning, not a place of living.",
            "Strong enough to walk away, Broken enough to look back"
    };

    Button mNextButton;
    Button mLastButton;
    ImageButton mShareButton;
    TextView mQuoteTextView;
    static  int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakup__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLastButton = findViewById(R.id.lastQuoteButton);
        mNextButton = findViewById(R.id.nextQuoteButoon);
        mShareButton =  findViewById(R.id.shareButton);
        mQuoteTextView = findViewById(R.id.QuoteTextView);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);

        mQuoteTextView.setText(BreakupQuotes[i]);

        mQuoteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copied Quote",mQuoteTextView.getText().toString());
                assert clipboardManager != null;
                clipboardManager.setPrimaryClip(clipData);
                displayToast("Copied to Clipboard");
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder
                .from(Breakup_Quotes.this)
                .setType("text/plain")
                .setText(mQuoteTextView.getText().toString())
                .setChooserTitle("Share this Quote with")
                .startChooser();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<BreakupQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(BreakupQuotes[i]);
                }else {
                    i=0;
                    mQuoteTextView.setText(BreakupQuotes[i]);
                }
            }
        });

        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(BreakupQuotes[i]);
                }else {
                    i=BreakupQuotes.length-1;
                    mQuoteTextView.setText(BreakupQuotes[i]);
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Favorite_Quotes.FavoriteQuotes.contains(mQuoteTextView.getText().toString())){
                    Favorite_Quotes.FavoriteQuotes.addLast(mQuoteTextView.getText().toString());
                    Snackbar.make(view, "Added to Favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "Already Added to Favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            startActivity(new Intent(this,Favorite_Quotes.class));
            return true;
        }

        if(id == R.id.action_aboutUs){
            startActivity(new Intent(this,About_US.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
