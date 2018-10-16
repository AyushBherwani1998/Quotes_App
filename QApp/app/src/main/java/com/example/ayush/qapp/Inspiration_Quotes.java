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

    public static String InspirationalQuotes[] = {
            "Don’t rush into any kind of relationship. Work on yourself. Feel yourself, experience yourself, and love yourself. Do this first and you will soon attract that special loving other.",
            "Traveling is like flirting with life. It’s like saying, ‘I would stay and love you, but I have to go; this is my station.",
            "Don’t be the reason someone feels insecure. Be the reason someone feels seen, heard, and supported",
            "Self worth is so vital to happiness. If you don’t feel good about you, it’s hard to feel good about anything else.",
            "Learn to be alone, and to like it. There’s nothing more freeing and empowering than learning to like your own company.",
            "Make yourself a priority. At the end of the day, you’re your longest commitment.",
            "Your journey will be much lighter and easier if you don’t carry your past with you.",
            "You have to get to a point where your mood doesn’t shift based on the insignificant actions of someone else.",
            "If you are afraid of falling off the mountain, don’t allow it to deter you from climbing.Just remember to be cautious once you’re at the top.",
            "You have enemies? Good. That means you’ve stood up for something, sometime in your life.",
            "You've gotta dance like there's nobody watching,Love like you'll never be hurt,Sing like there's nobody listening,And live like it's heaven on earth.",
            "No one can make you feel inferior without your consent",
            "Darkness cannot drive out darkness: only light can do that. Hate cannot drive out hate: only love can do that",
            "Imperfection is beauty, madness is genius and it's better to be absolutely ridiculous than absolutely boring.",
            "There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.",
            "Fairy tales are more than true: not because they tell us that dragons exist, but because they tell us that dragons can be beaten.",
            "There is no greater agony than bearing an untold story inside you.",
            "When one door of happiness closes, another opens; but often we look so long at the closed door that we do not see the one which has been opened for us.",
            "So, this is my life. And I want you to know that I am both happy and sad and I'm still trying to figure out how that could be.",
            "You may say I'm a dreamer, but I'm not the only one. I hope someday you'll join us. And the world will live as one.",
            "You can't live your life for other people. You've got to do what's right for you, even if it hurts some people you love.",
            "Do what you feel in your heart to be right – for you’ll be criticized anyway.",
            "Happiness is not something ready made. It comes from your own actions.",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
            "First they ignore you. Then they ridicule you. And then they attack you and want to burn you. And then they build monuments to you.",
            "You can't stay in your corner of the Forest waiting for others to come to you. You have to go to them sometimes.",
            "Isn't it nice to think that tomorrow is a new day with no mistakes in it yet?",
            "In the end, we will remember not the words of our enemies, but the silence of our friends.",
            "Pain is inevitable. Suffering is optional.",
            "Talent hits a target no one else can hit. Genius hits a target no one else can see",
            "Prayer is not asking. It is a longing of the soul. It is daily admission of one's weakness. It is better in prayer to have a heart without words than words without a heart",
            "It isn't what you have or who you are or where you are or what you are doing that makes you happy or unhappy. It is what you think about it.",
            "Clouds come floating into my life, no longer to carry rain or usher storm, but to add color to my sunset sky.",
            "A painter should begin every canvas with a wash of black, because all things in nature are dark except where exposed by the light.",
            "All the darkness in the world cannot extinguish the light of a single candle.",
            "To me, Fearless is not the absense of fear. It's not being completely unafraid. To me, Fearless is having fears. Fearless is having doubts. To me, Fearless is living in spite of those things that scare you to death.",
            "Any fool can be happy. It takes a man with real heart to make beauty out of the stuff that makes us weep.",
            "You see things; you say, 'Why?' But I dream things that never were; and I say 'Why not?",
            "Understanding is the first step to acceptance, and only with acceptance can there be recovery.",
            "Even if you are on the right track, you’ll get run over if you just sit there.",
            "Letting go means to come to the realization that some people are a part of your history, but not a part of your destiny.",
            "Nothing in the world is ever completely wrong. Even a stopped clock is right twice a day.",
            "My concern is not whether God is on our side; my greatest concern is to be on God's side, for God is always right.",
            "Parents can only give good advice or put them on the right paths, but the final forming of a person's character lies in their own hands",
            "Incredible change happens in your life when you decide to take control of what you do have power over instead of craving control over what you don't",
            "We never know the quality of someone else's life, though we seldom resist the temptation to assume and pass judgement",
            "Earth provides enough to satisfy every man's needs, but not every man's greed",
            "No matter how your heart is grieving, if you keep on believing, the dreams that you wish will come true.",
            "Any book that helps a child to form a habit of reading, to make reading one of his deep and continuing needs, is good for him.",
            "Sometimes life knocks you on your ass... get up, get up, get up!!! Happiness is not the absence of problems, it's the ability to deal with them",
            "Well, we all make mistakes, dear, so just put it behind you. We should regret our mistakes and learn from them, but never carry them forward into the future with us."

    };

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
