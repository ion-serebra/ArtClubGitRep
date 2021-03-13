package com.oshaev.artclub.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.AddChatActivity;
import com.oshaev.artclub.Chat;
import com.oshaev.artclub.ChatMessageAdapter;
import com.oshaev.artclub.ChatsRecyclerViewAdapter;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.ui.notifications.ProfileFragment;

import java.util.ArrayList;

public class ChooseChatFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    ArrayList<Chat> chats;


    private ListView messageListView;
    private ChatMessageAdapter messageAdapter;
    private ImageButton sendImageButton;
    private ImageButton sendMessageButton;
    private FloatingActionButton addChatButton;
    private EditText messageEditText;
    private RecyclerView chatsRecyclerView;
    private ChatsRecyclerViewAdapter adapter;
    private LinearLayoutManager manager;

    private String userName;

    FirebaseDatabase database;
    DatabaseReference messagesDatabaseReference;
    DatabaseReference chatsDatabaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of( this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_choose_chat, container, false);

        dashboardViewModel.getText();

        database = FirebaseDatabase.getInstance();
        chatsDatabaseReference = database.getReference().child("chats");

        sendImageButton = root.findViewById(R.id.sendImageButton);
        sendMessageButton = root.findViewById(R.id.sendMessageButton);
        messageEditText = root.findViewById(R.id.messageEditText);
        chatsRecyclerView = root.findViewById(R.id.chatsRecyclerView);
        addChatButton = root.findViewById(R.id.addChatButton);


        addChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddChatActivity.class);
                startActivity(intent);
            }
        });

        chats = new ArrayList<>();

        chatsDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (isUserChatMember(chat)) {
                        chat.setId(snapshot.getKey());
                        chats.add(chat);
                        runLayoutAnimation(chatsRecyclerView);
                        adapter.notifyDataSetChanged();
                    }
                } catch (Error e)
                {}
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

        buildRecyclerView();
        adapter.setOnUserLongClickListener(new ChatsRecyclerViewAdapter.OnChatLongClickListener() {
            @Override
            public void onChatLongClick(int position) {

            }
        });





        //runLayoutAnimation(chatsRecyclerView);

        return root;
    }

/*
    @Override
    public void onPause() {
        chats.clear();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

 */


    private void buildRecyclerView() {

        //chatsRecyclerView.setHasFixedSize(true);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(chatsRecyclerView.getContext(), resId);
        chatsRecyclerView.setLayoutAnimation(animation);
        manager = new LinearLayoutManager(getContext());
        adapter = new ChatsRecyclerViewAdapter(chats, getContext());
        chatsRecyclerView.setLayoutManager(manager);
        chatsRecyclerView.setAdapter(adapter);



        //chatsRecyclerView.getAdapter().notifyDataSetChanged();


    }

    private void runLayoutAnimation(RecyclerView recyclerView)
    {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    private boolean isUserChatMember(Chat chat)
    {
        for(User user : chat.getChatMembers())
        {
            if(user.getId().equals(FirebaseAuth.getInstance().getUid()))
            {
                return true;
            }
        }

        return false;
    }

}
