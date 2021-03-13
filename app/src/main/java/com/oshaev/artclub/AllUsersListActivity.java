package com.oshaev.artclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllUsersListActivity extends AppCompatActivity {

    private DatabaseReference usersDatabaseReference;
    private ChildEventListener usersChildEventListener;
    private ArrayList<User> userArrayList;
    private RecyclerView userRecyclerView;
    private UserListAdapter userListAdapter;
    private RecyclerView.LayoutManager userLayoutManager;
    private String childChatName;
    private String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users_list);

        userArrayList = new ArrayList<>();

        Intent intent = getIntent();
        if(intent!=null) {
            if(intent.getStringExtra("source").equals("chatUsers")) {
                childChatName = intent.getStringExtra("chatKey");
                usersDatabaseReference = FirebaseDatabase.getInstance()
                        .getReference().child("chats").child(childChatName).child("chatMembers");
                source = "chatUsers";
            }
            else if (intent.getStringExtra("source").equals("adminProfile"))
            {
                usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
                source = "adminProfile";
            }


        } else {

        }


        attachUserDatabaseReferenceListener();
        buildRecyclerView();

        userListAdapter.setOnUserClickListener(new UserListAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(int position) {

                if(source.equals("adminProfile")) {
                    User currentUser = userArrayList.get(position);
                    Intent intent = new Intent(AllUsersListActivity.this, UserForAdminActivity.class);
                    intent.putExtra("userId", currentUser.getId());
                    startActivity(intent);
                }
                else if (source.equals("chatUsers"))
                {
                    User currentUser = userArrayList.get(position);
                    Intent intent = new Intent(AllUsersListActivity.this, UserProfilePublic.class);
                    intent.putExtra("userId", currentUser.getId());
                    startActivity(intent);
                }
            }
        });



    }

    private void attachUserDatabaseReferenceListener() {

        if(usersChildEventListener == null)
        {
            usersChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    if(snapshot!=null) {
                        User user = snapshot.getValue(User.class);
                        user.setImageResource(R.drawable.user_avatar);
                        userArrayList.add(user);
                        userListAdapter.notifyDataSetChanged();
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
            };
            usersDatabaseReference.addChildEventListener(usersChildEventListener);
        }
    }

    private void buildRecyclerView() {

        userRecyclerView = findViewById(R.id.userRecyclerView);
        userRecyclerView.setHasFixedSize(true);
        userLayoutManager = new LinearLayoutManager(this);
        userListAdapter = new UserListAdapter(userArrayList, this);
        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(userListAdapter);
    }


}
