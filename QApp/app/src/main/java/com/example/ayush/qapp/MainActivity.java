package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView mShareButton;
    TextView mQuoteTextView;
    Button mNextQuoteButton;
    Button mLastQuoteButton;
    static int i=0;
    String motivationalQuotes[] = {
            "You must allow yourself to outgrow and depart from certain eras of your life with a gentle sort of ruthlessness",
            "I think people would be happier if they admitted things more often. In a sense we are all prisoners of some memory, or fear, or disappointment we are all defined by something we can’t change.",
            "Sometimes you pour your heart out and nothing comes back. Sometimes you pour your heart out and the world falls onto your lap. Keep trying.",
            "Be a lamp, or a lifeboat, or a ladder. Help someone’s soul heal.",
            "If you don’t go after what you want, you’ll never have it. If you don’t ask, the answer is always no. If you don’t step forward you are always in the same place",
            "Every child is an artist. The problem is how to remain an artist once he grows up.",
            "Focus on what you can do rather what you can’t. Small steps turn into miles.",
            "Self-forgiveness creates doors that weren’t there before. It’s something that sets you free.",
            "If you can’t sleep, then get up and do something instead of lying there and worrying. It’s the worry that gets you, not the loss of sleep",
            "If you hide from all storms, how will you grow?",
            "If you have been brutally broken, but still have the courage to be gentle to others then you deserve a love deeper than the ocean itself.",
            "When you stop chasing the wrong things, you give the right things a chance to catch you.",
            "Empty pockets never held anyone back. Only empty heads and empty hearts can do that.",
            "Healing is a choice. It is not an easy one because it takes work to turn around your habits. But keep making the choice and shifts will happen.",
            "There are people less qualified than you, doing the things you want to do, simply because they chose to believe in themselves.",
            "You will never follow your own inner voice until you clear up the doubts in your mind.",
            "Any plan is better than no plan, and a good plan executed now is far better than a perfect plan executed too late",
            "I stopped obsessing over whether or not I was any good and just focused on ‘How do I get better?’",
            "Far away there in the sunshine are my highest aspirations. I may not reach them, but I can look up and see their beauty, believe in them, and try to follow where they lead."
    };
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Motivational Quotes");
        mShareButton = findViewById(R.id.shareButton);
        mQuoteTextView = findViewById(R.id.QuoteTextView);
        mNextQuoteButton = findViewById(R.id.nextQuoteButoon);
        mLastQuoteButton = findViewById(R.id.lastQuoteButton);
        ConstraintLayout constraintLayout = findViewById(R.id.mainView);
        loadData();

        mQuoteTextView.setText(motivationalQuotes[i]);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);

        }


        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quoteText = mQuoteTextView.getText().toString();
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle("Share this Quote with")
                        .setText(quoteText)
                        .startChooser();
            }
        });

        mNextQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<motivationalQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }
            }
        });

        mLastQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }else{
                    i=motivationalQuotes.length-1;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }
            }
        });

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }else{
                    i=motivationalQuotes.length-1;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<motivationalQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(motivationalQuotes[i]);
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
        });

        mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            public void onSwipeRight() {
                if(i>0){
                    i--;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }else{
                    i=motivationalQuotes.length-1;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }
            }

            public void onSwipeLeft() {
                if(i<motivationalQuotes.length-1){
                    i++;
                    mQuoteTextView.setText(motivationalQuotes[i]);
                }else{
                    i=0;
                    mQuoteTextView.setText(motivationalQuotes[i]);
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
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.loveQuotes) {
            startActivity(new Intent(this,Love_Quotes.class));

        } else if (id == R.id.inspirationalQutoes) {
            startActivity(new Intent(this,Inspiration_Quotes.class));

        } else if (id == R.id.breakupQuotes) {
            startActivity(new Intent(this,Breakup_Quotes.class));

        } else if (id == R.id.favourtieQuotes) {
            startActivity(new Intent(this,Favorite_Quotes.class));

        } else if(id == R.id.friendshipQuotes){
            startActivity(new Intent(this,Friendship_Quotes.class));
        }else if(id == R.id.aboutUs){
            startActivity(new Intent(this,About_US.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        ArrayList<String> arrayList = new ArrayList<String>(Favorite_Quotes.FavoriteQuotes);
        String json = gson.toJson(arrayList);
        editor.putString("favoriteQuotes",json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoriteQuotes",null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> arrayList;
        arrayList = gson.fromJson(json,type);
        if( arrayList == null){
            Favorite_Quotes.FavoriteQuotes = new LinkedList<>();
        }else{
            Favorite_Quotes.FavoriteQuotes = new LinkedList<>(arrayList);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "font/Roboto-Medium.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


}
