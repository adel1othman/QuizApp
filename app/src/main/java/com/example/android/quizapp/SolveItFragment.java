package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class SolveItFragment extends Fragment {

    public static RadioButton rdoBtn1, rdoBtn2, rdoBtn3, rdoBtn4;

    public SolveItFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quiz2, container, false);

        rdoBtn1 = (RadioButton)rootView.findViewById(R.id.radioButton);
        rdoBtn2 = (RadioButton)rootView.findViewById(R.id.radioButton2);
        rdoBtn3 = (RadioButton)rootView.findViewById(R.id.radioButton3);
        rdoBtn4 = (RadioButton)rootView.findViewById(R.id.radioButton4);

        rdoBtn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoBtn2.setChecked(false);
                rdoBtn3.setChecked(false);
                rdoBtn4.setChecked(false);
            }
        });
        rdoBtn2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoBtn1.setChecked(false);
                rdoBtn3.setChecked(false);
                rdoBtn4.setChecked(false);
            }
        });
        rdoBtn3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoBtn1.setChecked(false);
                rdoBtn2.setChecked(false);
                rdoBtn4.setChecked(false);
            }
        });
        rdoBtn4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoBtn1.setChecked(false);
                rdoBtn2.setChecked(false);
                rdoBtn3.setChecked(false);
            }
        });

        return rootView;
    }
}