package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructTeamContract;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseConstructTeamHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructTeamTypeHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructTeamUserHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @BindView(R.id.tv_members_title)
    TextView tvMembersTitle;
    private ConstructTeamContract.View mRootView;
    private List<ConstructTeamBean> infos=new ArrayList<>();
    private Context mContext;
    private static final int TYPE_ITEMVIEW_ONE = 0;
    private static final int TYPE_ITEMVIEW_TWO = 1;
    private ConstructTeamContract.NetUtil mNetUtil;

    public ConstructTeamAdapter(Context context,ConstructTeamContract.NetUtil netUtil,List<ConstructTeamBean> infos) {
        this.mContext = context;
        this.mNetUtil=netUtil;
        this.infos = infos;
    }

    @Override
    public int getItemViewType(int position) {
        return infos.get(position).getCcnstructTeamType().equals("0") ? TYPE_ITEMVIEW_ONE : TYPE_ITEMVIEW_TWO;
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_ITEMVIEW_ONE:
                view = LayoutInflater.from(mContext).inflate(R.layout.mine_team_recyclerview_item1, null, false);
                holder = new ConstructTeamTypeHolder(view);
                break;
            case TYPE_ITEMVIEW_TWO:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_delete_member, null, false);
                holder = new ConstructTeamUserHolder(mContext,view,mNetUtil);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseConstructTeamHolder)holder).bindItemView(infos.get(position));
    }

}
