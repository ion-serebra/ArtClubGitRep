package com.oshaev.artclub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.collection.LLRBNode;
import com.oshaev.artclub.ui.notifications.ProfileFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatsRecyclerViewAdapter extends RecyclerView.Adapter<ChatsRecyclerViewAdapter.ChatsViewHolder> {

    ArrayList<Chat> chats;
    private ChatsViewHolder holder;
    private int position;
    Context context;
    View transitView;
    private OnChatClickListener listener;
    private OnChatLongClickListener longClickListener;


    public interface OnChatClickListener {
        void onChatClick(int position);
    }

    public void setOnChatClickListener(OnChatClickListener listener)  {
        this.listener = listener;
    }

    public interface OnChatLongClickListener
    {
        void onChatLongClick(int position);
    }

    public void setOnUserLongClickListener(OnChatLongClickListener longClickListener)
    {
        this.longClickListener = longClickListener;
    }

    public ChatsRecyclerViewAdapter(ArrayList<Chat> chats, Context context) {
        this.chats = chats;
        this.context = context;
    }


    public void clear()
    {
        chats.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Chat> allChats)
    {
        chats.addAll(allChats);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_preview_card,
                parent,false);
        transitView = view;
        ChatsViewHolder viewHolder = new ChatsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        Chat currentChat = chats.get(position);
        holder.itemView.setVisibility(View.VISIBLE);
        holder.chatName.setText(currentChat.getName());
        holder.chatChildName.setText(currentChat.getDBChild()+ " Уровень доступа: "+ currentChat.getAccessLevel());

        Random random = new Random();
        // Массив из пяти цветов
        int colors[] = { Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.RED,
                Color.CYAN };
        int pos = random.nextInt(colors.length);
        // Меняем цвет у кнопки
        holder.chatImageAvatar.setBackgroundColor(colors[pos]);

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ChatsViewHolder extends RecyclerView.ViewHolder {
        public TextView chatName;
        public TextView chatChildName;
        public ShapeableImageView chatImageAvatar;

        public ChatsViewHolder(@NonNull final View itemView) {
            super(itemView);
            chatName =  itemView.findViewById(R.id.chatName);
            chatChildName = itemView.findViewById(R.id.chatChild);
            chatImageAvatar = itemView.findViewById(R.id.chatImageAvatar);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();

                    if(longClickListener != null)
                    {
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            longClickListener.onChatLongClick(position); // выполняем полученный метод
                        }
                    }
                    return false;
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    // установка итерфейса OnClickListener
                    if(listener != null)
                    {
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onChatClick(position); // выполняем полученный метод
                        }
                    }



                    if (chats.get(getAdapterPosition()).getAccessLevel() <= ProfileFragment.getUserAccessLevel()
                    )
                    {


                     Bundle bundle;
                     View vv = chatName;
                     ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            vv, "transition");
                     bundle = options.toBundle();


                     Intent intent = new Intent(context, ChatActivity.class);
                     intent.putExtra("chatChild", chats.get(getAdapterPosition()).getDBChild());
                     intent.putExtra("chatName", chats.get(getAdapterPosition()).getName());
                     intent.putExtra("chatKey", chats.get(getAdapterPosition()).getId());



                      if (bundle == null)
                      {
                          context.startActivity(intent);
                      } else {
                          context.startActivity(intent, bundle);
                      }
                    }


                }
            });





        }
    }
}
