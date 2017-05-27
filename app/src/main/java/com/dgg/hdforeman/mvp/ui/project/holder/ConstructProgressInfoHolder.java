package com.dgg.hdforeman.mvp.ui.project.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructProgressInfoHolder extends BaseHolder<ConstructProgressBean> {

    private ConstructProgressBean mData;
    private ConstructProgressContract.View mRootView;

    @BindView(R.id.up_vertical_line)
    View upVerticalLine;
    @BindView(R.id.down_vertical_line)
    View downVerticalLine;
    @BindView(R.id.progress_state)
    ImageView progressState;
    @BindView(R.id.progress_sub_name)
    TextView progressSubName;
    @BindView(R.id.progress_state_finish)
    ImageView progressStateFinish;
    @BindView(R.id.sub_item_finish)
    Button subItemFinish;
//    @BindView(R.id.text_finish)
//    TextView textFinish;
    @BindView(R.id.progress_sub_time)
    TextView progressSubTime;
    @BindView(R.id.text_finish_noStart)
    TextView textFinishNoStart;
    @BindView(R.id.finish_ll)
    AutoLinearLayout finishLl;

    private Context mContext;

    public ConstructProgressInfoHolder(View itemView,ConstructProgressContract.View mRootView) {
        super(itemView);
        this.mRootView = mRootView;
        mContext=itemView.getContext();
    }

    @Override
    public void setData(ConstructProgressBean itemBean) {
        this.mData = itemBean;
//        if(getShutdown()==2){
//            subItemFinish.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundGray));
//            subItemFinish.setClickable(false);
//        }
        if(mData.getConstructProgressInfo().isFirst()){
            upVerticalLine.setVisibility(View.INVISIBLE);
        }else{
            upVerticalLine.setVisibility(View.VISIBLE);
        }

        if(mData.getConstructProgressInfo().isEnd()){
            downVerticalLine.setVisibility(View.INVISIBLE);
        }else{
            downVerticalLine.setVisibility(View.VISIBLE);
        }
        progressSubName.setText(mData.getConstructProgressInfo().getSn_stname());
        if (!TextUtils.isEmpty(mData.getConstructProgressInfo().getSn_enddate())) {
            progressSubTime.setText(TimeUtil.keepTimeYMD(mData.getConstructProgressInfo().getSn_enddate(),"MM-dd"));
        }
        setState(mData.getConstructProgressInfo());//设置进度状态显示
    }

    private void setState(ConstructProgress.PDBean.DBean.ListBean data) {
        progressState.setVisibility(data.getSn_state() == 0 ? View.VISIBLE : View.INVISIBLE);
        progressStateFinish.setVisibility(data.getSn_state() == 0 ? View.INVISIBLE : View.VISIBLE);
        subItemFinish.setVisibility(data.getSn_state() == 0 ? View.VISIBLE : View.GONE);
        finishLl.setVisibility(data.getSn_state() == 0 ? View.INVISIBLE : View.VISIBLE);
        if(data.isCanFinish() && data.getSn_state() == 0){
            textFinishNoStart.setVisibility(View.VISIBLE);
            subItemFinish.setVisibility(View.GONE);
            finishLl.setVisibility(View.GONE);

        }else{
            textFinishNoStart.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.sub_item_finish)
    public void onClicks(View view){
//        mData.setSn_state(1);
//        setState(mData);
        mRootView.updateStageNode(mData,getLayoutPosition());
    }
}
