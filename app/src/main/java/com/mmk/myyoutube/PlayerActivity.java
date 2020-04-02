package com.mmk.myyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mmk.myyoutube.model.Video;
import com.mmk.myyoutube.network.YoutubeApi;
import com.mmk.myyoutube.utils.Common;

public class PlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;

    private YouTubePlayer.OnInitializedListener initializedListener;
    private Video video;
    private TextView titleTextView,timePastTextView,watchNbTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (getIntent()!=null)
            video=(Video)getIntent().getSerializableExtra(MainActivity.EXTRA_VIDEO);

        init();

    }

    private void init() {

        youTubePlayerView=findViewById(R.id.youtubePlayer);
        titleTextView=findViewById(R.id.textView_player_name);
        timePastTextView=findViewById(R.id.textView_player_timePast);
        watchNbTextView=findViewById(R.id.textView_player_watchNb);

        if (video!=null){
            titleTextView.setText(video.getTitle());
            timePastTextView.setText(video.getTimePast());
            watchNbTextView.setText(video.getViewNumbers());
        }

        initializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (video!=null)
                    youTubePlayer.loadVideo(video.getId());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize(Common.API_KEY,initializedListener);
    }




}
