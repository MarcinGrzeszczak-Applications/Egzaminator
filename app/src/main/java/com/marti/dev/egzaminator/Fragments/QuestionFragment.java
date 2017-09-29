package com.marti.dev.egzaminator.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marti.dev.egzaminator.CustomViews.AnswerListAdapter;
import com.marti.dev.egzaminator.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuestionFragment extends Fragment {

    private Unbinder bind;

    @BindView(R.id.QuestionFragment_answerList)
    RecyclerView mAnswerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question,container,false);
        bind = ButterKnife.bind(this,view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        mAnswerList.setLayoutManager(layoutManager);

        List<String> test = new ArrayList<>();
        test.add("Odpowiedż 1");
        test.add("Odpowiedź 2");
        test.add("Odpowiedź 3");
        //test.add("Odpowiedź 4");

        AnswerListAdapter answerListAdapter = new AnswerListAdapter(test);

        mAnswerList.setAdapter(answerListAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
