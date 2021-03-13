package com.oshaev.artclub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private ArrayList<User> users;

    private Context context;
    private static String uId;
    private Integer cardResource;

    private OnUserClickListener listener;

    public interface OnUserClickListener {
        void onUserClick(int position);
    }

    public void setOnUserClickListener(OnUserClickListener listener)  {
    this.listener = listener;
    }

    public UserListAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }


    public UserListAdapter(ArrayList<User> users, Context context, Integer cardResource) {
        this.users = users;
        this.context = context;
        this.cardResource = cardResource;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(cardResource == null) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_card_layout, parent,
                    false);
        } else
        {
             view = LayoutInflater.from(parent.getContext()).inflate(cardResource, parent,
                    false);
        }

        UserViewHolder viewHolder = new UserViewHolder(view.getRootView(),listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = users.get(position);
        holder.userAvatarImageView.setImageResource(currentUser.getImageResource());
        holder.userNameTextView.setText(currentUser.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public ImageView userAvatarImageView;
        public TextView userNameTextView;

        public UserViewHolder(@NonNull View itemView, final OnUserClickListener listener) {
            super(itemView);
            userAvatarImageView = itemView.findViewById(R.id.userAvatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null)
                    {
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onUserClick(position); // выполняем полученный метод
                        }
                    }
                }
            });
        }
    }
}
