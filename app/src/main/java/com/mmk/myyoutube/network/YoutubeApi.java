package com.mmk.myyoutube.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeApi {

    private static final String BASE_URL="https://www.googleapis.com/youtube/v3/";
    private static YoutubeApiService youtubeApiService;
    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    public static YoutubeApiService getService(){
        if (youtubeApiService==null)
            youtubeApiService=retrofit.create(YoutubeApiService.class);

        return youtubeApiService;
    }

}
