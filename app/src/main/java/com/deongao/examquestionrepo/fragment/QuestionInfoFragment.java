package com.deongao.examquestionrepo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.contract.QuestionInfoContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.presenter.QuestionInfoPresenter;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;
import com.deongao.examquestionrepo.provider.QuestionInfoProvider;

public class QuestionInfoFragment extends BaseFragment implements QuestionInfoProcessor.OnAnswerCallback, QuestionInfoContract.View {

    private static final String ARG_QUESTION_TYPE = "type";
    QuestionInfoProcessor mProcessor;
    RadioGroup mRadioGroup, mJudgmentGroup;
    QuestionInfoContract.Presenter mPresenter;

    EditText mEtTitle, mEtA, mEtB, mEtC, mEtD;
    RadioButton mRbA,mRbB,mRbC,mRbD;
    Button mBtnSubmit;


    public static QuestionInfoFragment getSingleChoiceFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_QUESTION_TYPE, 1);
        QuestionInfoFragment questionInfoFragment = new QuestionInfoFragment();
        questionInfoFragment.setArguments(bundle);
        return questionInfoFragment;
    }

    public static QuestionInfoFragment getMultiChoicesFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_QUESTION_TYPE, 2);
        QuestionInfoFragment questionInfoFragment = new QuestionInfoFragment();
        questionInfoFragment.setArguments(bundle);
        return questionInfoFragment;
    }

    public static QuestionInfoFragment getJudgmentFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_QUESTION_TYPE, 3);
        QuestionInfoFragment questionInfoFragment = new QuestionInfoFragment();
        questionInfoFragment.setArguments(bundle);
        return questionInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("onCreateView-----------------");
        int type = getArguments().getInt(ARG_QUESTION_TYPE);
        if (type == QuestionInfoProcessor.SINGLE) {
            mProcessor = new QuestionInfoProcessor.SingleChoiceProcessor(this);
        } else if(type == QuestionInfoProcessor.MULTIPLE){
            mProcessor = new QuestionInfoProcessor.MultiChoiceProcessor(this);
        }else{
            mProcessor = new QuestionInfoProcessor.JudgmentProcessor(this);
        }
        mPresenter = new QuestionInfoPresenter(this, new QuestionInfoProvider());
        return inflater.inflate(R.layout.question_info_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println("onViewCreated-----------------");

        super.onViewCreated(view, savedInstanceState);

        mRadioGroup = view.findViewById(R.id.rgroup_single);
        mRadioGroup.setVisibility(mProcessor.isRadioGroupVisible() ? View.VISIBLE : View.GONE);

        mJudgmentGroup = view.findViewById(R.id.rgroup_judgment);
        mJudgmentGroup.setVisibility(mProcessor.isJudgmentGroupVisible() ? View.VISIBLE : View.GONE);

        view.findViewById(R.id.ll_multi).setVisibility(mProcessor.isCheckboxGroupVisible() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.ll_answer).setVisibility(mProcessor.isAnswerViewVisible() ? View.VISIBLE : View.GONE);

        Button btnDel = view.findViewById(R.id.btn_del);
        btnDel.setVisibility(((MainActivity)getActivity()).getSelectedQuestion()!=null?View.VISIBLE : View.GONE);
        btnDel.setOnClickListener(view12 -> mPresenter.delete(((MainActivity)getActivity()).getSelectedQuestion()));

        Button button = view.findViewById(R.id.btn_submit);
        button.setOnClickListener(view1 -> {
                mPresenter.submit(mEtTitle.getText().toString(), mEtA.getText().toString(), mEtB.getText().toString()
                        , mEtC.getText().toString(), mEtD.getText().toString(), mProcessor.getAnswer(),mProcessor.getType(),
                        ((MainActivity)getActivity()).getSelectedQuestion()!=null?((MainActivity)getActivity()).getSelectedQuestion().getId():-1);
        });

        mEtTitle = view.findViewById(R.id.et_title);
        mEtA = view.findViewById(R.id.et_A);
        mEtB = view.findViewById(R.id.et_B);
        mEtC = view.findViewById(R.id.et_C);
        mEtD = view.findViewById(R.id.et_D);

        mPresenter.start(((MainActivity)getActivity()).getSelectedQuestion());


    }

    @Override
    public String getSingleAnswer() {
        switch (mRadioGroup.getCheckedRadioButtonId()) {
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

    @Override
    public String getMultiAnswer() {
        CheckBox cbA = getView().findViewById(R.id.cb_a);
        CheckBox cbB = getView().findViewById(R.id.cb_b);
        CheckBox cbC = getView().findViewById(R.id.cb_c);
        CheckBox cbD = getView().findViewById(R.id.cb_d);

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

    @Override
    public String getJudgmentAnswer() {
        System.out.println("getJudgmentAnswer-----------------");
        switch (mJudgmentGroup.getCheckedRadioButtonId()) {
            case R.id.rbtn_right:
                return "A";
            case R.id.rbtn_wrong:
                return "B";
            default:
                return "A";
        }
    }


    @Override
    public void showQuestion(ExamQuestion examQuestion) {
        mEtTitle.setText(examQuestion.getTitle());
        mEtA.setText(examQuestion.getAnswerA());
        mEtB.setText(examQuestion.getAnswerB());
        mEtC.setText(examQuestion.getAnswerC());
        mEtD.setText(examQuestion.getAnswerD());
        if(examQuestion.getType() == QuestionInfoProcessor.SINGLE){
            switch (examQuestion.getRealAnswer()){
                case "A":
                    ((RadioButton)getView().findViewById(R.id.rbtn_a)).setChecked(true);
                    break;
                case "B":
                    ((RadioButton)getView().findViewById(R.id.rbtn_b)).setChecked(true);
                    break;
                case "C":
                    ((RadioButton)getView().findViewById(R.id.rbtn_c)).setChecked(true);
                    break;
                case "D":
                    ((RadioButton)getView().findViewById(R.id.rbtn_d)).setChecked(true);
                    break;

            }
        }else if(examQuestion.getType() == QuestionInfoProcessor.MULTIPLE){
            String [] s = examQuestion.getRealAnswer().split(",");
            for(String s1: s){
                switch (s1){
                    case "A":
                        ((CheckBox)getView().findViewById(R.id.cb_a)).setChecked(true);
                        break;
                    case "B":
                        ((CheckBox)getView().findViewById(R.id.cb_b)).setChecked(true);
                        break;
                    case "C":
                        ((CheckBox)getView().findViewById(R.id.cb_c)).setChecked(true);
                        break;
                    case "D":
                        ((CheckBox)getView().findViewById(R.id.cb_d)).setChecked(true);
                        break;

                }
            }
        }else {
            switch (examQuestion.getRealAnswer()){
                case "A":
                    ((RadioButton)getView().findViewById(R.id.rbtn_right)).setChecked(true);
                    break;
                case "B":
                    ((RadioButton)getView().findViewById(R.id.rbtn_wrong)).setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void back() {
        Log.d("DEON","back--------------");
        ((MainActivity)getActivity()).setSelectedQuestion(null);

        getNavigator().back();

    }

    @Override
    public void onDetach() {
        System.out.println("onDetach-----------------");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        ((MainActivity)getActivity()).setSelectedQuestion(null);
        System.out.println("onDestroyView-----------------");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroyView-----------------");
        super.onDestroy();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return super.onOptionsItemSelected(item);
        }

    @Override
    String getTitle() {
        return "题目详细";
    }
}
