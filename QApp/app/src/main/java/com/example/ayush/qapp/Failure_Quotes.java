package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Failure_Quotes extends AppCompatActivity {
    public static String FailureQuotes[]={"If you have made serious mistakes, there is always another chance for you. What we call failure is not the falling down, but the staying down.",
            "Breathe. It’s only a bad day, not a bad life.",
            "Those who dare to fail miserably can achieve greatly.",
            "Failure is simply the opportunity to begin again, this time more intelligently.",
            "I avoid doing things, because if I do not do them, I can’t be said to fail at them.",
            "Think like a queen. A queen is not afraid to fail. Failure is just another stepping stone to greatness.",
            "Pass on what you have learned. Strength, mastery. But weakness, folly, failure also. Yes, failure most of all. The greatest teacher, failure is.",
            "Most people quit because they look how far they have to go, not how far they have come.",
            "It is through your failures and adversity that you learn more about yourself and what you are made of. So embrace failure,it is just part of your path to greatness.",
            "YOU ARE not the failures and mistakes of your past, you are the lessons and skills you’ve gleaned from it.",
            "If you’re not failing every now and again, it’s a sign you’re not doing anything very innovative.",
            "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.",
            "You are not allowed to quit. You may only quit after you have done it to the best of your ability, and decided to. Otherwise, it’s not quitting, it’s failing.",
            "Remember all the times you were a winner; the times when you did something you were proud of, even small things. Hold these feelings close to you, this joy and confidence.",
            "Fear of failure is one attitude that will keep you at the same point in your life.",
            "What terrifies me the most is how we foam at the mouth with envy when others succeed but sigh in relief when they are failing.",
            "There is only one thing that makes a dream impossible to achieve: the fear of failure.",
            "My fault, my failure, is not in the passions I have, but in my lack of control of them.",
            "Failure doesn’t define you. It’s what you do after you fail that determines whether you are a leader or a waste of perfectly good air.",
            "We all have talents that, sometimes, we never quite fulfill. We’re all scared, deep down, but maybe we just need to lay it on the line and explore our abilities and just not be afraid of failing.",
            "The hardest thing of all is, to know that you have failed, that your best efforts have been defeated, to not be able to stand it, to not be able to go on and yet to go on nonetheless.",
            "Success is not final, failure is not fatal: it is the courage to continue that counts.",
            "Failure Will Never Overtake Me If My Determination To Succeed Is Strong Enough.",
            "Failure is a part of life. Success teaches you nothing, but failure teaches you resilience. It teaches you to pick yourself up and try again.",
            "Always remember that the crowd that applauds your coronation is the same crowd that will applaud your beheading. People love a show.",
            "You literally have your whole life to try and try and try again. How could you even think of quitting after your first failure.",
            "In our culture, we grow up thinking that failure is a terrible thing, that it’s a setback, or worse, the end. Often it turns out to be the beginning of something better.",
            "We shall not grow wiser before we learn that much that we have done was very foolish.",
            "I’m useless. I can’t face anything. I’m not even sure if there’s a reason. Maybe I’m just weak by nature.",
            "I think perfection is ugly. Somewhere in the things humans make, I want to see scars, failure, disorder, distortion.",
            "No matter how many times you fall, do not stop. Keep falling but keep journeying as well. Fall down, get up again and go. Repeat.",
            "We fail. We trip. We get lost. We make mistakes. And little by little, one step at a time, we push forward. It’s all we can do. On our own two feet.",
            "Maybe this is who I really am. Not a loner, exactly. But someone who can be alone.",
            "Failure is what makes me stronger. Fear is what makes me run.",
            "It takes 20 years to build a reputation and five minutes to ruin it. If you think about that, you’ll do things differently.",
            "It’s fine to celebrate success but it is more important to heed the lessons of failure.",
            "Failure and success live next door to each other and they have no numbers at the door. You just knock. The thing that is the most sublime, is the thing that can be the most ridiculous. Always. So you are risking it.",
            "I’m the master of my own mass destruction but for the life of me I don’t know how to stop",
            "We all make mistakes, don’t we? But if you can’t forgive yourself, you’ll always be an exile in your own life.",
            "Your worth isn’t defined by whether or not someone else loves, sees, values, appreciates, or adores you, it’s inherent. You are worthy because you’re alive, regardless of your mistakes or failures.",
            "My fault, my failure, is not in the passions I have, but in my lack of control of them.",
            "Good writing is ambitious. Which means that good writers must be willing to fail.",
            "Practice is never a straight line to a fixed goal. It is always a mixture of moments of confusion and moments of clarity, periods of discouragement and periods of aspiration, times of feeling like a failure and times of going deeper.",
            "Commit as many mistakes as possible, remembering only one thing: don’t commit the same mistake again. And you will be growing.",
            "I guess you could call it a “failure”, but I prefer the term “learning experience.",
            "Everything worth doing takes time. You have to write a hundred bad songs before you write one good one. And you have to sacrifice a lot of things that you might not be prepared for. Like it or not, you are in this alone.",
            "Most people, after one success, are so cringingly afraid of doing less well that they rub off all the edge off their subsequent work.",
            "Failure is a prerequisite for great success. If you want to succeed faster, double your rate of failure.",
            "Socialism in general has a record of failure so blatant that only an intellectual could ignore or evade it.",
            "Failure is not fun. It can be awful. But living so cautiously that you never fail is worse.",
            "Never let success get to ur head; never let failure get to ur heart.",
            "Everyone has hit rock-bottom at some point in their lives. It’s important to remember that although it might not seem so, there is always light at the end of the tunnel. Don’t give up on your dreams"
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
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);


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

        mQuoteTextView.setText(FailureQuotes[i]);

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(Failure_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }else{
                    i=FailureQuotes.length-1;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<FailureQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }
            }

            public void onSwipeTop(){
                if(!Favorite_Quotes.FavoriteQuotes.contains(mQuoteTextView.getText().toString())){
                    Favorite_Quotes.FavoriteQuotes.addLast(mQuoteTextView.getText().toString());
                    Snackbar.make(fab,"Added to Favorites",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(fab,"Already Added to Favorites",Snackbar.LENGTH_LONG).show();
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

        mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(Failure_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }else{
                    i=FailureQuotes.length-1;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<FailureQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }
            }

            public void onSwipeTop(){
                if(!Favorite_Quotes.FavoriteQuotes.contains(mQuoteTextView.getText().toString())){
                    Favorite_Quotes.FavoriteQuotes.addLast(mQuoteTextView.getText().toString());
                    Snackbar.make(fab,"Added to Favorites",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(fab,"Already Added to Favorites",Snackbar.LENGTH_LONG).show();
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
                if(i<FailureQuotes.length-1){
                    i+=1;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }
            }
        });


        mLastQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }else if(i==0){
                    i=FailureQuotes.length-1;
                    mQuoteTextView.setText(FailureQuotes[i]);
                }
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(Failure_Quotes.this)
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
