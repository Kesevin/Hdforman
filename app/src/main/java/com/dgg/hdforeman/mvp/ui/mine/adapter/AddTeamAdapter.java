package com.dgg.hdforeman.mvp.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddSearchContract;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.ui.mine.holder.AddTeamHolder;

import java.util.List;


/**
 * Created by Administrator on 2016/10/24.
 */

public class AddTeamAdapter extends RecyclerView.Adapter<AddTeamHolder> {
    private Context mContext;
    private List<TeamSearchResult> mList;
    private TeamAddSearchContract.NetUtil netUtil;

    public AddTeamAdapter(Context context, TeamAddSearchContract.NetUtil netUtil) {
        this.mContext = context;
        this.netUtil = netUtil;
    }

    public void addTeamList(List<TeamSearchResult> list){
        this.mList=list;
    }

    @Override
    public AddTeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.mine_team_add_recyclerview_item, parent,false);
        AddTeamHolder viewHolder = new AddTeamHolder(mContext,convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddTeamHolder holder, final int position) {
        holder.bindItemView(mList.get(position),netUtil);
//        holder.itemTeamAddListViewName.setText(mList.get(position).getWk_name());
//        holder.itemTeamAddListViewPhoneBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                netUtil.addTeamUserUtil(mList.get(position).getWk_userid());
//            }
//        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    class MyViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.item_team_add_listView_head)
//        ImageView itemTeamAddListViewHead;
//        @BindView(R.id.item_team_add_listView_name)
//        TextView itemTeamAddListViewName;
//        @BindView(R.id.item_team_add_listView_phoneBtn)
//        ImageView itemTeamAddListViewPhoneBtn;
//
//        public MyViewHolder(View view) {
//            super(view);
//            AutoUtils.auto(view);
//            ButterKnife.bind(this, view);
//        }
//    }
}
