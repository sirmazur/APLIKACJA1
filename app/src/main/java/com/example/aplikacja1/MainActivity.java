package com.example.aplikacja1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private static final String QUIZ_TAG = "MainActivity";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.quiz.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;


    private int currentIndex = 0;
    private boolean answerWasShown;
    private Question[] questions = new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false)

    };

    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (answerWasShown)
        {
            resultMessageId = R.string.answer_was_shown;
        }
        else
        {
            if (userAnswer == correctAnswer)
            {
                resultMessageId = R.string.correct_answer;
            }
            else
            {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();

    }

    private void setNextQuestion()
    {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(QUIZ_TAG, "Called lifecycle method: onCreate");

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        promptButton = findViewById(R.id.prompt_button);

        if (savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentIndex = (currentIndex + 1)%questions.length;
                answerWasShown = false;
                setNextQuestion();
            }

        });

        promptButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        });
        setNextQuestion();

    }
    protected void onStart()
    {
        super.onStart();
        Log.d(QUIZ_TAG, "Called lifecycle method : onStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(QUIZ_TAG, "Called lifecycle method: onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(QUIZ_TAG, "Called lifecycle method: onPause");

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(QUIZ_TAG, "Called lifecycle method: onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(QUIZ_TAG, "Called lifecycle method: onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Called method: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_CODE_PROMPT)
        {
            if (data == null)
                return;
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER, false);
        }
    }
}