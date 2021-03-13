package com.oshaev.artclub.usertasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.oshaev.artclub.R;
import com.oshaev.artclub.databinding.ActivityCreatePerformanceBinding;
import com.oshaev.artclub.databinding.ActivityUserProfilePublicBinding;

public class CreatePerformanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_performance);
        ActivityCreatePerformanceBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_performance);
        binding.setImpressive("Impressive world");


    }
}
