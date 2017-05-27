package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.AddFreeTermBean;

import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class ProductAdapter extends BaseQuickAdapter<AddFreeTermBean,BaseViewHolder>{
    public ProductClickListener getProductClickListener() {
        return productClickListener;
    }

    public void setProductClickListener(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
    }

    ProductClickListener productClickListener;
    public ProductAdapter(List<AddFreeTermBean> data) {
        super(R.layout.item_worker_name, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final AddFreeTermBean addFreeTermBean) {
        baseViewHolder.setText(R.id.tv_worker_type,addFreeTermBean.getUg_name());
        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productClickListener!=null){
                    productClickListener.pclick(addFreeTermBean);
                }
            }
        });
    }
    public interface ProductClickListener{
        void pclick(AddFreeTermBean addFreeTermBean);
    }
}
