package com.seven.sevenlivestream.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import com.seven.sevenlivestream.R;
import com.seven.sevenlivestream.utils.SystemBarTintManager;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by gengbaolong on 2017/8/16.
 */

public class PlayerActivity extends Activity implements View.OnClickListener{

    private String path = "";
    private VideoView mVideoView;
    private EditText mEditText;
    private Button mStartBtn;
    private Button mStopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setStateBarColor(R.color.colorPrimaryDark);

        setContentView(R.layout.activity_playvideo);
//        if (!LibsChecker.checkVitamioLibs(this))
//            return;
        mEditText = (EditText) findViewById(R.id.url);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mStartBtn = (Button) findViewById(R.id.start);
        mStopBtn = (Button) findViewById(R.id.stop);
        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
    }

    protected void setStateBarColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resId);
            tintManager.setStatusBarDarkMode(true, this);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                path = mEditText.getText().toString();
                if (!TextUtils.isEmpty(path)) {
                    mVideoView.setVideoPath("rtmp://192.168.1.124:1935/live/123456");
                    mVideoView.setMediaController(new MediaController(this));
                    mVideoView.requestFocus();

                    mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.setPlaybackSpeed(1.0f);
                        }
                    });
                }
                break;
            case R.id.stop:
                mVideoView.stopPlayback();
                break;
        }
    }

}
