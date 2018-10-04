package com.deongao.examquestionrepo.contract;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.model.ExamQuestion;

import java.util.List;

public class AdminContract {
    public interface View{
        void navigateToQuestionCreationPage();
        void navigateToQuestionDetailPage();
        void showList(List<ExamQuestion> list);
    }

    public interface Presenter{
        void start();
        void addQuestion();
        void viewQuestion();
    }

    public interface Provider{
        void getFullList(OnRequestCallbackListener<List<ExamQuestion>> onRequestCallbackListener);
    }
}
