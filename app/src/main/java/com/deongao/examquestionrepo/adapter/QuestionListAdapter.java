package com.deongao.examquestionrepo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.model.ExamQuestion;

import java.util.List;


public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {
    private List<ExamQuestion> list;
    OnItemClickListener mOnItemClickListener;

    public QuestionListAdapter(List<ExamQuestion> list,  OnItemClickListener onItemClickListener) {
        this.list = list;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public QuestionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        QuestionListAdapter.ViewHolder viewHolder = new QuestionListAdapter.ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionListAdapter.ViewHolder holder, int position) {
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
