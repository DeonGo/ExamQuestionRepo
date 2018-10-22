
package com.deongao.examquestionrepo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.deongao.examquestionrepo.fragment.AdminFragment;
import com.deongao.examquestionrepo.fragment.LoginFragment;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.navigator.MainActivityNavigator;

public class MainActivity extends AppCompatActivity implements MainActivityNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        if (id == R.id.action_settings) {
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
    public void navigateToQuestionCreationPage() {

    }

    @Override
    public void navigateToQuestionInfoPage() {

    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStack();
    }


    private ExamQuestion mSelectedQuestion;
    public ExamQuestion getSelectedQuestion() {
        return mSelectedQuestion;
    }

    public void setSelectedQuestion(ExamQuestion mSelectedQuestion) {
        this.mSelectedQuestion = mSelectedQuestion;
    }




}
