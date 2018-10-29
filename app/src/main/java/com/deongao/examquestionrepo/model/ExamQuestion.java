package com.deongao.examquestionrepo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ExamQuestion {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private int type;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private String realAnswer;

    @Generated(hash = 870610913)
    public ExamQuestion(Long id, String title, int type, String answerA,
            String answerB, String answerC, String answerD, String realAnswer) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.realAnswer = realAnswer;
    }

    @Generated(hash = 78936532)
    public ExamQuestion() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
