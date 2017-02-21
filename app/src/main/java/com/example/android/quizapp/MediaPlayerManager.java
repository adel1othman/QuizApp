package com.example.android.quizapp;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by Adel on 2/16/2017.
 */

public class MediaPlayerManager extends MediaPlayer{
    private static MediaPlayer myMediaPlayer;
    private static Activity act;

    private static AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                myMediaPlayer.pause();
                myMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                myMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    public static void startPlaying(Activity activity, int songID){
        act = activity;

        AudioManager mAudioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);

        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            myMediaPlayer = MediaPlayer.create(activity, songID);
            myMediaPlayer.start();
            myMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }

    private static MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private static void releaseMediaPlayer() {
        AudioManager mAudioManager = (AudioManager) act.getSystemService(Context.AUDIO_SERVICE);
        if (myMediaPlayer != null) {

            myMediaPlayer.release();
            myMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
