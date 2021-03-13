package com.oshaev.artclub.ui.notifications;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.usertasks.TasksRecyclerViewAdapter;
import com.oshaev.artclub.usertasks.UserTask;

import java.util.ArrayList;

public class CompletedTasksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<UserTask> completedTasks = new ArrayList<>();
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
        final TasksRecyclerViewAdapter adapter = new TasksRecyclerViewAdapter(completedTasks);

        recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();

        String userKey = intent.getStringExtra("userKey");
        db = FirebaseDatabase.getInstance();
        db.getReference().child("users").child(userKey).child("completedTasks").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getValue()!=null) {
                    //Log.d("erer", snapshot.getValue(UserTask.class).getName().toString());
                    UserTask userTask = snapshot.getValue(UserTask.class);
                    userTask.setKey(snapshot.getKey());
                    completedTasks.add(snapshot.getValue(UserTask.class));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
