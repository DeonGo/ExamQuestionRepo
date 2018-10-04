package com.deongao.examquestionrepo;


import com.deongao.examquestionrepo.greendao.ExamQuestionDao;
import com.deongao.examquestionrepo.model.ExamQuestion;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class EntityManager {
    private static EntityManager entityManager;
//    public ExamQuestionDao examQuestionDao;


    public ExamQuestionDao getExamQuestionDao(){
//        examQuestionDao = DaoManager.getInstance().getSession().getExamQuestionDao();
//        List<ExamQuestion> list= examQuestionDao.queryBuilder().limit(5).orderRaw("RANDOM()").list();
        return DaoManager.getInstance().getSession().getExamQuestionDao();
    }

    /**
     * 创建单例
     *
     * @return
     */
    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    public List<ExamQuestion> getSingleChoice(int count){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        List<ExamQuestion> list= examQuestionDao.queryBuilder()
                .limit(count)
                .where(ExamQuestionDao.Properties.Type.eq(1))
                .orderRaw("RANDOM()").list();
        return list;
    }

    public List<ExamQuestion> getSMultiChoices(int count){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        List<ExamQuestion> list= examQuestionDao.queryBuilder()
                .limit(count)
                .where(ExamQuestionDao.Properties.Type.eq(2))
                .orderRaw("RANDOM()").list();
        return list;
    }

    public void addQuestion(ExamQuestion examQuestion){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        examQuestionDao.insert(examQuestion);
    }

    public void deleteQuestion(ExamQuestion examQuestion){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        examQuestionDao.deleteByKey(examQuestion.getId());
    }

    public void updateQuestion(ExamQuestion examQuestion){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        examQuestionDao.update(examQuestion);
    }

}
