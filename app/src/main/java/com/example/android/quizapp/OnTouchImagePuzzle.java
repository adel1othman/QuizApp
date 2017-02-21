package com.example.android.quizapp;

import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.example.android.quizapp.ImagePuzzleFragment.android1;
import static com.example.android.quizapp.ImagePuzzleFragment.android2;
import static com.example.android.quizapp.ImagePuzzleFragment.android3;
import static com.example.android.quizapp.ImagePuzzleFragment.android4;
import static com.example.android.quizapp.ImagePuzzleFragment.android5;
import static com.example.android.quizapp.ImagePuzzleFragment.android6;
import static com.example.android.quizapp.ImagePuzzleFragment.android7;
import static com.example.android.quizapp.ImagePuzzleFragment.android8;
import static com.example.android.quizapp.ImagePuzzleFragment.android9;
import static com.example.android.quizapp.ImagePuzzleFragment.android10;
import static com.example.android.quizapp.ImagePuzzleFragment.android11;
import static com.example.android.quizapp.ImagePuzzleFragment.android12;
import static com.example.android.quizapp.ImagePuzzleFragment.android13;
import static com.example.android.quizapp.ImagePuzzleFragment.android14;
import static com.example.android.quizapp.ImagePuzzleFragment.android15;
import static com.example.android.quizapp.ImagePuzzleFragment.android16;
import static com.example.android.quizapp.ImagePuzzleFragment.android17;
import static com.example.android.quizapp.ImagePuzzleFragment.android18;
import static com.example.android.quizapp.ImagePuzzleFragment.android19;
import static com.example.android.quizapp.ImagePuzzleFragment.android20;
import static com.example.android.quizapp.ImagePuzzleFragment.androidFull;
import static com.example.android.quizapp.R.id.main;

/**
 * Created by Adel on 2/16/2017.
 */

public class OnTouchImagePuzzle implements View.OnTouchListener {

    public static int winImagePuzzle;
    /**
     * Callback used to indicate when the drag is finished
     */
    private interface OnDragActionListener {
        /**
         * Called when drag event is started
         *
         * @param view The view dragged
         */
        void onDragStart(View view);

        /**
         * Called when drag event is completed
         *
         * @param view The view dragged
         */
        void onDragEnd(View view);
    }

    private View mView;
    private View mParent;
    private boolean isDragging;
    private boolean isInitialized = false;

    private int width;
    private float xWhenAttached;
    private float maxLeft;
    private float maxRight;
    private float dX;

    private int height;
    private float yWhenAttached;
    private float maxTop;
    private float maxBottom;
    private float dY;

    private OnDragActionListener mOnDragActionListener;

    public OnTouchImagePuzzle(View view) {
        this(view, (View) view.getParent(), null);
    }

    public OnTouchImagePuzzle(View view, View parent) {
        this(view, parent, null);
    }

    public OnTouchImagePuzzle(View view, OnDragActionListener onDragActionListener) {
        this(view, (View) view.getParent(), onDragActionListener);
    }

    public OnTouchImagePuzzle(View view, View parent, OnDragActionListener onDragActionListener) {
        initListener(view, parent);
        setOnDragActionListener(onDragActionListener);
    }

    private void setOnDragActionListener(OnDragActionListener onDragActionListener) {
        mOnDragActionListener = onDragActionListener;
    }

    private void initListener(View view, View parent) {
        mView = view;
        mParent = parent;
        isDragging = false;
        isInitialized = false;
    }

    private void updateBounds() {
        updateViewBounds();
        updateParentBounds();
        isInitialized = true;
    }

    private void updateViewBounds() {
        width = mView.getWidth();
        xWhenAttached = mView.getX();
        dX = 0;

        height = mView.getHeight();
        yWhenAttached = mView.getY();
        dY = 0;
    }

