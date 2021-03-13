package com.oshaev.artclub.ui.notifications.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.ui.MainActivity.MainActivity;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.usertasks.CreatePerformanceActivity;
import com.oshaev.artclub.usertasks.Performance;
import com.oshaev.artclub.usertasks.PerformancesRecyclerViewAdapter;

import java.util.ArrayList;

public class PerformanceFragment extends Fragment {


    static RecyclerView performancesRecyclerView;
    static LinearLayoutManager manager;
    static PerformancesRecyclerViewAdapter adapter;
    FloatingActionButton fab;

    DatabaseReference usersDatabaseReference;




    private ArrayList<Performance> performances;

    /*public static ArrayList<Performance> getPerformances() {
    return performances;
    }
    public static void setPerformances(ArrayList<Performance> performances) {
        PerformanceFragment.performances = performances;
    }


     */



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_performance, container, false);

        fab = rootView.findViewById(R.id.fab);
        performancesRecyclerView = rootView.findViewById(R.id.performancesRecyclerView);
        //performances = null;
        usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        performances = new ArrayList<>();
        manager = new LinearLayoutManager(getContext());
        adapter = new PerformancesRecyclerViewAdapter(performances);
        performancesRecyclerView.setLayoutManager(manager);
        performancesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        usersDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
               {
                    performances = user.getPerformances();
                   Log.d("performances", performances.toString());
                   //Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();
                   adapter = new PerformancesRecyclerViewAdapter(performances);
                   performancesRecyclerView.setLayoutManager(manager);
                   performancesRecyclerView.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                {
                    performances = user.getPerformances();
                    Log.d("performances", performances.toString());
                  //Toast.makeText(getContext(), "changed", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
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




        if(MainActivity.getCurrentUser()!=null) {
            performances = MainActivity.getCurrentUser().getPerformances();
        }




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreatePerformanceActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public static void setPerformancesValues (ArrayList<Performance> performances)
    {
        adapter = new PerformancesRecyclerViewAdapter(performances);
        performancesRecyclerView.setLayoutManager(manager);
        performancesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
