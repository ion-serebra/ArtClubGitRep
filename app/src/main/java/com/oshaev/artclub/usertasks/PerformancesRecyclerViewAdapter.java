package com.oshaev.artclub.usertasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oshaev.artclub.R;

import java.util.ArrayList;

public class PerformancesRecyclerViewAdapter
        extends RecyclerView.Adapter<PerformancesRecyclerViewAdapter.PerformanceViewHolder> {

    ArrayList<Performance> performances;
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


    public PerformancesRecyclerViewAdapter(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    @NonNull
    @Override
    public PerformancesRecyclerViewAdapter.PerformanceViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.performance_card,
                parent, false);
        PerformanceViewHolder viewHolder = new PerformanceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PerformancesRecyclerViewAdapter.PerformanceViewHolder holder, int position) {

        Performance performance = performances.get(position);
        holder.performanceNameTextView.setText(performance.getName());
        holder.performanceValueTextView.setText(performance.getExperience()+"");

    }

    @Override
    public int getItemCount() {
        return performances.size();
    }

    public class PerformanceViewHolder extends RecyclerView.ViewHolder {

        ImageView performanceImageView;
        TextView performanceNameTextView;
        TextView performanceValueTextView;

        public PerformanceViewHolder(@NonNull View itemView) {
            super(itemView);

            performanceImageView = itemView.findViewById(R.id.performanceImageView);
            performanceNameTextView = itemView.findViewById(R.id.performanceNameTextView);
            performanceValueTextView = itemView.findViewById(R.id.performanceValueTextView);
        }
    }
}
