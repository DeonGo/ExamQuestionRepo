package com.deongao.examquestionrepo.presenter;

import android.text.TextUtils;

import com.deongao.examquestionrepo.contract.QuestionInfoContract;
import com.deongao.examquestionrepo.model.ExamQuestion;

public class QuestionInfoPresenter implements QuestionInfoContract.Presenter {

    QuestionInfoContract.View view;
    QuestionInfoContract.Provider provider;

    public QuestionInfoPresenter(QuestionInfoContract.View view, QuestionInfoContract.Provider provider) {
        this.view = view;
        this.provider = provider;
    }

    @Override
    public void start(ExamQuestion examQuestion) {
        if (examQuestion != null) view.showQuestion(examQuestion);
    }

    @Override
    public void submit(String title, String a, String b, String c, String d, String answer, int type,Long id) {
        if(TextUtils.isEmpty(title)){
            view.showError("题目不能为空！！！");
        }else if(TextUtils.isEmpty(a)||TextUtils.isEmpty(b)||TextUtils.isEmpty(c)||TextUtils.isEmpty(d)){
            view.showError("选择项不能为空！！！");
        }else if(TextUtils.isEmpty(answer)){
            view.showError("答案不能为空！！！");
        }else {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setAnswerA(a);
            examQuestion.setAnswerB(b);
            examQuestion.setAnswerC(c);
            examQuestion.setAnswerD(d);
            examQuestion.setTitle(title);
            examQuestion.setType(type);
            examQuestion.setRealAnswer(answer);

            if(id == -1){
                provider.addQuestion(examQuestion);
            }else {
                examQuestion.setId(id);
                provider.updateQuestion(examQuestion);
            }

            view.back();
        }

    }
}
