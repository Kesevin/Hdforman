package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectUpList;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import static android.widget.RelativeLayout.TRUE;
import static com.dgg.hdforeman.R.id.mDcOwnerState;


/**
 * Created by kelvin on 2016/11/2.
 */

public class DuringConstructAdapter extends BaseQuickAdapter<ProjectResponse,BaseViewHolder> {


    private DuringConstructAdapter.OnOptionclickListener listener;

    public DuringConstructAdapter(List<ProjectResponse> projectResponse) {
        super(R.layout.layout_during_accept, projectResponse);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final ProjectResponse data) {

        baseViewHolder.setText(R.id.mDcName, data.getPm_housesname())
                .setText(R.id.mDcLocation,data.getPm_roomno())
                .setText(R.id.mDcOwnerName,data.getPm_cusname())
                .setText(R.id.mDcAdress,data.getPm_housesaddress())
                .setText(R.id.mDcProjectState,data.getStage());


        switch (data.getPm_state()){
            case "0"://未签约
                baseViewHolder.setText(R.id.mDcProjectState,"待接单")
                        .setVisible(mDcOwnerState, false);
                break;
            case "1"://待开工(已签约)
                baseViewHolder.setText(R.id.mDcProjectState,"预计开工时间："+(TextUtils.isEmpty(data.getPm_startdate())?"无": TimeUtil.keepTimeYMD(data.getPm_startdate(),TimeUtil.YEAR_MONTH_DAY)))
                        .setVisible(mDcOwnerState, false);
                break;
            case "2"://施工中
                baseViewHolder.setText(R.id.mDcProjectState,data.getStage())
                        .setText(R.id.mDcOwnerState,data.getStageState())
                        .setVisible(R.id.mDcOwnerState, !(TextUtils.isEmpty(data.getStageState()) || data.getStageState().equals("null")));
                break;
            case "3"://停工(暂停)
//                baseViewHolder.setText(R.id.mDcProjectState,"已停工")
//                        .setVisible(mDcOwnerState, false);
                baseViewHolder.setText(R.id.mDcProjectState,data.getStage())
                        .setText(R.id.mDcOwnerState,"已停工")
                        .setVisible(R.id.mDcOwnerState, !(TextUtils.isEmpty(data.getStageState()) || data.getStageState().equals("null")));
                break;
            case "4"://已完工
                baseViewHolder.setText(R.id.mDcProjectState,"完工时间："+ (TextUtils.isEmpty(data.getPm_finishdate())?"无": TimeUtil.keepTimeYMD(data.getPm_finishdate(),TimeUtil.YEAR_MONTH_DAY)))
                        .setVisible(mDcOwnerState, false);
                break;
            case "5"://未签约工长已接手
                baseViewHolder.setText(R.id.mDcProjectState,"待报价")
                        .setVisible(mDcOwnerState, false);
                break;
            case "6"://工长已报价待签约)
                baseViewHolder.setText(mDcOwnerState,"待报价")
                        .setVisible(R.id.mDcProjectState, false);
                if(!data.getPm_quote().equals("0.0")){
                    baseViewHolder.setText(R.id.mDcProjectState,"报价金额："+data.getPm_quote());
                    baseViewHolder.setText(R.id.mDcProjectDate,"报价时间："+data.getPm_quotetime());
                    baseViewHolder  .setVisible(mDcOwnerState, false);
                    baseViewHolder  .setVisible(R.id.mDcProjectDate, true);
                    baseViewHolder  .setVisible(R.id.mDcProjectState, true);
                }
                break;
        }
        AutoLinearLayout addProList=baseViewHolder.getView(R.id.add_pro_list);
        addProList.removeAllViews();
        baseViewHolder.setVisible(R.id.normal_pro, true)
                .setVisible(R.id.during_rl, false)
                .setVisible(R.id.add_pro_tv, false)
                .setVisible(R.id.add_pro_list, false);
        if(data.getUpList()!=null && data.getUpList().size()!=0){
            baseViewHolder.setVisible(R.id.normal_pro, false)
                    .setVisible(R.id.during_rl, true)
                    .setVisible(R.id.add_pro_tv, true)
                    .setVisible(R.id.add_pro_list, true);
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
        baseViewHolder .addOnClickListener(R.id.pro_during_btn)
                .addOnClickListener(R.id.mDcCallPhone);
//
//        final TextView confirmTv= baseViewHolder.getView(R.id.pro_during_btn);
//        confirmTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null){
//                    listener.confirm(baseViewHolder.getLayoutPosition(),data);
//                }
//            }
//        });
//        ImageButton callPhone=baseViewHolder.getView(R.id.mDcCallPhone);
//        callPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null){
//                    listener.callPhone(baseViewHolder.getLayoutPosition(),data);
//                }
//            }
//        });
    }
    public void setListener(DuringConstructAdapter.OnOptionclickListener listener) {
        this.listener = listener;
    }

    public interface  OnOptionclickListener{
        void callPhone(int pos,ProjectResponse projectResponse);
        void confirm(int pos,ProjectResponse projectResponse);
    }
}
