package com.oshaev.artclub.usertasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.oshaev.artclub.R;

public class UserTaskActivity extends AppCompatActivity {

    TextView reward;
    TextView difficulty;
    TextView priority;
    TextView fear;
    Toolbar bar;

    int rewardValue = 0;
    int difficultyValue = 0;
    int priorityValue = 0;
    int fearValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_task);

        bar = findViewById(R.id.toolbar);
        reward = findViewById(R.id.reward);
        difficulty = findViewById(R.id.difficulty);
        priority = findViewById(R.id.priority);
        fear = findViewById(R.id.fear);

        Intent intent = getIntent();
        if(intent!=null)
        {
            rewardValue = intent.getIntExtra("reward", 0);
            difficultyValue = intent.getIntExtra("difficulty", 0);
            priorityValue = intent.getIntExtra("priority", 0);
            fearValue = intent.getIntExtra("fear", 0);
            bar.setTitle(intent.getStringExtra("title"));
        }

        reward.setText(rewardValue+"");
        difficulty.setText(difficultyValue+"");
        priority.setText(priorityValue+"");
        fear.setText(fearValue+"");
    }
}
