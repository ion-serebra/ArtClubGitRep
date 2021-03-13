package com.oshaev.artclub.ui.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.ui.dashboard.ChooseChatFragment;
import com.oshaev.artclub.ui.home.HomeFragment;
import com.oshaev.artclub.ui.notifications.FirstFragment;
import com.oshaev.artclub.ui.notifications.ProfileFragment;
import com.oshaev.artclub.ui.notifications.ProfileViewModel;

public class MainActivity extends AppCompatActivity {

    public static String userName;
    public static String userSurname;
    public static String userGroup;
    public static String userId;

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        MainActivity.currentUser = currentUser;
    }

    FirebaseDatabase database;
    DatabaseReference usersDatabaseReference;
    ChildEventListener usersChildEventListener;

    final Fragment fragment11 = new HomeFragment();
    final Fragment fragment22 = new ChooseChatFragment();
    final Fragment fragment33 = new FirstFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);
        model.getUser();
        model.getUserName();

        //currentUser = null;
        Intent intent = getIntent();
        if (intent != null) {
            userName = intent.getStringExtra("userName");
            userSurname = intent.getStringExtra("userSurname");
            userGroup = intent.getStringExtra("userGroup");
        }


        database = FirebaseDatabase.getInstance();
        usersDatabaseReference = database.getReference().child("users");
        database.getReference().child("users").child("-MKySZ0xUYwld90YiPPP").push();

        usersChildEventListener = new ChildEventListener() { // практически не используется, исправить
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    userName = user.getName();
                    userSurname = user.getSurname();
                    ProfileFragment.setUserAccessLevel(user.getAccessLevel());
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
        DatabaseReference testUsersDatabaseReference = database.getReference().child("testUsers");
        testUsersDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    currentUser = user;
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


        BottomNavigationView navView = findViewById(R.id.nav_view);

        fm.beginTransaction().add(R.id.nav_host_fragment, fragment11, "3").hide(fragment11).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment22, "2").hide(fragment22).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment33, "1").commit();


        navView.setSelectedItemId(R.id.navigation_fragment_first);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fm.beginTransaction().hide(active).show(fragment11).commit();
                        active = fragment11;
                        return true;

                    case R.id.navigation_dashboard:
                        fm.beginTransaction().hide(active).show(fragment22).commit();
                        active = fragment22;
                        return true;

                    case R.id.navigation_fragment_first:
                        fm.beginTransaction().hide(active).show(fragment33).commit();
                        active = fragment33;
                        return true;
                }
                return false;
            }
        });

        navView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                //Toast.makeText(MainActivity.this, "Reselected", Toast.LENGTH_SHORT).show();
            }
        });


        /*
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_fragment_first)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
         */
    }

}
