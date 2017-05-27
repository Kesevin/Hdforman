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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerFieldLiveComponent;
import com.dgg.hdforeman.di.module.FieldLiveModule;
import com.dgg.hdforeman.mvp.contract.project.FieldLiveContract;
import com.dgg.hdforeman.mvp.model.been.LiveBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.FieldLivePresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.PhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.PhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

import static com.dgg.hdforeman.mvp.ui.project.activity.HDPhotoPreviewActivity.CHOOSE_MAX_COUNT;
import static com.dgg.hdforeman.mvp.ui.project.activity.HDPhotoPreviewActivity.EXTRA_SELECTED_IMAGES;
import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by kelvin on 2016/11/8.
 */

public class FieldLiveActivity extends BacksActivity<FieldLivePresenter> implements FieldLiveContract.View, BGANinePhotoLayout.Delegate, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.upload_picture)
    AutoRelativeLayout uploadPicture;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.noData)
    LinearLayout noData;
    private BGANinePhotoLayout mBGANinePhotoLayout;

    public static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    //    public static final int REQUEST_CODE_PHOTO_PREVIEW = 2;
    public static final int REQUEST_CODE_LIST_REFRESH = 3;
    private ProjectInfoResponse mData;
    private String proid;
    private int minDistance = 40;
    private boolean showing;

    private Paginate mPaginate;
    private boolean isLoadingMoreEnd;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.field_live_activity, null, false);
    }

    @Override
    protected void initData() {
        rightMenu.setText("联系业主");
        rightMenu.setVisibility(View.VISIBLE);
        title.setText("工地直播");
        Bundle bd = getIntent().getExtras();
        mData = (ProjectInfoResponse) bd.getSerializable("live");
        boolean isUploadPicture = getIntent().getBooleanExtra("isUploadPicture", false);
        if (isUploadPicture) {
            setUploadPictures();
        }
        proid = mData.getId();
        initRecyclerView();
        mPresenter.initAdapter();
        initPaginate();
        mPresenter.requestList(proid, true);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFieldLiveComponent
                .builder()
                .appComponent(appComponent)
                .fieldLiveModule(new FieldLiveModule(this))
                .build()
                .inject(this);
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter.initAdapter();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > minDistance;
                if (isSignificantDelta) {
                    if (dy > 0 && uploadPicture.getVisibility() == View.VISIBLE) {
                        Animation animation = AnimationUtils.loadAnimation(FieldLiveActivity.this, R.anim.push_bottom_out);
                        uploadPicture.setVisibility(View.GONE);
                        uploadPicture.startAnimation(animation);
                    }
                    if (dy < 0 && uploadPicture.getVisibility() == View.GONE) {
                        Animation animation = AnimationUtils.loadAnimation(FieldLiveActivity.this, R.anim.push_bottom_in);
                        uploadPicture.setVisibility(View.VISIBLE);
                        uploadPicture.startAnimation(animation);
                    }
                }
            }
        });
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }


    /**
     * 上拉加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestList(proid, false);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMoreEnd;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return isLoadEnd;
                }
            };
            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(teamAddRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);

        }
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNodata() {
        noData.setVisibility(View.GONE);
    }

    @Override
    public void startLoadMore() {
        isLoadingMoreEnd = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMoreEnd = false;
    }

    @Override
    public void setIsLoad(boolean isLoad) {
        this.isLoadEnd = isLoad;
    }

    @OnClick(R.id.upload_picture)
    public void upLoadPicture() {
        if (mData.getPm_curtype() != 1) {
            showMessage("不在施工中阶段，无法上传图片");
            return;
        }
        PermissionUtil.launchCamera(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "HDPhotoPickerTakePhoto");
                startActivityForResult(PhotoPickerActivity.newIntent(FieldLiveActivity.this, takePhotoDir, 9, null, true), REQUEST_CODE_CHOOSE_PHOTO);
            }
        }, mHDApplication.getAppComponent().rxPermissions(), this, mHDApplication.getAppComponent().rxErrorHandler());

    }

    public void setUploadPictures() {
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "HDPhotoPickerTakePhoto");
        startActivityForResult(PhotoPickerActivity.newIntent(this, takePhotoDir, 9, null, true), REQUEST_CODE_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CommonUtil.logDebug("requestCode :" + requestCode + "  resultCode:" + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE_PHOTO:
                    Intent intent = new Intent(this, HDPhotoPreviewActivity.class);
                    intent.putExtra(CHOOSE_MAX_COUNT, 9);
                    intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, PhotoPickerActivity.getSelectedImages(data));
                    intent.putExtra("project_info", mData);
                    intent.putExtra("from", 101);
                    startActivityForResult(intent, REQUEST_CODE_LIST_REFRESH);
                    break;
//                case REQUEST_CODE_PHOTO_PREVIEW:
//                    mPresenter.upLoadPicture(data);
//                    mRecyclerView.smoothScrollToPosition(0);
//                    break;
                case REQUEST_CODE_LIST_REFRESH:
                    mPresenter.requestList(proid, true);
                    mRecyclerView.smoothScrollToPosition(0);
                    break;
            }

        }
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    /**
     * 设置图片回调显示
     *
     * @param bgaNinePhotoLayout
     * @param imgUrl
     */
    @Override
    public void configPhotoLayout(BGANinePhotoLayout bgaNinePhotoLayout, List<String> imgUrl) {
        bgaNinePhotoLayout.init(FieldLiveActivity.this);//必须注册当前activity
        bgaNinePhotoLayout.setDelegate(FieldLiveActivity.this);//设置回调接口 当前activity必须实现 BGANinePhotoLayout 中Delegate接口
        bgaNinePhotoLayout.setData(imgUrl);
    }

    @Override
    public void previewImage(LiveBean liveBean) {

    }

    @Override
    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void stopLoadMore() {

    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.makeText(message);
    }

    /**
     * 图片点击事件
     *
     * @param ninePhotoLayout
     * @param view
     * @param position
     * @param model
     * @param models
     */
    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        mBGANinePhotoLayout = ninePhotoLayout;
        photoPreviewWrapper();
    }

    /**
     * 图片长按事件
     *
     * @param ninePhotoLayout
     * @param view
     * @param position
     * @param model
     * @param models
     * @return
     */
    @Override
    public boolean onLongClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        return false;
    }

    /**
     * 图片预览
     */
    private void photoPreviewWrapper() {
        if (mBGANinePhotoLayout == null) {
            return;
        }

        if (mBGANinePhotoLayout.getItemCount() == 1) {
            // 预览单张图片
            /**
             * 参数说明
             * 1、Context 上下文
             * 2、保存图片的目录，改成你自己要保存图片的目录。如果不传递该参数的话就不会显示右上角的保存按钮
             * 3、String 当前点击的图片文件路径
             *
             */

            startActivity(PhotoPreviewActivity.newIntent(this, null, mBGANinePhotoLayout.getCurrentClickItem()));
        } else if (mBGANinePhotoLayout.getItemCount() > 1) {
            // 预览多张图片
            /**
             * 参数说明
             * 1、Context 上下文
             * 2、保存图片的目录，改成你自己要保存图片的目录。如果不传递该参数的话就不会显示右上角的保存按钮
             * 3、ArrayList<String> 当前显示的图片路径集合
             * 4、int  当前点击的图片在集合中下标position
             */
            startActivity(PhotoPreviewActivity.newIntent(this, null, mBGANinePhotoLayout.getData(), mBGANinePhotoLayout.getCurrentClickItemPosition()));
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.requestList(proid, true);
    }

    @OnClick(R.id.right_menu)
    public void onClick() {
        callPhone();
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
                    if (ActivityCompat.checkSelfPermission(FieldLiveActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + mData.getPm_cuscontactno())));
                }
            }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
        }
    }
}
