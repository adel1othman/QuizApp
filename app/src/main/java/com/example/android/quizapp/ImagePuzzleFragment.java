package com.example.android.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.example.android.quizapp.OnTouchImagePuzzle.winImagePuzzle;

/**
 * Created by Adel on 2/16/2017.
 */

public class ImagePuzzleFragment extends Fragment {

    public static ImageView android1,  android2, android3, android4, android5, android6, android7, android8, android9, android10,
    android11, android12, android13, android14, android15, android16, android17, android18, android19, android20, androidFull;
    public static Activity actImgPzl;

    public ImagePuzzleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.quiz5, container, false);

        winImagePuzzle = 0;
        android1 = (ImageView) rootView.findViewById(R.id.android1);
        android2 = (ImageView) rootView.findViewById(R.id.android2);
        android3 = (ImageView) rootView.findViewById(R.id.android3);
        android4 = (ImageView) rootView.findViewById(R.id.android4);
        android5 = (ImageView) rootView.findViewById(R.id.android5);
        android6 = (ImageView) rootView.findViewById(R.id.android6);
        android7 = (ImageView) rootView.findViewById(R.id.android7);
        android8 = (ImageView) rootView.findViewById(R.id.android8);
        android9 = (ImageView) rootView.findViewById(R.id.android9);
        android10 = (ImageView) rootView.findViewById(R.id.android10);
        android11 = (ImageView) rootView.findViewById(R.id.android11);
        android12 = (ImageView) rootView.findViewById(R.id.android12);
        android13 = (ImageView) rootView.findViewById(R.id.android13);
        android14 = (ImageView) rootView.findViewById(R.id.android14);
        android15 = (ImageView) rootView.findViewById(R.id.android15);
        android16 = (ImageView) rootView.findViewById(R.id.android16);
        android17 = (ImageView) rootView.findViewById(R.id.android17);
        android18 = (ImageView) rootView.findViewById(R.id.android18);
        android19 = (ImageView) rootView.findViewById(R.id.android19);
        android20 = (ImageView) rootView.findViewById(R.id.android20);
        androidFull = (ImageView) rootView.findViewById(R.id.androidFull);

        android1.setOnTouchListener(new OnTouchImagePuzzle(android1));
        android2.setOnTouchListener(new OnTouchImagePuzzle(android2));
        android3.setOnTouchListener(new OnTouchImagePuzzle(android3));
        android4.setOnTouchListener(new OnTouchImagePuzzle(android4));
        android5.setOnTouchListener(new OnTouchImagePuzzle(android5));
        android6.setOnTouchListener(new OnTouchImagePuzzle(android6));
        android7.setOnTouchListener(new OnTouchImagePuzzle(android7));
        android8.setOnTouchListener(new OnTouchImagePuzzle(android8));
        android9.setOnTouchListener(new OnTouchImagePuzzle(android9));
        android10.setOnTouchListener(new OnTouchImagePuzzle(android10));
        android11.setOnTouchListener(new OnTouchImagePuzzle(android11));
        android12.setOnTouchListener(new OnTouchImagePuzzle(android12));
        android13.setOnTouchListener(new OnTouchImagePuzzle(android13));
        android14.setOnTouchListener(new OnTouchImagePuzzle(android14));
        android15.setOnTouchListener(new OnTouchImagePuzzle(android15));
        android16.setOnTouchListener(new OnTouchImagePuzzle(android16));
        android17.setOnTouchListener(new OnTouchImagePuzzle(android17));
        android18.setOnTouchListener(new OnTouchImagePuzzle(android18));
        android19.setOnTouchListener(new OnTouchImagePuzzle(android19));
        android20.setOnTouchListener(new OnTouchImagePuzzle(android20));

        actImgPzl = getActivity();

        return rootView;
    }
}