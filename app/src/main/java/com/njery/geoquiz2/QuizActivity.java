package com.njery.geoquiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private boolean btnEnabled;
    private Button mTrueBtn;
    private Button mFalseBtn;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private int score = 0;
    private int questionsAnswered = 0;

    //an array type object Question, and name mQuestionBank that holds objects
    Question australia = new Question(R.string.question_australia, false);
    Question oceans = new Question(R.string.question_oceans, true);
    Question mideast = new Question(R.string.question_mideast, true);
    Question africa = new Question(R.string.question_africa, true);
    Question americas = new Question(R.string.question_americas, true);
    Question asia = new Question(R.string.question_asia, true);
    private Question[] mQuestionBank = new Question[]{
            australia, oceans, mideast,africa, americas, asia
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueBtn = (Button) findViewById(R.id.true_button);
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //this is an anonymous inner class
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseBtn = (Button) findViewById(R.id.false_button);
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                btnEnabled = mQuestionBank[mCurrentIndex].getButtonEnabled();
                if (!btnEnabled){
                    mFalseBtn.setEnabled(false);
                    mTrueBtn.setEnabled(false);
                }
                if (btnEnabled){
                    mFalseBtn.setEnabled(true);
                    mTrueBtn.setEnabled(true);
                }
                if (mCurrentIndex == 5){
                    mNextButton.setEnabled(false);
                }
                if (mCurrentIndex > 0){
                    mPrevButton.setEnabled(true);
                }
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setEnabled(false);

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = Math.abs((mCurrentIndex - 1) % mQuestionBank.length);
                btnEnabled = mQuestionBank[mCurrentIndex].getButtonEnabled();
                if (!btnEnabled){
                    mFalseBtn.setEnabled(false);
                    mTrueBtn.setEnabled(false);
                }
                if (btnEnabled){
                    mFalseBtn.setEnabled(true);
                    mTrueBtn.setEnabled(true);
                }
                if (mCurrentIndex == 0){
                    mPrevButton.setEnabled(false);
                }
                if (mCurrentIndex != 5){
                    mNextButton.setEnabled(true);
                }
                updateQuestion();

            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                btnEnabled = mQuestionBank[mCurrentIndex].getButtonEnabled();
                if (!btnEnabled){
                    mFalseBtn.setEnabled(false);
                    mTrueBtn.setEnabled(false);
                }
                if (btnEnabled){
                    mFalseBtn.setEnabled(true);
                    mTrueBtn.setEnabled(true);
                }
                if (mCurrentIndex == 5){
                    mNextButton.setEnabled(false);
                }
                if (mCurrentIndex > 0){
                    mPrevButton.setEnabled(true);
                }
                updateQuestion();
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        mQuestionBank[mCurrentIndex].setButtonEnabled();
        questionsAnswered ++;
        score ++;
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }
        else{
            messageResId = R.string.incorrect_toast;
            score --;
        }
        if (questionsAnswered == 6){
            String messageScore = String.format("Your score is %d out of 6, restart game to play again", score);
            Toast toast = Toast.makeText(QuizActivity.this, messageScore, Toast.LENGTH_SHORT);
            toast.show();
            toast.setGravity(Gravity.TOP, 0, 300);
        }


        Toast toast = Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.TOP, 0, 300);
        mFalseBtn.setEnabled(false);
        mTrueBtn.setEnabled(false);

    }
}
