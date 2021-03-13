package com.oshaev.artclub.ui.home;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DatabaseReference;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;

public class HomeFragment extends Fragment {

    private User currentUser;

    private RecyclerView postsRecyclerView;
    private NewsRecyclerViewAdapter adapter;
    private ArrayList<NewsItem> posts;
    private LinearLayoutManager manager;
    HomeButtonHandler buttonHandler;
    FragmentHomeBinding binding;
    HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        binding =  DataBindingUtil.inflate
                (inflater, R.layout.fragment_home
                        , container, false);
        View root = binding.getRoot();
        buttonHandler = new HomeButtonHandler(getContext());
        binding.setButtonHandler(buttonHandler);
        postsRecyclerView = binding.postsRecyclerView;

        posts = new ArrayList<>();

        //homeViewModel.getEventsList();
        //posts = homeViewModel.posts;

        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new NewsRecyclerViewAdapter(homeViewModel.posts,getContext());

        postsRecyclerView.setLayoutManager(manager);
        postsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        homeViewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                currentUser = user;
            }
        });

        adapter.setOnPostClickListener(new NewsRecyclerViewAdapter.OnPostClickListener() {
            @Override
            public void onPostClick(int position) {
                Intent intent = new Intent(getContext(), ShowingPostActivity.class);
                intent.putStringArrayListExtra("imagePathList", posts.get(position).getImageUrlStrings());
                intent.putExtra("title", posts.get(position).getTitle());
                intent.putExtra("paper",posts.get(position).getPaper());
                intent.putExtra("postKey", posts.get(position).getKey());

                if(currentUser!=null)
                {
                    intent.putExtra("userName", currentUser.getName());
                    intent.putExtra("userSurname", currentUser.getSurname());
                    intent.putExtra("userId", currentUser.getId());
                    intent.putExtra("userImgUrl", currentUser.getUserAvatarPath());
                }

                startActivity(intent);
            }
        });

        homeViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList<NewsItem>>() {
            @Override
            public void onChanged(ArrayList<NewsItem> newsItems) {
                posts = newsItems;
                Log.e("addedPost", posts.toString());
                adapter.notifyDataSetChanged();
            }
        });

        /*
        posts.add(new NewsItem("Мероприятие","12 фев","Новое мероприятие!"));
        posts.get(0).setImageUrlString("https://firebasestorage.googleapis.com/v0/b/art-club-social-network.appspot.com/o/chat_images%2Fstorage%2Femulated%2F0%2Fdownload%2F%D0%BC%D0%B5%D0%BC-%D0%B4%D0%B6%D0%B5%D1%80%D1%80%D0%B8.jpg?alt=media&token=b2b3bf44-cb82-4b04-8d71-588fabbb20f9");
        posts.add(new NewsItem("Большой квартирник ко дню студента>","24 янв","" +
                "\uD83D\uDD25А значит это, что завтра особенный день - ДЕНЬ СТУДЕНТА! Сессия наконец-то закрыта (или почти), и теперь самое время отметить самое яркое студенческое событие января!\n" +
                "\n" +
                "\uD83C\uDFA4В честь этого мы приглашаем вас отпраздновать этот день на БОЛЬШОМ КВАРТИРНИКЕ, который состоится 25 января! Наши резиденты будут дарить вам своё творчество из самых разных уголков страны в режиме онлайн!\n" +
                "\n" +
                "Присоединяйтесь к трансляции, чтобы вместе зажечь и отпраздновать День Студента в кругу самых творческих студентов лучшего технического!"));
        posts.add(new NewsItem("Кастинг на дебют","12 фев","\uD83E\uDD29 Спешим тебя обрадовать, ведь самое долгожданное событие этого месяца уже на пороге! КАСТИНГ на конкурс талантов\n" +
                "«Дебют на бауманской сцене» и ОТБОР\n" +
                "в Art Club BMSTU состоится уже совсем скоро.\n" +
                "И мы ждем именно тебя!"));
        posts.add(new NewsItem("Становись культоргом","12 фев","В каждой учебной группе есть как минимум 3 человека, которые всегда знают больше остальных.\n" +
                "\n" +
                "Нет, мы не про формулы и остальное, мы про ту самую информацию, мимо которой лучше не проходить"));
        //posts.add(new NewsItem("Мероприятие","12 фев","Новое мероприятие!"));

        posts.get(1).setImageUrlString("https://firebasestorage.googleapis.com/v0/b/art-club-social-network.appspot.com/o/culturalEvents%2Fimage%3A573732?alt=media&token=9ef0a239-8f07-458e-ae05-4ee0c6de60aa");

         */





        return root;
    }

    public class HomeButtonHandler
    {

        Context context;

        public HomeButtonHandler(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view)
        {
            startActivity(new Intent(context, AddSocialEventActivity.class));
        }
    }

}













