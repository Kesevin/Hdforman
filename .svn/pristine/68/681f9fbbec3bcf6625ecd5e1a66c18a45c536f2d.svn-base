package com.dgg.hdforeman.mvp.ui.project.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectInformationComponent;
import com.dgg.hdforeman.di.module.ProjectInformationModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectInformationPresenter;
import com.dgg.hdforeman.mvp.ui.mine.activity.MessageActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.transformation.GlideCircleTrasform;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.mine.activity.MessageActivity.MESSAGE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructProgressActivity.CONSTRUCT_PROGRESS;
import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructTeamActivity.CONSTRUCT_TEAM_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.FitmentPictureActivity.FITMENT_PICTURE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectAcceptActivity.PROJECT_ACCEPT_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectListActivity.PROJECT_LIST_DATA;
import static com.jess.arms.utils.Preconditions.checkNotNull;

public class ProjectInformationActivity extends BacksActivity<ProjectInformationPresenter> implements ProjectInformationContract.View, SwipeRefreshLayout.OnRefreshListener {

    //    @BindView(R.id.recyclerView)
//    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.imgLayout)
    AutoLinearLayout mImageLayout;
    @BindView(R.id.DcOwnerNameAndAcreage)
    TextView mOwnerNameAndAcreage;//业主名称 及 房屋面积
    @BindView(R.id.DcHouseNameAndAddress)
    TextView mHouseNameAndAddress;//房屋名称 及 地址
    @BindView(R.id.DcCurstate)
    TextView mDcCurstate;//阶段状态(默认0 未完工  1施工中 2停工中 3待验收 4验收中 5待付款 6已付款(完工))
    @BindView(R.id.DcCurstage)
    TextView mDcCurstage;//正在进行中阶段名称
    @BindView(R.id.DcStartdate)
    TextView mDcStartdate;//开始时间
    @BindView(R.id.DcPlanfinishdate)
    TextView mDcPlanfinishdate;//预计结束时间
    @BindView(R.id.DcDaysBetween)
    TextView mDcDaysBetween;//相差时间 天数
    @BindView(R.id.DcPricestate)
    TextView mDcPriceState;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title)
    TextView title;

    private ProjectInfoResponse mData;
