package com.marti.dev.egzaminator.CustomViews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.marti.dev.egzaminator.Fragments.QuestionFragment;


public class PageViewerAdapter extends FragmentStatePagerAdapter {

    private int mNumPages;

    public PageViewerAdapter(FragmentManager fm) {
        super(fm);
        mNumPages = 0;
    }

    public void setPagesCount(int pages) {mNumPages = pages;}

    @Override
    public Fragment getItem(int position) {
        return new QuestionFragment();
    }

    @Override
    public int getCount() {
        return mNumPages;
    }
}
