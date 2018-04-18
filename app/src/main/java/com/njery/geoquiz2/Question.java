package com.njery.geoquiz2;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean isButtonEnabled;

    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        isButtonEnabled = true;
    }

    public void setButtonEnabled(){
        this.isButtonEnabled = false;
    }

    public boolean getButtonEnabled(){
        return isButtonEnabled;
    }
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

}
