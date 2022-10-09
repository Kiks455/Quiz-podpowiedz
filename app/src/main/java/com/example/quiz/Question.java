package com.example.quiz;

public class Question {
    public Question(int questionId, boolean trueAnswer)
    {
        this.questionId=questionId;
        this.trueAnswer=trueAnswer;
    }
    private int questionId;
    private boolean trueAnswer;

    public boolean getTrueAnswer()
    {
        return trueAnswer;
    }
    public int getQuestionId()
    {
        return questionId;
    }
}
