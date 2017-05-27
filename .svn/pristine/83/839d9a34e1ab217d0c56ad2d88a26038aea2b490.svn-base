package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
import com.zhy.autolayout.AutoFrameLayout;

import butterknife.BindView;


/**
 * Created by tsang on 2016/10/20.
 */

public class ProgressItemModel extends EpoxyModelWithHolder<ProgressItemModel.ProgressItemHolder> {
    @EpoxyAttribute
    ExpandImageViewClickListener listener;
    private boolean isExpanded = false;
    @EpoxyAttribute
    ConstructProgress.PDBean.DBean dataBean;

    public void bind(final ProgressItemHolder holder) {
        //这里进行数据绑定
        if (dataBean != null) {
            holder.progressName.setText(dataBean.getPs_stname() + "-" + dataBean.getPs_period() + "天");
            String date = dataBean.getPs_startdate().substring(5, 10) + "至" + dataBean.getPs_enddate().substring(5, 10);
            holder.progressDate.setText(date);
        }
        if (isExpanded) {
            holder.expandSubItem.setBackgroundResource(R.mipmap.list_btn_zhankai);
        } else {
            holder.expandSubItem.setBackgroundResource(R.mipmap.list_btn_weizhankai);
        }
        holder.progressItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onExpandImageViewClick(id(), isExpanded, dataBean);
                if (isExpanded) {
                    isExpanded = false;
                    holder.expandSubItem.setBackgroundResource(R.mipmap.list_btn_weizhankai);
                } else {
                    isExpanded = true;
                    holder.expandSubItem.setBackgroundResource(R.mipmap.list_btn_zhankai);
                }
            }
        });
    }


    protected ProgressItemHolder createNewHolder() {
        return new ProgressItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.progress_item;
    }

    static class ProgressItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.progress_name)
        TextView progressName;
        @BindView(R.id.progress_date)
        TextView progressDate;
        @BindView(R.id.expand_sub_item)
        ImageView expandSubItem;
        @BindView(R.id.progress_item_container)
        AutoFrameLayout progressItemContainer;

    }

    public interface ExpandImageViewClickListener {
        void onExpandImageViewClick(long id, boolean isExpanded, ConstructProgress.PDBean.DBean dBean);
    }
}
