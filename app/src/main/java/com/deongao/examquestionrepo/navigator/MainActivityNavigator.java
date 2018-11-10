package com.deongao.examquestionrepo.navigator;

import com.deongao.examquestionrepo.model.ExamQuestion;

public interface MainActivityNavigator {
    void navigateToAdminPage();
    void navigateToUserPage();
    void navigateToSingleQuestionCreationPage();
    void navigateToMultiQuestionsCreationPage();
    void navigateToJudgmentQuestionsCreationPage();
    void navigateToQuestionInfoPage(ExamQuestion examQuestion);
    void navigateToQuestionListPage();
    void navigateToExamInfoPage(Long id, String ids);
    void back();
}
