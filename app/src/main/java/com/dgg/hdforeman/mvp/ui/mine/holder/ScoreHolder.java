package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.app.view.StarBar;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/1.
 */

public class ScoreHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.score_item_project_name)
    TextView scoreItemProjectName;
    @BindView(R.id.score_item_project_address)
    TextView scoreItemProjectAddress;
    @BindView(R.id.score_item_project_date)
    TextView scoreItemProjectDate;
    @BindView(R.id.score_item_ratingBar_harmony)
    StarBar scoreItemRatingBarHarmony;
    @BindView(R.id.score_item_ratingBar_service)
    StarBar scoreItemRatingBarService;
    @BindView(R.id.score_item_ratingBar_date)
    StarBar scoreItemRatingBarDate;
    @BindView(R.id.score_item_ratingBar_skill)
    StarBar scoreItemRatingBarSkill;
    @BindView(R.id.score_item_score)
    TextView scoreItemScore;
    public ScoreHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        AutoUtils.auto(itemView);
    }

    public void bindView(ScoreBean scoreBean){
        scoreItemProjectName.setText(scoreBean.getPm_housesname());
        scoreItemProjectAddress.setText(scoreBean.getPm_housesaddress());
        scoreItemProjectDate.setText(TimeUtil.keepTimeYMD(scoreBean.getPm_realfinishdate(),TimeUtil.YEAR_MONTH_DAY));
        scoreItemRatingBarHarmony.setStarMark(scoreBean.getPm_xtnl());
        scoreItemRatingBarService.setStarMark(scoreBean.getPm_fwtd());
        scoreItemRatingBarDate.setStarMark(scoreBean.getPm_gqgk());
        scoreItemRatingBarSkill.setStarMark(scoreBean.getPm_ztjn());
        scoreItemScore.setText(String.format("%.1f",(scoreBean.getAllScore())));
        if(scoreBean.getAllScore()==0.0){
            scoreItemScore.setText("暂无\n评分");
            scoreItemScore.setTextSize(AutoUtils.getPercentWidthSize(18));
        }else{
            scoreItemScore.setTextSize(AutoUtils.getPercentWidthSize(30));
        }
    }
}
