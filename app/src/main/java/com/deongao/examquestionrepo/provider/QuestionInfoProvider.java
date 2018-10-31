package com.deongao.examquestionrepo.provider;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.contract.QuestionInfoContract;
import com.deongao.examquestionrepo.model.ExamQuestion;

public class QuestionInfoProvider implements QuestionInfoContract.Provider {



    @Override
    public void addQuestion(ExamQuestion examQuestion) {
        EntityManager.getInstance().addQuestion(examQuestion);
    }

    @Override
    public void updateQuestion(ExamQuestion examQuestion) {
        EntityManager.getInstance().updateQuestion(examQuestion);
    }

    @Override
    public void delete(ExamQuestion examQuestion) {
        EntityManager.getInstance().deleteQuestion(examQuestion);
    }
}
