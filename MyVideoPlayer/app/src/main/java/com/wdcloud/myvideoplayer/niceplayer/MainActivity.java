package com.wdcloud.myvideoplayer.niceplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wdcloud.myvideoplayer.R;

public class MainActivity extends AppCompatActivity {
    private String video_url="http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NiceVideoPlayer niceVideoPlayer=findViewById(R.id.nice_player);
        niceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);
        niceVideoPlayer.setUp(video_url,null);
        TxVideoPlayerController txVideoPlayerController = new TxVideoPlayerController(this);
        txVideoPlayerController.setTitle("视频播放");
        txVideoPlayerController.setImage(R.drawable.timg);
        niceVideoPlayer.setController(txVideoPlayerController);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if(NiceVideoPlayerManager.instance().onBackPressd())
            return;
        super.onBackPressed();
    }
}
