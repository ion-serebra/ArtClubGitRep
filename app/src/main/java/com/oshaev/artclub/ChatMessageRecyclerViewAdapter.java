package com.oshaev.artclub;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChatMessageRecyclerViewAdapter extends RecyclerView.Adapter<ChatMessageRecyclerViewAdapter.MessageViewHolder> {

    ArrayList<ChatMessage> chatMessages;

    public ChatMessageRecyclerViewAdapter(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm  ddMMM");


        if(chatMessage.getImgUrl()!=null)
        {
            //holder.messageImg
            holder.messageText.setVisibility(View.GONE);
            //holder.messageText.setVisibility(View.GONE);
            Glide.with(holder.messageImg.getContext())
                    .asBitmap()
                    .load(chatMessage.getImgUrl())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            //messageImg.setMaxHeight(resource.getScaledHeight(3));
                            holder.messageImg.setImageBitmap(resource);
                            holder.messageImg.setAdjustViewBounds(true);
                        }
                    });
        }
        else {

            holder.messageImg.setVisibility(View.GONE);
            holder.nameText.setText(chatMessage.getName());
            holder.messageText.setText(chatMessage.getText());
        }

        if(chatMessage.getDate()!=null)
        {
            holder.messageTime.setText(formatForDateNow.format(chatMessage.getDate()));
        }

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        final ShapeableImageView messageImg;
        TextView nameText;
        TextView messageText;
        TextView messageTime;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageImg = itemView.findViewById(R.id.messageImg);
            nameText = itemView.findViewById(R.id.nameText);
            messageText = itemView.findViewById(R.id.messageText);
            messageTime = itemView.findViewById(R.id.timeTextView);

        }
    }
}
