package com.example.android.quizapp;

class City {

    private int mCityName;

    private int mImageResourceId;

    City(int cityName, int imageResourceId) {
        mCityName = cityName;
        mImageResourceId = imageResourceId;
    }

    int getmCityName() {
        return mCityName;
    }

    int getmImageResourceId() {
        return mImageResourceId;
    }
}