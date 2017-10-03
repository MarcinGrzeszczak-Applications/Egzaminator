package com.marti.dev.egzaminator.UI;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.marti.dev.egzaminator.CustomViews.PageViewerAdapter;
import com.marti.dev.egzaminator.Fragments.QuestionFragment;
import com.marti.dev.egzaminator.R;
import com.marti.dev.egzaminator.core.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Questions extends AppCompatActivity implements QuestionFragment.Listener {

    private TestModel mTestModel;
    private String mImagePath;
    private int[] results;


    @BindView(R.id.Questions_toolbar)
    Toolbar mQuestionToolbar;

    @BindView(R.id.Questions_fragment_container)
    ViewPager mPagerFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ButterKnife.bind(this);

        if(mQuestionToolbar != null) {
            setSupportActionBar(mQuestionToolbar);

            assert getSupportActionBar() !=null;
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mTestModel = getIntent().getParcelableExtra(TestModel.NAME);
        mImagePath = getIntent().getStringExtra("ImagePath");

        results = new int[mTestModel.questions.size()];

        for(int i = 0 ; i < results.length;i++)
            results[i] = 0;

        PageViewerAdapter pagerViewerAdapter = new PageViewerAdapter(getSupportFragmentManager());

        pagerViewerAdapter.setQuestionListener(this);
        pagerViewerAdapter.setTestModel(mTestModel);
        pagerViewerAdapter.setImagePath(mImagePath);

        mPagerFragmentContainer.setAdapter(pagerViewerAdapter);

    }

    @Override
    public void getResult(int questionPosition) {
        results[questionPosition] += 1;
    }

    @Override
    public void quit() {
        Intent scoreActivity = new Intent(Questions.this,Score.class);
        scoreActivity.putExtra("Scores",results);
        startActivity(scoreActivity);
    }

}
