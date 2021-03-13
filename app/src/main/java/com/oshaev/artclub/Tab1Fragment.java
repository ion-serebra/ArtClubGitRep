package com.oshaev.artclub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class Tab1Fragment extends Fragment {

    int resource = R.layout.activity_user_for_admin;


    public Tab1Fragment() {
        // Required empty public constructor
    }

    public Tab1Fragment(int resource) {
     this.resource = resource;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(resource, container, false);
    }

}