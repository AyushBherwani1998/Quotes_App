package com.example.ayush.qapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.util.Objects;

public class Friendship_Quotes extends AppCompatActivity {

    static int i=0;
    private String FriendShipQuotes[] = {
            "The best kind of people are the ones that come into your life, and make you see the sun where you once saw clouds. The people that believe in you so much.",
            "I think if I’ve learned anything about friendship, it’s to hang in, stay connected, fight for them, and let them fight for you. Don’t walk away, don’t be distracted, don’t be too busy or tired, don’t take them for granted. Friends are part of the glue that holds life and faith together. Powerful stuff.",
            "I value the friend who for me finds time on his calendar, but I cherish the friend who for me does not consult his calendar.",
            "The real test of friendship is can you literally do nothing with the other person? Can you enjoy those moments of life that are utterly simple?",
            "One measure of friendship consists not in the number of things friends can discuss, but in the number of things they need no longer mention. ",
            "You can always tell a real friend: when you’ve made a fool of yourself he doesn’t feel you’ve done a permanent job",
            "In the sweetness of friendship let there be laughter, for in the dew of little things the heart finds its morning and is refreshed. ",
            "Sometimes being a friend means mastering the art of timing. There is a time for silence. A time to let go and allow people to hurl themselves into their own destiny. And a time to prepare to pick up the pieces when it’s all over.",
            "We cannot tell the precise moment when friendship is formed. As in filling a vessel drop by drop, there is at last a drop which makes it run over; so in a series of kindnesses there is at last one which makes the heart run over.",
            "Every friendship travels at sometime through the black valley of despair. This tests every aspect of your affection. You lose the attraction and the magic",
            "True friendship can afford true knowledge. It does not depend on darkness and ignorance",
            "Some people arrive and make such a beautiful impact on your life, you can barely remember what life was like without them ",
            "Never leave a friend behind. Friends are all we have to get us through this life–and they are the only things from this world that we could hope to see in the next",
            "We come from homes far from perfect, so you end up almost parent and sibling to your friends – your own chosen family. There’s nothing like a really loyal, dependable, good friend. Nothing. "
    };

    Button mNextQuote;
    Button mLastQuote;
    ImageButton mShareButton;
    TextView mQuoteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendship__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLastQuote = findViewById(R.id.lastQuoteButton);
        mNextQuote = findViewById(R.id.nextQuoteButoon);
        mShareButton = findViewById(R.id.shareButton);
        mQuoteTextView = findViewById(R.id.QuoteTextView);

        mQuoteTextView.setText(FriendShipQuotes[i]);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);


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
                        .from(Friendship_Quotes.this)
                        .setChooserTitle("Share this Quote with")
                        .setType("text/plain")
                        .setText(mQuoteTextView.getText().toString())
                        .startChooser();
            }
        });

        mNextQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<FriendShipQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(FriendShipQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(FriendShipQuotes[i]);
                }
            }
        });

        mLastQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(FriendShipQuotes[i]);
                }else {
                    i= FriendShipQuotes.length-1;
                    mQuoteTextView.setText(FriendShipQuotes[i]);
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

        if(id == R.id.action_favorite){
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
