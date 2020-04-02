package com.mmk.myyoutube.model;

import java.util.List;

public class VideosResponse {
    private List<Video>videoList;
    private Throwable error;
    private int responseCode;
    private String nextPageToken;

    public VideosResponse(Throwable error) {
        this.error = error;
    }



    public VideosResponse(List<Video> videoList,String nextPageToken) {
        this.videoList = videoList;
        responseCode=200;
        this.nextPageToken = nextPageToken;
    }
    public VideosResponse(int responseCode){
        this.responseCode=responseCode;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
