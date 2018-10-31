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

    public ExamListAdapter(List<Exams> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        mOnItemClickListener = onItemClickListener;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tv_title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }
}
