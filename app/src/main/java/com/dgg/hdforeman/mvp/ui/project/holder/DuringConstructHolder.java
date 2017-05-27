package com.dgg.hdforeman.mvp.ui.project.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.mvp.contract.project.DuringConstructContract;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectUpList;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

import static android.widget.RelativeLayout.TRUE;


/**
 * Created by kelvin on 2015/11/2 12:56
 */
public class DuringConstructHolder extends BaseHolder<ProjectResponse> {

    private DuringConstructContract.View mRootView;

    @Nullable
    @BindView(R.id.mDcName)
    TextView mDcName;
    @Nullable
    @BindView(R.id.mDcLocation)
    TextView mDcLocation;
    @Nullable
    @BindView(R.id.mDcProjectState)
    TextView mDcProjectState;
    @Nullable
    @BindView(R.id.mDcOwnerState)
    TextView mDcOwnerState;
    @Nullable
    @BindView(R.id.mDcOwnerName)
    TextView mDcOwnerName;
    @Nullable
    @BindView(R.id.mDcCallPhone)
    ImageButton mDcCallPhone;
    @BindView(R.id.add_pro_tv)
    TextView addProTv;
    @BindView(R.id.add_pro_list)
    AutoLinearLayout addProList;
    @BindView(R.id.normal_pro)
    AutoRelativeLayout normalPro;
    @BindView(R.id.during_rl)
    AutoRelativeLayout duringRl;
    @BindView(R.id.pro_during_btn)
    TextView proDuringBtn;
    @BindView(R.id.mDcAdress)
    TextView mDcAdress;
    @BindView(R.id.mDcProjectDate)
    TextView mDcProjectDate;

    private Context mContext;

    private ProjectResponse mData;
    public DuringConstructHolder(View itemView,DuringConstructContract.View mRootView) {
        super(itemView);
        this.mRootView = mRootView;
        this.mContext=itemView.getContext();
    }

    @Override
    public void setData(ProjectResponse data) {
        this.mData = data;
        mDcName.setText(data.getPm_housesname());
        mDcLocation.setText(data.getPm_roomno());
        mDcOwnerName.setText(data.getPm_cusname());
        mDcProjectState.setText(data.getStage());
        mDcAdress.setText(data.getPm_housesaddress());
        switch (data.getPm_state()){
            case "0"://未签约
                mDcOwnerState.setText("待接单");
                mDcProjectState.setVisibility(View.GONE);
                break;
            case "1"://待开工(已签约)
                mDcProjectState.setText("预计开工时间："+data.getPm_startdate());
                mDcOwnerState.setVisibility(View.GONE);
                break;
            case "2"://施工中
                mDcProjectState.setText(data.getStage());
                mDcOwnerState.setText(data.getStageState());
                break;
            case "3"://停工(暂停)
                mDcOwnerState.setText("已停工");
                mDcProjectState.setVisibility(View.GONE);
                break;
            case "4"://已完工
                mDcProjectState.setText("完工时间："+data.getPm_finishdate());
                mDcOwnerState.setVisibility(View.GONE);
                break;
            case "5"://未签约工长已接手
                mDcOwnerState.setText("待报价");
                mDcProjectState.setVisibility(View.GONE);
                break;
            case "6"://工长已报价待签约)
                mDcOwnerState.setText("待报价");
                mDcProjectState.setVisibility(View.GONE);
                if(!data.getPm_quote().equals("0.0")){
                    mDcProjectState.setText("报价金额："+data.getPm_quote());
                    mDcProjectDate.setText("报价时间："+data.getPm_quotetime());
                    mDcProjectDate.setVisibility(View.VISIBLE);
                    mDcProjectState.setVisibility(View.VISIBLE);
                    mDcOwnerState.setVisibility(View.GONE);
                }
                break;
        }

        addProList.removeAllViews();
        normalPro.setVisibility(View.VISIBLE);
        duringRl.setVisibility(View.GONE);
        addProList.setVisibility(View.GONE);
        addProTv.setVisibility(View.GONE);
        if(data.getUpList()!=null && data.getUpList().size()!=0){
            normalPro.setVisibility(View.GONE);
            duringRl.setVisibility(View.VISIBLE);
            addProList.setVisibility(View.VISIBLE);
            addProTv.setVisibility(View.VISIBLE);
            for (ProjectUpList item: data.getUpList()) {
                AutoRelativeLayout autoRelativeLayout=new AutoRelativeLayout(mContext);
                TextView textN=new TextView(mContext);
                textN.setText(item.getPu_ugname());
                AutoRelativeLayout.LayoutParams params1 = new AutoRelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                autoRelativeLayout.addView(textN,params1);
                TextView textM=new TextView(mContext);
                textM.setText(item.getPu_number()+item.getPu_ugunit());
                AutoRelativeLayout.LayoutParams params2 = new AutoRelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT|RelativeLayout.ALIGN_PARENT_TOP,TRUE);
                autoRelativeLayout.addView(textM,params2);
                AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.topMargin= DensityUtil.getPercentWidthSize(10);
                params.bottomMargin= DensityUtil.getPercentWidthSize(10);
                addProList.addView(autoRelativeLayout,params);
            }
        }
    }

    @OnClick({R.id.mDcCallPhone,R.id.pro_during_btn})
    public void onClicks(View v){
        switch (v.getId()){
            case R.id.mDcCallPhone:
                mRootView.callPhone(mData.getPm_cuscontactno());
                break;
            case R.id.pro_during_btn:
                mRootView.doFreeitemsUtil(mData);
                break;
        }
    }

}
