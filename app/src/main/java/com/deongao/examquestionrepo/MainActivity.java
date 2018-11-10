
package com.deongao.examquestionrepo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deongao.examquestionrepo.fragment.AdminFragment;
import com.deongao.examquestionrepo.fragment.BaseFragment;
import com.deongao.examquestionrepo.fragment.ExamInfoFragment;
import com.deongao.examquestionrepo.fragment.ExamListFragment;
import com.deongao.examquestionrepo.fragment.LoginFragment;
import com.deongao.examquestionrepo.fragment.QuestionInfoFragment;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.model.Exams;
import com.deongao.examquestionrepo.navigator.MainActivityNavigator;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityNavigator {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fl_container, new LoginFragment());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("onCreateOptionsMenu---------------");
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.create_exam) {
            showExamCreationDialog();
            return true;
        }else if(id == R.id.exam_list){
            navigateToQuestionListPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void navigateToAdminPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, new AdminFragment());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void navigateToUserPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, new ExamListFragment());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void navigateToSingleQuestionCreationPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, QuestionInfoFragment.getSingleChoiceFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void navigateToMultiQuestionsCreationPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, QuestionInfoFragment.getMultiChoicesFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void navigateToJudgmentQuestionsCreationPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, QuestionInfoFragment.getJudgmentFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void navigateToQuestionInfoPage(ExamQuestion examQuestion) {
        setSelectedQuestion(examQuestion);
        if(examQuestion.getType() == QuestionInfoProcessor.SINGLE){
            navigateToSingleQuestionCreationPage();
        }else if(examQuestion.getType() == QuestionInfoProcessor.MULTIPLE){
            navigateToMultiQuestionsCreationPage();
        }else {
            navigateToJudgmentQuestionsCreationPage();
        }
    }

    @Override
    public void navigateToQuestionListPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, new ExamListFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void navigateToExamInfoPage(Long id, String ids) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_container, ExamInfoFragment.getInstance(id, ids));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().executePendingTransactions();
        ((BaseFragment)getSupportFragmentManager().getFragments().get(0)).setTitle();
    }

    public void setTitle(String title){
        mToolbar.setTitle(title);
    }


    private ExamQuestion mSelectedQuestion;
    public ExamQuestion getSelectedQuestion() {
        return mSelectedQuestion;
    }

    public void setSelectedQuestion(ExamQuestion mSelectedQuestion) {
        this.mSelectedQuestion = mSelectedQuestion;
    }

    private long mExitTime = 0;

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }else{
            getSupportFragmentManager().popBackStackImmediate();
            getSupportFragmentManager().executePendingTransactions();
            ((BaseFragment)getSupportFragmentManager().getFragments().get(0)).setTitle();
        }

    }


    private void showExamCreationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("请选择试题数目");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_create_exam, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText etSingle = view.findViewById(R.id.et_single);
        final EditText etMulti = view.findViewById(R.id.et_multi);
        final EditText etJudgment = view.findViewById(R.id.et_judgment);
        final EditText etTitle = view.findViewById(R.id.et_title);

        builder.setPositiveButton("确定", (dialog, which) -> {
            String title = etTitle.getText().toString().trim();

            String strSingle = etSingle.getText().toString().trim();
            String strMulti = etMulti.getText().toString().trim();
            String strJudgment = etJudgment.getText().toString().trim();

            if(TextUtils.isEmpty(title) ||TextUtils.isEmpty(strMulti) || TextUtils.isEmpty(strSingle) || TextUtils.isEmpty(strJudgment)){
                Toast.makeText(getApplicationContext(), "上述输入不能为空", Toast.LENGTH_SHORT).show();
            }else {

                List<ExamQuestion> listSingle = EntityManager.getInstance().getSingleChoice(Integer.valueOf(strSingle));
                List<ExamQuestion> listMulti = EntityManager.getInstance().getSMultiChoices(Integer.valueOf(strMulti));
                List<ExamQuestion> listJudgment = EntityManager.getInstance().getJudgment(Integer.valueOf(strMulti));

                System.out.println("listSingle--------------"+listSingle.size());
                System.out.println("listMulti--------------"+listMulti.size());

                StringBuilder stringBuilder = new StringBuilder();

                for (ExamQuestion examQuestion : listJudgment) {
                    stringBuilder.append(examQuestion.getId());
                    stringBuilder.append(",");
                }

                for (ExamQuestion examQuestion : listSingle) {
                    stringBuilder.append(examQuestion.getId());
                    stringBuilder.append(",");
                }

                for (ExamQuestion examQuestion : listMulti) {
                    stringBuilder.append(examQuestion.getId());
                    stringBuilder.append(",");
                }



                String ids = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

                Exams exams = new Exams();
                exams.setIds(ids);
                exams.setScore(-1);
                exams.setTitle(title);

                EntityManager.getInstance().createExam(exams);

                dialog.dismiss();
            }

        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder.show();
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    private boolean isAdmin;



}
