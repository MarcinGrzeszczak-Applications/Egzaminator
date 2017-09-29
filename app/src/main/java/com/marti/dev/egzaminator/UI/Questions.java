package com.marti.dev.egzaminator.UI;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.marti.dev.egzaminator.CustomViews.PageViewerAdapter;
import com.marti.dev.egzaminator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Questions extends AppCompatActivity {


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

        PageViewerAdapter pagerViewerAdapter = new PageViewerAdapter(getSupportFragmentManager());

        pagerViewerAdapter.setPagesCount(3);

        mPagerFragmentContainer.setAdapter(pagerViewerAdapter);

    }
}
