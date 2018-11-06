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
            public void onItemLongClick(View view) {

            }
        });
        mRvList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    String getTitle() {
        return "试卷列表";
    }
}
