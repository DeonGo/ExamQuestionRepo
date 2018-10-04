package com.deongao.examquestionrepo.presenter;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.AdminContract;
import com.deongao.examquestionrepo.contract.LoginContract;
import com.deongao.examquestionrepo.model.ExamQuestion;

import java.util.List;

public class AdminPresenter implements AdminContract.Presenter {

    AdminContract.View view;
    AdminContract.Provider provider;

    public AdminPresenter(AdminContract.View view, AdminContract.Provider provider) {
        this.view = view;
        this.provider = provider;
    }

    @Override
    public void start() {
        provider.getFullList(new OnRequestCallbackListener<List<ExamQuestion>>() {
            @Override
            public void onSuccess(List<ExamQuestion> examQuestions) {
                view.showList(examQuestions);
            }

            @Override
            public void onFailure(String s) {

            }
        });
    }

    @Override
    public void addQuestion() {
        view.navigateToQuestionCreationPage();
    }

    @Override
    public void viewQuestion() {
        view.navigateToQuestionDetailPage();
    }


}
