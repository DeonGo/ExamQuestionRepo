package com.deongao.examquestionrepo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;

import java.util.ArrayList;
import java.util.List;


public class ExamInfoAdapter extends RecyclerView.Adapter<ExamInfoAdapter.ViewHolder> {


    private List<ExamQuestion> list;
    OnItemClickListener mOnItemClickListener;
    private List<String> answers;

    public ExamInfoAdapter(List<ExamQuestion> list) {
        this.list = list;
        answers = new ArrayList<>(list.size());
        for(int i =0;i<list.size();i++){
            answers.add("");
        }
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

        holder.mLlAnswer.setVisibility(View.VISIBLE);

        if(examQuestion.getType() == QuestionInfoProcessor.SINGLE){
            holder.mRadioGroup.setVisibility(View.VISIBLE);
            holder.mLlMulti.setVisibility(View.GONE);
            holder.mRgJudgment.setVisibility(View.GONE);
            holder.mRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
                String answer = getSingleAnswer(radioGroup);
                answers.remove(position);
                answers.add(position, answer);
            });
        }else if(examQuestion.getType() == QuestionInfoProcessor.MULTIPLE){
            holder.mRadioGroup.setVisibility(View.GONE);
            holder.mLlMulti.setVisibility(View.VISIBLE);
            holder.mRgJudgment.setVisibility(View.GONE);

            CompoundButton.OnCheckedChangeListener onCheckedChangeListener = (compoundButton, b) -> {
                StringBuilder stringBuilder = new StringBuilder();
                if (holder.cbA.isChecked()) stringBuilder.append("A").append(",");
                if (holder.cbB.isChecked()) stringBuilder.append("B").append(",");
                if (holder.cbC.isChecked()) stringBuilder.append("C").append(",");
                if (holder.cbD.isChecked()) stringBuilder.append("D").append(",");

                String result=null;
                try {
                    result = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
                    answers.remove(position);
                    answers.add(position, result);
                }catch (Exception e){

                }
            };

            holder.cbA.setOnCheckedChangeListener(onCheckedChangeListener);
            holder.cbB.setOnCheckedChangeListener(onCheckedChangeListener);
            holder.cbC.setOnCheckedChangeListener(onCheckedChangeListener);
            holder.cbD.setOnCheckedChangeListener(onCheckedChangeListener);

        }else {
            holder.mLlAnswer.setVisibility(View.GONE);
            holder.mRadioGroup.setVisibility(View.GONE);
            holder.mLlMulti.setVisibility(View.GONE);
            holder.mRgJudgment.setVisibility(View.VISIBLE);
            holder.mRgJudgment.setOnCheckedChangeListener((radioGroup, i) -> {
                String answer = getJudgmentAnswer(radioGroup);
                answers.remove(position);
                answers.add(position, answer);
            });
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<ExamQuestion> getData() {
        return list;
    }

    public List<String> getAnswers(){
        return answers;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle,mTvA,mTvB,mTvC,mTvD;
        RadioGroup mRadioGroup,mRgJudgment;
        LinearLayout mLlMulti,mLlAnswer;
        CheckBox cbA,cbB,cbC,cbD;

        ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.et_title);
            mTvA = itemView.findViewById(R.id.et_A);
            mTvB = itemView.findViewById(R.id.et_B);
            mTvC = itemView.findViewById(R.id.et_C);
            mTvD = itemView.findViewById(R.id.et_D);
            mRadioGroup = itemView.findViewById(R.id.rgroup_single);
            mRgJudgment = itemView.findViewById(R.id.rgroup_judgment);

            cbA = itemView.findViewById(R.id.cb_a);
            cbB = itemView.findViewById(R.id.cb_b);
            cbC = itemView.findViewById(R.id.cb_c);
            cbD = itemView.findViewById(R.id.cb_d);
//            mRadioGroup.setVisibility(mProcessor.isRadioGroupVisible() ? View.VISIBLE : View.GONE);

            mLlMulti = itemView.findViewById(R.id.ll_multi);
            mLlAnswer = itemView.findViewById(R.id.ll_answer);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }

    public String getSingleAnswer(RadioGroup view) {
        switch (view.getCheckedRadioButtonId()) {
            case R.id.rbtn_a:
                return "A";
            case R.id.rbtn_b:
                return "B";
            case R.id.rbtn_c:
                return "C";
            case R.id.rbtn_d:
                return "D";
            default:
                return "A";
        }
    }

    public String getMultiAnswer(View view) {
        CheckBox cbA = view.findViewById(R.id.cb_a);
        CheckBox cbB = view.findViewById(R.id.cb_b);
        CheckBox cbC = view.findViewById(R.id.cb_c);
        CheckBox cbD = view.findViewById(R.id.cb_d);

        StringBuilder stringBuilder = new StringBuilder();
        if (cbA.isChecked()) stringBuilder.append("A").append(",");
        if (cbB.isChecked()) stringBuilder.append("B").append(",");
        if (cbC.isChecked()) stringBuilder.append("C").append(",");
        if (cbD.isChecked()) stringBuilder.append("D").append(",");

        String result=null;
        try {
            result = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        }catch (Exception e){

        }

        return result;
    }

    public String getJudgmentAnswer(RadioGroup view) {
        System.out.println("getJudgmentAnswer-----------------");
        switch (view.getCheckedRadioButtonId()) {
            case R.id.rbtn_right:
                return "A";
            case R.id.rbtn_wrong:
                return "B";
            default:
                return "A";
        }
    }
}
