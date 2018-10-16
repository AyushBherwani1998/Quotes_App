package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.MessageQueue;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Love_Quotes extends AppCompatActivity {

    public static String LoveQuotes[] = {
            "Fall in love with the person who enjoys your madness, not an idiot who forces you to be normal.",
            "Love is more than three words mumbled before bedtime. Love is sustained by action, a pattern of devotion in things we do for each other every day",
            "It is so tiring to hate someone you love.",
            "Seeing you is like seeing a ray of light in darkness. Oh how you are the hope that keeps me going with my blue life.",
            "There must be those among whom we can sit down and weep and still be counted as warriors.",
            "I wonder how many people don't get the one they want, but end up with the one they're supposed to be with.",
            "Only people who are capable of loving strongly can also suffer great sorrow, but this same necessity of loving serves to counteract their grief and heals them.",
            "I just want you to know that you’re very special… and the only reason I’m telling you is that I don’t know if anyone else ever has.",
            "No matter how plain a woman may be, if truth and honesty are written across her face, she will be beautiful.",
            "More smiling, less worrying. More compassion, less judgment. More blessed, less stressed. More love, less hate.",
            "True love is not so much a matter of romance as it is a matter of anxious concern for the well-being of one's companion.",
            "There is a candle in your heart, ready to be kindled. There is a void in your soul, ready to be filled. You feel it, don't you?",
            "To love and win is the best thing. To love and lose, the next best.",
            "You only need one man to love you. But him to love you free like a wildfire, crazy like the moon, always like tomorrow, sudden like an inhale and overcoming like the tides. Only one man and all of this.",
            "Everybody is special. Everybody. Everybody is a hero, a lover, a fool, a villain. Everybody. Everybody has their story to tell.",
            "We accept their flaws and the shines, With greatest pride of all times.",
            "Do not seek the because - in love there is no because, no reason, no explanation, no solutions.",
            "There is the great lesson of 'Beauty and the Beast,' that a thing must be loved before it is lovable.",
            "In a world painted in darkness I could always find light in you.",
            "Perhaps we were friends first and lovers second. But then perhaps this is what lovers are",
            "And I just want to wake up next to you, exhausted with the adventures from the night before still lingering on our skin.",
            "Falling skies and fading stars, and there you are, smiling like it changes nothing",
            "Whatever may come between us will never break us apart because love is stronger than anything",
            "Unexpressed emotions will never die. They are buried alive and they will come forth, later, in uglier ways",
            "Real women stay and commit. Girls come and go. Boys play around. Real men settle down.",
            "One of the best feelings is finding someone who really gets you. A person who lets you be vulnerable and honest. The kind of person who encourages you to push past your flaws because they accept you as you are. ",
            "You must understand I’m a little lost in the head so I’ll ask you things but I’m not judging you, I’m understanding you.",
            "After all, damn it, what does being in love mean if you can’t trust a person.",
            "A love like that was a serious illness, an illness from which you never entirely recover.",
            "i’m glad that every single particle in our universe did exactly what it did, because it all led up to me meeting you",
            "Okay but how do you casually tell someone their existence is the highlight of your day? ",
            "It’s beautiful when two souls fit together so seamlessly that it’s impossible to tell where one ends and the other begins. The world may be in chaos, but with one another, they find peace.",
            "True love doesn’t mean being inseparable, it means being separated and nothing changes.",
            "Every night, I wish to be cuddled up and drifting off to sleep safe in your arms instead of by myself, wrapped in a blanket.",
            "Don’t turn a minor disagreement into a flat-out war, sometimes you just have to let it go",
            "The problem with connected hearts is that the strings tend to tangle. While it takes years to connect, it only takes one second, one wrong move, for it to snap",
            "To love someone is to show to them their beauty, their worth and their importance.",
            "She had this way of always finding the good and believing in everything despite all that she had seen. And that is what I loved the most - the pure magic of her undying hope.",
            "Your eyes had the power to catch me and make my world to be filled with exotic colors.",
            "To love a person it to see all of their magic, and to remind them of it when they have forgotten.",
            "How could I not fall for you when your eyes had the night sky printed on them?",
            "Life has taught us that love does not consist in gazing at each other but in looking outward together in the same direction",
            "I would follow you down to death if you asked me, just to make sure the Devil didn’t have his way with you.",
            "Your eyes were my favorite color and the sound of your voice was the best tune. Slowly, little by little, everything about you became my favorite. Starting with those shining eyes.",
            "Of course I care about you, otherwise I wouldn’t be so afraid of losing you as soon as I saw you.",
            "You practically held the knife in your hand and asked why I was bleeding. Every single one of you.",
            "And there you came breaking down the guard, I had put up just be sure to not break my fragile heart",
            "Of all of the millions of shades of colors I’ve ever seen, none of them is more beautiful than your eyes",
            "How amazing it is to find someone who wants to hear about all the things that go on in your head.",
            "One day love met friendship. Love asked, why do you exist when I already exist? Friendship replied, to put a smile where you’ve left tears.",
            "It’s easier to turn a friendship into love than to turn love into a friendship."
    };

    Button mNextQuote;
    Button mLastQuote;
    ImageButton mShareButton;
    TextView mQuoteTextView;
    static int i = 0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNextQuote = findViewById(R.id.nextQuoteButoon);
        mLastQuote = findViewById(R.id.lastQuoteButton);
        mShareButton = findViewById(R.id.shareButton);
        mQuoteTextView = findViewById(R.id.QuoteTextView);
        ConstraintLayout constraintLayout = findViewById(R.id.mainView);


        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);

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

        mQuoteTextView.setText(LoveQuotes[i]);

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(Love_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }else{
                    i=LoveQuotes.length-1;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<LoveQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(LoveQuotes[i]);
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

        mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(Love_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }else{
                    i=LoveQuotes.length-1;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<LoveQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(LoveQuotes[i]);
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

        mNextQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<LoveQuotes.length-1){
                    i+=1;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }
            }
        });


        mLastQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }else if(i==0){
                    i=LoveQuotes.length-1;
                    mQuoteTextView.setText(LoveQuotes[i]);
                }
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(Love_Quotes.this)
                        .setType("text/plain")
                        .setText(mQuoteTextView.getText().toString())
                        .setChooserTitle("Share this Quote with")
                        .startChooser();
            }
        });
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

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
