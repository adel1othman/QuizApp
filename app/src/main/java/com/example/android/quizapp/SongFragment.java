package com.example.android.quizapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class SongFragment extends Fragment {

    public static EditText songName;
    public static EditText singerName;
    public static MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private SeekBar seekbar;
    double currentTime = 0;
    private Handler myHandler = new Handler();
    private TextView timeTxt;
    private String sngNm;
    private String sngrNm;


    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
            seekbar.setProgress(0);
            timeTxt.setText(R.string.startTime);
        }
    };

    public SongFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.quiz1, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        songName = (EditText)rootView.findViewById(R.id.editText);
        singerName = (EditText)rootView.findViewById(R.id.editText2);
        Button btnPlay = (Button)rootView.findViewById(R.id.btnPlay);
        Button btnStop = (Button)rootView.findViewById(R.id.btnStop);
        seekbar = (SeekBar)rootView.findViewById(R.id.skBar);
        timeTxt = (TextView)rootView.findViewById(R.id.txtViewTime);
        timeTxt.setText(R.string.startTime);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.quiz_song);
                    mMediaPlayer.start();

                    /*int currVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    int maxVolume = 50;
                    float log1=(float)(Math.log(maxVolume-currVolume)/Math.log(maxVolume));
                    mMediaPlayer.setVolume(1-log1, 1-log1);*/

                    currentTime = mMediaPlayer.getCurrentPosition();

                    timeTxt.setText(String.format("%dm:%ds", TimeUnit.MILLISECONDS.toMinutes((long) currentTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) currentTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) currentTime))));

                    seekbar.setMax(mMediaPlayer.getDuration());
                    seekbar.setProgress(mMediaPlayer.getCurrentPosition());
                    myHandler.postDelayed(UpdateSongTime,100);

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTxt.setText(R.string.startTime);
                seekbar.setProgress(0);
                releaseMediaPlayer();
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mMediaPlayer == null){
                    seekBar.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mMediaPlayer != null){
                    int newPos = seekBar.getProgress();
                    seekbar.setMax(mMediaPlayer.getDuration());
                    mMediaPlayer.seekTo(newPos);
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, newPos);
                }
            }
        });

        songName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                songName.setFocusable(true);
                songName.setFocusableInTouchMode(true);
                sngNm = songName.getText().toString();
                sngrNm = singerName.getText().toString();
                if (sngrNm.equals("")){
                    singerName.setText(R.string.artist);
                }
                if (sngNm.equals("Song name")){
                    songName.setText("");
                }
                return false;
            }
        });

        songName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    sngNm = songName.getText().toString();
                    if (sngNm.equals("")){
                        songName.setText(R.string.songName);
                    }
                }
            }
        });

        singerName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                singerName.setFocusable(true);
                singerName.setFocusableInTouchMode(true);
                sngrNm = singerName.getText().toString();
                sngNm = songName.getText().toString();
                if (sngNm.equals("")){
                    songName.setText(R.string.songName);
                }
                if (sngrNm.equals("Artist")){
                    singerName.setText("");
                }
                return false;
            }
        });

        singerName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    sngrNm = singerName.getText().toString();
                    if (sngrNm.equals("")){
                        singerName.setText(R.string.artist);
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (mMediaPlayer != null){
                currentTime = mMediaPlayer.getCurrentPosition();
                timeTxt.setText(String.format("%dm:%ds", TimeUnit.MILLISECONDS.toMinutes((long) currentTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) currentTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) currentTime))));
                seekbar.setProgress((int)currentTime);
                myHandler.postDelayed(this, 100);
            }
        }
    };
}