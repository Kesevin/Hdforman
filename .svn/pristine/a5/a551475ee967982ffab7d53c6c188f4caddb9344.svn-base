package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.contract.project.HDPhotoPreviewContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseData2;
import com.dgg.hdforeman.mvp.model.been.ImageInfo;
import com.dgg.hdforeman.mvp.model.been.UpLoadResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.UploadPicAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.UiUtils;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import cn.bingoogolapple.photopicker.activity.PhotoPickerActivity;
import id.zelory.compressor.Compressor;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by kelvin on 2016/11/2.
 */

@ActivityScope
public class HDPhotoPreviewPresenter extends BasePresenter<HDPhotoPreviewContract.Model, HDPhotoPreviewContract.View> implements UploadPicAdapter.OnItemClickListener {

    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private UploadPicAdapter mAdapter;
    private List<ImageInfo> imageList = new ArrayList<ImageInfo>();
    private int limit=9;
    final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    @Inject
    public HDPhotoPreviewPresenter(HDPhotoPreviewContract.Model model, HDPhotoPreviewContract.View view,
                                   RxErrorHandler handler, Application application) {
        super(model, view);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }


    public void initAdapter( int limit) {
        this.limit=limit;
        mAdapter = new UploadPicAdapter(imageList, limit);
        mRootView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
    }

    public void upLoadPictureList(final List<String> tmpImageList,String hasIds) {
        if(!TextUtils.isEmpty(hasIds)){
            imageList.addAll(changeData(hasIds));
        }
        imageList.addAll(wrapData(tmpImageList));
        mAdapter.notifyDataSetChanged();
        for (int i = 0; i < tmpImageList.size(); i++) {
            upLoadPictureSingle( imageList.get(imageList.size() -tmpImageList.size()+ i));
            }
    }

