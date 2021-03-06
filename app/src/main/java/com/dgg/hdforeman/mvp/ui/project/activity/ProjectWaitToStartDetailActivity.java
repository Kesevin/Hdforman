package com.dgg.hdforeman.mvp.ui.project.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectWaitToStartDetailComponent;
import com.dgg.hdforeman.di.module.ProjectWaitToStartDetailModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectWaitToStartDetailContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.model.been.WorkerInfo;
import com.dgg.hdforeman.mvp.presenter.project.ProjectWaitToStartDetailPresenter;
import com.dgg.hdforeman.mvp.ui.widget.CircleImageView;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.PhotoPickerActivity;

import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructProgressActivity.CONSTRUCT_PROGRESS;
import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructTeamActivity.CONSTRUCT_TEAM_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.HDPhotoPreviewActivity.CHOOSE_MAX_COUNT;
import static com.dgg.hdforeman.mvp.ui.project.activity.HDPhotoPreviewActivity.EXTRA_SELECTED_IMAGES;
import static com.dgg.hdforeman.mvp.ui.project.activity.FitmentPictureActivity.FITMENT_PICTURE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectAcceptActivity.PROJECT_ACCEPT_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectListActivity.PROJECT_LIST_DATA;
import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.ITEM_DATA;
import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Rex on 2016/11/14.
 */

