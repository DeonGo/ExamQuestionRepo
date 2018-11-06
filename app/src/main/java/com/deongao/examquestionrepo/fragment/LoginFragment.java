package com.deongao.examquestionrepo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.contract.LoginContract;
import com.deongao.examquestionrepo.presenter.LoginPresenter;
import com.deongao.examquestionrepo.provider.LoginProvider;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    TextInputEditText mUsername, mPwd;
    RadioButton mRbtnAdmin, mRbtnUser;
    Button mBtnLogin;

    LoginContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new LoginPresenter(this, new LoginProvider());

        mUsername = view.findViewById(R.id.et_name);
        mPwd = view.findViewById(R.id.et_pwd);
        mRbtnAdmin = view.findViewById(R.id.rbtn_admin);
        mRbtnUser = view.findViewById(R.id.rbtn_user);
        mBtnLogin = view.findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(loginView -> {
            mPresenter.login(mUsername.getText().toString(), mPwd.getText().toString(), mRbtnAdmin.isChecked());
        });
    }

    @Override
    public void navigateToAdminPage() {
        ((MainActivity)getActivity()).setAdmin(true);
        getNavigator().navigateToAdminPage();
    }

    @Override
    public void navigateToUserPage() {
        getNavigator().navigateToUserPage();
    }

    @Override
    public void showUsernameEmptyAlert() {
        Toast.makeText(getContext(),R.string.username_empty_alert,Toast.LENGTH_LONG).show();
}

    @Override
    public void showPwdEmptyAlbert() {
        Toast.makeText(getContext(),R.string.pwd_empty_alert,Toast.LENGTH_LONG).show();
    }

    @Override
    String getTitle() {
        return "登录";
    }
}
