package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerAddFreeTermComponent;
import com.dgg.hdforeman.di.module.AddFreeTermModule;
import com.dgg.hdforeman.mvp.contract.project.AddFreeTermContract;
import com.dgg.hdforeman.mvp.model.been.AddFreeTermBean;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.presenter.project.AddFreeTermPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProductAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.WokernameAdapter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.CustomPopupWindow;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFreeTermActivity extends BacksActivity<AddFreeTermPresenter> implements AddFreeTermContract.View {


    @BindView(R.id.rv_categrory)
    RecyclerView rvCategrory;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.toolbar_right)
    TextView toolbar_right;
    private String id;
    int slectedpos,shopnum;
    CustomPopupWindow mPopupWindow;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAddFreeTermComponent.builder().appComponent(appComponent)
                .addFreeTermModule(new AddFreeTermModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return getLayoutInflater().inflate(R.layout.activity_add_free_term, null, false);
//        return null;
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra(GlobalConfig.EXTRA_PID);
        shopnum=getIntent().getIntExtra(GlobalConfig.EXTRA_NUM,0);
        initpopwindow();
        setnumofshop();
        if (id == null) finish();
        toolbar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killMyself();
            }
        });
        mPresenter.initdata();
        mPresenter.setPid(id);
    }

    @Override
    public void initrecylerview(WokernameAdapter adapter, ProductAdapter padapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        rvCategrory.setLayoutManager(linearLayoutManager2);
        rvProduct.setLayoutManager(linearLayoutManager);
        rvProduct.setAdapter(padapter);
        rvCategrory.setAdapter(adapter);
    }

    @Override
    public void showpopwindow(final List<SpaceBean.SpacenameEntity> spacenameEntityList, final AddFreeTermBean freeTermBean) {
        View popview =mPopupWindow.getContentView();
        final TagFlowLayout flowLayout = (TagFlowLayout) popview.findViewById(R.id.tag_flow);
        TextView tv_name = (TextView) popview.findViewById(R.id.tv_pname);
        TextView tv_price = (TextView) popview.findViewById(R.id.tv_price);
        TextView tv_content = (TextView) popview.findViewById(R.id.tv_content);
        TextView tv_submit = (TextView) popview.findViewById(R.id.tv_submit);
        final EditText et_num = (EditText) popview.findViewById(R.id.et_num);
        CommonUtil.textWatcher(et_num);
        tv_name.setText(freeTermBean.getUg_name());
        tv_price.setText("￥" + freeTermBean.getUg_price() + "/"+freeTermBean.getUg_unit());
        tv_content.setText(freeTermBean.getUg_content());
        flowLayout.setAdapter(new TagAdapter<SpaceBean.SpacenameEntity>(spacenameEntityList) {

            @Override
            public View getView(FlowLayout parent, int position, SpaceBean.SpacenameEntity spacenameEntity) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_tag_name,
                        flowLayout, false);
                tv.setText(spacenameEntity.getPe_name());
                return tv;
            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                slectedpos = position;
                return false;
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slectedpos == -1) {
                    UiUtils.makeText("请先选择空间");
//                    showMessage();
                    return;
                }
                //增加自由项
                if (et_num.getText().toString() == null||et_num.getText().toString().length()==0) {
                    UiUtils.makeText("请先输入数量");
//                    showMessage();
                    return;
                } else {
                    if(Float.parseFloat(et_num.getText().toString())<=0){
                        UiUtils.makeText("输入数量必须大于0");
                        return;
                    }else {
                        mPresenter.addFreeTerm2server(freeTermBean, id, spacenameEntityList.get(slectedpos), et_num.getText().toString());
                        shopnum++;
                        setnumofshop();
                    }

                }
            }
        });
        if (!mPopupWindow.isShowing())
            slectedpos = -1;
        mPopupWindow.showAtLocation(rvCategrory, Gravity.BOTTOM, 0, 0);
        setbackgroudalpha(0.7f);
//
    }

    @Override
    public void hidepopwindow() {
        mPopupWindow.dismiss();
        setbackgroudalpha(1f);
    }

    public static void starActivity(Context context,String pid,int num) {
        Intent intent = new Intent(context, AddFreeTermActivity.class);
        intent.putExtra(GlobalConfig.EXTRA_PID, pid);
        intent.putExtra(GlobalConfig.EXTRA_NUM, num);
        context.startActivity(intent);

    }

    private void initpopwindow() {
        if (mPopupWindow == null) {
            View popview = getLayoutInflater().inflate(R.layout.pop_addfreeterm, null, false);
            mPopupWindow = CustomPopupWindow.builder().contentView(popview)
                    .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                        @Override
                        public void initPopupView(View contentView) {

                        }
                    }).isWrap(false)
                    .animationStyle(R.style.AnimBottom)
                    .isOutsideTouch(false)
                    .parentView(rvCategrory).build();
            mPopupWindow.setHeight(AutoLinearLayout.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    setbackgroudalpha(1f);
                }
            });
            mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

    }
    private void setbackgroudalpha(float f){
//        mPopupWindow.show();
        WindowManager.LayoutParams params=this.getWindow().getAttributes();
        params.alpha=f;
        this.getWindow().setAttributes(params);
    }
    private void setnumofshop(){
        toolbar_right.setText(shopnum+"");
    }
}
