package com.dgg.hdforeman.mvp.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.ui.mine.holder.BaseAbstractViewHolder;
import com.dgg.hdforeman.mvp.ui.mine.holder.TeamTypeHolder;
import com.dgg.hdforeman.mvp.ui.mine.holder.TeamUserListHolder;
import com.paginate.recycler.LoadingListItemCreator;

import java.util.List;


/**
 * Created by Administrator on 2016/10/21.
 */

public class TeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LoadingListItemCreator {
    private List<TeamBean> mList;
    private Context mContext;
    private static final int TYPE_ITEMVIEW_ONE=0;
    private static final int TYPE_ITEMVIEW_TWO=1;
//    private static final int TYPE_FOOTER=2;

    public TeamAdapter(Context context) {
        this.mContext = context;
    }

    public void addTeamList(List<TeamBean> list){
        this.mList = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
                view = LayoutInflater.from(mContext).inflate(R.layout.mine_team_recyclerview_item2, null, false);
                holder = new TeamUserListHolder(mContext,view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseAbstractViewHolder)holder).bindItemView(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getTeamType().equals("0") ? TYPE_ITEMVIEW_ONE : TYPE_ITEMVIEW_TWO;
    }
}
