package com.deongao.examquestionrepo.provider;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.LoginContract;

public class LoginProvider implements LoginContract.Provider {

    @Override
    public void login(String name, String pwd, OnRequestCallbackListener onRequestCallback) {
        onRequestCallback.onSuccess();
    }
}
