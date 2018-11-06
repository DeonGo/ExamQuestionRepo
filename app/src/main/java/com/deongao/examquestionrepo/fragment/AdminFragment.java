package com.deongao.examquestionrepo.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.adapter.QuestionListAdapter;
import com.deongao.examquestionrepo.contract.AdminContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.presenter.AdminPresenter;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;
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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.admin_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvList = view.findViewById(R.id.recyclerView);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvList.setItemAnimator(new DefaultItemAnimator());
        mRvList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        btnAdd = view.findViewById(R.id.fab);
        btnAdd.setOnClickListener(view1->{
//            mPresenter.addQuestion();
            dialogChoice();
        });

        mPresenter = new AdminPresenter(this, new AdminProvider());
        mPresenter.start();
    }


    @Override
    public void navigateToSingleQuestionCreationPage() {
        getNavigator().navigateToSingleQuestionCreationPage();
    }

    @Override
    public void navigateToMultiQuestionsCreationPage() {
        getNavigator().navigateToMultiQuestionsCreationPage();
    }

    @Override
    public void navigateToQuestionDetailPage() {

    }

    @Override
    public void showList(List<ExamQuestion> list) {
        mAdapter = new QuestionListAdapter(list, new QuestionListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = mRvList.getChildAdapterPosition(view);
                getNavigator().navigateToQuestionInfoPage(list.get(position));
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        mRvList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    int mType ;

    private void dialogChoice() {

        final String items[] = {"单选", "多选"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),3);
        builder.setTitle("增加题目");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mType = which +1;
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mType == 0){
                    Toast.makeText(getContext(),R.string.question_type, Toast.LENGTH_SHORT).show();
                }else {
                    dialog.dismiss();
                    if(mType == QuestionInfoProcessor.SINGLE){
                        navigateToSingleQuestionCreationPage();
                    }else {
                        navigateToMultiQuestionsCreationPage();
                    }
                    mType = 0;
                }

            }
        });
        builder.create().show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    String getTitle() {
        return "试题库";
    }
}
