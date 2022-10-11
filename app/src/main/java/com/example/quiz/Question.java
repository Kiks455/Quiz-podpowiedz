package com.example.quiz;

public class Question {
    public Question(int questionId, boolean trueAnswer, int prompt)
    {
        this.questionId=questionId;
        this.trueAnswer=trueAnswer;
        this.prompt=prompt;

    }
    private int questionId;
    private boolean trueAnswer;
    private int prompt;

    public boolean getTrueAnswer()
    {
        return trueAnswer;
    }
    public int getQuestionId()
    {
        return questionId;
    }
    public int getPrompt(){return prompt;}
}
