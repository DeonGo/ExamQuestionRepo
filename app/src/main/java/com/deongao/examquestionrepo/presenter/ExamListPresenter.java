package com.deongao.examquestionrepo.presenter;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.ExamListContract;
import com.deongao.examquestionrepo.model.Exams;

import java.util.List;

public class ExamListPresenter implements ExamListContract.Presenter {

    ExamListContract.View view;
    ExamListContract.Provider provider;

    public ExamListPresenter(ExamListContract.View view, ExamListContract.Provider provider) {
        this.view = view;
        this.provider = provider;
    }

    @Override
    public void start() {
        provider.getExamFullList(new OnRequestCallbackListener<List<Exams>>() {
            @Override
            public void onSuccess(List<Exams> exams) {
                view.showList(exams);
            }

            @Override
            public void onFailure(String s) {

            }
        });
    }


}
