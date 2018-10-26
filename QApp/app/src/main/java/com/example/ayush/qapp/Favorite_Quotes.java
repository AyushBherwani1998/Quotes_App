package com.example.ayush.qapp;

import android.annotation.SuppressLint;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;

import android.graphics.Typeface;

import android.net.Uri;
import android.os.Bundle;


import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.LinkedList;
import java.util.Objects;

public class Favorite_Quotes extends AppCompatActivity {

    public static LinkedList<String> FavoriteQuotes;
    Button mNextButton;
    Button mLastButton;
    ImageButton mShareButton;
    TextView mQuoteTextView;
    Button mExploreButton;
    int randomCategory;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    ConstraintLayout constraintLayout;
    public static int i = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, "ca-app-pub-1203140157527769~6707095223");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1203140157527769/2197128284");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mExploreButton = findViewById(R.id.exploreButton);
        mNextButton = findViewById(R.id.nextQuoteButoon);
        mQuoteTextView = findViewById(R.id.QuoteTextView);
        mLastButton = findViewById(R.id.lastQuoteButton);
        mShareButton = findViewById(R.id.shareButton);
        constraintLayout = findViewById(R.id.mainView);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       if(!FavoriteQuotes.isEmpty()){
           mQuoteTextView.setText(FavoriteQuotes.get(i));
       }else{
           mNextButton.setVisibility(View.INVISIBLE);
           mShareButton.setVisibility(View.INVISIBLE);
           mLastButton.setVisibility(View.INVISIBLE);
           mExploreButton.setVisibility(View.VISIBLE);
           mQuoteTextView.setText(R.string.no_favorites);
       }

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(Favorite_Quotes.this){
            public void onSwipeRight() {
                if(!FavoriteQuotes.isEmpty()){
                    if(i>0){
                        i--;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }else {
                        i = FavoriteQuotes.size()-1;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }
                }
            }

            public void onSwipeLeft() {
               if(!FavoriteQuotes.isEmpty()){
                   if(i<FavoriteQuotes.size()-1){
                       i++;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       i=0;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
            }

            public void onSwipeTop(){
                if(!FavoriteQuotes.isEmpty()){
                    Snackbar.make(fab,"Deleted From Favorites",Snackbar.LENGTH_LONG).show();
                    FavoriteQuotes.remove(mQuoteTextView.getText().toString());
                    if(FavoriteQuotes.isEmpty()){
                        mNextButton.setVisibility(View.INVISIBLE);
                        mShareButton.setVisibility(View.INVISIBLE);
                        mLastButton.setVisibility(View.INVISIBLE);
                        mQuoteTextView.setText(R.string.no_favorites);
                    }else if(!FavoriteQuotes.isEmpty() && i>=FavoriteQuotes.size()){
                        i--;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }else {
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }
                }
            }
            public void onSwipeBottom(){
                if(!FavoriteQuotes.isEmpty()){
                    ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Copied Quote",mQuoteTextView.getText().toString());
                    assert clipboardManager!=null;
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show();
                }
            }
        });

       mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(Favorite_Quotes.this){
           public void onSwipeRight() {
               if(!FavoriteQuotes.isEmpty()){
                   if(i>0){
                       i--;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       i = FavoriteQuotes.size()-1;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
           }

           public void onSwipeLeft() {
               if(!FavoriteQuotes.isEmpty()){
                   if(i<FavoriteQuotes.size()-1){
                       i++;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       i=0;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
           }

           public void onSwipeTop(){
               if(!FavoriteQuotes.isEmpty()){
                   Snackbar.make(fab,"Deleted From Favorites",Snackbar.LENGTH_LONG).show();
                   FavoriteQuotes.remove(mQuoteTextView.getText().toString());
                   if(FavoriteQuotes.isEmpty()){
                       mNextButton.setVisibility(View.INVISIBLE);
                       mShareButton.setVisibility(View.INVISIBLE);
                       mLastButton.setVisibility(View.INVISIBLE);
                       mExploreButton.setVisibility(View.VISIBLE);
                       mQuoteTextView.setText(R.string.no_favorites);
                   }else if(!FavoriteQuotes.isEmpty() && i>=FavoriteQuotes.size()){
                       i--;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
           }

           public void onSwipeBottom(){
             if(!FavoriteQuotes.isEmpty()){
                 ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                 ClipData clipData = ClipData.newPlainText("Copied Quote",mQuoteTextView.getText().toString());
                 assert clipboardManager!=null;
                 clipboardManager.setPrimaryClip(clipData);
                 Toast.makeText(getApplicationContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show();
             }
           }
       });

       Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
       mQuoteTextView.setTypeface(roboto);

       mNextButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(i<FavoriteQuotes.size()-1){
                   i++;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }else {
                   i=0;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }
           }
       });

       mLastButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(i>0){
                   i--;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }else {
                   i = FavoriteQuotes.size()-1;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }
           }
       });

       mShareButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ShareCompat.IntentBuilder
                       .from(Favorite_Quotes.this)
                       .setType("text/plain")
                       .setText(mQuoteTextView.getText().toString())
                       .setChooserTitle("Share this Quote with")
                       .startChooser();
           }
       });

       mExploreButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               randomCategory = (int) (Math.random()*7);
               switch (randomCategory){
                   case 0:
                       startActivity(new Intent(Favorite_Quotes.this,Inspiration_Quotes.class));
                       break;
                   case 1:
                       startActivity(new Intent(Favorite_Quotes.this,Motivational_Quotes.class));
                       break;
                   case 2:
                       startActivity(new Intent(Favorite_Quotes.this,Love_Quotes.class));
                       break;
                   case 3:
                       startActivity(new Intent(Favorite_Quotes.this,Friendship_Quotes.class));
                       break;
                   case 4:
                       startActivity(new Intent(Favorite_Quotes.this,Breakup_Quotes.class));
                       break;
                   case 5:
                       startActivity(new Intent(Favorite_Quotes.this,Failure_Quotes.class));
                       break;
                   case 6:
                       startActivity(new Intent(Favorite_Quotes.this,Witty_Quotes.class));
                       break;
               }
           }
       });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!FavoriteQuotes.isEmpty()){
                    Snackbar.make(view, "Deleted From Favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    FavoriteQuotes.remove(mQuoteTextView.getText().toString());
                    if(FavoriteQuotes.isEmpty()){
                        mNextButton.setVisibility(View.INVISIBLE);
                        mShareButton.setVisibility(View.INVISIBLE);
                        mLastButton.setVisibility(View.INVISIBLE);
                        mExploreButton.setVisibility(View.VISIBLE);
                        mQuoteTextView.setText(R.string.no_favorites);
                    }else if(!FavoriteQuotes.isEmpty() && i>=FavoriteQuotes.size()){
                        i--;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }else {
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }
                }else {
                    Snackbar.make(view, "No Quotes to Delete", Snackbar.LENGTH_LONG)
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
        if (id == R.id.action_favorite) {
            return true;
        }


        if(id == R.id.action_help){
            startActivity(new Intent(this,Help.class));
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onDestroy();
    }
}


