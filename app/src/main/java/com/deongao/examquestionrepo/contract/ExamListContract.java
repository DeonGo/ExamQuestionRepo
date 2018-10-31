package com.deongao.examquestionrepo.contract;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.model.Exams;

import java.util.List;

public class ExamListContract {
    public interface View{
        void showList(List<Exams> list);
    }

    public interface Presenter{
        void start();
    }

    public interface Provider{
        void getExamFullList(OnRequestCallbackListener<List<Exams>> onRequestCallbackListener);
    }
}
