package com.deongao.examquestionrepo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;

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
        viewHolder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionListAdapter.ViewHolder holder, int position) {
        holder.mText.setText(String.valueOf(position+1)+ ". "+list.get(position).getTitle());
        int type = list.get(position).getType();
        if(type == QuestionInfoProcessor.SINGLE){
            holder.mScore.setText("单选题");
        }else if(type == QuestionInfoProcessor.MULTIPLE){
            holder.mScore.setText("多选题");
        }else {
            holder.mScore.setText("判断题");
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
        void onItemLongClick(View view);
    }
}
