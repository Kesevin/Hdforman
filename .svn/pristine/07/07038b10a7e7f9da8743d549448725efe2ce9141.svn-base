package com.dgg.hdforeman.mvp.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.mine.holder.FooterViewHolder;
import com.paginate.recycler.LoadingListItemCreator;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/2.
 */

public class CustomLoadingListItemCreator implements LoadingListItemCreator {


    private RecyclerView teamRecyclerView;

    public CustomLoadingListItemCreator(RecyclerView teamRecyclerView) {
        this.teamRecyclerView = teamRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_footer, null, false);
        AutoUtils.auto(view);
        ButterKnife.bind(this, view);
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FooterViewHolder vh = (FooterViewHolder) holder;
        vh.footerTextView.setText(String.format("正在加载......"));
        vh.footer.setVisibility(View.VISIBLE);
        // This is how you can make full span if you are using StaggeredGridLayoutManager
        if (teamRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

}
