package com.example.ryan.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ryan on 6/1/2017.
 */

public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.ryan.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.example.ryan.geoquiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button showAnswer;
    private TextView mAndroidVersionTextView;
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);
        setAnswerShownResult(false);
        mAndroidVersionTextView = (TextView) findViewById(R.id.android_version);
        mAndroidVersionTextView.setText("API level:" + Build.VERSION.SDK_INT);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        showAnswer = (Button) findViewById(R.id.showAnswerButton);
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
}
