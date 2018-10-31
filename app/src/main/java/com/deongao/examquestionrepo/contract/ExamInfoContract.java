package com.deongao.examquestionrepo.contract;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.model.ExamQuestion;

import java.util.List;

public class ExamInfoContract {
    public interface View{
        void showList(List<ExamQuestion> list);
    }

    public interface Presenter{
        void start(String ids);
    }

    public interface Provider{
        void getQuestionList(String ids, OnRequestCallbackListener<List<ExamQuestion>> onRequestCallbackListener);
    }
}
