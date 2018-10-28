package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView mShareButton;
    TextView mQuoteTextView;
    TextView mHeading;
    ImageButton mRefreshButton;
    int randomIndex;
    int randomCategory;
    private AdView mAdView;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quotes Hub");


        MobileAds.initialize(this, "ca-app-pub-1203140157527769~6707095223");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mShareButton = findViewById(R.id.shareButton);
        mQuoteTextView = findViewById(R.id.QuoteTextView);
        mRefreshButton = findViewById(R.id.refreshButton);
        mHeading = findViewById(R.id.mainActivityHeading);

       final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ConstraintLayout constraintLayout = findViewById(R.id.mainView);
        loadData();


        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
        mQuoteTextView.setTypeface(roboto);
        mHeading.setTypeface(roboto);

        randomQuote();

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               randomQuote();
            }
        });

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        final Menu m = navView.getMenu();
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


        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){

            public void onSwipeLeft(){

               randomQuote();
            }

            public void onSwipeRight(){
              randomQuote();
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

        mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){

            public void onSwipeLeft(){
                randomQuote();
            }

            public void onSwipeRight(){
                randomQuote();
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        navView.setNavigationItemSelectedListener(this);
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

        if(id == R.id.action_rateus){
            startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ayushbherwani.ayush.qapp")));
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
        }else if(id == R.id.motivationaQuotes){
            startActivity(new Intent(this,Motivational_Quotes.class));
        }else if(id == R.id.failureQuotes){
            startActivity(new Intent(this,Failure_Quotes.class));
        }else if(id == R.id.wittyQuotes){
            startActivity(new Intent(this,Witty_Quotes.class));
        }else if(id == R.id.help){
            startActivity(new Intent(this,Help.class));
        }else if(id == R.id.feedback){
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

    public void randomQuote(){
        randomCategory = (int) (Math.random()*7);
        switch (randomCategory){
            case 0:
                randomIndex = (int)(Math.random()*Motivational_Quotes.motivationalQuotes.length);
                mQuoteTextView.setText(Motivational_Quotes.motivationalQuotes[randomIndex]);
                break;
            case 1:
                randomIndex = (int)(Math.random()*Love_Quotes.LoveQuotes.length);
                mQuoteTextView.setText(Love_Quotes.LoveQuotes[randomIndex]);
                break;
            case 2:
                randomIndex = (int)(Math.random()*Inspiration_Quotes.InspirationalQuotes.length);
                mQuoteTextView.setText(Inspiration_Quotes.InspirationalQuotes[randomIndex]);
                break;
            case 3:
                randomIndex = (int)(Math.random()*Breakup_Quotes.BreakupQuotes.length);
                mQuoteTextView.setText(Breakup_Quotes.BreakupQuotes[randomIndex]);
                break;
            case 4:
                randomIndex = (int)(Math.random()*Friendship_Quotes.FriendShipQuotes.length);
                mQuoteTextView.setText(Friendship_Quotes.FriendShipQuotes[randomIndex]);
                break;
            case 5:
                randomIndex = (int)(Math.random()*Failure_Quotes.FailureQuotes.length);
                mQuoteTextView.setText(Failure_Quotes.FailureQuotes[randomIndex]);
                break;
            case 6:
                randomIndex = (int)(Math.random()*Witty_Quotes.WittyQuotes.length);
                mQuoteTextView.setText(Witty_Quotes.WittyQuotes[randomIndex]);
                break;
        }
    }

}
