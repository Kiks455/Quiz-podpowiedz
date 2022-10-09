package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private Question[] questions=new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false),
    };
    private int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questionTextView=findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=(currentIndex+1)%questions.length;
                setNewQuestion();
            }
        });

        setNewQuestion();
    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer=questions[currentIndex].getTrueAnswer();
        int resultMessage;

        if(userAnswer==correctAnswer)
        {
            resultMessage=R.string.correct_answer;
        }
        else
        {
            resultMessage=R.string.wrong_answer;
        }

        Toast.makeText(this,resultMessage, Toast.LENGTH_SHORT).show();
    }
    private void setNewQuestion()
    {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }
}