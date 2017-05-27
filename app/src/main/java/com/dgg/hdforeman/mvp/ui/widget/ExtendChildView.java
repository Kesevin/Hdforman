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
import com.jess.arms.utils.UiUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.R.id.tv_stuff_child_size;

/**
 * Created by jess on 18/11/2016 20:53
 * Contact with jess.yan.effort@gmail.com
 */
public class ExtendChildView extends AutoRelativeLayout {


    @BindView(R.id.tv_stuff_child_name)
    TextView mTvStuffChildName;
    @BindView(tv_stuff_child_size)
    TextView mTvStuffChildSize;
    @BindView(R.id.iv_stuff_child_add)
    ImageView mIvStuffChildAdd;
    @BindView(R.id.ll_stuff_child_extend)
    AutoLinearLayout mLlStuffChildExtend;
    @BindView(R.id.rl_stuff_child_title)
    AutoRelativeLayout mRlStuffChildTitle;
    @BindView(R.id.rl_child_root)
    AutoRelativeLayout mRlChildRoot;

    private StuffInfo mData;
    private QuoteInputDialog mDialog;
    private boolean isChange;

    private List<SpaceBean.SpacenameEntity> mChildList = new ArrayList<>();//存储所有添加的子view信息
    private float mTotalAcreage;
    private ExtendChildView mParent;

    public ExtendChildView(Context context) {
        this(context, null);
    }

    public ExtendChildView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendChildView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_stuff_child, this);
        KnifeUtil.bindTarget(this, view);
    }

    public void setData(StuffInfo data, List<SpaceBean.SpacenameEntity> measureList) {
        this.mData = data;
        setName(data.getName());
        mDialog = new QuoteInputDialog(getContext(), this, measureList);
    }


    private void setName(String name) {
        mTvStuffChildName.setText(name);
    }

    /**
     * 用来存储用户输入的测量信息
     *
     * @param entity
     */
    public void setEntity(SpaceBean.SpacenameEntity entity) {
        mTvStuffChildName.setText("    " + entity.getPe_name());
        setAcreage(entity.getInput_acreage());
    }


    /**
     * 添加子view
     */
    public void addChildView(SpaceBean.SpacenameEntity entity) {
        if (mChildList.contains(entity)) {
            UiUtils.SnackbarText("不能重复添加");
            return;
        }
        entity.setInput_acreage(entity.getPre_acreage());//有可能重复添加,所以确认不是重复添加在给input赋值
        mTotalAcreage += entity.getInput_acreage();//统计子view面积的总合
        setAcreage(mTotalAcreage);

        ExtendChildView extendChildView = new ExtendChildView(getContext());
        extendChildView.changeState(this);
        extendChildView.setEntity(entity);
        mLlStuffChildExtend.addView(extendChildView);
        mChildList.add(entity);
    }


    /**
     * 将用户选择的升级包信息保存
     *
     * @param infos
     */
    public void addUpgradeInfo(Map<StuffInfo, List<SpaceBean.SpacenameEntity>> infos) {
        if (mData != null && mChildList.size() > 0) {
            infos.put(mData, mChildList);
        }
    }


    /**
     * 根据map加载子view
     */
    public void loadUpgradeInfo(Map<StuffInfo, List<SpaceBean.SpacenameEntity>> infos) {
        if (infos.containsKey(mData)) {
            List<SpaceBean.SpacenameEntity> list = infos.get(mData);
            for (SpaceBean.SpacenameEntity entity : list) {
                addChildView(entity);
            }
        }
    }

    /**
     * 移除子view
     */
    public void removeChild(View view) {
        int index = mLlStuffChildExtend.indexOfChild(view);
        if (index == -1) {//说明此view并没有加入进来
            return;
        }
        mTotalAcreage -= mChildList.get(index).getInput_acreage();
        setAcreage(mTotalAcreage);
        mLlStuffChildExtend.removeView(view);
        mChildList.remove(index);
        if (mChildList.size() == 0) {
            mTvStuffChildSize.setText("0㎡");
        }
    }

    /**
     * 设置面积
     *
     * @param acreage
     */
    public void setAcreage(float acreage) {
        mTvStuffChildSize.setText(String.format("%.2f㎡", acreage));
    }

    /**
     * 改变view状态
     *
     * @param extendChildView
     */
    public void changeState(ExtendChildView extendChildView) {
        this.mParent = extendChildView;
        mIvStuffChildAdd.setImageResource(R.mipmap.list_delete);
        mRlChildRoot.setBackgroundColor(UiUtils.getColor(R.color.color_gray));
        this.isChange = true;
    }

    @Nullable
    @OnClick({R.id.iv_stuff_child_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_stuff_child_add:
                if (isChange)
                    mParent.removeChild(ExtendChildView.this);
                else
                    mDialog.show();
                break;
        }
    }


}
