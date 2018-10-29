package com.deongao.examquestionrepo.contract;

import com.deongao.examquestionrepo.model.ExamQuestion;

public class QuestionInfoContract {
    public interface View{
        void showQuestion(ExamQuestion examQuestion);
        void back();
        void showError(String text);
    }

    public interface Presenter{
        void start(ExamQuestion examQuestion);
        void submit(String title, String a, String b, String c, String d, String answer, int type,Long id);
    }

    public interface Provider{
        void addQuestion(ExamQuestion examQuestion);
        void updateQuestion(ExamQuestion examQuestion);
    }
}
