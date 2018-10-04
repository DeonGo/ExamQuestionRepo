package com.deongao.examquestionrepo;

public interface OnRequestCallbackListener<T> {
    void onSuccess(T t);
    void onFailure(String s);
}
