package com.mmk.myyoutube.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mmk.myyoutube.R;
import com.mmk.myyoutube.callbacks.ItemClickListener;
import com.mmk.myyoutube.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<Video> videoList;
    private ItemClickListener itemClickListener;

    private final static int VIEW_TYPE_ITEM=0;
    private final static int VIEW_TYPE_LOADING=1;

    public VideosAdapter(Context context) {
        this.context = context;
        videoList=new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ITEM){
            View view= LayoutInflater.from(context).inflate(R.layout.item_row_video,parent,false);
            return new ItemViewHolder(view);
        }
        else if (viewType==VIEW_TYPE_LOADING){
            View view= LayoutInflater.from(context).inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder=(LoadingViewHolder)holder;
            loadingViewHolder.mProgress.setIndeterminate(true);
        }
        else if (holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder=(ItemViewHolder)holder;

            Video video=videoList.get(position);
            itemViewHolder.titleTextView.setText(video.getTitle());
            itemViewHolder.authorTextView.setText(video.getAuthor());
            itemViewHolder.viewNumbersTextView.setText(video.getViewNumbers());
            itemViewHolder.timePastTextView.setText(video.getTimePast());

            Picasso.with(context)
                    .load(video.getImageUri())
                    .into(itemViewHolder.videoImage);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return videoList.get(position)==null? VIEW_TYPE_LOADING: VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public void setVideoList(List<Video> videoList) {
        setLoading(false);
        int startPos=this.videoList.size();
        this.videoList.addAll(videoList);
        notifyItemRangeChanged(startPos,videoList.size());
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public void setLoading(boolean isLoading){
        if (isLoading) {
            videoList.add(null);
            notifyItemInserted(videoList.size() -1);
        }
        else{
            int lastPos=videoList.size()-1;
            videoList.remove(lastPos);
            notifyItemRemoved(lastPos);
        }
    }

    public void clearList(){
        videoList.clear();
        notifyDataSetChanged();

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView,authorTextView,timePastTextView,viewNumbersTextView;
        private ImageView videoImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView=itemView.findViewById(R.id.textView_itemVideo_name);
            authorTextView=itemView.findViewById(R.id.textView_itemVideo_author);
            timePastTextView=itemView.findViewById(R.id.textView_itemVideo_timePast);
            viewNumbersTextView=itemView.findViewById(R.id.textView_itemVideo_watchNb);
            videoImage=itemView.findViewById(R.id.imageView_itemVideo);

            itemView.setOnClickListener(view->{
                if (itemClickListener!=null)
                    itemClickListener.onClick(videoList.get(getAdapterPosition()));
            });
        }



    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar mProgress;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            mProgress=itemView.findViewById(R.id.item_progress);
        }
    }
}
