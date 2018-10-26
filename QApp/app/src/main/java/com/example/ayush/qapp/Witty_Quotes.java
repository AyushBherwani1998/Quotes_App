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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;

public class Witty_Quotes extends AppCompatActivity {

    private AdView mAdView;

    public static String []WittyQuotes = {"A quantum supercomputer calculating for a thousand years could not even approach the number of fucks I do not give.",
            "Knowledge is knowing a tomato is a fruit; Wisdom is not putting it in a fruit salad.",
            "I fear one day I’ll meet God, he’ll sneeze and I won’t know what to say.",
            "Is she naked because you love her? Or do you love her because she’s naked?",
            "Sorry, I checked my receipt and I didn’t buy any of your bullshit.",
            "I just bought a book about time management. It's over 1,000 pages long.",
            "I want to be the sun to your world, so I will be the sunshine in your cloudy days.",
            "Some people call it, `true love`, while others call it, `Best available at time.`",
            "Next time someone asks you to write a reference on their behalf, only use emojis.",
            "If someone comes to me with a serious problem, I calmly listen, take it all in, and with the utmost sincerity I wishper,`Call Batman`",
            "Can I just get back in the bubble we made, that was an amazing yet imperfect bliss of a fairytale?",
            "When I diet and exercise my body goes through a transformation. Like a Caterpillar changing into slightly slimmer Caterpillar.",
            "If you let me make love with your restless mind, I’ll allow you to devour my naked soul",
            "I say what I please, and at this moment it pleases me to tell you that you annoy me.",
            "The only people we can think of as normal are those we don’t yet know very well.",
            "A calm sea never made a good sailor",
            "My level of sarcasm has gotten to the point where I don’t even know if I’m joking anymore.",
            "Well it’s not really a measure of mental health to be well adjusted in a society that’s very sick",
            "To argue with a person who has renounced the use of reason is like administering medicine to the dead.",
            "Don’t mistake my kindness for weakness. I’ll choke you with the same hand I fed you with.",
            "Aim for the sun. That way if you miss, at least your arrow will fall far away, and the person it kills will likely be someone you don’t know",
            "Those people who think they know everything are a great annoyance to those of us who do.",
            "My dog is better at true love than most people in my age group.",
            "Half of seeming clever is keeping your mouth shut at the right times.",
            "I used to miss you so much. But it never seemed like you missed me. I guess because of that, I stopped missing you.",
            "Knowledge is knowing when someone is lying to you. Wisdom is seeing the truth in the lies.",
            "Always remember that the amount of pain we inflict on others, is directly proportionate to the amount of pain we feel within."
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

        MobileAds.initialize(this, "ca-app-pub-1203140157527769~6707095223");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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

        mQuoteTextView.setText(WittyQuotes[i]);

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(Witty_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }else{
                    i=WittyQuotes.length-1;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<WittyQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(WittyQuotes[i]);
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

        mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(Witty_Quotes.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }else{
                    i=WittyQuotes.length-1;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<WittyQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(WittyQuotes[i]);
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
                if(i<WittyQuotes.length-1){
                    i+=1;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }
            }
        });


        mLastQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }else if(i==0){
                    i=WittyQuotes.length-1;
                    mQuoteTextView.setText(WittyQuotes[i]);
                }
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(Witty_Quotes.this)
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

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }


}
