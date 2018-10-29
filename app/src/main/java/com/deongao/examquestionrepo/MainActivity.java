
package com.deongao.examquestionrepo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.deongao.examquestionrepo.fragment.AdminFragment;
import com.deongao.examquestionrepo.fragment.LoginFragment;
import com.deongao.examquestionrepo.fragment.QuestionInfoFragment;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.navigator.MainActivityNavigator;
import com.deongao.examquestionrepo.processor.QuestionInfoProcessor;

public class MainActivity extends AppCompatActivity implements MainActivityNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.create_exam) {
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
    public void navigateToQuestionInfoPage(ExamQuestion examQuestion) {
        setSelectedQuestion(examQuestion);
        if(examQuestion.getType() == QuestionInfoProcessor.SINGLE){
            navigateToSingleQuestionCreationPage();
        }else {
            navigateToMultiQuestionsCreationPage();
        }
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().executePendingTransactions();
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
            getSupportFragmentManager().popBackStack();
        }

    }







}
