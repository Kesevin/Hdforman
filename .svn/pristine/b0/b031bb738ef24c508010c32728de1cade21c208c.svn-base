package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;
import android.content.Intent;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectMessureResultContract;
import com.dgg.hdforeman.mvp.contract.project.ProjectQuoteContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.model.been.PackageInfo;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.StuffInfo;
import com.dgg.hdforeman.mvp.model.been.UpgradeInfo;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.jakewharton.rxbinding.view.RxView;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by jess on 18/11/2016 10:02
 * Contact with jess.yan.effort@gmail.com
 */

@ActivityScope
public class ProjectQuotePresenter extends BasePresenter<ProjectQuoteContract.Model, ProjectQuoteContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;
    private Map<String, List<StuffInfo>> mStuffs;
    private ProjectMessureResultContract.Model mMeasureModel;

    @Inject
    public ProjectQuotePresenter(ProjectQuoteContract.Model model, ProjectQuoteContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader, ProjectMessureResultContract.Model measureModel) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
        this.mMeasureModel = measureModel;
    }

    /**
     * 获取所有材料信息
     *
     * @return
     */
    public Map<String, List<StuffInfo>> getStuffs() {
        return mStuffs;
    }


    /**
     * 获取测量数据
     *
     * @param id
     * @return
     */
    public Observable<List<SpaceBean.SpacenameEntity>> getMessurelist(String id) {
       return mMeasureModel.getMessurelist(id)
                .compose(RxUtils.<BaseJson<SpaceBean>>applySchedulers(mRootView))
                .filter(new Func1<BaseJson<SpaceBean>, Boolean>() {
                    @Override
                    public Boolean call(BaseJson<SpaceBean> data) {
                        if (!data.isSuccess()) {
                            mRootView.showMessage(data.getMsg());
                        }
                        return data.isSuccess();
                    }
                }).flatMap(new Func1<BaseJson<SpaceBean>, Observable<List<SpaceBean.SpacenameEntity>>>() {
            @Override
            public Observable<List<SpaceBean.SpacenameEntity>> call(BaseJson<SpaceBean> spaceBeanBaseJson) {
                return Observable.just(spaceBeanBaseJson.getD().getSpacename());
            }
        });
    }

    /**
     * 获得基础包信息
     *
     * @return
     */
    @RxLogObservable
    public Observable<PackageInfo> projectPackage() {
        Intent intent = new Intent();
        return mModel.projectPackage()
                .compose(RxUtils.<BaseData<List<PackageInfo>>>applySchedulers(mRootView))
                .filter(new Func1<BaseData<List<PackageInfo>>, Boolean>() {
                    @Override
                    public Boolean call(BaseData<List<PackageInfo>> data) {
                        if (!data.isSuccess()) {
                            mRootView.showMessage(data.getMsg());
                        }
                        return data.isSuccess();
                    }
                }).flatMap(new Func1<BaseData<List<PackageInfo>>, Observable<PackageInfo>>() {
                    @Override
                    public Observable<PackageInfo> call(BaseData<List<PackageInfo>> data) {
                        return Observable.from(data.getD());
                    }
                });
    }


    /**
     * 获得升级包信息
     *
     * @return
     */
    @RxLogObservable
    public Observable<UpgradeInfo> getUpgrade() {
        return mModel.getUpgrade()
                .compose(RxUtils.<BaseData<UpgradeInfo>>applySchedulers(mRootView))
                .filter(new Func1<BaseData<UpgradeInfo>, Boolean>() {
                    @Override
                    public Boolean call(BaseData<UpgradeInfo> data) {
                        if (!data.isSuccess()) {
                            mRootView.showMessage(data.getMsg());
                        }
                        return data.isSuccess();
                    }
                }).flatMap(new Func1<BaseData<UpgradeInfo>, Observable<UpgradeInfo>>() {
                    @Override
                    public Observable<UpgradeInfo> call(BaseData<UpgradeInfo> data) {
                        return Observable.just(data.getD());
                    }
                });
    }


    /**
     * 获得项目材料
     *
     * @return
     */
    @RxLogObservable
    public Observable<Map<String, List<StuffInfo>>> getProjectStuff() {
        return mModel.getProjectStuff()
                .compose(RxUtils.<BaseData<List<StuffInfo>>>applySchedulers(mRootView))
                .filter(new Func1<BaseData<List<StuffInfo>>, Boolean>() {
                    @Override
                    public Boolean call(BaseData<List<StuffInfo>> data) {
                        if (!data.isSuccess()) {
                            mRootView.showMessage(data.getMsg());
                        }
                        return data.isSuccess();
                    }
                }).doOnNext(new Action1<BaseData<List<StuffInfo>>>() {
                    @Override
                    public void call(BaseData<List<StuffInfo>> data) {
                        if (mStuffs == null) {
                            mStuffs = new HashMap<>();
                        }
                    }
                }).flatMap(new Func1<BaseData<List<StuffInfo>>, Observable<Map<String, List<StuffInfo>>>>() {
                    @Override
                    public Observable<Map<String, List<StuffInfo>>> call(BaseData<List<StuffInfo>> data) {
                        Observable.from(data.getD())
                                .subscribe(new Action1<StuffInfo>() {
                                    @Override
                                    public void call(StuffInfo stuffInfo) {
                                        List<StuffInfo> stuffInfos = mStuffs.get(stuffInfo.getParentid());
                                        if (stuffInfos == null) {
                                            stuffInfos = new ArrayList<>();
                                            mStuffs.put(stuffInfo.getParentid(), stuffInfos);
                                        }
                                        stuffInfos.add(stuffInfo);
                                    }
                                });

                        return Observable.just(mStuffs);
                    }
                });
    }


    /**
     * 创建基础包的view
     *
     * @param packageInfos
     * @return
     */
    @RxLogObservable
    public Observable<TextView> creatPackageView(final List<TextView> packageInfos) {
        return Observable.just(new TextView(mApplication))
                .doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.weight = 1;
                        params.leftMargin = AutoUtils.getPercentWidthSize(18);
                        params.rightMargin = AutoUtils.getPercentWidthSize(18);
                        textView.setLayoutParams(params);
                    }
                }).doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        textView.setPadding(0, AutoUtils.getPercentHeightSize(15), 0, AutoUtils.getPercentHeightSize(15));
                        textView.setTextSize(AutoUtils.getPercentHeightSize(10));
                        mRootView.changePackage(textView, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                        textView.setGravity(Gravity.CENTER);
                    }
                }).doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(final TextView textView) {
                        RxView.clicks(textView)
                                .filter(new Func1<Void, Boolean>() {
                                    @Override
                                    public Boolean call(Void aVoid) {
                                        return !packageInfos.isEmpty();
                                    }
                                }).subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                for (TextView info : packageInfos) {
                                    if (info == textView) {
                                        mRootView.setSelctPackageInfo((PackageInfo) info.getTag());
                                        mRootView.changePackage(info, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
                                    } else {
                                        mRootView.changePackage(info, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                                    }

                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                });
    }

    /**
     * 创建电视墙的view
     *
     * @return
     */
    @RxLogObservable
    public Observable<TextView> creatTVView(final List<TextView> packageInfos) {
        return Observable.just(new TextView(mApplication))
                .doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.bottomMargin = AutoUtils.getPercentHeightSize(15);
                        textView.setLayoutParams(params);
                    }
                }).doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        textView.setPadding(0, AutoUtils.getPercentHeightSize(20), 0, AutoUtils.getPercentHeightSize(20));
                        textView.setTextSize(AutoUtils.getPercentHeightSize(10));
                        mRootView.changePackage(textView, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                        textView.setGravity(Gravity.CENTER);
                    }
                })
                .doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(final TextView textView) {
                        RxView.clicks(textView)
                                .filter(new Func1<Void, Boolean>() {
                                    @Override
                                    public Boolean call(Void aVoid) {
                                        return !packageInfos.isEmpty();
                                    }
                                }).subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                if (textView.getCurrentTextColor() == UiUtils.getColor(R.color.textGray)) {//说明没有被选择
                                    for (TextView info : packageInfos) {
                                        if (info == textView) {
                                            mRootView.changePackage(info, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
                                        } else {
                                            mRootView.changePackage(info, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                                        }
                                    }
                                } else {//说明已经被选择
                                    mRootView.changePackage(textView, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                });
    }

    /**
     * 从数据库查询自由项
     * @param id
     * @return
     */
    public Observable<List<FreeTermlistBean>> getFreedatas(String id){
        return mModel.queryById(id);
    }


}
