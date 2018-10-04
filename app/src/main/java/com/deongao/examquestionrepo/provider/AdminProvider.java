package com.deongao.examquestionrepo.provider;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.AdminContract;
import com.deongao.examquestionrepo.model.ExamQuestion;

import java.util.List;

public class AdminProvider implements AdminContract.Provider {
    @Override
    public void getFullList(OnRequestCallbackListener<List<ExamQuestion>> onRequestCallbackListener) {
        onRequestCallbackListener.onSuccess(EntityManager.getInstance().getFullList());
    }
}
