package com.oshaev.artclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oshaev.artclub.ui.MainActivity.MainActivity;
import com.oshaev.artclub.ui.notifications.ProfileFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private  static final int REQUEST_CODE_IMAGE = 111;

    Chat currentChat;

    private ListView messageListView;

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    ChatMessageRecyclerViewAdapter recyclerViewAdapter;


    private ChatMessageAdapter messageAdapter;
    private ImageButton sendImageButton;
    private ImageButton sendMessageButton;
    private EditText messageEditText;
    TextView appBarText;
    ImageView infoImageView;

    private String userName;
    private Toolbar chatTitle;
    private String childChatName;
    private String chatKey;

    FirebaseDatabase database;
    DatabaseReference messagesDatabaseReference;
    ChildEventListener messagesChildEventListener;
    FirebaseStorage storage;
    StorageReference chatImgStorageReference;

    public static String currentUserName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        database = FirebaseDatabase.getInstance();
        storage =FirebaseStorage.getInstance();

        childChatName = "messages";
        chatTitle = findViewById(R.id.chatTitle);
        appBarText = findViewById(R.id.appBarText);
        infoImageView = findViewById(R.id.infoImageView);
        Intent intent = getIntent();

        if(intent!=null)
        {
            childChatName = intent.getStringExtra("chatChild");
            chatTitle.setTitle(intent.getStringExtra("chatName"));
            appBarText.setText(intent.getStringExtra("chatName"));
            chatKey = intent.getStringExtra("chatKey");
        }

        messagesDatabaseReference = database.getReference().child(childChatName);
        chatImgStorageReference = storage.getReference().child("chat_images");

        /*
        messagesDatabaseReference.setValue("Hello");
        messagesDatabaseReference.child("user1").setValue("Hi, i'm user1");
        messagesDatabaseReference.child("user2").child("Редактированное сообщение").setValue("Hello!, I'm ty:)");
        */

        sendImageButton = findViewById(R.id.sendImageButton );
        sendMessageButton = findViewById(R.id.sendMessageButton );
        messageEditText = findViewById(R.id.messageEditText );


        userName = MainActivity.userName;
       // currentUserName = userName;

        messageListView = findViewById(R.id.messageListView);
        final List<ChatMessage> chatMessages = new ArrayList<>();
        messageAdapter = new ChatMessageAdapter(this, R.layout.message_layout,
               chatMessages);
        messageListView.setAdapter(messageAdapter);


        /*
        recyclerView = findViewById(R.id.chatMessagesRecyclerView);
        manager = new LinearLayoutManager(this);
        recyclerViewAdapter = new ChatMessageRecyclerViewAdapter((ArrayList) chatMessages);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

         */





        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(ChatActivity.this, chatMessages.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });



/*
        messageAdapter.setImageClickListener(new ChatMessageAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int position, View view) {

                Toast.makeText(ChatActivity.this, chatMessages.get(position).getName(), Toast.LENGTH_SHORT).show();


                if(chatMessages.get(position).getImgUrl()!=null)
                {
                    Intent showImageIntent = new Intent(getApplicationContext(), ShowImageActivity.class);
                    Bundle bundle;
                    View messageImg = view.findViewById(R.id.messageImg);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) (getApplicationContext()),
                            messageImg, "showingImage");
                    bundle = options.toBundle();
                    showImageIntent.putExtra("path", chatMessages.get(position).getImgUrl());

                    if (bundle == null) {
                        getApplicationContext().startActivity(showImageIntent);
                    } else {
                        getApplicationContext().startActivity(showImageIntent, bundle);
                    }
                }



            }
        });
        */

        // удаление сообщения на долгий клик по нему
        messageListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if((chatMessages.get(position).getName().equals(userName))|| ProfileFragment.getUserAccessLevel()>=3) {
                    messagesDatabaseReference.child(chatMessages.get(position).getKey()).removeValue();
                    messageAdapter.remove(chatMessages.get(position));
                    messageAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });






        // слушатель messageEditText
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { // когда текст поменялся
                if (s.toString().trim().length() > 0)
                {
                    sendMessageButton.setEnabled(true);
                } else
                {
                    sendMessageButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        messageEditText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(500)});


        //слушатель кнопки отправления сообщения
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage message = new ChatMessage();
                message.setText(messageEditText.getText().toString().trim());
                message.setName(userName);
                message.setDate(new Date());
                message.setImgUrl(null);

                messagesDatabaseReference.push().setValue(message);

                messageEditText.setText("");
                messageListView.setSelection(messageAdapter.getCount());
            }
        });


        //слушатель кнопки отправления фото
        sendImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent, "Выберите картинку"),
                        REQUEST_CODE_IMAGE);

            }
        });

        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent
                        (ChatActivity.this, AllUsersListActivity.class);
                intent.putExtra("chatName", childChatName);
                intent.putExtra("chatKey", chatKey);
                intent.putExtra("source", "chatUsers");
                startActivity(intent);

            }
        });






        // слушатель узла сообщений
        messagesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatMessage message = snapshot.getValue(ChatMessage.class);
                message.setId(snapshot.getChildren().toString());
                message.setKey(snapshot.getKey());
                messageAdapter.add(message);
                messageListView.setSelection(messageAdapter.getCount());
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
        messagesDatabaseReference.addChildEventListener(messagesChildEventListener);


    }

    // загрузка фото

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK)
        {
            Uri selectedImageUri = data.getData();
            final StorageReference imageReference = chatImgStorageReference
                    .child(selectedImageUri.getLastPathSegment());
            //content://images/folder/3

            UploadTask uploadTask = imageReference.putFile(selectedImageUri);


            uploadTask = imageReference.putFile(selectedImageUri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return imageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult(); //uri для помещения в базу данных
                        ChatMessage message = new ChatMessage();
                        message.setImgUrl(downloadUri.toString());
                        message.setName(userName);
                        message.setDate(new Date());
                        messagesDatabaseReference.push().setValue(message);
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Toast.makeText(getApplication(), "Uploaded", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}
