package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;

import butterknife.BindView;


/**
 * Created by tsang on 2016/10/20.
 */

public class ProgressSubItemModel extends EpoxyModelWithHolder<ProgressSubItemModel.ProgressSubItemHolder> {
    @EpoxyAttribute
    boolean isFirst = false;
    @EpoxyAttribute
    boolean isLast = false;
    @EpoxyAttribute
    ConstructProgress.PDBean.DBean.ListBean data;
    @EpoxyAttribute
    OnSubItemFinishClickListener listener;

    @Override
    public void bind(ProgressSubItemHolder holder) {
        //绑定数据
        if (isFirst) {
            holder.upVerticalLine.setVisibility(View.INVISIBLE);
        } else {
            holder.upVerticalLine.setVisibility(View.VISIBLE);
        }
        if (isLast) {
            holder.downVerticalLine.setVisibility(View.INVISIBLE);
        } else {
            holder.downVerticalLine.setVisibility(View.VISIBLE);
        }
        if (data.getSn_state() != 0) {
            holder.progressState.setVisibility(View.INVISIBLE);
            holder.progressStateFinish.setVisibility(View.VISIBLE);
            holder.subItemFinish.setVisibility(View.GONE);
            holder.textFinish.setVisibility(View.VISIBLE);
        } else {
            holder.progressState.setVisibility(View.VISIBLE);
            holder.progressStateFinish.setVisibility(View.INVISIBLE);
            holder.subItemFinish.setVisibility(View.VISIBLE);
            holder.textFinish.setVisibility(View.INVISIBLE);
        }
        holder.progressSubName.setText(data.getSn_stname());
        holder.subItemFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener != null)
//                    listener.onSubItemFinishClick(data.getSn_id());
            }
        });
    }

    @Override
    protected ProgressSubItemHolder createNewHolder() {
        return new ProgressSubItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.progress_sub_item;
    }

    static class ProgressSubItemHolder extends BaseEpoxyHolder {
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
        @BindView(R.id.text_finish)
        TextView textFinish;
    }

    public interface OnSubItemFinishClickListener {
        void onSubItemFinishClick(int id);
    }
}
