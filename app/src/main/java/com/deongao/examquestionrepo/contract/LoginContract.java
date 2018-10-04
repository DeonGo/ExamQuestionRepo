package com.deongao.examquestionrepo.contract;

import com.deongao.examquestionrepo.OnRequestCallbackListener;

public class LoginContract {
    public interface View{
        void navigateToAdminPage();
        void navigateToUserPage();
        void showUsernameEmptyAlert();
        void showPwdEmptyAlbert();
    }

    public interface Presenter{
        void login(String name, String pwd, boolean isAdmin);
    }

    public interface Provider{
        void login(String name, String pwd, OnRequestCallbackListener onRequestCallback);
    }
}
