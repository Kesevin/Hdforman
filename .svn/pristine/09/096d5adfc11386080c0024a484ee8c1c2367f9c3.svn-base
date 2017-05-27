package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.QuoteListInfo;
import com.dgg.hdforeman.mvp.model.been.UppacInfo;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class ChoiceAdapter extends BaseQuickAdapter<QuoteListInfo, BaseViewHolder> {

    public List<QuoteListInfo.InlistBean> getInlistBeanList() {
        return inlistBeanList;
    }

    public void setInlistBeanList(List<QuoteListInfo.InlistBean> inlistBeanList) {
        this.inlistBeanList = inlistBeanList;
    }

    List<QuoteListInfo.InlistBean> inlistBeanList=new ArrayList<>();
    public ChoiceAdapter(List<QuoteListInfo> data) {
        super(R.layout.item_quote_partent, data);
    }


    public List<QuoteListInfo.InlistBean> getUppacInfos() {
        return uppacInfos;
    }

    public void setUppacInfos(List<QuoteListInfo.InlistBean> uppacInfos) {
        this.uppacInfos = uppacInfos;
    }

    private List<QuoteListInfo.InlistBean> uppacInfos;
    @Override
    protected void convert(BaseViewHolder baseViewHolder, final QuoteListInfo quoteListInfo) {
       TagFlowLayout tagFlowLayout=baseViewHolder.getView(R.id.tag_flow);
        baseViewHolder.setText(R.id.tv_header,quoteListInfo.getUg_typename());
        tagFlowLayout.setAdapter(new TagAdapter<QuoteListInfo.InlistBean>(quoteListInfo.getInlist()) {

            @Override
            public View getView(FlowLayout parent, int position, QuoteListInfo.InlistBean inlistBean) {
                if (inlistBean.getItemType()==1){
                    TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_quote_choice,
                            parent, false);
                    tv.setText(inlistBean.getUg_name());
                    return tv;
                }else {
                    TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_quote_choice2,
                            parent, false);
                    tv.setText(inlistBean.getUg_name());
                    return tv;
                }
            }
        });
        if (uppacInfos!=null){
            List<QuoteListInfo.InlistBean> lists=quoteListInfo.getInlist();
            HashSet set=new HashSet();
            for (QuoteListInfo.InlistBean uppacInfo:uppacInfos){
                for (int i=0;i<lists.size();i++){
                    QuoteListInfo.InlistBean inlistBean=lists.get(i);
                    if (uppacInfo.getId().equals(inlistBean.getId())){
                        inlistBeanList.add(inlistBean);
                        set.add(i);
                    }
                    tagFlowLayout.getAdapter().setSelectedList(set);
                }
            }
        }
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                List<QuoteListInfo.InlistBean> list=quoteListInfo.getInlist();
                TagView tagView= (TagView) view.getParent();
                if (tagView.isChecked()){
                    if(!inlistBeanList.contains(list.get(position)))
                    inlistBeanList.add(list.get(position));
                }else {
                    if (inlistBeanList.contains(list.get(position))){
                        inlistBeanList.remove(list.get(position));
                    }
                }
                return false;
            }
        });
    }
}
