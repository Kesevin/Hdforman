package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.EventBusTag;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectInformationComponent;
import com.dgg.hdforeman.di.module.ProjectInformationModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectInformationPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.CharactorHandler;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.Preconditions;
import com.jess.arms.utils.UiUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import org.simple.eventbus.Subscriber;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Func1;

import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;
import static com.jess.arms.utils.CharactorHandler.excludeEmpty;

/**
 * Created by jess on 15/11/2016 16:04
 * Contact with jess.yan.effort@gmail.com
 */

public class NotSignInfoActivity extends BacksActivity<ProjectInformationPresenter> implements ProjectInformationContract.View {

    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.DcOwnerNameAndAcreage)
    TextView mDcOwnerNameAndAcreage;
    @BindView(R.id.DcHouseNameAndAddress)
    TextView mDcHouseNameAndAddress;
    @BindView(R.id.DcProjectInfo)
    AutoRelativeLayout mDcProjectInfo;
    @BindView(R.id.tv_not_sign_size_title)
    TextView mTvNotSignSizeTitle;
    @BindView(R.id.tv_not_sign_size_total)
    TextView mTvNotSignSizeTotal;
    @BindView(R.id.tv_not_sign_size)
    TextView mTvNotSignSize;
    @BindView(R.id.tv_not_sign_measure)
    AutoRelativeLayout mTvNotSignMeasure;
    @BindView(R.id.tv_not_sign_price_title)
    TextView mTvNotSignPriceTitle;
    @BindView(R.id.tv_not_sign_price_totle)
    TextView mTvNotSignPriceTotle;
    @BindView(R.id.tv_not_sign_price_money)
    TextView mTvNotSignPriceMoney;
    @BindView(R.id.tv_not_sign_price)
    AutoRelativeLayout mTvNotSignPrice;

    private ProjectInfoResponse mResponse;


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getProjectInformation(getIntent());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectInformationComponent
                .builder()
                .appComponent(appComponent)
                .projectInformationModule(new ProjectInformationModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_not_sign_info, null, false);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {

    }

    @Override
    public void updateLayout(final ProjectInfoResponse data) {
        this.mResponse = data;
        //显示界面信息


        Observable.just(data.getPm_cusname())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        if (data.getMeasure().equals("1")) {
                            return s + " / " + data.getPm_acreage() + "㎡";
                        }

                        return s;
                    }
                }).subscribe(RxTextView.text(mDcOwnerNameAndAcreage));

        Observable.just(excludeEmpty(data.getPm_housesname()) + "  " + excludeEmpty(data.getPm_housesaddress()))
                .subscribe(RxTextView.text(mDcHouseNameAndAddress));


        Observable.just(data.getMeasure())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.equals("1");//1为已测量
                    }
                }).map(new Func1<Boolean, String>() {
            @Override
            public String call(Boolean b) {
                if (b) {
                    return excludeEmpty(data.getPm_acreage()) + "㎡";
                }
                return "待测量";
            }
        }).subscribe(RxTextView.text(mTvNotSignSize));


        Observable.just(data.getPricestate())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.equals("0.0");//0为待报价
                    }
                }).map(new Func1<Boolean, String>() {
            @Override
            public String call(Boolean b) {
                if (b)
                    return "待报价";
                return excludeEmpty(data.getPricestate());
            }
        }).subscribe(RxTextView.text(mTvNotSignPriceMoney));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        Preconditions.checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        Preconditions.checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @OnClick({R.id.DcProjectInfo, R.id.tv_not_sign_price, R.id.tv_not_sign_measure, R.id.tv_not_sign_contact})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.DcProjectInfo:
                if (mResponse != null)
                    launchWithData(ProjectInfoActivity.class);
                break;
            case R.id.tv_not_sign_measure:
                //测量
                mPresenter.startActivity(NotSignInfoActivity.this);
                break;
            case R.id.tv_not_sign_price:
                if (mResponse != null && mResponse.getMeasure().equals("1"))
                    if (mResponse.getPricestate().equals("0"))
                        launchWithData(ProjectQuoteActivity.class);
                    else
                        launchWithData(BudgetSheetActivity.class);
                else
                    showMessage("工地测量后才可以跳转到项目报价页面");
                break;
            case R.id.tv_not_sign_contact://联系业主
                contactUser();//联系业主
                break;
        }
    }

    private void contactUser() {
        if (mResponse == null || TextUtils.isEmpty(mResponse.getPm_cuscontactno())) {
            showMessage("电话号码不能为空");
        }else if(!CharactorHandler.isPhone(mResponse.getPm_cuscontactno())){
            showMessage("电话号码格式不正确");
        }else{
            PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
                                       @Override
                                       public void onRequestPermissionSuccess() {
                                           startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                                                   + mResponse.getPm_cuscontactno())));
                                       }
                                   }, mHDApplication.getAppComponent().rxPermissions()
                    , this, mHDApplication.getAppComponent().rxErrorHandler());
        }
    }

    /**
     * 附带数据跳转到相应页面
     */
    private void launchWithData(Class<?> cls) {
        Intent intent = new Intent(mApplication, cls);
        intent.putExtras(composeBundle(mResponse, PROJECTINFO_DATA));
        launchActivity(intent);
    }


    /**
     * 填充数据到bundle
     *
     * @param data
     * @param type
     * @return
     */
    protected Bundle composeBundle(Serializable data, String type) {
        Bundle bundle = new Bundle();//用bundle封装数据
        bundle.putSerializable(type, data);
        return bundle;
    }

    //开启eventbus功能
    @Override
    protected boolean useEventBus() {
        return true;
    }

    //测量发生改变时接收改变不去请求网络
    @Subscriber(tag = EventBusTag.MESSURE_CHANGE)
    public void messurechange(String messure) {
        if (mResponse != null) {
            mResponse.setPm_acreage(messure);
            //表示已测量
            mResponse.setMeasure("1");
            updateLayout(mResponse);
        }
    }


}
