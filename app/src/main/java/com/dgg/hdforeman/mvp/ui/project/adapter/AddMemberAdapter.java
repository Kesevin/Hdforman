package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.AddMembersContract;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.ui.mine.holder.BaseAbstractViewHolder;
import com.dgg.hdforeman.mvp.ui.mine.holder.TeamTypeHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.AddMemberUserHolder;

import java.util.List;

/**
 * Created by twick on 2016/10/25.
 */

public class AddMemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEMVIEW_ONE=0;
    private static final int TYPE_ITEMVIEW_TWO=1;
    private Context mContext;
    private List<TeamBean> mList;
    private AddMembersContract.NetUtil mNetUtil;

    public AddMemberAdapter(Context context, AddMembersContract.NetUtil netUtil,List<TeamBean> list) {
        this.mContext=context;
        this.mNetUtil=netUtil;
        this.mList=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case TYPE_ITEMVIEW_ONE:
                view = LayoutInflater.from(mContext).inflate(R.layout.mine_team_recyclerview_item1, null, false);
                holder = new TeamTypeHolder(view);
                break;
            case TYPE_ITEMVIEW_TWO:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_add_meber, null, false);
                holder = new AddMemberUserHolder(mContext,view,mNetUtil);
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getTeamType().equals("0") ? TYPE_ITEMVIEW_ONE : TYPE_ITEMVIEW_TWO;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseAbstractViewHolder)holder).bindItemView(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
