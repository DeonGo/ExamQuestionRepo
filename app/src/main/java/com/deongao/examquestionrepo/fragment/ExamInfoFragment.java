package com.deongao.examquestionrepo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.adapter.ExamInfoAdapter;
import com.deongao.examquestionrepo.contract.ExamInfoContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.presenter.ExamInfoPresenter;
import com.deongao.examquestionrepo.provider.ExamInfoProvider;

import java.util.List;

public class ExamInfoFragment extends BaseFragment implements ExamInfoContract.View {
    ExamInfoContract.Presenter mPresenter;
    RecyclerView mRvList;
    ExamInfoAdapter mAdapter;
    private static final String ID = "id";

    public static ExamInfoFragment getInstance(String ids) {
        Bundle bundle = new Bundle();
        bundle.putString(ID, ids);
        ExamInfoFragment examInfoFragment = new ExamInfoFragment();
        examInfoFragment.setArguments(bundle);
        return examInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.exam_list_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvList = view.findViewById(R.id.recyclerView);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvList.setItemAnimator(new DefaultItemAnimator());
        mRvList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mPresenter = new ExamInfoPresenter(this, new ExamInfoProvider());
        mPresenter.start(getArguments().getString(ID));
    }

    @Override
    public void showList(List<ExamQuestion> list) {
        System.out.println("list--------------"+list.size());
        for(ExamQuestion examQuestion:list){
            System.out.println("examQuestion--------------"+examQuestion.getTitle());
        }
        mAdapter = new ExamInfoAdapter(list);
        mRvList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    String getTitle() {
        return "试卷";
    }
}
