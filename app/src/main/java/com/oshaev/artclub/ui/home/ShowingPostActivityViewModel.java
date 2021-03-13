package com.oshaev.artclub.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oshaev.artclub.Comment;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowingPostActivityViewModel extends ViewModel {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    public ArrayList<Comment> comments;
    public MutableLiveData<ArrayList<Comment>> commentsLiveData = new MutableLiveData<>();


    public ShowingPostActivityViewModel()
    {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("cultural event");
        comments = new ArrayList<>();

    }


    public LiveData<ArrayList<Comment>> getComments(String postKey)
    {
        if(commentsLiveData == null)
        {
            getCommentsListFromServer(postKey);
            FirebaseDatabase.getInstance().getReference().child("cultural event")
                    .child(postKey).child("comments").
                    addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                //HashMap<String, Comment> commentsHashMap = snapshot.getValue(HashMap.class);
                                Comment comment = snapshot.getValue(Comment.class);
                                comments.add(comment);
                                commentsLiveData.postValue(comments);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
        return commentsLiveData;
    }


    public void getCommentsListFromServer(String postKey)
    {

        reference.child(postKey).child("comments").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Comment comment = snapshot.getValue(Comment.class);
                if(comment!=null)
                {
                    Log.e("Изменение в comments", "onChildAdded");
                    comments.add(comment);
                    commentsLiveData.postValue(comments);
                    Log.e("Изменение в comments", comment.toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("Изменение в comments", "onChildChanged");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.e("Изменение в comments", "onChildRemoved");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("Изменение в comments", "onChildMoved");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Изменение в comments", "onCancelled");
            }
        });

    }


















}
