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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Friendship_Quotes extends AppCompatActivity {


    public static String FriendShipQuotes[] = {
            "The best kind of people are the ones that come into your life, and make you see the sun where you once saw clouds. The people that believe in you so much.",
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
            "We come from homes far from perfect, so you end up almost parent and sibling to your friends – your own chosen family. There’s nothing like a really loyal, dependable, good friend. Nothing. ",
            "Always remember people who have helped you along the way, and don’t forget to lift someone up.",
            "You are not stupid. You are not ugly. You are not worthless. You are not weak. You are not a burden. Your anxiety is lying to you.",
            "Cutting people out of my life does not mean I hate them. It simply means I respect me",
            "Imagine a world where we take compassion and sympathy and use it to sync our heartbeats as the bass for the symphony we’ll create",
            "When you are your own best friend, you don’t endlessly seek out relationships, friendships, and validation from the wrong sources because you realize that the only approval and validation you need is your own.",
            "I want to tell you how special you are to me. But I can’t Cause I’m risking the only way i can be with you for a life time,Being friends.",
            "A true friend is someone who sees the pain in your eyes when everyone else sees the smile on your face.",
            "Self worth is so vital to your happiness. If you don’t feel good about you, it’s hard to feel good about anything else.",
            "Close friends are truly life’s treasures. Sometimes they know us better than we know ourselves. With gentle honesty, they are there to guide and support us, to share our laughter and our tears. ",
            "I have no more fight in me when it comes to friendships… if you want to go, go.",
            "Sometimes you just need someone to tell you you’re not as terrible as you think you are.",
            "Real friends are there to pick you up when no one else has even noticed you’ve fallen",
            "Sometimes someone isn’t ready to see the bright side. Sometimes they need to sit with the shadow first. So be a friend and sit with them. Make the darkness beautiful",
            "A good friend is a connection to life, a tie to the past, a road to the future, the key to sanity in a totally insane world.",
            "I’m perfectly fine with you having other friends. But when you start to ditch me and ignore my existence to become close to them, that’s not fucking okay.",
            "If you are not losing friends you are not growing up.",
            "Between best friends, there is a vow they never made; to watch over the other and keep secrets from the rest of the world.",
            "Doesn’t it feel amazing to have someone you can fight with at first, but later on solve every problem and come out even stronger?",
            "A true friend knows your weaknesses but shows you your strengths; feels your fears but fortifies your faith; sees your anxieties but frees your spirit; recognizes your disabilities but emphasizes your possibilities.",
            "True friends are families which you can select.",
            "If you have two friends in your lifetime, you’re lucky. If you have one good friend, you’re more than lucky.",
            "Side by side or miles apart, real friends are always close to the heart.",
            "Don’t walk behind me; I may not lead. Don’t walk in front of me; I may not follow. Just walk beside me and be my friend",
            "A real friend sees the first tear, catches the second and prevent the third.",
            "It takes a great deal of bravery to stand up to our enemies, but just as much to stand up to our friends.",
            "Friendship is born at the moment when one person says to another “What! You too? I thought that no one but myself",
            "A good friend is like a four-leaf clover; hard to find and lucky to have.",
            "Walking with a friend in the dark is better than walking alone in the light.",
            "It is not a lack of love, but a lack of friendship that makes unhappy marriages.",
            "I’m not yours and you’re not mine but what we have goes further than being left undefined.",
            "I no doubt deserved my enemies, but I don’t believe I deserved my friends.",
            "Never underestimate the power of friends who can make you laugh when you don’t even feel like smiling",
            "Your best friend is your human diary that needs live updates.",
            "It’s important to realize that people are monsters. It’s also important to realize that monsters need friends too",
            "Be careful who you call your friends. I’d rather have four quarters than one hundred pennies",
            "Sometimes when you see a person cry, it’s better not to ask why. Sometimes it takes only three words to make them happy again. And those three words are “I am here.“"
    };




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendship__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewFriendshipQuotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(FriendShipQuotes,this));

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

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoriteQuotes", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList;
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            Favorite_Quotes.FavoriteQuotes = new LinkedList<>();
        } else {
            Favorite_Quotes.FavoriteQuotes = new LinkedList<>(arrayList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}
