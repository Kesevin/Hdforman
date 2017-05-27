package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerHDPhotoPreviewComponent;
import com.dgg.hdforeman.di.module.HDPhotoPreviewModule;
import com.dgg.hdforeman.mvp.contract.project.HDPhotoPreviewContract;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.HDPhotoPreviewPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.UploadPicAdapter;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jess.arms.utils.UiUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.PhotoPickerActivity;
import cn.bingoogolapple.photopicker.util.SpaceItemDecoration;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

import static com.dgg.hdforeman.mvp.ui.project.activity.FieldLiveActivity.REQUEST_CODE_CHOOSE_PHOTO;
import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by kelvin on 2016/11/8.
 */
public class HDPhotoPreviewActivity extends BacksActivity<HDPhotoPreviewPresenter> implements HDPhotoPreviewContract.View{

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.pic_recycler)
    RecyclerView picRecycler;

    List<String> backImagesList = new ArrayList<>();

    public static final String EXTRA_SELECTED_IMAGES = "EXTRA_SELECTED_IMAGES";
    public static final String HAS_SELECTED_IMG = "has_selected_img";
    public static final String CHOOSE_MAX_COUNT = "choose_max_count";
    private  int from;
    String hasIds="";


    /**
     * 最多选择多少张图片，默认等于1，为单选
     */
    private int mMaxChooseCount = 9;
    private GridLayoutManager mGridLayoutManager;
    private int mItemSpanCount = 3;
    private  ProjectInfoResponse projectInfo;
    private WaitingDialog mWaitingDialog;
    private ProjectAcceptResponse acceptanceInfo;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerHDPhotoPreviewComponent
                .builder()
                .appComponent(appComponent)
                .hDPhotoPreviewModule(new HDPhotoPreviewModule(this))
                .build()
                .inject(this);
    }

//    /**
//     * @param context 应用程序上下文
//     * @param maxChooseCount 图片选择张数的最大值
//     * @param selectedImages 当前已选中的图片路径集合，可以传null
//     * @return
//     */
//    public static Intent newIntent(Context context, int maxChooseCount,ArrayList<String> selectedImages) {
//        Intent intent = new Intent(context, HDPhotoPreviewActivity.class);
//        intent.putExtra(CHOOSE_MAX_COUNT, maxChooseCount);
//        intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, selectedImages);
//        return intent;
//    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_photo_preview_layout, null, false);
    }

    @Override
    protected void initData() {
        title.setText("添加图片");
        // 获取图片选择的最大张数
        mMaxChooseCount = getIntent().getIntExtra(CHOOSE_MAX_COUNT, 1);
        if (mMaxChooseCount < 1) {
            mMaxChooseCount = 1;
        }
        backImagesList=getIntent().getStringArrayListExtra(EXTRA_SELECTED_IMAGES);
         projectInfo=  (ProjectInfoResponse) getIntent().getSerializableExtra("project_info");
        acceptanceInfo=  (ProjectAcceptResponse) getIntent().getSerializableExtra("acceptance");
        from=getIntent().getIntExtra("from",0);
        hasIds=getIntent().getStringExtra("alreadyhave");
        CommonUtil.logDebug("from "+from);
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false);
        initRecyclerView();
        mPresenter.upLoadPictureList(backImagesList,hasIds);
    }

    @Override
    public void showLoading() {
        mWaitingDialog.show();
    }

    @Override
    public void hideLoading() {
if(mWaitingDialog!=null)
    mWaitingDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        UiUtils.makeText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
    }

    @Override
    public void killMyself() {
        setResult(RESULT_OK);
        finish();
    }


    private void initRecyclerView() {
        mGridLayoutManager = new GridLayoutManager(this, mItemSpanCount);
        picRecycler.setLayoutManager(mGridLayoutManager);
        picRecycler.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(cn.bingoogolapple.photopicker.R.dimen.bga_pp_size_photo_divider)));
        picRecycler.setHasFixedSize(true);
        mPresenter.initAdapter(mMaxChooseCount);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_PHOTO) {
            mPresenter.upLoadPictureList(PhotoPickerActivity.getSelectedImages(data),"");
        }
    }

    /**
     * 上传图片
     */
    @OnClick(R.id.upLoadPictureBtn)
    public void onClick(){

        if(projectInfo==null){
            return;
        }
        switch (from) {
            case 101:
            mPresenter.confirmUploadPic(projectInfo.getId());
            break;
            case 102:
                mPresenter.confirmDecorationPermit(projectInfo.getId());
                break;
            case 103:
                mPresenter.confirmAcceptanceCheck(projectInfo.getId(),acceptanceInfo.getId());
                break;


        }
    }

    @Override
    public void finishWithData(String data) {
        Intent intent = new Intent();
        intent.putExtra("ids", data);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void setAdapter(UploadPicAdapter adapter) {
        picRecycler.setAdapter(adapter);
    }

}
