package com.marti.dev.egzaminator.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.marti.dev.egzaminator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Score extends AppCompatActivity {

    @BindView(R.id.Score_text_scoreNumber)
    TextView mScoreText;

    private int[] mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        mResults = getIntent().getIntArrayExtra("Scores");

        int goodAnswers = 0;
        int totalQuestions = 0;
        if(mResults != null){
            totalQuestions = mResults.length;
            for(int i = 0 ; i < totalQuestions;i++)
                goodAnswers += mResults[i];
        }

        mScoreText.setText(goodAnswers+"/"+totalQuestions);
    }


}
