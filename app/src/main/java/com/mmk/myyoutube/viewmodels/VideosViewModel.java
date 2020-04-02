package com.mmk.myyoutube.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mmk.myyoutube.model.Video;
import com.mmk.myyoutube.model.VideosResponse;
import com.mmk.myyoutube.repository.VideosRepository;

import java.util.List;

public class VideosViewModel extends AndroidViewModel {

    public VideosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<VideosResponse>loadVideos(String search, String pageToken){
        return VideosRepository
                .getInstance()
                .loadVideos(search,pageToken);
    }
}
