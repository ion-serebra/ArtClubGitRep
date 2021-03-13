package com.oshaev.artclub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.imageview.ShapeableImageView;
import com.oshaev.artclub.ui.MainActivity.MainActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {



    OnLongClickListener listener;
    OnImageClickListener imageClickListener;

    ArrayList<ChatMessage> chatMessages;




    public interface OnImageClickListener {
        void onImageClick(int position, View view);
    }

    public void setImageClickListener(OnImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public ChatMessageAdapter(@NonNull Context context, int resource, List<ChatMessage> messages) {
        super(context, resource, messages);
        chatMessages = (ArrayList) messages;
    }


    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ChatMessage message = getItem(position);

        if(convertView == null)
        {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_layout,
                    parent,false);
        }

        if(message.getName().trim().equals(MainActivity.userName.trim()))
        {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.user_message_layout,
                    parent,false);
        } else {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_layout,
                    parent,false);
        }



        final ShapeableImageView messageImg = convertView.findViewById(R.id.messageImg);
        TextView nameText = convertView.findViewById(R.id.nameText);
        TextView messageText = convertView.findViewById(R.id.messageText);
        TextView messageTime = convertView.findViewById(R.id.timeTextView);
        final CardView imageMessageCardView = convertView.findViewById(R.id.imageMessageCardView);

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm  ddMMM");

        messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "55", Toast.LENGTH_SHORT).show();

                //int position = getAdapterPosition();
                // установка итерфейса OnClickListener
                if(listener != null)
                {
                    if(position!= ListView.NO_ID)
                    {
                        imageClickListener.onImageClick(position, imageMessageCardView); // выполняем полученный метод
                    }
                }

                if(chatMessages.get(position).getImgUrl()!=null)
                {
                    Intent showImageIntent = new Intent(getContext(), ShowImageActivity.class);
                    Bundle bundle;
                    //View messageImage = view.findViewById(R.id.messageImg);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) (getContext()),
                            imageMessageCardView, "showingImage");
                    bundle = options.toBundle();
                    showImageIntent.putExtra("path", chatMessages.get(position).getImgUrl());

                    if (bundle == null) {
                        getContext().startActivity(showImageIntent);
                    } else {
                        getContext().startActivity(showImageIntent, bundle);
                    }
                }


            }
        });


        boolean isText = message.getImgUrl() == null; // если == null, значит сообщение с текстом
        if (isText)
        {
            messageText.setVisibility(View.VISIBLE);
            messageImg.setVisibility(View.GONE);
            messageText.setText(message.getText());
            if(message.getDate()!=null)
            {
                messageTime.setText(formatForDateNow.format(message.getDate()));
            }

        } else
        {
            messageText.setVisibility(View.GONE);
            messageImg.setVisibility(View.VISIBLE);
            if(message.getDate()!=null)
            {
                messageTime.setText(formatForDateNow.format(message.getDate()));
            }

            //messageImg.setMaxHeight();

            Glide.with(messageImg.getContext())
                    .asBitmap()
                    .load(message.getImgUrl())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            //messageImg.setMaxHeight(resource.getScaledHeight(3));
                            messageImg.setImageBitmap(resource);
                            messageImg.setAdjustViewBounds(true);
                        }
                    });


            //Glide.with(messageImg.getContext()).load(message.getImgUrl()).into(messageImg);
            //messageImg.setScaleType(ImageView.ScaleType.CENTER);
        }
        nameText.setText(message.getName());

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if(listener!=null)
                {
                    if(position!= ListView.INVALID_POSITION)
                    {
                        listener.onLongMessageClick(position); // выполняем полученный метод
                    }
                }

                return false;
            }
        });




        return convertView;
    }

    public interface OnLongClickListener
    {
        void onLongMessageClick(int position);
    }

    public void setOnLongMessageClickListener(OnLongClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void remove(@Nullable ChatMessage object) {
        super.remove(object);
    }
}
