package com.example.android.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.example.android.quizapp.OnDragTouchListener.winUnblockMe;

/**
 * Created by Adel on 2/14/2017.
 */

public class UnblockMeFragment extends Fragment {

    public static ImageView image, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;
    public static Activity actUnlkMe;
    RelativeLayout main;

    public UnblockMeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.quiz4, container, false);

        winUnblockMe = 0;

        main = (RelativeLayout)rootView.findViewById(R.id.main);
        image = (ImageView) rootView.findViewById(R.id.image);
        image1 = (ImageView) rootView.findViewById(R.id.image1);
        image2 = (ImageView) rootView.findViewById(R.id.image2);
        image3 = (ImageView) rootView.findViewById(R.id.image3);
        image4 = (ImageView) rootView.findViewById(R.id.theOne);
        image5 = (ImageView) rootView.findViewById(R.id.image5);
        image6 = (ImageView) rootView.findViewById(R.id.image6);
        image7 = (ImageView) rootView.findViewById(R.id.image7);
        image8 = (ImageView) rootView.findViewById(R.id.image8);
        image9 = (ImageView) rootView.findViewById(R.id.image9);
        image10 = (ImageView) rootView.findViewById(R.id.image10);

        image.setOnTouchListener(new OnDragTouchListener(image));
        image1.setOnTouchListener(new OnDragTouchListener(image1));
        image2.setOnTouchListener(new OnDragTouchListener(image2));
        image3.setOnTouchListener(new OnDragTouchListener(image3));
        image5.setOnTouchListener(new OnDragTouchListener(image5));
        image4.setOnTouchListener(new OnDragTouchListener(image4));
        image6.setOnTouchListener(new OnDragTouchListener(image6));
        image7.setOnTouchListener(new OnDragTouchListener(image7));
        image8.setOnTouchListener(new OnDragTouchListener(image8));
        image9.setOnTouchListener(new OnDragTouchListener(image9));
        image10.setOnTouchListener(new OnDragTouchListener(image10));

        actUnlkMe = getActivity();

        return rootView;
    }
}