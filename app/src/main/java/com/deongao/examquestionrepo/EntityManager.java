package com.deongao.examquestionrepo;


import com.deongao.examquestionrepo.greendao.ExamQuestionDao;
import com.deongao.examquestionrepo.greendao.ExamsDao;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.model.Exams;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
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

    public List<ExamQuestion> getFullList(){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        return examQuestionDao.queryBuilder().list();
    }

    public List<ExamQuestion> getSingleChoice(int count){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        return examQuestionDao.queryBuilder()
                .limit(count)
                .where(ExamQuestionDao.Properties.Type.eq(1))
                .orderRaw("RANDOM()").list();
    }

    public List<ExamQuestion> getSMultiChoices(int count){
        System.out.println("count--------------"+count);
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        return examQuestionDao.queryBuilder()
                .where(ExamQuestionDao.Properties.Type.notEq(1))
                .orderRaw("RANDOM()")
                .limit(count)
                .list();
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

    public ExamQuestion getQuestion(Long id){
        ExamQuestionDao examQuestionDao = getExamQuestionDao();
        return examQuestionDao.queryBuilder()
                .where(ExamQuestionDao.Properties.Id.eq(id))
                .unique();
    }

    public void createExam(Exams exams){
        ExamsDao examsDao = DaoManager.getInstance().getSession().getExamsDao();
        examsDao.insert(exams);
    }

    public List<Exams> getExamList(){
        ExamsDao examsDao = DaoManager.getInstance().getSession().getExamsDao();
        return examsDao.queryBuilder().list();
    }

    public void updateExam(Exams exam){
        ExamsDao examsDao = DaoManager.getInstance().getSession().getExamsDao();
        examsDao.update(exam);
    }

    public Exams getExams(Long id){
        ExamsDao examsDao = DaoManager.getInstance().getSession().getExamsDao();
        return examsDao.queryBuilder()
                .where(ExamsDao.Properties.Id.eq(id))
                .unique();
    }

    public List<ExamQuestion> getExamInfoList(String ids){
        List<ExamQuestion> examQuestions = new ArrayList<>();
        String [] s = ids.split(",");
        for(String id: s){
            examQuestions.add(getQuestion(Long.valueOf(id)));
        }
//        ExamsDao examsDao = DaoManager.getInstance().getSession().getExamsDao();
        return examQuestions;
    }

}
