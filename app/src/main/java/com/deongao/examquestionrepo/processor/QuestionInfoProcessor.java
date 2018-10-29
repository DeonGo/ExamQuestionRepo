package com.deongao.examquestionrepo.processor;

public abstract class QuestionInfoProcessor {

    private OnAnswerCallback mOnAnswerCallback;
    public static int SINGLE = 1;
    public static int MULTIPLE = 2;

    public QuestionInfoProcessor(OnAnswerCallback onAnswerCallback) {
        mOnAnswerCallback = onAnswerCallback;
    }

    public abstract boolean isRadioGroupVisible();
    public abstract boolean isCheckboxGroupVisible();
    public abstract String getAnswer();
    public abstract int getType();

    public interface OnAnswerCallback{
        String getSingleAnswer();
        String getMultiAnswer();
    }

    protected OnAnswerCallback getOnAnswerCallback() {
        return mOnAnswerCallback;
    }


    public static class SingleChoiceProcessor extends QuestionInfoProcessor{

        public SingleChoiceProcessor(OnAnswerCallback onAnswerCallback) {
            super(onAnswerCallback);
        }

        @Override
        public boolean isRadioGroupVisible() {
            return true;
        }

        @Override
        public boolean isCheckboxGroupVisible() {
            return false;
        }

        @Override
        public String getAnswer() {
            return getOnAnswerCallback().getSingleAnswer();
        }

        @Override
        public int getType() {
            return 1;
        }
    }

    public static class MultiChoiceProcessor extends QuestionInfoProcessor{

        public MultiChoiceProcessor(OnAnswerCallback onAnswerCallback) {
            super(onAnswerCallback);
        }

        @Override
        public boolean isRadioGroupVisible() {
            return false;
        }

        @Override
        public boolean isCheckboxGroupVisible() {
            return true;
        }

        @Override
        public String getAnswer() {
            return getOnAnswerCallback().getMultiAnswer();
        }

        @Override
        public int getType() {
            return 2;
        }
    }

}
