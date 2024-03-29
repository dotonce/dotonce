package com.dotonce.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class VideoActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback {
    Bundle bundle;
    Intent intent;
    String VIDEO_URL="",title="";
    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;
    View mVideoLayout;
    Toolbar toolbar;


    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    int mSeekPosition,rawFile;
    private int cachedHeight;
    private boolean isFullscreen,fitXY, isFile = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        intent = getIntent();
        bundle = intent.getExtras();
        if(bundle !=null){
            VIDEO_URL = bundle.getString("url","");
            title = bundle.getString("title","");
            fitXY = bundle.getBoolean("fitXY",false);
            rawFile = bundle.getInt("rawFile",0);
            isFile = bundle.getBoolean("isFile",false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
        mVideoLayout = findViewById(R.id.video_layout);
        mVideoView = findViewById(R.id.videoView);
        toolbar = findViewById(R.id.generalToolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        mMediaController = findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        mMediaController.setTitle(title);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mVideoView.setOnCompletionListener(mp -> {

        });
        mVideoView.setFitXY(fitXY);
    }


    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);


        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
           // layoutParams.height = this.cachedHeight;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);

        }

        switchTitleBar(!isFullscreen);
        FullScreen.set(VideoActivity.this);
    }
    private void switchTitleBar(boolean show) {


        if (show) {
            toolbar.setVisibility(View.VISIBLE);
            try {
                this.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }catch (Exception | Error ignored){}
        } else {
            toolbar.setVisibility(View.GONE);
            try {
                this.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }catch (Exception | Error ignored){}

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        FullScreen.set(VideoActivity.this);
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);
        }
    }
    private void setVideoAreaSize() {
        mVideoLayout.post(() -> {
            int width = mVideoLayout.getWidth();
            cachedHeight = (int) (width * 405f / 720f);
            ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            videoLayoutParams.height = cachedHeight;
            videoLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;;
            mVideoLayout.setLayoutParams(videoLayoutParams);
            String videoPath = "android.resource://" + getPackageName() + "/" + rawFile;

            if(isFile){
                mVideoView.setVideoURI(Uri.parse(videoPath));
            }else {
                mVideoView.setVideoPath(VIDEO_URL);
            }
            mVideoView.requestFocus();
            mVideoView.start();
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);

    }

}