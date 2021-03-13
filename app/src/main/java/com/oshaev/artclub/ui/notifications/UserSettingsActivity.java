package com.oshaev.artclub.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.oshaev.artclub.LoginActivity;
import com.oshaev.artclub.R;
import com.oshaev.artclub.databinding.ActivityUserProfilePublicBinding;
import com.oshaev.artclub.databinding.ActivityUserSettingsBinding;
import com.oshaev.artclub.ui.MainActivity.MainActivity;

public class UserSettingsActivity extends AppCompatActivity {

    ActivityUserSettingsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_settings);
        UserSettingsButtonsHandler buttonsHandler = new UserSettingsButtonsHandler(this);
        binding.setButtonsHandler(buttonsHandler);





    }


    public class UserSettingsButtonsHandler
    {
        Context context;

        public UserSettingsButtonsHandler(Context context) {
            this.context = context;
        }

        public void onLogOutClicked(View view)
        {
            FirebaseAuth.getInstance().signOut();
            MainActivity.setCurrentUser(null);
            startActivity(new Intent(context, LoginActivity.class));
        }

    }

}
