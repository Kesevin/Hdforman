package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.app.view.StarBar;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectInformationComponent;
import com.dgg.hdforeman.di.module.ProjectInformationModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectInformationPresenter;
import com.dgg.hdforeman.mvp.ui.mine.activity.MessageActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.transformation.GlideCircleTrasform;
import com.jess.arms.utils.Preconditions;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.mine.activity.MessageActivity.MESSAGE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructProgressActivity.CONSTRUCT_PROGRESS;
import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructTeamActivity.CONSTRUCT_TEAM_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.FitmentPictureActivity.FITMENT_PICTURE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectAcceptActivity.PROJECT_ACCEPT_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectListActivity.PROJECT_LIST_DATA;


public class ProjectCompletedDetailActivity extends BacksActivity<ProjectInformationPresenter> implements ProjectInformationContract.View,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more_btn)
    ImageButton moreBtn;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.search_editText)
    EditText searchEditText;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.search_layout)
    AutoRelativeLayout searchLayout;
    @BindView(R.id.title_lay)
    AutoFrameLayout titleLay;
    @BindView(R.id.coordination_score)
    StarBar coordinationScore;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.service_score)
    StarBar serviceScore;
    @BindView(R.id.date_score)
    StarBar dateScore;
    @BindView(R.id.skill_score)
    StarBar skillScore;
    @BindView(R.id.score_item_score)
    TextView scoreItemScore;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.ll_start_time)
    AutoLinearLayout llStartTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.rl_time)
    AutoRelativeLayout rlTime;
    @BindView(R.id.tv_project_list)
    TextView tvProjectList;
    @BindView(R.id.tv_project_total)
    TextView tvProjectTotal;
    @BindView(R.id.engineering_cost)
    TextView engineeringCost;
    @BindView(R.id.project_list_lay)
    AutoRelativeLayout projectListLay;
    @BindView(R.id.tv_alive)
    TextView tvAlive;
    @BindView(R.id.project_alive_lay)
    AutoLinearLayout projectAliveLay;
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
    @BindView(R.id.team_lay)
    AutoRelativeLayout teamLay;
    @BindView(R.id.acceptance_lay)
    AutoLinearLayout acceptanceLay;
    @BindView(R.id.ll_intermediate_accepted)
    AutoLinearLayout llIntermediateAccepted;
    @BindView(R.id.detail_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.imgLayout)
    AutoLinearLayout mImageLayout;

    private ProjectInfoResponse mData;
    private ImageLoader mImageLoader;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_completed_detail_activity, null);
    }

    @Override
    protected void initData() {
        title.setText("项目详情");
        rightMenu.setText("联系业主");
//        initRecyclerView();
        initSwipeRefreshLayout();
        mPresenter.getProjectInformation(getIntent());
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectInformationComponent
                .builder()
                .appComponent(appComponent)
                .projectInformationModule(new ProjectInformationModule(this))
                .build()
                .inject(this);
        mImageLoader=appComponent.imageLoader();
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
//        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void updateLayout(ProjectInfoResponse data) {
        this.mData = data;
        coordinationScore.setStarMark(data.getSocer().getPm_xtnl());
        serviceScore.setStarMark(data.getSocer().getPm_fwtd());
        dateScore.setStarMark(data.getSocer().getPm_gqgk());
        skillScore.setStarMark(data.getSocer().getPm_ztjn());
        startTime.setText(TimeUtil.keepTimeYMD(data.getStartdate(),TimeUtil.YEAR_MONTH_DAY));
        endTime.setText(TimeUtil.keepTimeYMD(data.getPlanfinishdate(),TimeUtil.YEAR_MONTH_DAY));
        engineeringCost.setText("¥"+data.getPricestate());
        scoreItemScore.setText(data.getSocer().getAverage());
        if(data.getSocer().getAverage().equals("0.0")){
            scoreItemScore.setText("暂无\n评分");
            scoreItemScore.setTextSize(AutoUtils.getPercentWidthSize(18));
        }
        int tempNum=0;
        mImageLayout.removeAllViews();
        for (int i = 0; i < data.getTeam().size(); i++) {
            if (data.getTeam().get(i).getTe() != null && data.getTeam().get(i).getTe().size() != 0 && tempNum<4) {
                for (int j = 0; j < data.getTeam().get(i).getTe().size(); j++) {
                    if(tempNum<4){
                        tempNum++;
                        ImageView imageView = new ImageView(this);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setImageResource(R.mipmap.default_image);
                        mImageLoader.loadImage(this, GlideImageConfig
                                .builder()
                                .url(GlideImageLoader.getImageUrl(data.getTeam().get(i).getTe().get(j).getWk_headpic(),"100","100"))//"http://d.hiphotos.baidu.com/image/pic/item/5882b2b7d0a20cf482c772bf73094b36acaf997f.jpg"
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
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        Preconditions.checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @OnClick({R.id.rl_time,R.id.project_list_lay,R.id.project_alive_lay,R.id.DcMessageLayout, R.id.materials_lay, R.id.sketch_map_lay, R.id.team_lay, R.id.acceptance_lay})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rl_time://进度
                launchProgressActivityActivity();
                break;
            case R.id.project_list_lay://项目清单
                launchProjectListActivity();
                break;
            case R.id.project_alive_lay://工地直播
                launchAliveActivity();
                break;
            case R.id.DcMessageLayout://施工消息
                launchMessageActivity();
                break;
            case R.id.materials_lay://施工材料
                launchMaterialsActivity();
                break;
            case R.id.sketch_map_lay://装修示意图
                launchFitmentPictureActivity();
                break;
            case R.id.team_lay://施工团队
                launchTeamActivity();
                break;
            case R.id.acceptance_lay://工程验收
                launchProjectAcceptanceActivity();
                break;
        }
    }

    /**
     * 跳转到项目清单页面
     */
    private void launchProjectListActivity() {
        Intent intent = new Intent(this, ProjectListNewActivity.class);
        intent.putExtras(composeBundle(mData,PROJECT_LIST_DATA));
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
     * 跳转施工消息界面
     */
    private void launchMessageActivity() {
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtras(composeBundle(mData, MESSAGE));
        intent.putExtras(composeBundle("2", "type"));
        launchActivity(intent);
    }

    private void launchProgressActivityActivity() {
        Intent intent = new Intent(this, ConstructProgressActivity.class);
        Bundle bundle = new Bundle();//用bundle封装数据
        bundle.putSerializable(CONSTRUCT_PROGRESS, mData);
        bundle.putInt("from",2);
        intent.putExtras(bundle);
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
     * 跳转到装修示意图页面
     */
    private void launchFitmentPictureActivity() {
        Intent intent = new Intent(this, FitmentPictureActivity.class);
        intent.putExtras(composeBundle(mData,FITMENT_PICTURE));
        launchActivity(intent);
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
     * 跳转到工程验收界面
     */
    private void launchProjectAcceptanceActivity() {
        Intent intent = new Intent(this, ProjectAcceptActivity.class);
        intent.putExtras(composeBundle(mData,PROJECT_ACCEPT_DATA));
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

    @Override
    public void onRefresh() {
        mPresenter.getProjectInformation(getIntent());
    }
}
