package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Inspiration_Quotes extends AppCompatActivity {

    Button mNextQuote;
    Button mLastQuote;
    ImageButton mShareButton;
    TextView mQuoteTextView;
    static int i=0;

    private String InspirationalQuotes[] = {
            "Don’t rush into any kind of relationship. Work on yourself. Feel yourself, experience yourself, and love yourself. Do this first and you will soon attract that special loving other.",
            "Traveling is like flirting with life. It’s like saying, ‘I would stay and love you, but I have to go; this is my station.",
            "Don’t be the reason someone feels insecure. Be the reason someone feels seen, heard, and supported",
            "Self worth is so vital to happiness. If you don’t feel good about you, it’s hard to feel good about anything else.",
            "Learn to be alone, and to like it. There’s nothing more freeing and empowering than learning to like your own company.",
            "Make yourself a priority. At the end of the day, you’re your longest commitment.",
            "Your journey will be much lighter and easier if you don’t carry your past with you.",
            "You have to get to a point where your mood doesn’t shift based on the insignificant actions of someone else.",
            "If you are afraid of falling off the mountain, don’t allow it to deter you from climbing.Just remember to be cautious once you’re at the top.",
            "You have enemies? Good. That means you’ve stood up for something, sometime in your life."};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNextQuote = findViewById(R.id.nextQuoteButoon);
        mLastQuote = findViewById(R.id.lastQuoteButton);
        mShareButton = findViewById(R.id.shareButton);
        mQuoteTextView = findViewById(R.id.QuoteTextView);
        ConstraintLayout constraintLayout = findViewById(R.id.mainView);

        mQuoteTextView.setText(InspirationalQuotes[i]);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(Inspiration_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }else{
                    i=InspirationalQuotes.length-1;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<InspirationalQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }
            }

            public void onSwipeTop(){
                if(!Favorite_Quotes.FavoriteQuotes.contains(mQuoteTextView.getText().toString())){
                    Favorite_Quotes.FavoriteQuotes.addLast(mQuoteTextView.getText().toString());
                    Snackbar.make(getWindow().getDecorView().getRootView(),"Added to Favorties",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(getWindow().getDecorView().getRootView(),"Already Added to Favorites",Snackbar.LENGTH_LONG).show();
                }

            }

            public void onSwipeBottom(){
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copied Quote",mQuoteTextView.getText().toString());
                assert clipboardManager!=null;
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

        mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(Inspiration_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }else{
                    i=InspirationalQuotes.length-1;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<InspirationalQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }
            }

            public void onSwipeTop(){
                if(!Favorite_Quotes.FavoriteQuotes.contains(mQuoteTextView.getText().toString())){
                    Favorite_Quotes.FavoriteQuotes.addLast(mQuoteTextView.getText().toString());
                    Snackbar.make(getWindow().getDecorView().getRootView(),"Added to Favorites",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(getWindow().getDecorView().getRootView(),"Already Added to Favorites",Snackbar.LENGTH_LONG).show();
                }

            }

            public void onSwipeBottom(){
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copied Quote",mQuoteTextView.getText().toString());
                assert clipboardManager!=null;
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder
                        .from(Inspiration_Quotes.this)
                        .setType("text/plain")
                        .setText(mQuoteTextView.getText().toString())
                        .setChooserTitle("Share this Quote with")
                        .startChooser();
            }
        });

        mNextQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<InspirationalQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }
            }
        });

        mLastQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
                }else {
                    i=InspirationalQuotes.length-1;
                    mQuoteTextView.setText(InspirationalQuotes[i]);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
