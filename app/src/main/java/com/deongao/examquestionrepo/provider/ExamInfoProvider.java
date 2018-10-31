package com.deongao.examquestionrepo.provider;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.ExamInfoContract;
import com.deongao.examquestionrepo.contract.ExamListContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.model.Exams;

import java.util.List;

public class ExamInfoProvider implements ExamInfoContract.Provider {

    @Override
    public void getQuestionList(String ids, OnRequestCallbackListener<List<ExamQuestion>> onRequestCallbackListener) {
            onRequestCallbackListener.onSuccess(EntityManager.getInstance().getExamInfoList(ids));
    }
}
