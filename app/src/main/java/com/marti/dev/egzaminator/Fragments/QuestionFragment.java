package com.marti.dev.egzaminator.Fragments;


import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.marti.dev.egzaminator.CustomViews.AnswerListAdapter;
import com.marti.dev.egzaminator.CustomViews.FontTextView;
import com.marti.dev.egzaminator.R;
import com.marti.dev.egzaminator.core.TestModel;


import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class QuestionFragment extends Fragment implements AnswerListAdapter.Listener {

    public interface Listener{
        void getResult(int questionPosition);
        void quit();
    }

    private final int OVERLAY_DIMISS_TIME = 4;

    private Unbinder bind;
    private String mQuestion;
    private List<TestModel.Question.Answers> mAnswers;
    private int mCorrectAnswer;
    private AnswerListAdapter mAnswerListAdapter;
    private String mImagePath;
    private Listener mQuestionListener;
    private int mQuestionId;
    private boolean mOverlayShowed;


    @BindView(R.id.QuestionFragment_text_question)
    TextView mQuestionView;

    @BindView(R.id.QuestionFragment_answerList)
    RecyclerView mAnswerList;

    @BindView(R.id.Overlay_GoodBad)
    RelativeLayout mOverlay;

    @BindView(R.id.Overlay_GoodBad_text)
    FontTextView mOverlayText;

    @BindView(R.id.QuestionFragment_image)
    ImageView mImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question,container,false);
        bind = ButterKnife.bind(this,view);

        mQuestionView.setText(mQuestion);
        if(mImagePath != null) {
            final int imageSize = (int) getResources().getDimension(R.dimen.question_image_size);
            view.post(new Runnable() {
                @Override
                public void run() {
                               Glide.with(QuestionFragment.this)
                                .load(new File(mImagePath))
                                .apply(new RequestOptions().dontAnimate().diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true)
                                        .override(imageSize, imageSize))
                                .into(mImage);

                }

            });

        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        mAnswerList.setLayoutManager(layoutManager);
        mAnswerList.setItemAnimator(null);
          mAnswerListAdapter = new AnswerListAdapter(mAnswers,mAnswerList,this);
        mAnswerListAdapter.setClickable(true);
          mAnswerList.setAdapter(mAnswerListAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    public void onClick(int position) {
        checkAnswer(position);
    }

    @OnClick(R.id.Overlay_GoodBad)
    public void onClickOverlay(){
        dismissOverlay();
    }

    public void setQuestionId(int id) { mQuestionId = id;}
    public void setImagePath(String imagePath){
        mImagePath = imagePath;
    }
    public void setQuestionListener(Listener listener){mQuestionListener = listener;}
    public void setQuestion(String question){
        mQuestion = question;
    }
    public void setAnswers(int correctAnswer , List<TestModel.Question.Answers> answers){
        mCorrectAnswer = correctAnswer;
        mAnswers = answers;
    }

    private void checkAnswer(int position){
        int color=0,textColor=0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = getResources().getColor(R.color.good_overlay, null);
            textColor = getResources().getColor(R.color.colorAccent, null);
        }
        else {
            color = getResources().getColor(R.color.good_overlay);
            textColor = getResources().getColor(R.color.colorAccent);

        }

        if(mCorrectAnswer == mAnswers.get(position).id) {
            mQuestionListener.getResult(mQuestionId);
            showOverlay(true);
        }
        else {
            mAnswerListAdapter.changeItemColor(mCorrectAnswer,color,textColor);
            showOverlay(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = getResources().getColor(R.color.bad_overlay, null);
                textColor = getResources().getColor(R.color.colorAccent, null);
            }
            else {
                color = getResources().getColor(R.color.bad_overlay);
                textColor = getResources().getColor(R.color.colorAccent);

            }

        }

        mAnswerListAdapter.changeItemColor(position,color,textColor);
    }

    private void showOverlay(boolean good){

        Handler overlayDismissHandler =  new Handler();
        Runnable dismissRunnable = new Runnable() {
            @Override
            public void run() {
                if(mOverlayShowed)
                    dismissOverlay();
            }
        };

        if(good) {
            mOverlayText.setText(getString(R.string.overlay_good));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                mOverlay.setBackgroundColor(getResources().getColor(R.color.good_overlay,null));
            else
                mOverlay.setBackgroundColor(getResources().getColor(R.color.good_overlay));
        }
        else {
            mOverlayText.setText(getString(R.string.overlay_bad));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                mOverlay.setBackgroundColor(getResources().getColor(R.color.bad_overlay,null));
            else
                mOverlay.setBackgroundColor(getResources().getColor(R.color.bad_overlay));

        }
        mOverlay.setVisibility(View.VISIBLE);
        mOverlayShowed = true;
        overlayDismissHandler.postDelayed(dismissRunnable,OVERLAY_DIMISS_TIME*1000);
    }

    private void dismissOverlay(){
        mOverlay.setVisibility(View.GONE);
        mOverlayShowed = false;
        mAnswerListAdapter.setClickable(false);
        mQuestionListener.quit();
    }

}
