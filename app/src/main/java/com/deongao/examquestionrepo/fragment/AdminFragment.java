package com.deongao.examquestionrepo.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.adapter.QuestionListAdapter;
import com.deongao.examquestionrepo.contract.AdminContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.presenter.AdminPresenter;
import com.deongao.examquestionrepo.provider.AdminProvider;

import java.util.List;

public class AdminFragment extends BaseFragment implements AdminContract.View {

    AdminContract.Presenter mPresenter;
    RecyclerView mRvList;
    QuestionListAdapter mAdapter;
    FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.admin_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvList = view.findViewById(R.id.recyclerView);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvList.setItemAnimator(new DefaultItemAnimator());

        btnAdd = view.findViewById(R.id.fab);
        btnAdd.setOnClickListener(view1->{
            mPresenter.addQuestion();
        });

        mPresenter = new AdminPresenter(this, new AdminProvider());
        mPresenter.start();
    }

    @Override
    public void navigateToQuestionCreationPage() {

    }

    @Override
    public void navigateToQuestionDetailPage() {

    }

    @Override
    public void showList(List<ExamQuestion> list) {
        mAdapter = new QuestionListAdapter(list);
        mRvList.setAdapter(mAdapter);
    }

    private void dialogChoice() {
        final String items[] = {"男", "女", "其他"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),3);
        builder.setTitle("单选");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(), items[which],
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
