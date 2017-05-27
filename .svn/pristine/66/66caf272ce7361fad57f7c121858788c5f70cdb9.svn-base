package com.dgg.hdforeman.mvp.ui.project.adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.jess.arms.utils.UiUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * author:zhangjing
 * 作用:
 *
 * 测试
 * return:
 */

public class AddSpaceAdapter extends BaseQuickAdapter<SpaceBean.SpacenameEntity,BaseViewHolder>{
    public AddSpaceAdapter(List<SpaceBean.SpacenameEntity> data) {
        super(R.layout.item_space, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final SpaceBean.SpacenameEntity spaceBean) {
        if (spaceBean!=null){
            baseViewHolder.setText(R.id.tv_spacename,spaceBean.getPe_name());
            final EditText lenEt=baseViewHolder.getView(R.id.et_length);
            final EditText widEt=baseViewHolder.getView(R.id.et_width);
            CommonUtil.textWatcher(lenEt);
            CommonUtil.textWatcher(widEt);
            final LinearLayout linearLayout=baseViewHolder.getView(R.id.linear_item);
            if (spaceBean.getLen()!=0){
                lenEt.setText(spaceBean.getLen()+"");
            }else {
                lenEt.setText("");
            }
            if (spaceBean.getWid()!=0){
                widEt.setText(spaceBean.getWid()+"");
            }else {
                widEt.setText("");
            }
            baseViewHolder.setText(R.id.tv_area,spaceBean.getPe_acreage()+"m²");
            if (spaceBean.getCallItems().size()!=0){
                linearLayout.removeAllViews();
                for (SpaceBean.CalItem item:spaceBean.getCallItems()) {
                    showItem(linearLayout,item,baseViewHolder.getAdapterPosition());
                }
            }else {
                linearLayout.removeAllViews();
            }
            baseViewHolder.getView(R.id.tv_calculate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float len,width;
                    if (TextUtils.isEmpty(lenEt.getText().toString())){
                        UiUtils.SnackbarText("请输入长度");
                        return;
                    }else {
                        if (!matchDecimal(lenEt.getText().toString())){
                            return;
                        }else {
                            if (Float.valueOf(lenEt.getText().toString())<=0){
                                UiUtils.SnackbarText("输入长度必须大于0");
                                return;
                            }else {
                                len=Float.valueOf(lenEt.getText().toString());
                            }

                        }
//                        len=Float.valueOf(lenEt.getText().toString());
//                        spaceBean.setLen(len);
                    }
                    if (TextUtils.isEmpty(widEt.getText().toString())){
                        UiUtils.SnackbarText("请输入宽度");
                        return;
                    }else {
                        if (!matchDecimal(widEt.getText().toString())){
                            return;
                        }else {
                            if (Float.valueOf(widEt.getText().toString())<=0){
                                UiUtils.SnackbarText("输入宽度必须大于0");
                                return;
                            }else {
                                width=Float.valueOf(widEt.getText().toString());
                            }

                        }

//                        spaceBean.setWid(width);
                    }
                    String idstring="面积"+(linearLayout.getChildCount()+1)+":";
                    String str="("+len+"+0.18*2)*"+"("+width+"+0.18*2)";
                    changeitem(idstring,str,spaceBean,baseViewHolder.getAdapterPosition(),calculateArea(len,width));
                    setFooterview();

                }
            });
            baseViewHolder.getView(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spaceBean.setLen(0);
                    spaceBean.setWid(0);
                    for (SpaceBean.CalItem item:spaceBean.getCallItems()){
                        spaceBean.setPe_acreage(spaceBean.getPe_acreage()-item.getResult());
                    }
                    spaceBean.setPe_acreage(0);
                    spaceBean.getCallItems().clear();
                    notifyDataSetChanged();
                    setFooterview();
                }
            });
            setFooterview();
        }
    }
    public float calculateArea(float i, float j) {
        return (float) ((i+2*0.18)*(j+2*0.18));
    }

    //计算所有的面积总和
    public void setFooterview(){
        float sum=0;
        for (int i=0;i<mData.size();i++){
            sum+=mData.get(i).getPe_acreage();
        }
        View footview=((BaseQuickAdapter)this).getFooterLayout().getChildAt(0);
        TextView tv_area= (TextView) footview.findViewById(R.id.tv_area);
        TextView tv_rule= (TextView) footview.findViewById(R.id.tv_rule);
//        tv_rule.setText(UiUtils.getResources().getText(R.string.stringrule));
        tv_area.setText(CommonUtil.numformater4(sum)+"m²");

    }
    public void changeitem(String idstring,final String str, final SpaceBean.SpacenameEntity spacebean, final int pos, final float area){
        final View view=LayoutInflater.from(mContext).inflate(R.layout.item_cal,null,false);
        SpaceBean.CalItem item=new SpaceBean.CalItem();
        item.setExpression(str);
        item.setIdexpression(idstring);
        item.setResult(area);
        spacebean.getCallItems().add(item);
        spacebean.setPe_acreage(spacebean.getPe_acreage()+area);
        this.notifyItemChanged(pos);
//        this.notifyDataSetChanged();

    }
    public void showItem(final LinearLayout linearLayout, final SpaceBean.CalItem item, final int pos){
        final View view=LayoutInflater.from(mContext).inflate(R.layout.item_cal,null,false);
        TextView showtext= (TextView) view.findViewById(R.id.stext);
        TextView tvareaid= (TextView) view.findViewById(R.id.area_id);
        ImageButton ivdel= (ImageButton) view.findViewById(R.id.iv_del);
        final SpaceBean.SpacenameEntity spacenameEntity=mData.get(pos);
        showtext.setText(item.getExpression());
        tvareaid.setText(item.getIdexpression());
        ivdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spacenameEntity.getCallItems().remove(item);
                spacenameEntity.setPe_acreage(spacenameEntity.getPe_acreage()-item.getResult());
                notifyItemChanged(pos);
            }
        });
        linearLayout.addView(view);
    }
    private boolean matchDecimal(String str){
        if (!CommonUtil.isdecimal(str)){
            UiUtils.SnackbarText("请输入合法的数字格式");
            return false;
        }else if (Float.parseFloat(str)>100||Float.parseFloat(str)<0){
            UiUtils.SnackbarText("请输入0～100的");
            return false;
        }else {
            return true;
        }
    }
}
