package com.dgg.hdforeman.mvp.ui.project.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerConstructProgressComponent;
import com.dgg.hdforeman.di.module.ConstructProgressModule;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ConstructProgressPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.widget.PromptDialog;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.project.activity.IntermediateAcceptActivity.INTERMEDIATE_ACCEPT_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.IntermediateAcceptActivity.PROJECT_ACCEPT_RESPONSE_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectShutActivity.PROJECT_SHUT_DATA;
import static com.jess.arms.utils.Preconditions.checkNotNull;

public class ConstructProgressActivity extends BacksActivity<ConstructProgressPresenter> implements ConstructProgressContract.View,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    AutoToolbar  mToolbar;
    @BindView(R.id.toolbar_right)
    ImageButton toolbarRight;
    @BindDrawable(R.drawable.divider_popupwindow)
    Drawable divider_popupwindow;

    public static final String CONSTRUCT_PROGRESS = "construct_progress";
    public static final int UPDATE=1;
    private ProjectInfoResponse mData;
    private PopupWindow mPopupWindow;

//    private WaitingDialog mWaitingDialog;

    private int shutdown;

    private  int from;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_construct_progress_layout, null, false);
    }

    @Override
    protected void initData() {
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(CONSTRUCT_PROGRESS);
        from=getIntent().getExtras().getInt("from");
        if(from==2){
            toolbarRight.setVisibility(View.GONE);
        }
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mWaitingDialog = new WaitingDialog(this);
//        mWaitingDialog.setCanceledOnTouchOutside(false);
        initRecyclerView();
        String[] contents = {"联系业主", "申请停工"};
        initPopWindow(contents);
        mPresenter.initAdapter();
        qryData();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerConstructProgressComponent
                .builder()
                .appComponent(appComponent)
                .constructProgressModule(new ConstructProgressModule(this))
                .build()
                .inject(this);
    }

    /**
     * 请求施工进度列表
     */
    private void qryData(){
        mPresenter.requestList(mData,from);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化PopWindow
     * @return
     */
    public void initPopWindow(String[] contents){
        LinearLayout mParentLayout = new LinearLayout(this);
        mParentLayout.setOrientation(LinearLayout.VERTICAL);
        mParentLayout.setBackgroundResource(R.color.translate_60);
        mParentLayout.setDividerDrawable(divider_popupwindow);
        mParentLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        ArrayList<LinearLayout> items = new ArrayList<>(2);
//        String[] contents = {"联系业主", "申请停工"};

        for (int i = 0; i < contents.length; i++)
        {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER);
            layout.setPadding(DensityUtil.getPercentWidthSize(34), DensityUtil.getPercentWidthSize(36), DensityUtil.getPercentWidthSize(34), DensityUtil.getPercentWidthSize(36));

            TextView text = new TextView(this);
            text.setText(contents[i]);
            text.setTextColor(Color.WHITE);
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, DensityUtil.getPercentWidthSize(28));
            layout.addView(text);

            mParentLayout.addView(layout);
            items.add(layout);
        }

        //popView即popupWindow的布局，ture设置focusAble.
        mPopupWindow = new PopupWindow(mParentLayout, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效。这里在XML中定义背景，所以这里设置为null;
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.setOutsideTouchable(true); //点击外部关闭。
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);    //设置一个动画。
        items.get(0).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPopupWindow.dismiss();
                //联系业主
                callPhone();
            }
        });
        items.get(1).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPopupWindow.dismiss();
                if(shutdown==2){//申请复工
                    applyStartWork();
                }else if(shutdown==1){
                    showMessage("停工正在申请中");
                }
                else{
                    launchProjectShutActivity();//申请停工
                }
            }
        });

    }

    public void showPop(PopupWindow popWind){
        /**
         * 定位PopupWindow，让它恰好显示在Action Bar的下方。 通过设置Gravity，确定PopupWindow的大致位置。
         * 首先获得状态栏的高度，再获取Action bar的高度，这两者相加设置y方向的offset样PopupWindow就显示在action
         * bar的下方了。 通过dp计算出px，就可以在不同密度屏幕统一X方向的offset.但是要注意不要让背景阴影大于所设置的offset，
         * 否则阴影的宽度为offset.
         */
        // 获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int xOffset = DensityUtil.getPercentWidthSize(8);
        int yOffset = frame.top + getSupportActionBar().getHeight();//减去阴影宽度，适配UI.
        //设置Gravity，让它显示在右上角。
        popWind.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, xOffset, yOffset);
    }

    /**
     * 跳转到申请停工页面
     */
    private void launchProjectShutActivity(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(PROJECT_SHUT_DATA,mData);
        Intent intent = new Intent(this,ProjectShutActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,UPDATE);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestList(mData,from);
    }

    @OnClick(R.id.toolbar_right)
    public void onClick(){
        showPop(mPopupWindow);
    }

    /**
     * 给业主打电话
     */
    public void callPhone() {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ConstructProgressActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this,mHDApplication.getAppComponent().rxErrorHandler());
    }

    /**
     * 根据子节点ID修改其状态（子节点完成状态)
     */
    public void applyStartWork() {
        PromptDialog.Builder builder = new PromptDialog.Builder(this)
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.applyStartWork(mData.getId());
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    /**
     * 展开或关闭进度信息 状态详情
     * @param isExpanded
     * @param dBean
     */
    @Override
    public void onExpandImageViewClick(boolean isExpanded, ConstructProgressBean dBean) {
        mPresenter.onExpandImageViewClick(isExpanded,dBean);
    }

    /**
     * 根据子节点ID修改其状态（子节点完成状态)
     * @param data
     */
    @Override
    public void updateStageNode(final ConstructProgressBean data, final int position) {
        PromptDialog.Builder builder = new PromptDialog.Builder(this)
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.updateStageNode(data,position);
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    @Override
    public void updatePop(int shutdown) {
        this.shutdown=shutdown;//停工申请状态(默认0 未申请  1申请中 2 业主同意 3业主不同意停工)
//        String[] contents = {"联系业主", "申请停工"};
        String[] contents = new String[2];
        contents[0]="联系业主";
        switch (shutdown){
            case 0:
            case 3:
                contents[1]="申请停工";
                break;
            case 1:
                contents[1]="申请中";
                break;
            case 2:
                contents[1]="申请复工";
                break;
        }
        initPopWindow(contents);
    }

    @Override
    public void setDialog(final ProjectAcceptResponse data) {
        PromptDialog.Builder builder=new PromptDialog.Builder(ConstructProgressActivity.this);
        builder.setMessage("当前阶段已经全部完成，确定要去阶段验收吗？")
       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(ConstructProgressActivity.this,IntermediateAcceptActivity.class);
                intent.putExtra(INTERMEDIATE_ACCEPT_DATA,mData);
                Bundle bundle = new Bundle();
                bundle.putSerializable(PROJECT_ACCEPT_RESPONSE_DATA,data);
                intent.putExtras(bundle);
                launchActivity(intent);
                dialog.dismiss();
            }
        })
       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    @Override
    public void showLoading() {
//        mWaitingDialog.show();
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
//        mWaitingDialog.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        super.showMessage(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0 && requestCode==UPDATE){
            mPresenter.requestList(mData,from);
        }
    }
}
