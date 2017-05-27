package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ImageInfo;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.IntermediaImgHolder;

import java.util.List;

/**
 * Created by kelvin on 2016/11/18.
 */

public class IntermediateImgAdapter extends BaseAdapter<String> {


    private IntermediateImgAdapter.OnPreviewClickListener mOnPreviewClickListener;
    public IntermediateImgAdapter(List<String> infos) {
        super(infos);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new IntermediaImgHolder(v,mOnPreviewClickListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_intermediate_image;
    }

    public void setOnPreviewClickListener(IntermediateImgAdapter.OnPreviewClickListener mOnItemClickListener){
        this.mOnPreviewClickListener = mOnItemClickListener;
    }

    public interface OnPreviewClickListener{
        void onItemPreview(View view,int position);
    }
}
