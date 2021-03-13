package com.oshaev.artclub.usertasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oshaev.artclub.ChatsRecyclerViewAdapter;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.TaskViewHolder> {

    ArrayList<UserTask> tasks;
    OnItemClickListener listener;
    OnButtonClickListener buttonClickListener;




    public interface OnItemClickListener
    {
        void OnClick(int position);
    }

    public interface OnButtonClickListener
    {
        void OnClick(int position);
    }


    public TasksRecyclerViewAdapter(ArrayList<UserTask> tasks) {
        this.tasks = tasks;
    }

    public void setOnItemClickListener(OnItemClickListener listener)  {
        this.listener = listener;
    }
    public void setOnButtonClickListener(OnButtonClickListener buttonClickListener)  {
        this.buttonClickListener = buttonClickListener;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card,
                parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        UserTask task = tasks.get(position);
        holder.taskName.setText(task.getName());
        if(task.getDate()!=null)
        {
            holder.taskButton.setVisibility(View.INVISIBLE);
            holder.timeTextView.setVisibility(View.VISIBLE);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss", Locale.getDefault());
            holder.timeTextView.setText(dateFormat.format(task.getDate()));
        }
        else
        {
            holder.taskButton.setVisibility(View.VISIBLE);
            holder.timeTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
   try
   {
       return tasks.size();
   } catch (NullPointerException e)
   {
       return 0;
   }
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView timeTextView;
        ImageView taskImage;
        ImageButton taskButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
            taskImage = itemView.findViewById(R.id.taskImage);
            taskButton = itemView.findViewById(R.id.taskButton);
            timeTextView = itemView.findViewById(R.id.timeTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!=null)
                    {
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.OnClick(position);
                        }
                    }
                }
            });

            taskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(buttonClickListener!=null)
                    {
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            buttonClickListener.OnClick(position);
                        }
                    }
                }
            });

        }
    }
}











