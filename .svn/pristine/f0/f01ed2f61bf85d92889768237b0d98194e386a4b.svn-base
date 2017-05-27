package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ImageInfo;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.UploadHolder;

import java.util.List;

/**
 * Created by Rex on 2016/11/22.
 */

public class UploadPicAdapter extends RecyclerView.Adapter<UploadHolder>{

    private OnItemClickListener mOnItemClickListener;
    private int limit;
    List<ImageInfo> imageList;

    public UploadPicAdapter(List<ImageInfo> imageList,int limit) {
        this.imageList=imageList;
          this.limit=limit;

    }
    @Override
    public UploadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_image_item, parent, false);
        return new UploadHolder(view,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(UploadHolder holder, int position) {
      if( position == imageList.size()&&position!=limit)
        holder.setData(null,true);
        else
          holder.setData(imageList.get(position),false);
    }

    public void setData(List<ImageInfo> data) {
        if (data != null) {
            imageList = data;
        } else {
            imageList.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageList.size()+1>limit?limit:imageList.size()+1;
    }
    public void setItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onRetryClick(View view,int position,ImageInfo info);
        void onDeleteClick(View view,int position,ImageInfo info);
        void onItem(View view,int position);
        }
}
