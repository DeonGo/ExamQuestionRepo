package com.deongao.examquestionrepo.provider;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.ExamListContract;
import com.deongao.examquestionrepo.model.Exams;

import java.util.List;

public class ExamListProvider implements ExamListContract.Provider {

    @Override
    public void getExamFullList(OnRequestCallbackListener<List<Exams>> onRequestCallbackListener) {
        onRequestCallbackListener.onSuccess(EntityManager.getInstance().getExamList());
    }
}
