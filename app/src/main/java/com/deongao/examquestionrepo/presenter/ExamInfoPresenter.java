package com.deongao.examquestionrepo.presenter;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.ExamInfoContract;
import com.deongao.examquestionrepo.contract.ExamListContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.model.Exams;

import java.util.List;

public class ExamInfoPresenter implements ExamInfoContract.Presenter {

    ExamInfoContract.View view;
    ExamInfoContract.Provider provider;

    public ExamInfoPresenter(ExamInfoContract.View view, ExamInfoContract.Provider provider) {
        this.view = view;
        this.provider = provider;
    }

    @Override
    public void start(String ids) {
        provider.getQuestionList(ids, new OnRequestCallbackListener<List<ExamQuestion>>() {
            @Override
            public void onSuccess(List<ExamQuestion> examQuestions) {
                view.showList(examQuestions);
            }

            @Override
            public void onFailure(String s) {

            }
        });
    }


}
