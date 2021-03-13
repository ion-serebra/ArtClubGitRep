package com.oshaev.artclub.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.Comment;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.ui.MainActivity.MainActivity;

import java.util.ArrayList;
import java.util.Date;

public class ShowingPostActivity extends AppCompatActivity {

    ImageView titleImage;
    ImageView paperImage1;
    ImageView paperImage2;
    TextView titleTextView;
    TextView paperTextView1;
    TextView paperTextView2;
    ImageView sendCommentButton;
    EditText commentEditText;

    LinearLayoutManager manager;
    CommentsRecyclerViewAdapter adapter;
    RecyclerView commentsRecyclerView;


    String title;
    String paper;
    String titleImagePath;
    String postKey;

    String userName;
    String userSurname;
    String userId;
    String userImgUrl;


    ArrayList<String> imagePathList;
    ArrayList<Comment> showingComments;

    FirebaseDatabase database;
    DatabaseReference commentsReference;

    ShowingPostActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShowingPostActivityViewModel.class);

        database = FirebaseDatabase.getInstance();
        commentsReference = database.getReference();

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;

        setContentView(R.layout.activity_showing_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleTextView = findViewById(R.id.titleTextView);
        paperTextView1 = findViewById(R.id.paperTextView);
        sendCommentButton = findViewById(R.id.sendCommentButton);
        commentEditText = findViewById(R.id.commentEditText);
        //paperTextView2 = findViewById(R.id.paperTextView);
        titleImage = findViewById(R.id.titleImage);
        paperImage1 = findViewById(R.id.paperImage1);
        paperImage2 = findViewById(R.id.paperImage2);
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);




        Intent intent = getIntent();
        if(intent!=null) {
            title = intent.getStringExtra("title");
            paper = intent.getStringExtra("paper");
            imagePathList = intent.getStringArrayListExtra("imagePathList");
            postKey = intent.getStringExtra("postKey");

            userName = intent.getStringExtra("userName");
            userSurname = intent.getStringExtra("userSurname");
            userId = intent.getStringExtra("userId");
            userImgUrl = intent.getStringExtra("userImgUrl");

            if(imagePathList.size() > 0)
            {
                Glide.with(titleImage.getContext()).load(imagePathList.get(0)).into(titleImage);
            }
            if(imagePathList.size()==2)
            {
                Glide.with(paperImage1.getContext()).load(imagePathList.get(1)).into(paperImage1);
            }

            if(imagePathList.size()==3)
            {
                Glide.with(paperImage1.getContext()).load(imagePathList.get(1)).into(paperImage1);
                Glide.with(paperImage2.getContext()).load(imagePathList.get(2)).into(paperImage2);
            }

            titleTextView.setText(title);
            paperTextView1.setText(paper);
        }


        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = MainActivity.getCurrentUser();

                Toast.makeText(ShowingPostActivity.this, userImgUrl+"ff", Toast.LENGTH_SHORT).show();

                if(!commentEditText.getText().toString().trim().equals("")) {
                    commentsReference
                            .child("cultural event")
                            .child(postKey)
                            .child("comments")
                            .push()
                            .setValue(new Comment
                                    (commentEditText.getText().toString(), new Date(),userName,
                                            userSurname, userId, userImgUrl));
                    commentEditText.setText("");

                    //commentsRecyclerView.smoothScrollToPosition(commentsRecyclerView.getAdapter().getItemCount()-1);
                    //Toast.makeText(ShowingPostActivity.this, MainActivity.getCurrentUser().toString()+" 77", Toast.LENGTH_SHORT).show();
                    if(postKey!=null) {
                        //viewModel.getCommentsListFromServer(postKey);
                    }
                }
            }
        });



        if(postKey!=null)
        {
            viewModel.getCommentsListFromServer(postKey);
        }
        manager = new LinearLayoutManager(this);
        adapter = new CommentsRecyclerViewAdapter(viewModel.comments);
        commentsRecyclerView.setLayoutManager(manager);
        commentsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        // ↓↓↓ это работает, так и надо делать ↓↓↓

        if(postKey!=null)
        {
            viewModel.getComments(postKey).observe(this, new Observer<ArrayList<Comment>>() {
                @Override
                public void onChanged(ArrayList<Comment> comments) {
                    adapter.comments = comments;
                    adapter.notifyDataSetChanged();
                }
            });
        }


    }
}