//    private WaitingDialog mWaitingDialog;

    private ImageLoader mImageLoader;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectInformationComponent
                .builder()
                .appComponent(appComponent)
                .projectInformationModule(new ProjectInformationModule(this))
                .build()
                .inject(this);
        mImageLoader = appComponent.imageLoader();
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_project_information_layout, null, false);
    }

    @Override
    protected void initData() {
        rightMenu.setText("联系业主");
        rightMenu.setVisibility(View.VISIBLE);
        title.setText("项目详情");
//        mWaitingDialog = new WaitingDialog(this);
//        mWaitingDialog.setCanceledOnTouchOutside(false);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
//        initRecyclerView();
//        mPresenter.initAdapter();
        mPresenter.getProjectInformation(getIntent());

    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getProjectInformation(getIntent());
    }

    @OnClick({R.id.DcProjectInfo, R.id.DcProjectListLayout, R.id.DcConstructionProgress, R.id.DcAliveLayout,R.id.DcMessageLayout, R.id.DcMaterialsLayout, R.id.DcSketchMapLayout, R.id.DcTeamLayout, R.id.DcProjectAcceptance, R.id.right_menu,R.id.rl_upload_image})
    public void onClick(View view) {
        if (mData == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.DcProjectInfo:
                launchProjectInfoActivity();
                break;
            case R.id.DcProjectListLayout:
                launchProjectListActivity();
                break;
            case R.id.DcAliveLayout:
                launchAliveActivity();
                break;
            case R.id.DcMessageLayout:
                launchMessageActivity();
                break;
            case R.id.DcConstructionProgress:
                launchConstructProgressActivity();
                break;
            case R.id.DcMaterialsLayout:
                launchMaterialsActivity();
                break;
            case R.id.DcSketchMapLayout:
                launchSketchMapActivity();
                break;
            case R.id.DcTeamLayout:
                launchTeamActivity();
                break;
            case R.id.DcProjectAcceptance:
                launchProjectAcceptanceActivity();
                break;
            case R.id.right_menu:
                callPhone();
                break;
            case R.id.rl_upload_image:
                if(mData.getPm_curtype()!=1){
                    showMessage("不在施工中阶段，无法上传图片");
                     return;
                }
                launchAliveActivityUpload();
                break;
        }
    }

    /**
     * 跳转到项目信息页面
     */
    private void launchProjectInfoActivity() {
        Intent intent = new Intent(this, ProjectInfoActivity.class);
        intent.putExtras(composeBundle(mData, PROJECTINFO_DATA));
        launchActivity(intent);
    }

    /**
     * 跳转到项目清单页面
     */
    private void launchProjectListActivity() {
        Intent intent = new Intent(this, ProjectListNewActivity.class);
        intent.putExtras(composeBundle(mData, PROJECT_LIST_DATA));
        launchActivity(intent);
    }

    /**
     * 跳转到施工进度页面
     */
    private void launchConstructProgressActivity() {
        Intent intent = new Intent(this, ConstructProgressActivity.class);
        Bundle bundle = new Bundle();//用bundle封装数据
        bundle.putSerializable(CONSTRUCT_PROGRESS, mData);
        bundle.putInt("from",1);
        intent.putExtras(bundle);
        launchActivity(intent);
    }

    /**
     * 跳转到直播页面
     */
    private void launchAliveActivity() {
        Intent intent = new Intent(this, FieldLiveActivity.class);
        intent.putExtras(composeBundle(mData, "live"));
        launchActivity(intent);
    }

    /**
     * 跳转到施工材料界面
     */
    private void launchMaterialsActivity() {
        Intent intent = new Intent(this, ConstructMaterialActivity.class);
        intent.putExtras(composeBundle(mData, "material"));
        launchActivity(intent);
    }

    /**
     * 跳转到装修示意图界面
     */
    private void launchSketchMapActivity() {
        Intent intent = new Intent(this, FitmentPictureActivity.class);
        intent.putExtras(composeBundle(mData, FITMENT_PICTURE));
        launchActivity(intent);
    }

    /**
     * 跳转施工消息界面
     */
    private void launchMessageActivity() {
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtras(composeBundle(mData, MESSAGE));
        intent.putExtras(composeBundle("2", "type"));
        launchActivity(intent);
    }

    /**
     * 跳转到施工团队界面
     */
    private void launchTeamActivity() {
        Intent intent = new Intent(this, ConstructTeamActivity.class);
        intent.putExtras(composeBundle(mData, CONSTRUCT_TEAM_DATA));
        launchActivity(intent);
    }

    /**
     * 跳转到工程验收界面
     */
    private void launchProjectAcceptanceActivity() {
        Intent intent = new Intent(this, ProjectAcceptActivity.class);
        intent.putExtras(composeBundle(mData, PROJECT_ACCEPT_DATA));
        launchActivity(intent);
    }

    /**
     * 跳转到直播页面
     */
    private void launchAliveActivityUpload() {
        Intent intent = new Intent(this, FieldLiveActivity.class);
        intent.putExtras(composeBundle(mData, "live"));
        intent.putExtra("isUploadPicture",true);
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

    /**
     * 给业主打电话
     */
    public void callPhone() {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ProjectInformationActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }

    /**
     * 更新UI
     *
     * @param data
     */
    @Override
    public void updateLayout(ProjectInfoResponse data) {
        this.mData = data;
        mOwnerNameAndAcreage.setText(String.format("%s  /  %sm²", data.getPm_cusname(), data.getPm_acreage()));
        mHouseNameAndAddress.setText(data.getPm_housesname() + "  " + data.getPm_housesaddress());
        mDcPriceState.setText("¥" + data.getPricestate());
        mDcCurstage.setText(data.getPm_curstage());
        if (!TextUtils.isEmpty(data.getStartdate()) && !TextUtils.isEmpty(data.getPlanfinishdate())) {
            mDcDaysBetween.setText(TimeUtil.daysBetween(data.getStartdate(), data.getPlanfinishdate()) + "天");
            mDcStartdate.setText(TimeUtil.getMonthDay(data.getStartdate()));
            mDcPlanfinishdate.setText(TimeUtil.getMonthDay(data.getPlanfinishdate()));
        }
        mDcCurstate.setText(data.getPm_curstate());
        if(TextUtils.isEmpty(data.getPm_curstate()) || data.getPm_curstate().equals("null")){
            mDcCurstate.setVisibility(View.GONE);
        }
        int tempNum = 0;
        mImageLayout.removeAllViews();
        for (int i = 0; i < data.getTeam().size(); i++) {
            if (data.getTeam().get(i).getTe() != null && data.getTeam().get(i).getTe().size() != 0 && tempNum < 4) {
                for (int j = 0; j < data.getTeam().get(i).getTe().size(); j++) {
                    if (tempNum < 4) {
                        tempNum++;
                        ImageView imageView = new ImageView(this);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setImageResource(R.mipmap.default_image);
                        mImageLoader.loadImage(this, GlideImageConfig
                                .builder()
                                .url(GlideImageLoader.getImageUrl(data.getTeam().get(i).getTe().get(j).getWk_headpic(), "100", "100"))//"http://d.hiphotos.baidu.com/image/pic/item/5882b2b7d0a20cf482c772bf73094b36acaf997f.jpg"
                                .transformation(new GlideCircleTrasform(this))
                                .errorPic(R.mipmap.default_image)
                                .imagerView(imageView)
                                .build());
                        AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(DensityUtil.getPercentWidthSize(92), DensityUtil.getPercentWidthSize(92));
                        params.rightMargin = DensityUtil.getPercentWidthSize(30);

                        mImageLayout.addView(imageView, params);
                    }
                }
            }
        }
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
//        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        super.onBackPressed();
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
//        mWaitingDialog.show();
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
//        mWaitingDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.makeText(message);
    }
}
