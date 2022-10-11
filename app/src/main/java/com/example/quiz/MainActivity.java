package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_CURRENT_INDEX="currentIndex";
    public static final String KEY_EXTRA_PROMPT="com.example.quiz.prompt";
    private static final int REQUEST_CODE_PROMPT=0;
    private boolean promptShown;
    private Button promptButton;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private Question[] questions=new Question[]{
            new Question(R.string.question1, true, R.string.prompt1),
            new Question(R.string.question2, false, R.string.prompt2),
            new Question(R.string.question3, true, R.string.prompt3),
            new Question(R.string.question4, true, R.string.prompt4),
            new Question(R.string.question5, false, R.string.prompt5),
    };
    private int currentIndex=0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_OK)
        {
            return;
        }

        if(requestCode!=REQUEST_CODE_PROMPT)
        {
            return;
        }

        if(data==null)
        {
            return;
        }

        promptShown=data.getBooleanExtra(PromptActivity.KEY_EXTRA_PROMPT_SHOWN, false);

        if(promptShown==true)
        {
            Toast.makeText(this,R.string.promptWasShown, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "OnCreate method was fired");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            currentIndex=savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        promptButton=findViewById(R.id.promptButton);
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questionTextView=findViewById(R.id.question_text_view);

        promptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, PromptActivity.class);
                intent.putExtra(KEY_EXTRA_PROMPT, questions[currentIndex].getPrompt());
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        });

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
                promptShown=false;
                currentIndex=(currentIndex+1)%questions.length;
                setNewQuestion();
            }
        });

        setNewQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "OnStart method fired!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "OnPause method fired!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "OnDestroy method fired!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "OnStop method fired!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "OnResume method fired!");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity","OnSaveInstanceState method fired!");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);
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