package com.deongao.examquestionrepo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;

import java.util.List;


public class ExamInfoAdapter extends RecyclerView.Adapter<ExamInfoAdapter.ViewHolder> {
    private List<ExamQuestion> list;
    OnItemClickListener mOnItemClickListener;

    public ExamInfoAdapter(List<ExamQuestion> list) {
        this.list = list;
//        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ExamInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_question_item, parent, false);
        ExamInfoAdapter.ViewHolder viewHolder = new ExamInfoAdapter.ViewHolder(view);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemClickListener.onItemClick(v);
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExamInfoAdapter.ViewHolder holder, int position) {
        System.out.println("ExamInfoAdapter----position--------------"+position);
        ExamQuestion examQuestion = list.get(position);
        holder.mTvTitle.setText(String.valueOf(position+1)+" : "+examQuestion.getTitle());
        holder.mTvA.setText("A: "+examQuestion.getAnswerA());
        holder.mTvB.setText("B: "+examQuestion.getAnswerB());
        holder.mTvC.setText("C: "+examQuestion.getAnswerC());
        holder.mTvD.setText("D: "+examQuestion.getAnswerD());

        if(examQuestion.getType() == QuestionInfoProcessor.SINGLE){
            holder.mRadioGroup.setVisibility(View.VISIBLE);
            holder.mLlMulti.setVisibility(View.GONE);
        }else {
            holder.mRadioGroup.setVisibility(View.GONE);
            holder.mLlMulti.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle,mTvA,mTvB,mTvC,mTvD;
        RadioGroup mRadioGroup;
        LinearLayout mLlMulti;

        ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.et_title);
            mTvA = itemView.findViewById(R.id.et_A);
            mTvB = itemView.findViewById(R.id.et_B);
            mTvC = itemView.findViewById(R.id.et_C);
            mTvD = itemView.findViewById(R.id.et_D);
            mRadioGroup = itemView.findViewById(R.id.rgroup_single);
//            mRadioGroup.setVisibility(mProcessor.isRadioGroupVisible() ? View.VISIBLE : View.GONE);

            mLlMulti = itemView.findViewById(R.id.ll_multi);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }
}
