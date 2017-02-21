package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.util.ArrayList;

import static com.example.android.quizapp.CityAdapter.positionArray;
import static com.example.android.quizapp.SolveItFragment.rdoBtn1;
import static com.example.android.quizapp.SolveItFragment.rdoBtn2;
import static com.example.android.quizapp.SolveItFragment.rdoBtn3;
import static com.example.android.quizapp.SolveItFragment.rdoBtn4;
import static com.example.android.quizapp.SongFragment.mMediaPlayer;
import static com.example.android.quizapp.SongFragment.singerName;
import static com.example.android.quizapp.SongFragment.songName;

public class MainActivity extends AppCompatActivity {

    double finalResult;
    NonSwipeableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (NonSwipeableViewPager) findViewById(R.id.viewpager);

        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(limit);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3){
                    UnblockMeFragment.actUnlkMe.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else if (tab.getPosition() == 4){
                    ImagePuzzleFragment.actImgPzl.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else {
                    ImagePuzzleFragment.actImgPzl.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void submitClick(View view){
        Intent intent = new Intent(this, Score.class);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        ArrayList chkBoxes = positionArray;
        double result = 0;
        String sngNm = songName.getText().toString().toLowerCase();
        String sngrNm = singerName.getText().toString().toLowerCase();
        double chkBoxResult = 0;
        if (sngNm.equals("song name") || sngNm.equals("") || sngrNm.equals("artist") || sngrNm.equals("") || !chkBoxes.contains(true) ||
                (!rdoBtn1.isChecked() && !rdoBtn2.isChecked() && !rdoBtn3.isChecked() && !rdoBtn4.isChecked())){
            Toast.makeText(getApplicationContext(),"You didn't answer all questions!",Toast.LENGTH_SHORT).show();
        }
        else {
            if (sngNm.equals("no money") || sngNm.equals("no  money") || sngNm.equals("nomoney")){
                result += 1;
            }
            if (sngrNm.equals("galantis")){
                result += 1;
            }
            if (chkBoxes.get(0).equals(true)){
                chkBoxResult += 1;
            }
            if (chkBoxes.get(6).equals(true)){
                chkBoxResult += 1;
            }
            if (chkBoxes.get(9).equals(true)){
                chkBoxResult += 1;
            }
            if (chkBoxes.get(10).equals(true)){
                chkBoxResult += 1;
            }
            if (chkBoxes.get(1).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(2).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(3).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(4).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(5).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(7).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(8).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (chkBoxes.get(11).equals(true)){
                if (chkBoxResult > 0){
                    chkBoxResult -= 0.5;
                }
            }
            if (rdoBtn3.isChecked()){
                result += 1;
            }
            if (OnDragTouchListener.winUnblockMe == 1){
                result += 1;
            }
            if (OnTouchImagePuzzle.winImagePuzzle == 1){
                result += 1;
            }

            if (mMediaPlayer != null){
                mMediaPlayer.stop();
            }
            finalResult = result + chkBoxResult;
            intent.putExtra("fnlRslt", finalResult);

            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(intent)
                    .startActivities();
        }
    }
}