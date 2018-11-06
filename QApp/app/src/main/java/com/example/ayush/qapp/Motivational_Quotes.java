package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Motivational_Quotes extends AppCompatActivity {

    RecyclerView recyclerView;
    public static String motivationalQuotes[] = {
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
            "Far away there in the sunshine are my highest aspirations. I may not reach them, but I can look up and see their beauty, believe in them, and try to follow where they lead.",
            "Happiness is like a butterfly which, when pursued, is always beyond our grasp, but, if you will sit down quietly, may alight upon you.",
            "Success is not how high you have climbed, but how you make a positive difference to the world.",
            "Do not let the memories of your past limit the potential of your future. There are no limits to what you can achieve on your journey through life, except in your mind.",
            "The reason birds can fly and we can't is simply because they have perfect faith, for to have faith is to have wings.",
            "It’s your life; you don’t need someone’s permission to live the life you want. Be brave to live from your heart.",
            "Learn to light a candle in the darkest moments of someone’s life. Be the light that helps others see; it is what gives life its deepest significance.",
            "Death is not the greatest loss in life. The greatest loss is what dies inside while still alive. Never surrender.",
            "Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine.",
            "Great things happen to those who don't stop believing, trying, learning, and being grateful.",
            "We either make ourselves miserable, or we make ourselves strong. The amount of work is the same",
            "Be more concerned with your character than your reputation, because your character is what you really are, while your reputation is merely what others think you are.",
            "Anxiety happens when you think you have to figure out everything all at once. Breathe. You’re strong. You’ve got this. Take it day by day",
            "When you become comfortable with uncertainty, infinite possibilities open up in your life.",
            "Life is cruel, tomorrow you’ll wake up and realise you don’t want what you thought you wanted, and you’ll resent yourself, but look if you’re still here, it’s not too late.",
            "Be soft. Do not let the world make you hard. Do not let the pain make you hate. Do not let the bitterness steal your sweetness.",
            "The less you respond to negative people the more peaceful your life will become.",
            "If you focus on failure, you can never succeed.If you focus on being hurt in a relationship, you can never be loved.Whatever you focus on, you create.",
            "One of the most healing things you can do is recognize where in your life you are your own poison.",
            "You can’t change how people treat you or what they say about you. All you can do is change how you react to it",
            "Sometimes we need to stop analyzing the past, stop planning the future, stop figuring out precisely how we feel, stop deciding exactly what we want, and just see what happens.",
            "Just because you are struggling doesn’t mean you are failing. Every great success needs some kind of struggle to get there.",
            "Courage doesn’t mean you don’t get afraid. Courage means you don’t let fear stop you.",
            "The lesson we learn is life can be harsh but we as people don’t have to be. And that is what separates us. We are better than the ones we hear the most about.",
            "At your absolute best, you still won’t be good enough for the wrong person. At your worst, you’ll still be worth it to the right person.",
            "Don’t be scared to move to the next chapter in fear you will won’t see characters in the next. After all, the main and important characters always reappear",
            "Maybe it’s unlikely you’ll be happy it happened one day, but I hope you’ll find something that helped you become a better person and made you feel that at least the pain was worth it.",
            "I wish things could go back to the way they were. Even though it seemed so wrong at the time, it sure as hell is better than how it is now.",
            "It’s crazy how many people will stab you in the back once they get the chance. You’ll be surprised, so be careful and keep your eyes open.",
            "I know it hurts like hell to be disappointed at every turn, but you have to keep trying. I swear that things will get better if you can just push past this hill.",
            "Happiness is a state of inner fulfillment, not the gratification of inexhaustible desires for outward things.",
            "Some changes look negative on the surface but you will soon realize that space is being created in your life for something new to emerge.",
            "Suddenly you’re ripped into being alive. And life is pain, and life is suffering, and life is horror, but my god you’re alive and its spectacular."

    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(motivationalQuotes,this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
