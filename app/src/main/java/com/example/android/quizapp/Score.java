package com.example.android.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Score extends MainActivity {

    double fnlRslt;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private TextView txtScore;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        fnlRslt  = getIntent().getDoubleExtra("fnlRslt", finalResult);
        LinearLayout starsView = (LinearLayout)findViewById(R.id.starsView);
        ImageView smiley = (ImageView)findViewById(R.id.smiley);
        txtScore = (TextView)findViewById(R.id.scoreTxtView);

        if (fnlRslt != 9){
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.not_five);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer();
                }
            });

            if (fnlRslt < 2){
                starsView.removeViewAt(4);
                starsView.removeViewAt(3);
                starsView.removeViewAt(2);
                starsView.removeViewAt(1);
                smiley.setImageResource(R.drawable.bad);
                partColoring("Your score is: " + fnlRslt + "/9.0", 15, 22, 255, 0, 0);
            }else if (fnlRslt >= 2 && fnlRslt <= 4){
                starsView.removeViewAt(4);
                starsView.removeViewAt(3);
                starsView.removeViewAt(2);
                smiley.setImageResource(R.drawable.not_bad);
                partColoring("Your score is: " + fnlRslt + "/9.0", 15, 22, 255, 165, 0);
            }else if (fnlRslt > 4 && fnlRslt <= 6){
                starsView.removeViewAt(4);
                starsView.removeViewAt(3);
                smiley.setImageResource(R.drawable.good);
                partColoring("Your score is: " + fnlRslt + "/9.0", 15, 22, 255, 255, 0);
            }else {
                starsView.removeViewAt(4);
                smiley.setImageResource(R.drawable.very_good);
                partColoring("Your score is: " + fnlRslt + "/9.0", 15, 22, 173, 255, 47);
            }
        }else {
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.five);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer();
                }
            });
            smiley.setImageResource(R.drawable.excelent);

            partColoring("WOOHOO you got 5 on it :) " + fnlRslt + "/9.0", 26, 33, 50, 205, 50);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    private void partColoring(String a, int b, int c, int d, int e, int f){
        final SpannableStringBuilder sb = new SpannableStringBuilder(a);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(d, e, f));
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(fcs, b, c, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(bss, b, c, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        txtScore.setText(sb);
    }
}
