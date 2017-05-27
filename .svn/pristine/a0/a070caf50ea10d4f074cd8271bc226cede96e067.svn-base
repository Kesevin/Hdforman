package com.dgg.hdforeman.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.StuffInfo;
import com.jess.arms.utils.KnifeUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jess on 18/11/2016 20:53
 * Contact with jess.yan.effort@gmail.com
 */
public class ExtendView extends AutoRelativeLayout {


    @BindView(R.id.tv_stuff_top_title)
    TextView mTvStuffTopTitle;
    @BindView(R.id.iv_stuff_top_down)
    ImageView mIvStuffTopDown;
    @BindView(R.id.ll_stuff_top_extend)
    AutoLinearLayout mLlStuffTopExtend;


    private boolean isOpen;

    public ExtendView(Context context) {
        this(context, null);
    }

    public ExtendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_stuff_top, this);
        KnifeUtil.bindTarget(this, view);
    }


    public void setTitle(String title) {
        mTvStuffTopTitle.setText(title);
    }

    public void setExtendData(List<StuffInfo> lists, List<SpaceBean.SpacenameEntity> measureList) {
        for (StuffInfo data : lists) {
            ExtendChildView child = new ExtendChildView(getContext());
            child.setData(data, measureList);
            mLlStuffTopExtend.addView(child);
        }
        mLlStuffTopExtend.setVisibility(View.GONE);
    }

    /**
     * 将用户选择的升级包信息保存
     *
     * @param infos
     */
    public void addUpgradeInfo(Map<StuffInfo, List<SpaceBean.SpacenameEntity>> infos) {
        if (mLlStuffTopExtend == null)
            return;
        for (int i = 0; i < mLlStuffTopExtend.getChildCount(); i++) {
            ExtendChildView child = (ExtendChildView) mLlStuffTopExtend.getChildAt(i);
            child.addUpgradeInfo(infos);
        }
    }


    /**
     * 根据map加载子view
     *
     * @param infos
     */
    public void loadUpgradeInfo(Map<StuffInfo, List<SpaceBean.SpacenameEntity>> infos) {
        if (mLlStuffTopExtend == null)
            return;
        for (int i = 0; i < mLlStuffTopExtend.getChildCount(); i++) {
            ExtendChildView child = (ExtendChildView) mLlStuffTopExtend.getChildAt(i);
            child.loadUpgradeInfo(infos);
        }
    }


    @Nullable
    @OnClick({R.id.rl_stuff_top_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_stuff_top_title:
                this.isOpen = !isOpen;
                extend(isOpen);
                break;
        }
    }

    /**
     * 受否展开
     *
     * @param isExtend
     */
    public void extend(boolean isExtend) {
        mIvStuffTopDown.setImageResource(isExtend ? R.mipmap.list_btn_zhankai : R.mipmap.list_btn_weizhankai);
        mLlStuffTopExtend.setVisibility(isExtend ? View.VISIBLE : View.GONE);
    }


}
