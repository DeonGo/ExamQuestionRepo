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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.adapter.ExamInfoAdapter;
import com.deongao.examquestionrepo.contract.ExamInfoContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.model.Exams;
import com.deongao.examquestionrepo.presenter.ExamInfoPresenter;
import com.deongao.examquestionrepo.provider.ExamInfoProvider;

import java.util.List;

public class ExamInfoFragment extends BaseFragment implements ExamInfoContract.View {
    ExamInfoContract.Presenter mPresenter;
    RecyclerView mRvList;
    ExamInfoAdapter mAdapter;
    private static final String IDS = "ids";
    private static final String ID = "id";
    RecyclerView.LayoutManager mLayoutManager;

    public static ExamInfoFragment getInstance(Long id, String ids) {
        Bundle bundle = new Bundle();
        bundle.putString(IDS, ids);
        bundle.putLong(ID, id);
        ExamInfoFragment examInfoFragment = new ExamInfoFragment();
        examInfoFragment.setArguments(bundle);
        return examInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.exam_info_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvList = view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvList.setLayoutManager(mLayoutManager);
        mRvList.setItemAnimator(new DefaultItemAnimator());
        mRvList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        if(((MainActivity)getActivity()).isAdmin()){
            view.findViewById(R.id.btn_submit).setVisibility(View.GONE);
        }else {
            Button button = view.findViewById(R.id.btn_submit);
            button.setOnClickListener(view1 -> {
                int count = 0;
                for(int i=0;i<mAdapter.getItemCount();i++){
                    View item = mLayoutManager.findViewByPosition(i);
                    String answer;
                    ExamQuestion examQuestion = mAdapter.getData().get(i);
                    if(examQuestion.getType() == 1){
                        answer = getSingleAnswer(item);
                    }else {
                        answer = getMultiAnswer(item);
                    }

                    if(examQuestion.getRealAnswer().trim().equalsIgnoreCase(answer)){
                        count++;
                    }
                }

                int score = count*100 / mAdapter.getItemCount();
                Exams exams = EntityManager.getInstance().getExams(getArguments().getLong(ID));
                exams.setScore(score);
                EntityManager.getInstance().updateExam(exams);
                getNavigator().back();
            });
        }

        mPresenter = new ExamInfoPresenter(this, new ExamInfoProvider());
        mPresenter.start(getArguments().getString(IDS));
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

    public String getSingleAnswer(View view) {
        switch (((RadioGroup)view.findViewById(R.id.rgroup_single)).getCheckedRadioButtonId()) {
            case R.id.rbtn_a:
                return "A";
            case R.id.rbtn_b:
                return "B";
            case R.id.rbtn_c:
                return "C";
            case R.id.rbtn_d:
                return "D";
            default:
                return "A";
        }
    }

    public String getMultiAnswer(View view) {
        CheckBox cbA = view.findViewById(R.id.cb_a);
        CheckBox cbB = view.findViewById(R.id.cb_b);
        CheckBox cbC = view.findViewById(R.id.cb_c);
        CheckBox cbD = view.findViewById(R.id.cb_d);

        StringBuilder stringBuilder = new StringBuilder();
        if (cbA.isChecked()) stringBuilder.append("A").append(",");
        if (cbB.isChecked()) stringBuilder.append("B").append(",");
        if (cbC.isChecked()) stringBuilder.append("C").append(",");
        if (cbD.isChecked()) stringBuilder.append("D").append(",");

        String result=null;
        try {
            result = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        }catch (Exception e){

        }

        return result;
    }

}
