package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.mine.DataContract;
import com.dgg.hdforeman.mvp.model.been.MineData;
import com.dgg.hdforeman.mvp.presenter.mine.MineDataPresenter;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;
import com.zhy.autolayout.AutoFrameLayout;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/20.
 */

public class DataActivity extends BaseActivity implements DataContract.View{
    @BindView(R.id.toolbar_back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more_btn)
    ImageButton moreBtn;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title_lay)
    AutoFrameLayout titleLay;
//    @BindView(R.id.data_workType)
//    TextView dataWorkType;
    @BindView(R.id.data_fitment)
    TextView dataFitment;
    @BindView(R.id.data_address)
    TextView dataAddress;
    @BindView(R.id.data_cardNumber)
    TextView dataCardNumber;
    @BindView(R.id.data_phoneNumber)
    TextView dataPhoneNumber;
    @BindView(R.id.data_bankNumber)
    TextView dataBankNumber;

    private MineDataPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_data_activity);
    }

    @Override
    protected void initView() {
        title.setText("我的资料");
        presenter=new MineDataPresenter(this,(HDApplication) getApplication());
        presenter.getMineData();
    }

    @OnClick(R.id.toolbar_back)
    public void onClick() {
        finish();
    }

    @Override
    public void bindDataToView(MineData mineData) {
//        dataWorkType.setText(mineData.getWk_worktype()+"");
        dataFitment.setText(mineData.getWk_workage()+"年");
        dataAddress.setText(mineData.getWk_homeaddress());
        dataCardNumber.setText(mineData.getWk_identityno());
        dataPhoneNumber.setText(mineData.getWk_contactno());
        dataBankNumber.setText(mineData.getWk_bankno());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

}
