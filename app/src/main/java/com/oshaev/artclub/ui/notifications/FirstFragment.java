package com.oshaev.artclub.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.oshaev.artclub.R;
import com.oshaev.artclub.databinding.FragmentFirstBinding;
import com.oshaev.artclub.databinding.FragmentUserInfoBinding;
import com.oshaev.artclub.ui.notifications.fragments.PerformanceFragment;
import com.oshaev.artclub.ui.notifications.fragments.SkillsFragment;

public class FirstFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager firstViewPager;

    FragmentFirstBinding binding;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding =
                DataBindingUtil.inflate
                        (inflater, R.layout.fragment_first, container, false);
        View root= binding.getRoot();

        FirstFragmentButtonsHandler buttonsHandler = new FirstFragmentButtonsHandler(getContext());
        binding.setButtonHandler(buttonsHandler);

        firstViewPager = (ViewPager) root.findViewById(R.id.viewpager_content);
        firstViewPager.setOffscreenPageLimit(3);
        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(firstViewPager);
        setupViewPager(firstViewPager);

        return root;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new ProfileFragment(), "Профиль");
        adapter.addFragment(new PerformanceFragment(), "Качества");
        adapter.addFragment(new SkillsFragment(), "Навыки");

        viewPager.setAdapter(adapter);
    }

    public class FirstFragmentButtonsHandler
    {
        Context context;

        public FirstFragmentButtonsHandler(Context context) {
            this.context = context;
        }

        public void onSettingsClicked(View view)
        {
            Intent intent = new Intent(context, UserSettingsActivity.class);
            startActivity(intent);
        }

    }


}