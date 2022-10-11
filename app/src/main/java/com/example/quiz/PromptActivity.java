package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    private int prompt;
    private Button promptButton;
    private Button goBackButton;
    private TextView promptText;
    private boolean promptShown=false;
    public static final String KEY_EXTRA_PROMPT_SHOWN="com.example.quiz.promptShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        promptButton=findViewById(R.id.showPromptButton);
        goBackButton=findViewById(R.id.goBackButton);
        promptText=findViewById(R.id.promptText);
        prompt= getIntent().getIntExtra(MainActivity.KEY_EXTRA_PROMPT, -1);

        promptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptShown=true;
                promptText.setText(prompt);
            }
        });
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(KEY_EXTRA_PROMPT_SHOWN, promptShown);
                setResult(RESULT_OK, intent);
                PromptActivity.super.finish();
            }
        });
    }
}