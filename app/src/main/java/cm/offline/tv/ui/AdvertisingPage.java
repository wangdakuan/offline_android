package cm.offline.tv.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.offline_android.R;

import chuangyuan.ycj.videolibrary.video.MediaSourceBuilder;
import cm.offline.tv.utils.ActivityUtils;
import com.google.android.exoplayer2.ExoPlaybackException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chuangyuan.ycj.videolibrary.listener.VideoInfoListener;
import chuangyuan.ycj.videolibrary.video.ExoUserPlayer;
import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: AdvertisingPage
 * Author: wangdakuan
 * Date: 2020-03-06 15:03
 * Description: 主要息屏后整个屏幕为广告
 * History:
 * version：1.5
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class AdvertisingPage extends AppCompatActivity {

    @BindView(R.id.video_view)
    VideoPlayerView mVideoView; //视频控件

    ExoUserPlayer exoPlayerManager; //视频播放管理组件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_advertising);
        ButterKnife.bind(this);

        exoPlayerManager = new VideoPlayerManager.Builder(VideoPlayerManager.TYPE_PLAY_GESTURE, mVideoView)
                //加载rtmp 协议视频
                .setPlayUri("http://mp4.vjshi.com/2013-07-25/2013072519392517096.mp4").create();
        //是否屏蔽进度控件拖拽快进视频（例如广告视频，（不允许用户））
        exoPlayerManager.setSeekBarSeek(true);

        exoPlayerManager.addVideoInfoListener(new VideoInfoListener() {
            @Override
            public void onPlayStart(long currPosition) {

            }

            @Override
            public void onLoadingChanged() {

            }

            @Override
            public void onPlayerError(@Nullable ExoPlaybackException e) {

            }

            @Override
            public void onPlayEnd() {

            }

            @Override
            public void isPlaying(boolean playWhenReady) {

            }
        });
        //播放视频
        exoPlayerManager.startPlayer();
    }

    @OnClick(R.id.view_screen)
    public void onViewClicked() {
        ActivityUtils.navigateTo(HomeActivity.class);
    }
}
