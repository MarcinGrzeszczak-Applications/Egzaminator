package com.marti.dev.egzaminator.CustomViews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.marti.dev.egzaminator.Fragments.QuestionFragment;
import com.marti.dev.egzaminator.core.TestModel;

public class PageViewerAdapter extends FragmentStatePagerAdapter implements QuestionFragment.Listener{

    private int mNumPages;
    private TestModel mTestModel;
    private String mImagePath;
    private int mAcctualPostion;
    private QuestionFragment.Listener mQuestionListener;

    public PageViewerAdapter(FragmentManager fm) {
        super(fm);
        mNumPages = 0;
        mAcctualPostion = 0;
    }

    public void setTestModel(TestModel testModel){
        mTestModel = testModel;
        mNumPages = mTestModel.questions.size();}

    public void setQuestionListener(QuestionFragment.Listener listener){
        mQuestionListener = listener;
    }

    public void setImagePath(String imagePath){
        mImagePath = imagePath;
    }


    @Override
    public Fragment getItem(int position) {
        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setQuestionListener(this);
        questionFragment.setQuestionId(mTestModel.questions.get(position).id);
        questionFragment.setImagePath(mImagePath+mTestModel.questions.get(position).imagePath);
        questionFragment.setQuestion(mTestModel.questions.get(position).questionText);
        questionFragment.setAnswers(mTestModel.questions.get(position).correctAnswer,mTestModel.questions.get(position).answers);
        return questionFragment;
    }

    @Override
    public int getCount() {
        return mNumPages;
    }

    @Override
    public void getResult(int questionPosition) {
        mQuestionListener.getResult(questionPosition);
    }

    @Override
    public void quit() {
        mAcctualPostion++;
        if(mAcctualPostion >= mNumPages)
            mQuestionListener.quit();
    }
}
