package com.example.android.quizapp;

import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.example.android.quizapp.UnblockMeFragment.image;
import static com.example.android.quizapp.UnblockMeFragment.image1;
import static com.example.android.quizapp.UnblockMeFragment.image2;
import static com.example.android.quizapp.UnblockMeFragment.image3;
import static com.example.android.quizapp.UnblockMeFragment.image4;
import static com.example.android.quizapp.UnblockMeFragment.image5;
import static com.example.android.quizapp.UnblockMeFragment.image6;
import static com.example.android.quizapp.UnblockMeFragment.image7;
import static com.example.android.quizapp.UnblockMeFragment.image8;
import static com.example.android.quizapp.UnblockMeFragment.image9;
import static com.example.android.quizapp.UnblockMeFragment.image10;

/**
 * Created by Adel on 2/14/2017.
 */

public class OnDragTouchListener implements View.OnTouchListener {

    public static int winUnblockMe;
    /**
     * Callback used to indicate when the drag is finished
     */
    public interface OnDragActionListener {
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

    public OnDragTouchListener(View view) {
        this(view, (View) view.getParent(), null);
    }

    public OnDragTouchListener(View view, View parent) {
        this(view, parent, null);
    }

    public OnDragTouchListener(View view, OnDragActionListener onDragActionListener) {
        this(view, (View) view.getParent(), onDragActionListener);
    }

    public OnDragTouchListener(View view, View parent, OnDragActionListener onDragActionListener) {
        initListener(view, parent);
        setOnDragActionListener(onDragActionListener);
    }

    public void setOnDragActionListener(OnDragActionListener onDragActionListener) {
        mOnDragActionListener = onDragActionListener;
    }

    public void initListener(View view, View parent) {
        mView = view;
        mParent = parent;
        isDragging = false;
        isInitialized = false;
    }

    public void updateBounds() {
        updateViewBounds();
        updateParentBounds();
        isInitialized = true;
    }

    public void updateViewBounds() {
        width = mView.getWidth();
        xWhenAttached = mView.getX();
        dX = 0;

        height = mView.getHeight();
        yWhenAttached = mView.getY();
        dY = 0;
    }

