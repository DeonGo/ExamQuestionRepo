package com.deongao.examquestionrepo.presenter;

import android.text.TextUtils;

import com.deongao.examquestionrepo.OnRequestCallbackListener;
import com.deongao.examquestionrepo.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;
    LoginContract.Provider provider;

    public LoginPresenter(LoginContract.View view, LoginContract.Provider provider) {
        this.view = view;
        this.provider = provider;
    }

    @Override
    public void login(String name, String pwd, boolean isAdmin) {
        if(TextUtils.isEmpty(name)){
            view.showUsernameEmptyAlert();
        }else if(TextUtils.isEmpty(pwd)){
            view.showPwdEmptyAlbert();
        }else {
            provider.login(name, pwd, new OnRequestCallbackListener() {
                @Override
                public void onSuccess() {
                    if(isAdmin){
                        view.navigateToAdminPage();
                    }else {
                        view.navigateToUserPage();
                    }
                }

                @Override
                public void onFailure() {

                }
            });
        }

    }
}
