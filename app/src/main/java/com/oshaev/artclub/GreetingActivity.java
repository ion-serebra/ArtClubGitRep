package com.oshaev.artclub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);


        Bundle bundle;
        View vv = findViewById(R.id.letter_a);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                (GreetingActivity.this, vv, "transition");
        bundle = options.toBundle();



        Intent intent = new Intent(GreetingActivity.this, LoginActivity.class);
        intent.putExtra("chatName","Art Media");
        intent.putExtra("chatChild", "media");
        if (bundle == null) {
            startActivity(intent);
        } else {
            startActivity(intent, bundle);
        }


    }
}
