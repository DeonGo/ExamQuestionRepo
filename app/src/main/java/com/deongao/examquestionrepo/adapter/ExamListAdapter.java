package com.deongao.examquestionrepo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.model.Exams;

import java.util.List;


public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ViewHolder> {
    private List<Exams> list;
    OnItemClickListener mOnItemClickListener;
    private boolean isAdmin;

    public ExamListAdapter(List<Exams> list, boolean isAdmin, OnItemClickListener onItemClickListener) {
        this.list = list;
        mOnItemClickListener = onItemClickListener;
        this.isAdmin = isAdmin;
    }

    @Override
    public ExamListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        ExamListAdapter.ViewHolder viewHolder = new ExamListAdapter.ViewHolder(view);
        viewHolder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExamListAdapter.ViewHolder holder, int position) {
        System.out.println("ExamListAdapter----position--------------"+position);
        holder.mText.setText(list.get(position).getTitle());
        if(isAdmin){
            holder.mScore.setText("删除");
            holder.mScore.setTag(list.get(position));
            holder.mScore.setOnClickListener(view -> mOnItemClickListener.onDelClick((Exams) view.getTag()));
        }else if(list.get(position).getScore() == -1){
            holder.mScore.setText("未评测");
            holder.mScore.setOnClickListener(null);
        }else {
            holder.mScore.setText(String.format("%s 分",list.get(position).getScore()));
            holder.mScore.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText, mScore;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tv_title);
            mScore = itemView.findViewById(R.id.tv_score);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
        void onDelClick(Exams exams);
    }
}
