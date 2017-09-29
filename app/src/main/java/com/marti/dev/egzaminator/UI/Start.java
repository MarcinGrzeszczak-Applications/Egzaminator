package com.marti.dev.egzaminator.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marti.dev.egzaminator.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.Start_button_start)
    public void onClickStart(){
        startActivity(new Intent(Start.this,Questions.class));
    }
}
