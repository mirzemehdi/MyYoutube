package com.mmk.myyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.mmk.myyoutube.adapter.VideosAdapter;
import com.mmk.myyoutube.callbacks.ILoadMore;
import com.mmk.myyoutube.callbacks.ItemClickListener;
import com.mmk.myyoutube.model.Video;
import com.mmk.myyoutube.network.YoutubeApi;
import com.mmk.myyoutube.network.responses.SearchItemResponse;
import com.mmk.myyoutube.network.responses.SearchResponse;
import com.mmk.myyoutube.network.responses.VideoDetailResponse;
import com.mmk.myyoutube.utils.Common;
import com.mmk.myyoutube.utils.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_VIDEO = "com.mmk.myyoutube.EXTRA_VIDEO";

    private SearchView searchView;
    private RecyclerView recyclerViewVideos;
    private InfiniteScrollListener infiniteScrollListener;
    private VideosAdapter videosAdapter;
    private String nextPageToken = "";
    private String currentSearch = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setClicks();
    }


    private void init() {
        searchView = findViewById(R.id.searchView_main);
        recyclerViewVideos = findViewById(R.id.recyclerViewVideos);
        recyclerViewVideos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewVideos.setLayoutManager(layoutManager);
        videosAdapter = new VideosAdapter(this);
        recyclerViewVideos.setAdapter(videosAdapter);

        loadVideos(currentSearch);
        infiniteScrollListener = new InfiniteScrollListener(layoutManager, () -> loadVideos(currentSearch));

        recyclerViewVideos.addOnScrollListener(infiniteScrollListener);
    }

    private void setClicks() {
        videosAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(Video video) {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra(EXTRA_VIDEO, video);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                reset();
                currentSearch = query;
                loadVideos(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void loadVideos(String search) {
        videosAdapter.setLoading(true);
        YoutubeApi.getService()
                .search(search, nextPageToken, Common.API_KEY)
                .enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                        Log.d("Reasonnnn", response.message());
                        if (response.isSuccessful()) {
                            SearchResponse searchResponse = response.body();
                            List<SearchItemResponse> itemResponses = searchResponse.getItems();
                            getVideoDetails(itemResponses);
                            nextPageToken = searchResponse.getNextPageToken();

                        } else {
                            if (response.code() == 403)
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_api_limit),
                                        Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_server_error),
                                        Toast.LENGTH_SHORT).show();
                            reset();

                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_server_error),
                                Toast.LENGTH_SHORT).show();
                        reset();
                    }
                });


    }

    private void getVideoDetails(List<SearchItemResponse> itemResponses) {
        List<String> idList = new ArrayList<>();
        for (SearchItemResponse item : itemResponses)
            idList.add(item.getId().getVideoId());

        List<Video> videoList = new ArrayList<>();
        YoutubeApi.getService().getVideoDetail(TextUtils.join(",", idList), Common.API_KEY)
                .enqueue(new Callback<VideoDetailResponse>() {
                    @Override
                    public void onResponse(Call<VideoDetailResponse> call, Response<VideoDetailResponse> response) {
                        if (response.isSuccessful()) {
                            VideoDetailResponse videoDetailResponse = response.body();
                            for (VideoDetailResponse.Item item : videoDetailResponse.getItems()) {

                                Video video = new Video(item.getId(),
                                        item.getSnippet().getTitle(),
                                        item.getSnippet().getChannelTitle(),
                                        item.getSnippet().getPublishedAt(),
                                        item.getStatistics().getViewCount(),
                                        item.getSnippet().getThumbnails().getMedium().getUrl());
                                videoList.add(video);

                            }
                            videosAdapter.setVideoList(videoList);


                        } else {
                            if (response.code() == 403)
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_api_limit),
                                        Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_server_error),
                                        Toast.LENGTH_SHORT).show();
                            reset();

                        }
                    }

                    @Override
                    public void onFailure(Call<VideoDetailResponse> call, Throwable t) {

                        Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_server_error),
                                Toast.LENGTH_SHORT).show();
                        reset();
                    }
                });


    }


    public void reset() {
        nextPageToken = "";
        currentSearch = "";
        videosAdapter.clearList();
        infiniteScrollListener.reset();
    }


}
