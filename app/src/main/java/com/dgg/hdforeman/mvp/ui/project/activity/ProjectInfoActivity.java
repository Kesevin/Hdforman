package com.dgg.hdforeman.mvp.ui.project.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectInformationComponent;
import com.dgg.hdforeman.di.module.ProjectInformationModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectInformationPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.Preconditions;
import com.jess.arms.utils.UiUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by kelvin on 2016/11/7.
 */

public class ProjectInfoActivity extends BacksActivity<ProjectInformationPresenter> implements ProjectInformationContract.View {

    public static final String PROJECTINFO_DATA = "projectinfo_data";

    @BindView(R.id.tv_owner_name)
    TextView tvOwnerName;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_project_address)
    TextView tvProjectAddress;
    @BindView(R.id.tv_site_info)
    TextView tvSiteInfo;
    @BindView(R.id.btn_receive_order)
    Button btnReceiveOrder;

    private ProjectInfoResponse mData;
    private JSONObject mJSONObject;

    private WaitingDialog mWaitingDialog;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_info_activity, null, false);
    }

    @Override
    protected void initData() {
//        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(PROJECTINFO_DATA);

        initDialog();

        Serializable serializable = getIntent().getExtras().getSerializable(PROJECTINFO_DATA);

        String s = mHDApplication.getAppComponent().gson().toJson(serializable);

        try {
            mJSONObject = new JSONObject(s);
            StringBuilder builder = new StringBuilder();

            if (mJSONObject.has("pm_cusname"))
                builder.append(mJSONObject.getString("pm_cusname"));

            if (mJSONObject.has("pm_cuscontactno"))
                builder.append("   " + mJSONObject.getString("pm_cuscontactno"));


            Observable.just(builder.toString())
                    .subscribe(RxTextView.text(tvOwnerName));

            if (mJSONObject.has("pm_housesname") && mJSONObject.has("pm_roomno"))
                Observable.just(mJSONObject.getString("pm_housesname") + "  " + ((mJSONObject.getString("pm_roomno").equals("null")) ? "" : mJSONObject.getString("pm_roomno")))
                        .subscribe(RxTextView.text(tvProjectName));


            if (mJSONObject.has("pm_housesaddress"))
                Observable.just(mJSONObject.getString("pm_housesaddress"))
                        .subscribe(RxTextView.text(tvProjectAddress));


            if (mJSONObject.has("pm_acreage") && mJSONObject.has("pm_housetype"))
                Observable.just(String.format("%s/%sm²",
                        TextUtils.isEmpty(mJSONObject.getString("pm_housetype")) ||
                                mJSONObject.getString("pm_housetype").equals("null") ? "待测量" :
                                mJSONObject.getString("pm_housetype"), TextUtils.isEmpty(
                                mJSONObject.getString("pm_acreage")) ||
                                mJSONObject.getString("pm_acreage").equals("null") ? "0" : mJSONObject.getString("pm_acreage")))
                        .subscribe(RxTextView.text(tvSiteInfo));


            else
                Observable.just("待测量")
                        .subscribe(RxTextView.text(tvSiteInfo));


            if (mJSONObject.has("pm_state") && Integer.parseInt(mJSONObject.getString("pm_state")) < 5)
                Observable.just(true)
                        .subscribe(RxView.visibility(btnReceiveOrder));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        btnReceiveOrder.setVisibility(View.GONE);
        try {
            String state = mJSONObject.getString("pm_state");
            if (state != null && state.equals("0")) {
                btnReceiveOrder.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        tvOwnerName.setText(mData.getPm_cusname());
//        tvProjectName.setText(mData.getPm_housesname() + "  " + mData.getPm_roomno());
//        tvProjectAddress.setText(mData.getPm_housesaddress());
//        tvSiteInfo.setText(String.format("两室一厅/%sm²" ,mData.getPm_acreage()));

    }

    private void initDialog() {
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false);

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

    @OnClick({R.id.ib_call, R.id.btn_receive_order})
    public void onClicks(View v) {
        switch (v.getId()) {
            case R.id.ib_call:
                callPhone();
                break;
            case R.id.btn_receive_order:
                try {
                    if (mJSONObject != null && mJSONObject.has("id"))
                        mPresenter.signProject(mJSONObject.getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 给业主打电话
     */
    public void callPhone() {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ProjectInfoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                try {
                    if (mJSONObject != null && mJSONObject.has("pm_cuscontactno"))
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                                + mJSONObject.getString("pm_cuscontactno"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
//                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void showLoading() {
        mWaitingDialog.show();
    }

    @Override
    public void hideLoading() {
        mWaitingDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        Preconditions.checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {

    }

    @Override
    public void updateLayout(ProjectInfoResponse data) {

    }
}
