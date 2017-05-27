package com.dgg.hdforeman.mvp.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.dgg.hdforeman.mvp.ui.mine.holder.ScoreHolder;

import java.util.List;


/**
 * Created by Administrator on 2016/10/24.
 */

public class ScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ScoreBean> mList;
    private Context mContext;

    public ScoreAdapter(Context context) {
        this.mContext = context;
    }

    public void addListData(List<ScoreBean> list){
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.mine_score_listview_item,parent,false);
        RecyclerView.ViewHolder scoreHolder=new ScoreHolder(view);
        return scoreHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ScoreHolder)holder).bindView(mList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
