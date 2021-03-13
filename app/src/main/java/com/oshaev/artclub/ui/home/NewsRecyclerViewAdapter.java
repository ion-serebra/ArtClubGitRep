package com.oshaev.artclub.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oshaev.artclub.ChatMessageAdapter;
import com.oshaev.artclub.R;
import com.oshaev.artclub.databinding.PostItemLayoutBinding;

import java.util.ArrayList;



public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.PostViewHolder> {


    public interface OnPostClickListener
    {
     void onPostClick(int position);
    }

    OnPostClickListener onPostClickListener;
    Context context;
    ArrayList<NewsItem> posts;

    public NewsRecyclerViewAdapter(ArrayList<NewsItem> arrayList, Context context) {
        posts = arrayList;
        this.context = context;
    }


    public void setOnPostClickListener(OnPostClickListener onPostClickListener)
    {
        this.onPostClickListener = onPostClickListener;
    }



    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout ,
                parent , false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
         */

        PostItemLayoutBinding binding = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()),R.layout.post_item_layout,
                        parent,false    );
        return new PostViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        NewsItem post = posts.get(position);

        holder.itemLayoutBinding.getClickHandler().setPosition(position);
        /*holder.titleTextView.setText(post.getTitle());
        //holder.authorTextView.setText(post.getAuthor());
        holder.postTextView.setText(post.getPaper());
        //holder.postImagePreview.setImageResource(post.getImageUrl());

         */

        holder.itemLayoutBinding.setPost(post);



    }

    @BindingAdapter({"bind:imgUrl"})
    public static void setProfilePicture(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).
                load(imgUrl).into(imageView);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }



    public class PostViewHolder extends RecyclerView.ViewHolder
    {
        /*
        public ImageView postImagePreview;
        public TextView authorTextView;
        public TextView titleTextView;
        public TextView postTextView;
         */

        private PostItemLayoutBinding itemLayoutBinding;

        ImageView imageView;

        public PostViewHolder(@NonNull PostItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;

            NewsAdapterClickHandler handler = new NewsAdapterClickHandler(context, getAdapterPosition());
            itemLayoutBinding.setClickHandler(handler);

            /*
            postImagePreview = itemView.findViewById(R.id.postImagePreview);
            //authorTextView = itemView.findViewById(R.id.authorTextView);
            titleTextView = itemView.findViewById(R.id.postTitleTextView);
            postTextView = itemView.findViewById(R.id.postTextView);
             */
        }
    }

    public class NewsAdapterClickHandler{
        Context context;
        int position;

        public void setPosition(int position) {
            this.position = position;
        }

        public NewsAdapterClickHandler(Context context, int position) {
            this.position = position;
            this.context = context;
        }

        public void onPostClicked(View view)
        {
            if(onPostClickListener!=null)
            {
                if(position!=RecyclerView.NO_POSITION) {
                    onPostClickListener.onPostClick(position);
                }
            }
        }
    }









}
