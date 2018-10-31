package com.deongao.examquestionrepo.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.navigator.MainActivityNavigator;

public abstract class BaseFragment extends Fragment {

    MainActivityNavigator mNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivityNavigator){
            mNavigator = (MainActivityNavigator) context;
        }

        ((MainActivity)getActivity()).setTitle(getTitle());
    }

    public void setTitle(){
        ((MainActivity)getActivity()).setTitle(getTitle());
    }

    protected MainActivityNavigator getNavigator(){
        return mNavigator;
    }

    abstract String getTitle();
}
