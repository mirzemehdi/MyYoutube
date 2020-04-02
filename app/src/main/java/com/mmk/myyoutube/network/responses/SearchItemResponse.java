package com.mmk.myyoutube.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchItemResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("id")
    @Expose
    private IdResponse idResponse;
    @SerializedName("snippet")
    @Expose
    private SnippetResponse snippet;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public IdResponse getId() {
        return idResponse;
    }

    public void setId(IdResponse idResponse) {
        this.idResponse = idResponse;
    }

    public SnippetResponse getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetResponse snippet) {
        this.snippet = snippet;
    }

    public class IdResponse {


        @SerializedName("videoId")
        @Expose
        private String videoId;

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

    }
}
