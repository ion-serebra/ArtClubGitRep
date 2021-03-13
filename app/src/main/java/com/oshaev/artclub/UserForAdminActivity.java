package com.oshaev.artclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.ui.notifications.ProfileFragment;

import java.util.HashMap;
import java.util.Map;

public class UserForAdminActivity extends AppCompatActivity {
    private String uId;
    private FirebaseDatabase usersDatabase;
    private DatabaseReference usersDatabaseReference;
    User user;
    String userKey;

    TextView userName;
    TextView userSurname;
    TextView userGroup;
    TextView userKeyTextView;
    TextView userAccessLevel;
    private int accessLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_for_admin);

        userName = findViewById(R.id.userName);
        userSurname = findViewById(R.id.userSurname);
        userGroup = findViewById(R.id.userGroup);
        userKeyTextView = findViewById(R.id.userKey);
        userAccessLevel = findViewById(R.id.accessLevelTextView);



        usersDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = usersDatabase.getReference().child("users");



        Intent intent = getIntent();
        if(intent!=null){
           try{ uId = intent.getStringExtra("userId"); } catch (Error n){

           };
        }

        usersDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User currentUser = snapshot.getValue(User.class);
                if(currentUser.getId().equals(uId))
                {
                    user = currentUser;
                    userKey = snapshot.getKey();
                    userName.setText(user.getName());
                    userSurname.setText(user.getSurname());
                    userGroup.setText(user.getGroup());
                    userKeyTextView.setText(userKey);
                    accessLevel = user.getAccessLevel();
                    userAccessLevel.setText("Уровень доступа: " );
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

         final Spinner spinner = findViewById(R.id.accessLevelSpinner);

// Настраиваем адаптер
        Integer[] items = new Integer[]{0,1,2,3,4};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.setAccessLevel(position);
                ProfileFragment.setUserAccessLevel(position);
                DatabaseReference updateUsersReference = usersDatabase.getReference().child("users");
                updateUsersReference.child(userKey).setValue(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner.setSelection(accessLevel);
        spinner.setAdapter(adapter);

        spinner.post(new Runnable() {
            public void run() {
                spinner.setSelection(accessLevel);
            }
        });

        //spinner.setSelection(accessLevel, false);
    }
}