    public void upLoadPictureSingle(final ImageInfo info) {
        Compressor.getDefault((Activity)mRootView)
                .compressToFileAsObservable(new File(info.getPath()))
                .subscribeOn(Schedulers.from(fixedThreadPool))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        mModel.upLoadImg(file.getAbsolutePath())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Action1<BaseData2<UpLoadResponse>>() {
                                    @Override
                                    public void call(BaseData2<UpLoadResponse> mData) {
                                        int position= imageList.indexOf(info);
                                        if (mData.isSuccess()) {
                                            imageList.get(position).setState(1);
                                            imageList.get(position).setBackId(mData.getData().getId());
                                            mAdapter.notifyItemChanged(position);
                                        } else {
                                            mRootView.showMessage(mData.getMsg());
                                            imageList.get(position).setState(2);
                                            mAdapter.notifyItemChanged(position);
                                        }

                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable e) {
                                        int position= imageList.indexOf(info);
                                        CommonUtil.logDebug(e.getMessage());
                                        mRootView.showMessage("上传失败，请重试");
                                        imageList.get(position).setState(2);
                                        mAdapter.notifyItemChanged(position);
                                    }
                                });
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        UiUtils.makeText(throwable.getMessage());;
                    }
                });

    }

    @Override
    public void onRetryClick(View view, int position, ImageInfo info) {
        imageList.get(position).setState(0);
        mAdapter.notifyItemChanged(position);
        upLoadPictureSingle(info);
    }

    @Override
    public void onDeleteClick(View view, int position, ImageInfo info) {
        imageList.remove(position);
        mAdapter.notifyItemRemoved(position);

    }

    @Override
    public void onItem(View view, int position) {
        if (position == (imageList.size())) {
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "HDPhotoPickerTakePhoto");
            mRootView.launchActivity( PhotoPickerActivity.newIntent(mApplication, takePhotoDir, limit-imageList.size(), null, true));
        }
    }

    /**
     * 直播
     * @param projectId
     */
    public void confirmUploadPic( String projectId){

       if(imageList.size()==0){
           mRootView.showMessage("未选择要上传的照片");
           return;
       }else{
           String pictureIds="";
           int count = 0;
           for (int i=0;i<imageList.size();i++){
               if(imageList.get(i).getState()==0){
                   mRootView.showMessage("图片上传中，请稍后");
                   return;
               }
               String tmp=imageList.get(i).getBackId();
               if(!TextUtils.isEmpty(tmp)){
                   pictureIds+=","+tmp;
                   count++;
               }
           }
           if(count<3){
               mRootView.showMessage("至少上传3张图片");
               return;
           }
           pictureIds=  pictureIds.substring(1,pictureIds.length());
           CommonUtil.logDebug("pictureIds : "+pictureIds);
           if(TextUtils.isEmpty(pictureIds)){
               mRootView.showMessage("未选择要上传的照片");
           }else{
//               Timestamp start = new Timestamp(System.currentTimeMillis());//获取系统当前时间
               mModel.confirmUpload(projectId, pictureIds)
                       .compose(RxUtils.<BaseData<String>>applySchedulers(mRootView))
                       .doAfterTerminate(new Action0() {
                           @Override
                           public void call() {
                               mRootView.hideLoading();
                           }
                       })
                       .subscribe(new ErrorHandleSubscriber<BaseData<String>>(mErrorHandler) {
                           @Override
                           public void onNext(BaseData<String> mData) {
                               if (mData.isSuccess()) {
                                   imageList.clear();
                                   mRootView.showMessage("上传成功");
                                   mRootView.killMyself();
//                                   Timestamp end = new Timestamp(System.currentTimeMillis());//获取系统当前时间
                               }else {
                                   mRootView.showMessage(mData.getMsg());
                               }
                           }
                       });

           }
       }

    }
    /**
     * 开工许可证
     */
    public void confirmDecorationPermit( String projectId){
        if(imageList.size()==0){
            mRootView.showMessage("未选择要上传的照片");
            return;
        }else{
          String pictureIds="";
            for (int i=0;i<imageList.size();i++){
                if(imageList.get(i).getState()==0){
                    mRootView.showMessage("图片上传中，请稍后");
                    return;
                }
                if(!TextUtils.isEmpty(imageList.get(i).getBackId())){
                    pictureIds+=","+imageList.get(i).getBackId();
                }
            }
            if(TextUtils.isEmpty(pictureIds)){
                mRootView.showMessage("没有要上传的图片");
                return;
            }
            pictureIds=  pictureIds.substring(1,pictureIds.length());
            CommonUtil.logDebug("pictureIds : "+pictureIds);
            if(TextUtils.isEmpty(pictureIds)){
                mRootView.showMessage("未选择要上传的照片");
            }else{
                mModel.uploadDecorationPermit(projectId, pictureIds)
                        .compose(RxUtils.<BaseData<String>>applySchedulers(mRootView))
                        .doAfterTerminate(new Action0() {
                            @Override
                            public void call() {
                                mRootView.hideLoading();
                            }
                        })
                        .subscribe(new ErrorHandleSubscriber<BaseData<String>>(mErrorHandler) {
                            @Override
                            public void onNext(BaseData<String> mData) {
                                if (mData.isSuccess()) {
                                    imageList.clear();
                                    mRootView.showMessage("上传成功");
                                    mRootView.killMyself();
                                    EventBus.getDefault().post(true,"startWork");
                                }

                                else {
                                    mRootView.showMessage(mData.getMsg());
                                }
                            }
                        });

            }
        }

    }

    /**
     * 阶段验收
     * @param proid
     * @param stage
     */
    public void confirmAcceptanceCheck (String proid ,String stage ){
        if(imageList.size()==0){
            mRootView.showMessage("未选择要上传的照片");
            return;
        }else{
            String pictureIds="";
            int count = 0;
            for (int i=0;i<imageList.size();i++){
                if(imageList.get(i).getState()==0){
                    mRootView.showMessage("图片上传中，请稍后");
                    return;
                }
                if(!TextUtils.isEmpty(imageList.get(i).getBackId())){
                    pictureIds+=","+imageList.get(i).getBackId();
                    count++;
                }
            }
            if(count<limit-6){
                mRootView.showMessage("至少上传"+(limit-6)+"张图片");
                return;
            }
            pictureIds=  pictureIds.substring(1,pictureIds.length());
            CommonUtil.logDebug("pictureIds : "+pictureIds);
            if(TextUtils.isEmpty(pictureIds)){
                mRootView.showMessage("未选择要上传的照片");
            }else{
                final String tp=pictureIds;
                mModel.confirmAcceptance(proid, stage,pictureIds)
                        .compose(RxUtils.<BaseData<String>>applySchedulers(mRootView))
                        .doAfterTerminate(new Action0() {
                            @Override
                            public void call() {
                                mRootView.hideLoading();
                            }
                        })
                        .subscribe(new ErrorHandleSubscriber<BaseData<String>>(mErrorHandler) {
                            @Override
                            public void onNext(BaseData<String> mData) {
                                if (mData.isSuccess()) {
                                    imageList.clear();
                                    mRootView.showMessage("上传成功");
                                    mRootView.finishWithData(tp);
                                    mRootView.killMyself();
                                }

                                else {
                                    mRootView.showMessage(mData.getMsg());
                                }
                            }
                        });

            }
        }

    }
    public List<ImageInfo> changeData(String ids){
        String[] imgUrlId = ids.split(",");
        List<ImageInfo> tmpList=new ArrayList<>();
        for (int i = 0; i < imgUrlId.length; i++) {
            ImageInfo info=new ImageInfo(CommonUtil.getImageUrl(imgUrlId[i],100,100),1,1);
            info.setBackId(imgUrlId[i]);
            tmpList.add(info);
        }
        return tmpList;
    }
    public List<ImageInfo>  wrapData(List<String> backImagesList){
        List<ImageInfo> tepList=new ArrayList<>();
        for (int i=0;i<backImagesList.size();i++){
            ImageInfo info=new ImageInfo(backImagesList.get(i),0,0);
            tepList.add(info);
        }
         return tepList;
    }
}
