package com.mmk.myyoutube.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mmk.myyoutube.callbacks.ILoadMore;

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    private boolean isLoading = true;
    private int totalItemCount = 0;
    private int lastVisibleItem = 0;
    private int visibleThreshold = 5;
    private int previousTotalCount = 0;
    private ILoadMore loadMore;


    public InfiniteScrollListener(LinearLayoutManager layoutManager, ILoadMore loadMore) {
        this.layoutManager = layoutManager;
        this.loadMore = loadMore;
    }

    public void reset() {
        isLoading = true;
        totalItemCount = 0;
        lastVisibleItem = 0;
        visibleThreshold = 5;
        previousTotalCount = 0;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {

            totalItemCount = layoutManager.getItemCount();
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();


            if (isLoading && totalItemCount > previousTotalCount) {
                previousTotalCount = totalItemCount + 1; //+1 for progress item
                isLoading = false;
            }

            if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                if (loadMore != null) loadMore.onLoad();
                isLoading = true;
            }


        }

    }
}