public class ProjectWaitToStartDetailActivity extends BacksActivity<ProjectWaitToStartDetailPresenter> implements ProjectWaitToStartDetailContract.View ,SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.owner_size)
    TextView ownerSize;
    @BindView(R.id.house_address)
    TextView houseAddress;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.period)
    TextView period;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.tv_project_list)
    TextView tvProjectList;
    @BindView(R.id.tv_project_total)
    TextView tvProjectTotal;
    @BindView(R.id.tv_project_total_num)
    TextView tvProjectTotalNum;
    @BindView(R.id.project_list)
    AutoRelativeLayout projectList;
    @BindView(R.id.tv_construction_progress)
    TextView tvConstructionProgress;
    @BindView(R.id.work_type)
    TextView workType;
    @BindView(R.id.construction_state)
    TextView constructionState;
    @BindView(R.id.iv_construction)
    ImageView ivConstruction;
    @BindView(R.id.construction_progress_lay)
    AutoRelativeLayout constructionProgressLay;
    @BindView(R.id.tv_alive)
    TextView tvAlive;
    @BindView(R.id.alive_lay)
    AutoLinearLayout aliveLay;
    @BindView(R.id.tv_materials)
    TextView tvMaterials;
    @BindView(R.id.materials_lay)
    AutoLinearLayout materialsLay;
    @BindView(R.id.sketch_map_lay)
    AutoLinearLayout sketchMapLay;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.iv_team)
    ImageView ivTeam;
    @BindView(R.id.ll_team)
    AutoLinearLayout llTeam;
    @BindView(R.id.team_lay)
    AutoRelativeLayout teamLay;
    @BindView(R.id.acceptance_lay)
    AutoLinearLayout acceptanceLay;
    @BindView(R.id.sv_project_detail)
    ScrollView svProjectDetail;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.upload_license_lay)
    AutoRelativeLayout uploadLicenseLay;

    private WaitingDialog mWaitingDialog;

    private ProjectInfoResponse mData;

    private String proid;

    private ImageLoader mImageLoader;

    private List<WorkerInfo> teamMember=new ArrayList<WorkerInfo>();

    public static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    public static final int REQUEST_CODE_PHOTO_PREVIEW = 2;

    public int flag;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectWaitToStartDetailComponent
                .builder()
                .appComponent(appComponent)
                .projectWaitToStartDetailModule(new ProjectWaitToStartDetailModule(this))//请将ProjectWaitToStartDetailModule()第一个首字母改为小写
                .build()
                .inject(this);
        mImageLoader = appComponent.imageLoader();
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_wait_to_start_detail_activity, null, false);
    }

    @Override
    protected void initData() {
        title.setText("项目详情");
        rightMenu.setText("联系业主");
//        rightMenu.setTextSize(DensityUtil.getPercentWidthSize(30));
        proid = ((ProjectResponse) getIntent().getExtras().get(ITEM_DATA)).getId();
        rightMenu.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false)     ;
        mPresenter.getData(proid);

    }


    @Override
    public void showLoading() {
        switch (flag){
            case 0:
                mSwipeRefreshLayout.setRefreshing(true);
                break;
            case 1:
                mWaitingDialog.show();
                break;
        }
    }

    @Override
    public void hideLoading() {

        switch (flag){
            case 0:
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case 1:
                if (mWaitingDialog != null) {
                    mWaitingDialog.dismiss();
                }
                break;
        }

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.makeText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.right_menu,R.id.project_info_lay, R.id.project_list, R.id.alive_lay, R.id.materials_lay, R.id.sketch_map_lay, R.id.team_lay, R.id.upload_license_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_menu:
                callPhone();
                break;
            case R.id.project_info_lay:
                launchProjectInfoActivity();
                break;

            case R.id.project_list://项目清单
                launchProjectListActivity();
                break;
            /*case R.id.construction_progress_lay://施工进度
                launchConstructProgressActivity();
                break;*/
            case R.id.alive_lay://工地直播
                launchAliveActivity();
                break;
            case R.id.materials_lay://施工材料
                launchMaterialsActivity();
                break;
            case R.id.sketch_map_lay://装修示意图
                launchSketchMapActivity();
                break;
            case R.id.team_lay://施工团队
                launchTeamActivity();
                break;
            /*case R.id.acceptance_lay://工程验收
                launchProjectAcceptanceActivity();
                break;*/
            case R.id.upload_license_lay:
                if(mData==null)
                    return;

                if(mData.getIspermit()==0){
                    if(mData.getIs_stuff()==0){
                        showMessage("主材不足，请联系业主添加主材");
                        return;
                    }

                    if(mData.getIs_team()==0){
                        showMessage("团队人员不足，请先添加团队成员");
                        return;
                    }
                    PermissionUtil.launchCamera(new PermissionUtil.RequestPermission() {
                        @Override
                        public void onRequestPermissionSuccess() {
                            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "HDPhotoPickerTakePhoto");
                            startActivityForResult(PhotoPickerActivity.newIntent(ProjectWaitToStartDetailActivity.this, takePhotoDir, 1, null, true), REQUEST_CODE_CHOOSE_PHOTO);
                        }
                    }, mHDApplication.getAppComponent().rxPermissions(), this, mHDApplication.getAppComponent().rxErrorHandler());

                }else{
                   flag=1;
                   mPresenter.startWork(mData.getId());
                }

                break;
        }
    }

    @Override
    public void updateLayout(ProjectInfoResponse data) {
        this.mData = data;
        teamMember.clear();
        ownerSize.setText(String.format("%1$s / %2$sm²", data.getPm_cusname(), data.getPm_acreage()));
        houseAddress.setText(data.getPm_housesname() + "  " + data.getPm_housesaddress());
        startTime.setText(TimeUtil.getMonthDay(data.getStartdate()));
        period.setText(TimeUtil.daysBetween(data.getStartdate(), data.getPlanfinishdate()) + "天");
        endTime.setText(TimeUtil.getMonthDay(data.getPlanfinishdate()));
        constructionState.setText(data.getPm_curstage());
        if ("0".equals(data.getPricestate())) {
            tvProjectTotalNum.setText("暂未报价");
        } else {
            tvProjectTotalNum.setText(" ¥ " + data.getPricestate());
        }
        if(data.getIspermit()==0){
            tvStart.setText("开工\n(上传装修许可证)");
        }else{
            tvStart.setText("开工");
        }

        for (int i = 0; i < data.getTeam().size(); i++) {
            List<WorkerInfo> te = data.getTeam().get(i).getTe();
            if (te.size() > 0) {
                for (int j = 0; j < te.size(); j++) {
                    teamMember.add(te.get(j));
                }
            }
        }
        int length = teamMember.size() > 4 ? 4 : teamMember.size();
        llTeam.removeAllViews();
        for (int k = 0; k < length; k++) {
            CircleImageView imageView = new CircleImageView(this);
            imageView.setImageResource(R.mipmap.default_image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageLoader.loadImage(this, GlideImageConfig
                    .builder()
                    .url(CommonUtil.getImageUrl(teamMember.get(k).getWk_headpic(), 80, 80))
                    .imagerView(imageView)
                    .errorPic(R.mipmap.default_image)
                    .build());
            AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(DensityUtil.getPercentWidthSize(92), DensityUtil.getPercentWidthSize(92));
            params.rightMargin = DensityUtil.getPercentWidthSize(30);
            llTeam.addView(imageView, params);
        }
    }

    /**
     * 跳转到项目清单页面
     */
    private void launchProjectListActivity() {
        Intent intent = new Intent(this, ProjectListNewActivity.class);
        intent.putExtras(composeBundle(mData, PROJECT_LIST_DATA));
        launchActivity(intent);
    }

    private void launchProjectInfoActivity() {
        Intent intent = new Intent(this, ProjectInfoActivity.class);
        intent.putExtras(composeBundle(mData, PROJECTINFO_DATA));
        launchActivity(intent);
    }
    /**
     * 跳转到施工进度页面
     */
    private void launchConstructProgressActivity() {
        Intent intent = new Intent(this, ConstructProgressActivity.class);
        intent.putExtras(composeBundle(mData, CONSTRUCT_PROGRESS));
        launchActivity(intent);
    }

    /**
     * 跳转到直播页面
     */
    private void launchAliveActivity() {
        Intent intent = new Intent(this, FieldLiveActivity.class);
        launchActivity(intent);
    }

    /**
     * 跳转到施工材料界面
     */
    private void launchMaterialsActivity() {
        Intent intent = new Intent(this, ConstructMaterialActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("material", mData);
        intent.putExtras(bundle);
        launchActivity(intent);
    }

    /**
     * 跳转到装修示意图界面
     */
    private void launchSketchMapActivity() {
        Intent intent = new Intent(this, FitmentPictureActivity.class);
        intent.putExtras(composeBundle(mData,FITMENT_PICTURE));
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
     * 跳转到施工团队界面
     */
    private void launchTeamActivity() {
        Intent intent = new Intent(this, ConstructTeamActivity.class);
        intent.putExtras(composeBundle(mData,CONSTRUCT_TEAM_DATA));
        launchActivity(intent);
    }
    /**
     * 给业主打电话
     */
    public void callPhone() {
        if (mData == null)
            return;
        if (TextUtils.isEmpty(mData.getPm_cuscontactno())) {
            Toast.makeText(mApplication, "暂无联系号码！", Toast.LENGTH_SHORT).show();
        } else {

            PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ProjectWaitToStartDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE_PHOTO:
                    Intent i= new Intent(this, HDPhotoPreviewActivity.class);
                    i.putExtra(CHOOSE_MAX_COUNT, 1 );
                    i.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, PhotoPickerActivity.getSelectedImages(data));
                    i.putExtra("project_info", mData);
                    i.putExtra("from", 102);
                    startActivity(i);
                    break;

//                case 3:
////                    mPresenter.getData(proid);
//                    break;
            }

        }
    }

    @Override
    public void onRefresh() {
        flag=0;
        mPresenter.getData(proid);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscriber(tag = "refreshMember", mode = ThreadMode.MAIN)
    public void updateTab(boolean b) {
        onRefresh();
    }

    @Subscriber(tag = "refreshTeamMember", mode = ThreadMode.MAIN)
    public void updateTab2(boolean b) {
        onRefresh();
    }

    @Subscriber(tag = "startWork", mode = ThreadMode.MAIN)
    public void updateTab3(boolean b) {
        flag=1;
        mPresenter.startWork(mData.getId());
    }
}