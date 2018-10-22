package com.deongao.examquestionrepo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

    private static final String ARG_QUESTION_TYPE = "is_single_choice";
    QuestionInfoProcessor mProcessor;
    RadioGroup mRadioGroup;
    QuestionInfoContract.Presenter mPresenter;

    EditText mEtTitle, mEtA, mEtB, mEtC, mEtD;
    Button mBtnSubmit;


    public static QuestionInfoFragment getSingleChoiceFragment() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_QUESTION_TYPE, true);
        QuestionInfoFragment questionInfoFragment = new QuestionInfoFragment();
        questionInfoFragment.setArguments(bundle);
        return questionInfoFragment;
    }

    public static QuestionInfoFragment getMultiChoicesFragment() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_QUESTION_TYPE, false);
        QuestionInfoFragment questionInfoFragment = new QuestionInfoFragment();
        questionInfoFragment.setArguments(bundle);
        return questionInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments().getBoolean(ARG_QUESTION_TYPE)) {
            mProcessor = new QuestionInfoProcessor.SingleChoiceProcessor(this);
        } else {
            mProcessor = new QuestionInfoProcessor.MultiChoiceProcessor(this);
        }
        mPresenter = new QuestionInfoPresenter(this, new QuestionInfoProvider());
        return inflater.inflate(R.layout.question_info_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRadioGroup = view.findViewById(R.id.rgroup_single);
        mRadioGroup.setVisibility(mProcessor.isRadioGroupVisible() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.ll_multi).setVisibility(mProcessor.isCheckboxGroupVisible() ? View.VISIBLE : View.GONE);

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

        return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
    }


    @Override
    public void showQuestion(ExamQuestion examQuestion) {
        mEtTitle.setText(examQuestion.getTitle());
        mEtA.setText(examQuestion.getAnswerA());
        mEtB.setText(examQuestion.getAnswerB());
        mEtC.setText(examQuestion.getAnswerC());
        mEtD.setText(examQuestion.getAnswerD());
    }

    @Override
    public void back() {

    }

    @Override
    public void showError(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }
}