    public void updateParentBounds() {
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
                    onDragFinish();
                    updateParentBounds();

                    final MediaPlayer mpClick = MediaPlayer.create(mParent.getContext(), R.raw.click);
                    if (winUnblockMe != 1){
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
                    if (mView == image){
                        mView.animate().x(0).setDuration(0).start();
                        mView.animate().y(bounds[1]).setDuration(0).start();
                    }else if (mView == image5){
                        mView.animate().x(v.getX()).setDuration(0).start();
                        mView.animate().y(bounds[1]).setDuration(0).start();
                    }else if (mView == image6){
                        mView.animate().x(v.getX()).setDuration(0).start();
                        mView.animate().y(bounds[1]).setDuration(0).start();
                    }else if (mView == image9){
                        mView.animate().x(v.getX()).setDuration(0).start();
                        mView.animate().y(bounds[1]).setDuration(0).start();
                    }else if (mView == image1){
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(0).setDuration(0).start();
                    }else if (mView == image2){
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(0).setDuration(0).start();
                    }else if (mView == image3){
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(v.getY()).setDuration(0).start();
                    }else if (mView == image4){
                        if (mView.getX() + mView.getWidth() >= mParent.getWidth() - 1){
                            RelativeLayout main = (RelativeLayout)mParent.findViewById(R.id.main);
                            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)mView.getLayoutParams();
                            main.removeAllViews();
                            updateBounds();
                            ImageView winImage = new ImageView(main.getContext());
                            winImage.setImageResource(R.drawable.star);
                            lParams.height = 150;
                            lParams.width = 150;
                            winImage.setLayoutParams(lParams);
                            winImage.setX(mParent.getWidth()/2 - lParams.width/2);
                            winImage.setY(mParent.getHeight()/2 - lParams.height/2);
                            main.addView(winImage);
                            winImage.setOnTouchListener(new OnDragTouchListener(winImage));

                            final MediaPlayer mediaPlayer = MediaPlayer.create(mParent.getContext(), R.raw.winner);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer.release();
                                }
                            });
                            winUnblockMe = 1;
                        }
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(v.getY()).setDuration(0).start();
                    }else if (mView == image7){
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(v.getY()).setDuration(0).start();
                    }else if (mView == image8){
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(v.getY()).setDuration(0).start();
                    }else if (mView == image10){
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(v.getY()).setDuration(0).start();
                    }else {
                        mView.animate().x(bounds[0]).setDuration(0).start();
                        mView.animate().y(bounds[1]).setDuration(0).start();
                    }
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

                    if (mView == image){
                        if (mView.getY() + height <= image4.getY() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxBottom = image4.getY();
                        }else if ((mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))) && (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth())))){
                            maxBottom = image10.getY();
                            maxTop = image1.getY() + image1.getHeight();
                        }else if (mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxBottom = image10.getY();
                        }else if ((mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))) && (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() <= image3.getX() + image3.getWidth())))){
                            maxBottom = image8.getY();
                            maxTop = image3.getY() + image3.getHeight();
                        }else if ((mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))) && (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth())))){
                            maxBottom = image8.getY();
                            maxTop = image1.getY() + image1.getHeight();
                        }else if (mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))){
                            maxBottom = image8.getY();
                        }else if (mView.getY() >= image10.getY() + image10.getHeight() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxTop = image10.getY() + image10.getHeight();
                        }else if (mView.getY() >= image4.getY() + image4.getHeight() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxTop = image4.getY() + image4.getHeight();
                        }else if (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() <= image3.getX() + image3.getWidth()))){
                            maxTop = image3.getY() + image3.getHeight();
                        }else if (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth()))){
                            maxTop = image1.getY() + image1.getHeight();
                        }
                    }else if (mView == image5){
                        if (mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxBottom = image10.getY();
                        }else if ((mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))) && (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth())))){
                            maxBottom = image7.getY();
                            maxTop = image2.getY() + image2.getHeight();
                        }else if (mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))){
                            maxBottom = image7.getY();
                        }else if (mView.getY() >= image4.getY() + image4.getHeight() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxTop = image4.getY() + image4.getHeight();
                        }else if (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() < image3.getX() + image3.getWidth()))){
                            maxTop = image3.getY() + image3.getHeight();
                        }else if (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth()))){
                            maxTop = image2.getY() + image2.getHeight();
                        }
                    }else if (mView == image6){
                        if (mView.getY() + height <= image4.getY() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxBottom = image4.getY();
                        }else if ((mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))) && (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth())))){
                            maxBottom = image10.getY();
                            maxTop = image2.getY() + image2.getHeight();
                        }else if (mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxBottom = image10.getY();
                        }else if ((mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))) && (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() < image3.getX() + image3.getWidth())))){
                            maxBottom = image7.getY();
                            maxTop = image3.getY() + image3.getHeight();
                        }else if ((mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))) && (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth())))){
                            maxBottom = image7.getY();
                            maxTop = image2.getY() + image2.getHeight();
                        }else if (mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))){
                            maxBottom = image7.getY();
                        }else if (mView.getY() >= image10.getY() + image10.getHeight() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxTop = image10.getY() + image10.getHeight();
                        }else if (mView.getY() >= image4.getY() + image4.getHeight() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxTop = image4.getY() + image4.getHeight();
                        }else if (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() < image3.getX() + image3.getWidth()))){
                            maxTop = image3.getY() + image3.getHeight();
                        }else if (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth()))){
                            maxTop = image2.getY() + image2.getHeight();
                        }
                    }else if (mView == image9){
                        if ((mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))) && (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth())))){
                            maxBottom = image10.getY();
                            maxTop = image2.getY() + image2.getHeight();
                        }else if ((mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))) && (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth())))){
                            maxBottom = image10.getY();
                            maxTop = image1.getY() + image1.getHeight();
                        }else if ((mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))) && (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() < image3.getX() + image3.getWidth())))){
                            maxBottom = image7.getY();
                            maxTop = image3.getY() + image3.getHeight();
                        }else if ((mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))) && (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth())))){
                            maxBottom = image7.getY();
                            maxTop = image2.getY() + image2.getHeight();
                        }else if ((mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))) && (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth())))){
                            maxBottom = image7.getY();
                            maxTop = image1.getY() + image1.getHeight();
                        }else if ((mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))) && (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() < image3.getX() + image3.getWidth())))){
                            maxBottom = image8.getY();
                            maxTop = image3.getY() + image3.getHeight();
                        }else if ((mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))) && (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth())))){
                            maxBottom = image8.getY();
                            maxTop = image2.getY() + image2.getHeight();
                        }else if ((mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))) && (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth())))){
                            maxBottom = image8.getY();
                            maxTop = image1.getY() + image1.getHeight();
                        }else if (mView.getY() + height <= image4.getY() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxBottom = image4.getY();
                        }else if (mView.getY() + height <= image10.getY() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxBottom = image10.getY();
                        }else if (mView.getY() + height <= image7.getY() &&
                                ((mView.getX() > image7.getX() && mView.getX() < image7.getX() + image7.getWidth()) || (mView.getX() + mView.getWidth() > image7.getX() &&
                                        mView.getX() + mView.getWidth() < image7.getX() + image7.getWidth()))){
                            maxBottom = image7.getY();
                        }else if (mView.getY() + height <= image8.getY() &&
                                ((mView.getX() > image8.getX() && mView.getX() < image8.getX() + image8.getWidth()) || (mView.getX() + mView.getWidth() > image8.getX() &&
                                        mView.getX() + mView.getWidth() < image8.getX() + image8.getWidth()))){
                            maxBottom = image8.getY();
                        }else if (mView.getY() >= image10.getY() + image10.getHeight() &&
                                ((mView.getX() > image10.getX() && mView.getX() < image10.getX() + image10.getWidth()) || (mView.getX() + mView.getWidth() > image10.getX() &&
                                        mView.getX() + mView.getWidth() < image10.getX() + image10.getWidth()))){
                            maxTop = image10.getY() + image10.getHeight();
                        }else if (mView.getY() >= image4.getY() + image4.getHeight() &&
                                ((mView.getX() > image4.getX() && mView.getX() < image4.getX() + image4.getWidth()) || (mView.getX() + mView.getWidth() > image4.getX() &&
                                        mView.getX() + mView.getWidth() < image4.getX() + image4.getWidth()))){
                            maxTop = image4.getY() + image4.getHeight();
                        }else if (mView.getY() >= image3.getY() + image3.getHeight() &&
                                ((mView.getX() > image3.getX() && mView.getX() < image3.getX() + image3.getWidth()) || (mView.getX() + mView.getWidth() > image3.getX() &&
                                        mView.getX() + mView.getWidth() < image3.getX() + image3.getWidth()))){
                            maxTop = image3.getY() + image3.getHeight();
                        }else if (mView.getY() >= image2.getY() + image2.getHeight() &&
                                ((mView.getX() > image2.getX() && mView.getX() < image2.getX() + image2.getWidth()) || (mView.getX() + mView.getWidth() > image2.getX() &&
                                        mView.getX() + mView.getWidth() < image2.getX() + image2.getWidth()))){
                            maxTop = image2.getY() + image2.getHeight();
                        }else if (mView.getY() >= image1.getY() + image1.getHeight() &&
                                ((mView.getX() > image1.getX() && mView.getX() < image1.getX() + image1.getWidth()) || (mView.getX() + mView.getWidth() > image1.getX() &&
                                        mView.getX() + mView.getWidth() < image1.getX() + image1.getWidth()))){
                            maxTop = image1.getY() + image1.getHeight();
                        }
                    }else if (mView == image1){
                        if (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight()))){
                            maxLeft = image.getX() + image.getWidth();
                            maxRight = image2.getX();
                        }else if (mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))) {
                            maxRight = image9.getX();
                        }else if (mView.getX() + width <= image2.getX() &&
                                mView.getY() == image2.getY()){
                            maxRight = image2.getX();
                        }
                    }else if (mView == image2){
                        if (mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))){
                            maxRight = image6.getX();
                            maxLeft = image1.getX() + image1.getWidth();
                        }else if (mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))){
                            maxRight = image5.getX();
                            maxLeft = image1.getX() + image1.getWidth();
                        }else if (mView.getX() >= image9.getX() + image9.getWidth() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))){
                            maxLeft = image9.getX() + image9.getWidth();
                        }else if (mView.getX() >= image1.getX() + image1.getWidth() &&
                                mView.getY() == image1.getY()){
                            maxLeft = image1.getX() + image1.getWidth();
                        }
                    }else if (mView == image3){
                        if (mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))) {
                            maxRight = image9.getX();
                        }else if ((mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxRight = image6.getX();
                            maxLeft = image.getX() + image.getWidth();
                        }else if ((mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxRight = image5.getX();
                            maxLeft = image.getX() + image.getWidth();
                        }else if (mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))){
                            maxRight = image6.getX();
                        }else if (mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))){
                            maxRight = image5.getX();
                        }else if (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight()))){
                            maxLeft = image.getX() + image.getWidth();
                        }
                    }else if (mView == image4){
                         if ((mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxLeft = image.getX() + image.getWidth();
                            maxRight = image9.getX();
                        }else if (mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))){
                            maxRight = image9.getX();
                        }else if ((mView.getX() + width <= image6.getX() &&
                                (mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight())) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxLeft = image.getX() + image.getWidth();
                            maxRight = image6.getX();
                        }else if (mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))){
                            maxRight = image6.getX();
                        }else if ((mView.getX() + width <= image5.getX() &&
                                 ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                         mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                 ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                         mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                             maxLeft = image.getX() + image.getWidth();
                             maxRight = image5.getX();
                         }else if (mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))){
                            maxRight = image5.getX();
                        }else if (mView.getX() >= image9.getX() + image9.getWidth() &&
                                 ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                         mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))){
                             maxLeft = image9.getX() + image9.getWidth();
                         }else if (mView.getX() >= image.getX() + image.getWidth() &&
                                 ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                         mView.getY() + mView.getHeight() < image.getY() + image.getHeight()))){
                             maxLeft = image.getX() + image.getWidth();
                         }
                    }else if (mView == image7) {
                        if (mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))) {
                            maxLeft = image8.getX() + image8.getWidth();
                            maxRight = image6.getX();
                        }else if (mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))) {
                            maxLeft = image8.getX() + image8.getWidth();
                            maxRight = image5.getX();
                        }else if (mView.getX() >= image9.getX() + image9.getWidth() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))) {
                            maxLeft = image9.getX() + image9.getWidth();
                        }else if (mView.getX() >= image8.getX() + image8.getWidth() &&
                                mView.getY() == image8.getY()) {
                            maxLeft = image8.getX() + image8.getWidth();
                        }
                    }else if (mView == image8){
                        if ((mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxRight = image9.getX();
                            maxLeft = image.getX() + image.getWidth();
                        }else if (mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))){
                            maxRight = image9.getX();
                        }else if (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight()))){
                            maxLeft = image.getX() + image.getWidth();
                            maxRight = image7.getX();
                        }else if (mView.getX() + width <= image7.getX() &&
                                mView.getY() == image7.getY()){
                            maxRight = image7.getX();
                        }
                    }else if (mView == image10){
                        if (mView.getX() + width <= image9.getX() &&
                                ((mView.getY() > image9.getY() && mView.getY() < image9.getY() + image9.getHeight()) || (mView.getY() + mView.getHeight() > image9.getY() &&
                                        mView.getY() + mView.getHeight() < image9.getY() + image9.getHeight()))){
                            maxRight = image9.getX();
                        }else if ((mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxRight = image6.getX();
                            maxLeft = image.getX() + image.getWidth();
                        }else if (mView.getX() + width <= image6.getX() &&
                                ((mView.getY() > image6.getY() && mView.getY() < image6.getY() + image6.getHeight()) || (mView.getY() + mView.getHeight() > image6.getY() &&
                                        mView.getY() + mView.getHeight() < image6.getY() + image6.getHeight()))){
                            maxRight = image6.getX();
                        }else if ((mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))) && (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight())))){
                            maxRight = image5.getX();
                            maxLeft = image.getX() + image.getWidth();
                        }else if (mView.getX() + width <= image5.getX() &&
                                ((mView.getY() > image5.getY() && mView.getY() < image5.getY() + image5.getHeight()) || (mView.getY() + mView.getHeight() > image5.getY() &&
                                        mView.getY() + mView.getHeight() < image5.getY() + image5.getHeight()))){
                            maxRight = image5.getX();
                        }else if (mView.getX() >= image.getX() + image.getWidth() &&
                                ((mView.getY() > image.getY() && mView.getY() < image.getY() + image.getHeight()) || (mView.getY() + mView.getHeight() > image.getY() &&
                                        mView.getY() + mView.getHeight() < image.getY() + image.getHeight()))){
                            maxLeft = image.getX() + image.getWidth();
                        }
                    }

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
