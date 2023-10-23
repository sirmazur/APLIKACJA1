package com.example.aplikacja1;

public class Question
{
    private int questionId;
    private boolean trueAnswer;

    public Question(int questionId, boolean trueAnswer)
    {
        this.questionId = questionId;
        this.trueAnswer = trueAnswer;
    }

    //get
    public int getQuestionId() {
        return questionId;
    }
    //set
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
