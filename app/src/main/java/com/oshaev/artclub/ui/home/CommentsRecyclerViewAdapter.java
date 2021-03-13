package com.oshaev.artclub.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.oshaev.artclub.Comment;
import com.oshaev.artclub.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.CommentsViewHolder> {

    public ArrayList<Comment> comments;

    public CommentsRecyclerViewAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsRecyclerViewAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comment, parent, false);
        CommentsViewHolder viewHolder = new CommentsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsRecyclerViewAdapter.CommentsViewHolder holder, int position) {

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm  dd MMM");
        Comment comment = comments.get(position);
        if(comment.getSender()!=null) {
            holder.commentUserNameTextView.setText(comment.getSender().getName());
        }

        if(comment.getUserName()!=null && comment.getUserSurname()!=null)
        {
            String userFullName = comment.getUserName()+" "+comment.getUserSurname();
            holder.commentUserNameTextView.setText(userFullName);
        }

        if(comment.getUserImgUrl()!=null)
        {

            Glide.with(holder.userAvatar)
                    .load(comment.getUserImgUrl())
                    .into(holder.userAvatar);
        }



        holder.commentContentTextView.setText(comment.getCommentContent());
        holder.commentTimeTextView.setText(formatForDateNow.format(comment.getDate()));

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView commentUserNameTextView;
        MaterialTextView commentContentTextView;
        MaterialTextView commentTimeTextView;
        ImageView userAvatar;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            commentUserNameTextView = itemView.findViewById(R.id.commentUserNameTextView);
            commentContentTextView = itemView.findViewById(R.id.commentContentTextView);
            commentTimeTextView = itemView.findViewById(R.id.commentTimeTextView);
            userAvatar = itemView.findViewById(R.id.userAvatar);
        }
    }
}