    private void updateParentBounds() {
        maxLeft = 0;
        maxRight = maxLeft + mParent.getWidth();

        maxTop = 0;
        maxBottom = maxTop + mParent.getHeight();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDragging) {
            float[] bounds = new float[4];
            // LEFT
            bounds[0] = event.getRawX() + dX;
            if (bounds[0] < maxLeft) {
                bounds[0] = maxLeft;
            }
            // RIGHT
            bounds[2] = bounds[0] + width;
            if (bounds[2] > maxRight) {
                bounds[2] = maxRight;
                bounds[0] = bounds[2] - width;
            }
            // TOP
            bounds[1] = event.getRawY() + dY;
            if (bounds[1] < maxTop) {
                bounds[1] = maxTop;
            }
            // BOTTOM
            bounds[3] = bounds[1] + height;
            if (bounds[3] > maxBottom) {
                bounds[3] = maxBottom;
                bounds[1] = bounds[3] - height;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:

                    if (mView == android1){
                        if (android1.getX() >= androidFull.getX() - 40 && android1.getX() <= androidFull.getX() + 40 &&
                                android1.getY() >= androidFull.getY() - 40 && android1.getY() <= androidFull.getY() + 40){
                            android1.animate().x(androidFull.getX()).setDuration(0).start();
                            android1.animate().y(androidFull.getY()).setDuration(0).start();
                            android1.setEnabled(false);
                        }
                    }else if (mView == android2){
                        if (android2.getX() >= androidFull.getX() + (androidFull.getWidth()/4) - 40 && android2.getX() <= androidFull.getX() + (androidFull.getWidth()/4) + 40 &&
                                android2.getY() >= androidFull.getY() - 40 && android2.getY() <= androidFull.getY() + 40){
                            android2.animate().x(androidFull.getX() + (androidFull.getWidth()/4)).setDuration(0).start();
                            android2.animate().y(androidFull.getY()).setDuration(0).start();
                            android2.setEnabled(false);
                        }
                    }else if (mView == android3){
                        if (android3.getX() >= androidFull.getX() + (androidFull.getWidth()/2) - 40 && android3.getX() <= androidFull.getX() + (androidFull.getWidth()/2) + 40 &&
                                android3.getY() >= androidFull.getY() - 40 && android3.getY() <= androidFull.getY() + 40){
                            android3.animate().x(androidFull.getX() + (androidFull.getWidth()/2)).setDuration(0).start();
                            android3.animate().y(androidFull.getY()).setDuration(0).start();
                            android3.setEnabled(false);
                        }
                    }else if (mView == android4){
                        if (android4.getX() >= androidFull.getX() + ((androidFull.getWidth()/4) * 3) - 40 && android4.getX() <= androidFull.getX() + ((androidFull.getWidth()/4) * 3) + 40 &&
                                android4.getY() >= androidFull.getY() - 40 && android4.getY() <= androidFull.getY() + 40){
                            android4.animate().x(androidFull.getX() + ((androidFull.getWidth()/4) * 3)).setDuration(0).start();
                            android4.animate().y(androidFull.getY()).setDuration(0).start();
                            android4.setEnabled(false);
                        }
                    }else if (mView == android5){
                        if (android5.getX() >= androidFull.getX() - 40 && android5.getX() <= androidFull.getX() + 40 &&
                                android5.getY() >= androidFull.getY() + (androidFull.getHeight()/5) - 40 && android5.getY() <= androidFull.getY() + (androidFull.getHeight()/5) + 40){
                            android5.animate().x(androidFull.getX()).setDuration(0).start();
                            android5.animate().y(androidFull.getY() + (androidFull.getHeight()/5)).setDuration(0).start();
                            android5.setEnabled(false);
                        }
                    }else if (mView == android6){
                        if (android6.getX() >= androidFull.getX() + (androidFull.getWidth()/4) - 40 && android6.getX() <= androidFull.getX() + (androidFull.getWidth()/4) + 40 &&
                                android6.getY() >= androidFull.getY() + (androidFull.getHeight()/5) - 40 && android6.getY() <= androidFull.getY() + (androidFull.getHeight()/5) + 40){
                            android6.animate().x(androidFull.getX() + (androidFull.getWidth()/4)).setDuration(0).start();
                            android6.animate().y(androidFull.getY() + (androidFull.getHeight()/5)).setDuration(0).start();
                            android6.setEnabled(false);
                        }
                    }else if (mView == android7){
                        if (android7.getX() >= androidFull.getX() + (androidFull.getWidth()/2) - 40 && android7.getX() <= androidFull.getX() + (androidFull.getWidth()/2) + 40 &&
                                android7.getY() >= androidFull.getY() + (androidFull.getHeight()/5) - 40 && android7.getY() <= androidFull.getY() + (androidFull.getHeight()/5) + 40){
                            android7.animate().x(androidFull.getX() + (androidFull.getWidth()/2)).setDuration(0).start();
                            android7.animate().y(androidFull.getY() + (androidFull.getHeight()/5)).setDuration(0).start();
                            android7.setEnabled(false);
                        }
                    }else if (mView == android8){
                        if (android8.getX() >= androidFull.getX() + ((androidFull.getWidth()/4) * 3) - 40 && android8.getX() <= androidFull.getX() + ((androidFull.getWidth()/4) * 3) + 40 &&
                                android8.getY() >= androidFull.getY() + (androidFull.getHeight()/5) - 40 && android8.getY() <= androidFull.getY() + (androidFull.getHeight()/5) + 40){
                            android8.animate().x(androidFull.getX() + ((androidFull.getWidth()/4) * 3)).setDuration(0).start();
                            android8.animate().y(androidFull.getY() + (androidFull.getHeight()/5)).setDuration(0).start();
                            android8.setEnabled(false);
                        }
                    }else if (mView == android9){
                        if (android9.getX() >= androidFull.getX() - 40 && android9.getX() <= androidFull.getX() + 40 &&
                                android9.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 2) - 40 && android9.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 2) + 40){
                            android9.animate().x(androidFull.getX()).setDuration(0).start();
                            android9.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 2)).setDuration(0).start();
                            android9.setEnabled(false);
                        }
                    }else if (mView == android10){
                        if (android10.getX() >= androidFull.getX() + (androidFull.getWidth()/4) - 40 && android10.getX() <= androidFull.getX() + (androidFull.getWidth()/4) + 40 &&
                                android10.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 2) - 40 && android10.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 2) + 40){
                            android10.animate().x(androidFull.getX() + (androidFull.getWidth()/4)).setDuration(0).start();
                            android10.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 2)).setDuration(0).start();
                            android10.setEnabled(false);
                        }
                    }else if (mView == android11){
                        if (android11.getX() >= androidFull.getX() + (androidFull.getWidth()/2) - 40 && android11.getX() <= androidFull.getX() + (androidFull.getWidth()/2) + 40 &&
                                android11.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 2) - 40 && android11.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 2) + 40){
                            android11.animate().x(androidFull.getX() + (androidFull.getWidth()/2)).setDuration(0).start();
                            android11.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 2)).setDuration(0).start();
                            android11.setEnabled(false);
                        }
                    }else if (mView == android12){
                        if (android12.getX() >= androidFull.getX() + ((androidFull.getWidth()/4) * 3) - 40 && android12.getX() <= androidFull.getX() + ((androidFull.getWidth()/4) * 3) + 40 &&
                                android12.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 2) - 40 && android12.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 2) + 40){
                            android12.animate().x(androidFull.getX() + ((androidFull.getWidth()/4) * 3)).setDuration(0).start();
                            android12.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 2)).setDuration(0).start();
                            android12.setEnabled(false);
                        }
                    }else if (mView == android13){
                        if (android13.getX() >= androidFull.getX() - 40 && android13.getX() <= androidFull.getX() + 40 &&
                                android13.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 3) - 40 && android13.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 3) + 40){
                            android13.animate().x(androidFull.getX()).setDuration(0).start();
                            android13.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 3)).setDuration(0).start();
                            android13.setEnabled(false);
                        }
                    }else if (mView == android14){
                        if (android14.getX() >= androidFull.getX() + (androidFull.getWidth()/4) - 40 && android14.getX() <= androidFull.getX() + (androidFull.getWidth()/4) + 40 &&
                                android14.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 3) - 40 && android14.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 3) + 40){
                            android14.animate().x(androidFull.getX() + (androidFull.getWidth()/4)).setDuration(0).start();
                            android14.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 3)).setDuration(0).start();
                            android14.setEnabled(false);
                        }
                    }else if (mView == android15){
                        if (android15.getX() >= androidFull.getX() + (androidFull.getWidth()/2) - 40 && android15.getX() <= androidFull.getX() + (androidFull.getWidth()/2) + 40 &&
                                android15.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 3) - 40 && android15.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 3) + 40){
                            android15.animate().x(androidFull.getX() + (androidFull.getWidth()/2)).setDuration(0).start();
                            android15.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 3)).setDuration(0).start();
                            android15.setEnabled(false);
                        }
                    }else if (mView == android16){
                        if (android16.getX() >= androidFull.getX() + ((androidFull.getWidth()/4) * 3) - 40 && android16.getX() <= androidFull.getX() + ((androidFull.getWidth()/4) * 3) + 40 &&
                                android16.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 3) - 40 && android16.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 3) + 40){
                            android16.animate().x(androidFull.getX() + ((androidFull.getWidth()/4) * 3)).setDuration(0).start();
                            android16.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 3)).setDuration(0).start();
                            android16.setEnabled(false);
                        }
                    }else if (mView == android17){
                        if (android17.getX() >= androidFull.getX() - 40 && android17.getX() <= androidFull.getX() + 40 &&
                                android17.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 4) - 40 && android17.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 4) + 40){
                            android17.animate().x(androidFull.getX()).setDuration(0).start();
                            android17.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 4)).setDuration(0).start();
                            android17.setEnabled(false);
                        }
                    }else if (mView == android18){
                        if (android18.getX() >= androidFull.getX() + (androidFull.getWidth()/4) - 40 && android18.getX() <= androidFull.getX() + (androidFull.getWidth()/4) + 40 &&
                                android18.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 4) - 40 && android18.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 4) + 40){
                            android18.animate().x(androidFull.getX() + (androidFull.getWidth()/4)).setDuration(0).start();
                            android18.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 4)).setDuration(0).start();
                            android18.setEnabled(false);
                        }
                    }else if (mView == android19){
                        if (android19.getX() >= androidFull.getX() + (androidFull.getWidth()/2) - 40 && android19.getX() <= androidFull.getX() + (androidFull.getWidth()/2) + 40 &&
                                android19.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 4) - 40 && android19.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 4) + 40){
                            android19.animate().x(androidFull.getX() + (androidFull.getWidth()/2)).setDuration(0).start();
                            android19.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 4)).setDuration(0).start();
                            android19.setEnabled(false);
                        }
                    }else if (mView == android20){
                        if (android20.getX() >= androidFull.getX() + ((androidFull.getWidth()/4) * 3) - 40 && android20.getX() <= androidFull.getX() + ((androidFull.getWidth()/4) * 3) + 40 &&
                                android20.getY() >= androidFull.getY() + ((androidFull.getHeight()/5) * 4) - 40 && android20.getY() <= androidFull.getY() + ((androidFull.getHeight()/5) * 4) + 40){
                            android20.animate().x(androidFull.getX() + ((androidFull.getWidth()/4) * 3)).setDuration(0).start();
                            android20.animate().y(androidFull.getY() + ((androidFull.getHeight()/5) * 4)).setDuration(0).start();
                            android20.setEnabled(false);
                        }
                    }

                    if (!android1.isEnabled() && !android2.isEnabled() && !android3.isEnabled() && !android4.isEnabled() && !android5.isEnabled() && !android6.isEnabled()
                            && !android7.isEnabled() && !android8.isEnabled() && !android9.isEnabled() && !android10.isEnabled() && !android11.isEnabled() && !android12.isEnabled()
                            && !android13.isEnabled() && !android14.isEnabled() && !android15.isEnabled() && !android16.isEnabled() && !android17.isEnabled() && !android18.isEnabled()
                            && !android19.isEnabled() && !android20.isEnabled()){

                        if (winImagePuzzle != 1){
                            RelativeLayout main = (RelativeLayout)mParent.findViewById(R.id.mainImgPzl);

                            final MediaPlayer mediaPlayer = MediaPlayer.create(mParent.getContext(), R.raw.winner);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer.release();
                                }
                            });

                            ImageView winImage = new ImageView(main.getContext());
                            RelativeLayout.LayoutParams winParams = new RelativeLayout.LayoutParams(150, 150);
                            winImage.setImageResource(R.drawable.star);
                            winImage.setLayoutParams(winParams);
                            winImage.setX(main.getWidth()/2 - winParams.width/2);
                            winImage.setY(main.getHeight()/2 - winParams.height/2);
                            main.addView(winImage);
                            winImage.setOnTouchListener(new OnTouchImagePuzzle(winImage));
                            winImage.bringToFront();
                        }

                        winImagePuzzle = 1;
                    }

                    onDragFinish();
                    updateParentBounds();

                    final MediaPlayer mpClick = MediaPlayer.create(mParent.getContext(), R.raw.click);
                    if (winImagePuzzle != 1){
                        mpClick.start();
                    }
                    mpClick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mpClick.release();
                        }
                    });

                    break;
                case MotionEvent.ACTION_MOVE:
                    mView.animate().x(bounds[0]).setDuration(0).start();
                    mView.animate().y(bounds[1]).setDuration(0).start();
                    break;
            }
            return true;
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDragging = true;
                    if (!isInitialized) {
                        updateBounds();
                    }
                    dX = v.getX() - event.getRawX();
                    dY = v.getY() - event.getRawY();
                    if (mOnDragActionListener != null) {
                        mOnDragActionListener.onDragStart(mView);
                    }
                    v.bringToFront();
                    return true;
            }
        }
        return false;
    }

    private void onDragFinish() {
        if (mOnDragActionListener != null) {
            mOnDragActionListener.onDragEnd(mView);
        }

        dX = 0;
        dY = 0;
        isDragging = false;
    }
}