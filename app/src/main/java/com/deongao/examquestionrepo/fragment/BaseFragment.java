package com.deongao.examquestionrepo.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.navigator.MainActivityNavigator;

public class BaseFragment extends Fragment {

    MainActivityNavigator mNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivityNavigator){
            mNavigator = (MainActivityNavigator) context;
        }
    }

    protected MainActivityNavigator getNavigator(){
        return mNavigator;
    }
}
