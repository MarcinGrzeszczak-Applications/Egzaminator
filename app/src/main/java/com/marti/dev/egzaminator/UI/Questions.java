package com.marti.dev.egzaminator.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.marti.dev.egzaminator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Questions extends AppCompatActivity {

    private final int FRAGMENT_CONTAINER = R.id.Questions_fragment_container;

    @BindView(R.id.Questions_toolbar)
    Toolbar mQuestionToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ButterKnife.bind(this);

        setSupportActionBar(mQuestionToolbar);

    }
}
