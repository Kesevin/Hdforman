package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.AddFreeTermBean;
import com.dgg.hdforeman.mvp.model.been.workertypeDb;

import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class WokernameAdapter extends BaseQuickAdapter<AddFreeTermBean,BaseViewHolder> {
    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    private OnitemClickListener onitemClickListener;
    public WokernameAdapter(List<AddFreeTermBean> data) {
        super(R.layout.item_worker_name, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final AddFreeTermBean addFreeTermBean) {
        baseViewHolder.setText(R.id.tv_worker_type,addFreeTermBean.getWt_name());
        if (addFreeTermBean.isselected()){
            baseViewHolder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.gray_BFBFBF));
        }else {
            baseViewHolder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemClickListener!=null){
                    for (AddFreeTermBean item:mData){
                        if (item!=addFreeTermBean){
                            item.setIsselected(false);
                        }else {
                            item.setIsselected(true);
                        }
                    }
                    notifyDataSetChanged();
                    onitemClickListener.typeclick(addFreeTermBean);
                }
            }
        });
    }

    public interface OnitemClickListener{
        void typeclick(AddFreeTermBean workertypeDb);
    }
}
