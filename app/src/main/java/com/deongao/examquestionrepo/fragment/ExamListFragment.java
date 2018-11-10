package com.deongao.examquestionrepo.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.adapter.ExamListAdapter;
import com.deongao.examquestionrepo.contract.ExamListContract;
import com.deongao.examquestionrepo.model.Exams;
import com.deongao.examquestionrepo.presenter.ExamListPresenter;
import com.deongao.examquestionrepo.provider.ExamListProvider;

import java.util.List;

public class ExamListFragment extends BaseFragment implements ExamListContract.View {
    ExamListContract.Presenter mPresenter;
    RecyclerView mRvList;
    ExamListAdapter mAdapter;

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

        mPresenter = new ExamListPresenter(this, new ExamListProvider());
        mPresenter.start();
    }

    @Override
    public void showList(List<Exams> list) {
        mAdapter = new ExamListAdapter(list, ((MainActivity)getActivity()).isAdmin(),new ExamListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                System.out.println("ExamListAdapter-------------------");
                int position = mRvList.getChildAdapterPosition(view);
                getNavigator().navigateToExamInfoPage(list.get(position).getId(),list.get(position).getIds());
            }

            @Override
            public void onDelClick(Exams exams) {
                showDialog(exams);
            }
        });
        mRvList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    String getTitle() {
        return "试卷列表";
    }

    private void showDialog(Exams exams){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("弹出警告框");
        builder.setMessage("确定删除吗？");
        builder.setPositiveButton("确定", (dialog, which) -> {
            EntityManager.getInstance().deleteExam(exams);
            mPresenter.start();
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
        });
        builder.show();
    }
}


