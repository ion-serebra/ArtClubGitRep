package com.oshaev.artclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.ui.MainActivity.MainActivity;

import java.util.ArrayList;


public class AddChatActivity extends AppCompatActivity {

    private Chat chat;

    private EditText chatName;
    private Spinner accessSpinner;
    private Button createButton;
    private ArrayAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference chatsDatabaseReference;

    private DatabaseReference usersDatabaseReference;
    private ChildEventListener usersChildEventListener;

    private ArrayList<User> chatMembersArrayList; // добавленные участники чата
    private ArrayList<User> allUsersArrayList;
    private RecyclerView userRecyclerView;
    private UserListAdapter userListAdapter;
    private RecyclerView.LayoutManager userLayoutManager;

    private RecyclerView addedUsersRecyclerView;
    private UserListAdapter addedUsersListAdapter;
    private RecyclerView.LayoutManager addedUsersLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);
        setTitle("Новый чат");

        allUsersArrayList = new ArrayList<>();
        chatMembersArrayList = new ArrayList<>();
        chat = new Chat();

        //установка связи с базой данных и введение references:
        database = FirebaseDatabase.getInstance();
        usersDatabaseReference = database.getReference().child("users");
        chatsDatabaseReference = database.getReference().child("chats");

        buildAddedUsersRecyclerView();

        attachUserDatabaseReferenceListener();
        buildRecyclerView();
        userListAdapter.setOnUserClickListener(new UserListAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(int position) {
                boolean addUserFlag = true;
                for(int i = 0; i < chatMembersArrayList.size(); i++)
                {
                    if(allUsersArrayList.get(position).equals(chatMembersArrayList.get(i)))
                    {
                        addUserFlag = false;
                        break;  //выйти из цикла, если в списке уже есть такой человек
                    }
                }
                if(addUserFlag) {
                    chatMembersArrayList.add(allUsersArrayList.get(position));
                    addedUsersListAdapter.notifyDataSetChanged();
                }

            }
        });

        addedUsersListAdapter.setOnUserClickListener(new UserListAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(int position) {
                chatMembersArrayList.remove(position);
                addedUsersListAdapter.notifyDataSetChanged();
            }
        });



        chatName = findViewById(R.id.chatName);
        createButton = findViewById(R.id.createButton);

        final String[] accessLevels = {"наблюдатель", "участник Art Academy",
                "член Art Club", "модератор", "администратор"};



        //установка и настройка спиннера уровней доступа
        accessSpinner = findViewById(R.id.accessLevelSpinner);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, accessLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chat.setAccessLevel(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        accessSpinner.setAdapter(adapter);





        // получение списка чатов из базы данных
        final ArrayList<Chat> chats = new ArrayList<>();
        chatsDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                chats.add(snapshot.getValue(Chat.class));
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


        //внесение чата в базу данных и выход из активити создания чата
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatName.getText().toString().equals("") == false) {
                    chat.setName(chatName.getText().toString().trim());
                    chat.setDBChild(chatName.getText().toString().trim());
                    chat.setChatMembers(chatMembersArrayList);
                    chatsDatabaseReference.push().setValue(chat);
                    Intent intent = new Intent(AddChatActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(AddChatActivity.this, "Введите название чата", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void buildAddedUsersRecyclerView() {

        addedUsersRecyclerView = findViewById(R.id.addedUsersRecyclerView);
        addedUsersRecyclerView.setHasFixedSize(true);
        addedUsersLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        addedUsersListAdapter = new UserListAdapter(chatMembersArrayList, this, R.layout.chat_added_user);
        addedUsersRecyclerView.setLayoutManager(addedUsersLayoutManager);
        addedUsersRecyclerView.setAdapter(addedUsersListAdapter);
    }

    private void buildRecyclerView() {

        userRecyclerView = findViewById(R.id.userRecyclerView);
        userRecyclerView.setHasFixedSize(true);
        userLayoutManager = new LinearLayoutManager(this);
        userListAdapter = new UserListAdapter(allUsersArrayList, this);
        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(userListAdapter);
    }


    //получение списка пользователей из базы данных
    private void attachUserDatabaseReferenceListener() {
        usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        if(usersChildEventListener == null)
        {
            usersChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    if(snapshot!=null) {
                        User user = snapshot.getValue(User.class);
                        user.setImageResource(R.drawable.user_avatar);
                        allUsersArrayList.add(user);
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

}











