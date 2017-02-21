package com.example.android.quizapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SongFragment();
        } else if (position == 1) {
            return new RightImageFragment();
        } else if (position == 2) {
            return new SolveItFragment();
        }else if (position == 3) {
            return new UnblockMeFragment();
        }else {
            return new ImagePuzzleFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.song);
        } else if (position == 1) {
            return mContext.getString(R.string.rightImage);
        } else if (position == 2) {
            return mContext.getString(R.string.solveIt);
        }else if (position == 3) {
            return mContext.getString(R.string.unblockMe);
        }else {
            return mContext.getString(R.string.imagePuzzle);
        }
    }
}