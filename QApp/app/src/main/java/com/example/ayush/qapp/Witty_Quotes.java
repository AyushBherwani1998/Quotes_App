package com.example.ayush.qapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Witty_Quotes extends AppCompatActivity {

    public static String []WittyQuotes = {
            "A quantum supercomputer calculating for a thousand years could not even approach the number of fucks I do not give.",
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_witty__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.RecyclerViewWittyQuote);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Witty_Quotes.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapter(WittyQuotes,Witty_Quotes.this));
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
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}
