package com.deongao.examquestionrepo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Exams {
    @Id(autoincrement = true)
    private Long id;
    private String title;



    private String ids;



    private int score;


    @Generated(hash = 1418557532)
    public Exams(Long id, String title, String ids, int score) {
        this.id = id;
        this.title = title;
        this.ids = ids;
        this.score = score;
    }

    @Generated(hash = 1665727768)
    public Exams() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
