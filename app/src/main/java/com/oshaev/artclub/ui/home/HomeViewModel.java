package com.oshaev.artclub.ui.home;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oshaev.artclub.Comment;
import com.oshaev.artclub.User;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    User user;
    MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> mText;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference eventsReference;
    public ArrayList<NewsItem> posts;
    private MutableLiveData<ArrayList<NewsItem>> postsMutableLiveData = new MutableLiveData<>();

    public ArrayList<Comment> comments;
    private MutableLiveData<ArrayList<Comment>> commentsMutableLiveData = new MutableLiveData<>();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("events");
        database = FirebaseDatabase.getInstance();
        eventsReference = database.getReference().child("cultural event");
        posts = new ArrayList<>();
        comments = new ArrayList<>();


    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<NewsItem>> getPosts()
    {
        getEventsList();
        //if(posts!=null) {
            //postsMutableLiveData.setValue(posts);
        //}
        return postsMutableLiveData;
    }

    public MutableLiveData<User> getCurrentUser()
    {
        final DatabaseReference userReference = database.getReference().child("users");
        userReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getValue(User.class).getId().equals(FirebaseAuth.getInstance().getUid()))
                {
                    user = snapshot.getValue(User.class);
                    userMutableLiveData.postValue(user);
                    userReference.removeEventListener(this);
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
        return userMutableLiveData;
    }

    public void getEventsList()
    {
        eventsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewsItem post = snapshot.getValue(NewsItem.class);
                if(post!=null)
                {
                    //Log.e("addedPost", post.getTitle());
                    post.setKey(snapshot.getKey());
                    posts.add(post);
                    postsMutableLiveData.setValue(posts);
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
    }

    public void getEventComments(String postKey)
    {
        database.getReference().child("cultural event")
                .child(postKey).child("comments")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Comment comment = snapshot.getValue(Comment.class);

                        if(comment!=null)
                        {
                            comments.add(comment);
                            commentsMutableLiveData.setValue(comments);
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
    }

}
























