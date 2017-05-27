package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;

import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class FreeTermAdapter extends BaseQuickAdapter<FreeTermlistBean,BaseViewHolder>{
    public OnOptionclickListener getListener() {
        return listener;
    }

    public void setListener(OnOptionclickListener listener) {
        this.listener = listener;
    }

    private OnOptionclickListener listener;
    public FreeTermAdapter(List<FreeTermlistBean> data) {
        super(R.layout.item_freeterm, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final FreeTermlistBean freeTermlistBean) {
        final int pos=baseViewHolder.getLayoutPosition() - this.getHeaderLayoutCount();
        final TextView tv_edit= baseViewHolder.getView(R.id.tv_edit);
        final EditText editText=baseViewHolder.getView(R.id.et_num);
        CommonUtil.textWatcher(editText);
        baseViewHolder.setText(R.id.tv_fname,freeTermlistBean.getUgworktypename());
        baseViewHolder.setText(R.id.pu_puugname_edit,freeTermlistBean.getPuugname());
        baseViewHolder.setText(R.id.pu_puugname,freeTermlistBean.getPuugname());
        baseViewHolder.setText(R.id.tv_pu_price,"¥ "+freeTermlistBean.getPuugprice()+"/"+freeTermlistBean.getPuugunit());
        baseViewHolder.setText(R.id.tv_num,"x"+freeTermlistBean.getPunumber());
        editText.setText(freeTermlistBean.getPunumber()+"");
        //删除
        baseViewHolder.getView(R.id.tv_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.del(pos,freeTermlistBean);
                }
            }
        });
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_edit.getText().equals("编辑")){
                    //打开编辑模式
                    tv_edit.setText("完成");
                    baseViewHolder.getView(R.id.rl_content).setVisibility(View.INVISIBLE);
                    baseViewHolder.getView(R.id.rl_content_edit).setVisibility(View.VISIBLE);

                }else {
                    tv_edit.setText("编辑");
                    baseViewHolder.getView(R.id.rl_content).setVisibility(View.VISIBLE);
                    baseViewHolder.getView(R.id.rl_content_edit).setVisibility(View.INVISIBLE);
                    if (listener!=null){
                        mData.get(pos).setPunumber(editText.getText().toString());
                        baseViewHolder.getView(R.id.rl_content).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_content_edit).setVisibility(View.INVISIBLE);
                        listener.update(pos,freeTermlistBean);
                    }
                }
            }
        });
    }
    public interface  OnOptionclickListener{
        void del(int pos,FreeTermlistBean freeTermlistBean);
        void update(int pos,FreeTermlistBean freeTermlistBean);
    }
}
