package com.mmk.myyoutube.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoDetailResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;



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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public class Item {

        @SerializedName("kind")
        @Expose
        private String kind;
        @SerializedName("etag")
        @Expose
        private String etag;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("snippet")
        @Expose
        private SnippetResponse snippet;
        @SerializedName("contentDetails")
        @Expose
        private ContentDetails contentDetails;
        @SerializedName("statistics")
        @Expose
        private Statistics statistics;

        public SnippetResponse getSnippet() {
            return snippet;
        }

        public void setSnippet(SnippetResponse snippet) {
            this.snippet = snippet;
        }

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public void setContentDetails(ContentDetails contentDetails) {
            this.contentDetails = contentDetails;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
        }



        public class ContentDetails {

            @SerializedName("duration")
            @Expose
            private String duration;
            @SerializedName("dimension")
            @Expose
            private String dimension;
            @SerializedName("definition")
            @Expose
            private String definition;
            @SerializedName("caption")
            @Expose
            private String caption;
            @SerializedName("licensedContent")
            @Expose
            private Boolean licensedContent;
            @SerializedName("projection")
            @Expose
            private String projection;

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getDimension() {
                return dimension;
            }

            public void setDimension(String dimension) {
                this.dimension = dimension;
            }

            public String getDefinition() {
                return definition;
            }

            public void setDefinition(String definition) {
                this.definition = definition;
            }

            public String getCaption() {
                return caption;
            }

            public void setCaption(String caption) {
                this.caption = caption;
            }

            public Boolean getLicensedContent() {
                return licensedContent;
            }

            public void setLicensedContent(Boolean licensedContent) {
                this.licensedContent = licensedContent;
            }

            public String getProjection() {
                return projection;
            }

            public void setProjection(String projection) {
                this.projection = projection;
            }

        }

        public class Statistics {

            @SerializedName("viewCount")
            @Expose
            private String viewCount;
            @SerializedName("likeCount")
            @Expose
            private String likeCount;
            @SerializedName("dislikeCount")
            @Expose
            private String dislikeCount;
            @SerializedName("favoriteCount")
            @Expose
            private String favoriteCount;
            @SerializedName("commentCount")
            @Expose
            private String commentCount;

            public String getViewCount() {
                return viewCount;
            }

            public void setViewCount(String viewCount) {
                this.viewCount = viewCount;
            }

            public String getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(String likeCount) {
                this.likeCount = likeCount;
            }

            public String getDislikeCount() {
                return dislikeCount;
            }

            public void setDislikeCount(String dislikeCount) {
                this.dislikeCount = dislikeCount;
            }

            public String getFavoriteCount() {
                return favoriteCount;
            }

            public void setFavoriteCount(String favoriteCount) {
                this.favoriteCount = favoriteCount;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

        }


    }



}
