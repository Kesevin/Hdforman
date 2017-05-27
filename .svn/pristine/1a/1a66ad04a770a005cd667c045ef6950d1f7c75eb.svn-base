package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.QuoteListContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.QuoteListInfo;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.StuffInfo;
import com.dgg.hdforeman.mvp.model.been.UpgradeInfo;
import com.dgg.hdforeman.mvp.model.been.UppacInfo;
import com.dgg.hdforeman.mvp.ui.project.activity.BudgetSheetActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.ChoiceAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.UpgradeAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.CharactorHandler;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

import static com.dgg.hdforeman.app.config.EventBusTag.KILL_PROJECT_QUOTE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_FREE_INFO;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_OTHER_UPGRADE;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_PACKAGE_ID;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_PRICE_EXPRESSION;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_PROJECT_ID;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_UPGRADE_INFO;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by jess on 29/11/2016 15:41
 * Contact with jess.yan.effort@gmail.com
 */

@ActivityScope
public class QuoteListPresenter extends BasePresenter<QuoteListContract.Model, QuoteListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;
    private Gson mGson;
    private List<UppacInfo> mUppacInfos = new ArrayList<>();
    private DefaultAdapter mAdapter;

    private ChoiceAdapter adapter;
    private List<QuoteListInfo> datas = new ArrayList<>();
    private String mProjectId;
    private String mPackageId;
    private ProjectInfoResponse mResponse;
    private String time;
    private String remark;

    @Inject
    public QuoteListPresenter(QuoteListContract.Model model, QuoteListContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader, Gson gson) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
        this.mGson = gson;
    }

    public void initRvChoice() {
        adapter = new ChoiceAdapter(datas);
        mRootView.initRvChoice(adapter);
        mModel.getOtherUP().compose(RxUtils.<BaseData<List<QuoteListInfo>>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData<List<QuoteListInfo>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<List<QuoteListInfo>> listBaseData) {
                        if (listBaseData.isSuccess()) {
                            adapter.setUppacInfos(getcacheUppacs());
                            adapter.setNewData(listBaseData.getD());
                        } else {
                            mRootView.showMessage(listBaseData.getMsg());
                        }
                    }
                });
    }

    /**
     * 整理升级项
     *
     * @return
     */
    public List<UppacInfo> getUpgradeInfos() {
        List<UppacInfo> infoList = new ArrayList<>();

        for (UppacInfo uppacInfo : mUppacInfos) {//筛选用用勾选的
            if (uppacInfo.isCheck()) {
                infoList.add(uppacInfo);
            }
        }

        if (adapter != null && adapter.getInlistBeanList() != null && adapter.getInlistBeanList().size() > 0) {
            for (QuoteListInfo.InlistBean bean : adapter.getInlistBeanList()) {
                UppacInfo info = new UppacInfo(bean.getUg_name(), "", "", "1", bean.getId() + "");
                infoList.add(info);
            }
        }

        return infoList;
    }

    /**
     * 解析intent
     *
     * @param intent
     */
    public void parseIntent(Intent intent) {
        Observable.just(intent)
                .filter(new Func1<Intent, Boolean>() {
                    @Override
                    public Boolean call(Intent intent) {
                        return intent != null;
                    }
                }).map(new Func1<Intent, Bundle>() {
            @Override
            public Bundle call(Intent intent) {
                return intent.getExtras();
            }
        }).filter(new Func1<Bundle, Boolean>() {
            @Override
            public Boolean call(Bundle bundle) {
                return bundle != null;
            }
        }).doOnNext(new Action1<Bundle>() {
            @Override
            public void call(Bundle bundle) {
                mProjectId = bundle.getString(PACKAGE_PROJECT_ID);
                mPackageId = bundle.getString(PACKAGE_PACKAGE_ID);
                mResponse = (ProjectInfoResponse) bundle.getSerializable(PROJECTINFO_DATA);
            }
        }).doOnNext(new Action1<Bundle>() {
            @Override
            public void call(Bundle bundle) {//解析基础包数据
                Observable.just(bundle.getString(PACKAGE_PRICE_EXPRESSION))
                        .filter(new Func1<String, Boolean>() {
                            @Override
                            public Boolean call(String s) {
                                return s != null;
                            }
                        }).map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Timber.tag(TAG).w("----------->" + s);
                        mRootView.setBaseExpression(s);
                        return s.split("=")[1];
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mRootView.setBaseTotal(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).doOnNext(new Action1<Bundle>() {
            @Override
            public void call(Bundle bundle) {//解析升级包数据
                Observable.just(bundle.getString(PACKAGE_UPGRADE_INFO))
                        .filter(new Func1<String, Boolean>() {
                            @Override
                            public Boolean call(String s) {
                                return s != null;
                            }
                        }).map(new Func1<String, Map<StuffInfo, List<SpaceBean.SpacenameEntity>>>() {
                    @Override
                    public Map<StuffInfo, List<SpaceBean.SpacenameEntity>> call(String s) {
                        Log.e("1111111",s);
                        HashMap<StuffInfo, List<SpaceBean.SpacenameEntity>> map = mGson.fromJson(s,
                                new TypeToken<HashMap<StuffInfo, List<SpaceBean.SpacenameEntity>>>() {
                                }.getType());

                        return map;
                    }
                }).subscribe(new Action1<Map<StuffInfo, List<SpaceBean.SpacenameEntity>>>() {
                    @Override
                    public void call(Map<StuffInfo, List<SpaceBean.SpacenameEntity>> stuffInfoListHashMap) {
                        for (Map.Entry<StuffInfo, List<SpaceBean.SpacenameEntity>> stuffInfoListEntry : stuffInfoListHashMap.entrySet()) {
                            for (SpaceBean.SpacenameEntity entity : stuffInfoListEntry.getValue()) {
                                Timber.tag(TAG).w(stuffInfoListEntry.getKey().getName() + "=============" + entity.getPe_name());
                                UppacInfo info = new UppacInfo(stuffInfoListEntry.getKey().getName(), stuffInfoListEntry.getKey().getPrice() + "",
                                        stuffInfoListEntry.getKey().getUnit(), entity.getInput_acreage() + "", stuffInfoListEntry.getKey().getId());
                                info.setSpacecname(entity.getPe_name());
                                info.setSpaceid(entity.getId() + "");
                                mUppacInfos.add(info);
                            }
                        }
                    }
                });

            }
        }).doOnNext(new Action1<Bundle>() {//解析自由项数据
            @Override
            public void call(Bundle bundle) {
                Observable.just(bundle.getString(PACKAGE_FREE_INFO))
                        .filter(new Func1<String, Boolean>() {
                            @Override
                            public Boolean call(String s) {
                                return s != null;
                            }
                        }).map(new Func1<String, List<FreeTermlistBean>>() {
                    @Override
                    public List<FreeTermlistBean> call(String s) {
                        List<FreeTermlistBean> list = mGson.fromJson(s,
                                new TypeToken<List<FreeTermlistBean>>() {
                                }.getType());
                        return list;
                    }
                }).subscribe(new Action1<List<FreeTermlistBean>>() {
                    @Override
                    public void call(List<FreeTermlistBean> list) {
                        for (FreeTermlistBean termlistBean : list) {
                            UppacInfo info = new UppacInfo(termlistBean.getPuugname(), termlistBean.getPuugprice(), termlistBean.getPuugunit()
                                    , termlistBean.getPunumber() + "", termlistBean.getFreeid());
                            info.setSpacecname(termlistBean.getSpacename());
                            info.setSpaceid(termlistBean.getSpaceid());
                            mUppacInfos.add(info);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).doOnNext(new Action1<Bundle>() {//解析其余升级项
            @Override
            public void call(Bundle bundle) {
                Observable.just(bundle.getString(PACKAGE_OTHER_UPGRADE))
                        .filter(new Func1<String, Boolean>() {
                            @Override
                            public Boolean call(String s) {
                                return s != null;
                            }
                        }).map(new Func1<String, List<UpgradeInfo.InfoBean>>() {
                    @Override
                    public List<UpgradeInfo.InfoBean> call(String s) {
                        List<UpgradeInfo.InfoBean> list = mGson.fromJson(s,
                                new TypeToken<List<UpgradeInfo.InfoBean>>() {
                                }.getType());
                        return list;
                    }
                }).subscribe(new Action1<List<UpgradeInfo.InfoBean>>() {
                    @Override
                    public void call(List<UpgradeInfo.InfoBean> infoBeans) {
                        for (UpgradeInfo.InfoBean infoBean : infoBeans) {
                            UppacInfo info = new UppacInfo(infoBean.getUg_name(), infoBean.getUg_price() + "", infoBean.getUg_unit()
                                    , infoBean.getInput_num(), infoBean.getId());
                            mUppacInfos.add(info);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).subscribe(new ErrorHandleSubscriber<Bundle>(mErrorHandler) {
            @Override
            public void onNext(Bundle bundle) {
                if (mAdapter == null) {
                    mAdapter = new UpgradeAdapter(mUppacInfos);
                    mRootView.setAdapter(mAdapter);
                }

                float total = 0;
                for (UppacInfo uppacInfo : mUppacInfos) {
                    if (TextUtils.isEmpty(uppacInfo.getUpmeasure()))
                        continue;
                    float num = Float.parseFloat(uppacInfo.getUpmeasure());//数量
                    float price = Float.parseFloat(uppacInfo.getPrice());//价格
                    total += num * price;
                }

                Observable.just(total)
                        .map(new Func1<Float, String>() {
                            @Override
                            public String call(Float f) {
                                return String.format("¥ %.2f", f);
                            }
                        }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mRootView.setUpgradeTotal(s);
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


    public void addBudgetSheet(final String startdate, boolean isweeksuported) {
        if (TextUtils.isEmpty(startdate)) {
            mRootView.showMessage("请选择预计开工时间");
            return;
        }
        mModel.addBudgetSheet(mProjectId, mPackageId, startdate, isweeksuported ? "1" : "0"
                , CharactorHandler.excludeEmpty(mRootView.getRemark()), getUpgradeInfos())
                .compose(RxUtils.<BaseData>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData baseData) {
                        mRootView.showMessage(baseData.getMsg());
                        if (baseData.isSuccess()) {
                            saveCacheUppacs(adapter.getInlistBeanList(),startdate,mRootView.getRemark());
                            boolean contain = false;
                            for (BaseActivity activity : ((HDApplication) mApplication).getActivityList()) {
                                //BudgetSheetActivity是否已经打开
                                Timber.tag(TAG).w(activity.getClass().getSimpleName());
                                if (activity.getClass().getSimpleName().equals("BudgetSheetActivity"))
                                    contain = true;
                            }

                            //跳转到BudgetSheetActivity,如果BudgetSheetActivity存在,它为singleTask,所以会将它推到栈顶,关闭它之前的activity
                            Intent intent = new Intent(mApplication, BudgetSheetActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(PROJECTINFO_DATA, mResponse);
                            intent.putExtras(bundle);
                            mRootView.launchActivity(intent);

                            //如果BudgetSheetActivity不存在,则自己关闭之前的页面
                            if (!contain) {
                                EventBus.getDefault().post(true, KILL_PROJECT_QUOTE);
                                mRootView.killMyself();
                            }


                        }
                    }
                });
    }
       public List<QuoteListInfo.InlistBean> getcacheUppacs(){
           if (mResponse!=null){
               time=DataHelper.getStringSF(mApplication,mResponse.getId()+"upidtime");
               remark=DataHelper.getStringSF(mApplication,mResponse.getId()+"upidmark");
               mRootView.settime(time);
               mRootView.setremark(remark);
           }
           if (mResponse==null){
               UiUtils.SnackbarText("项目id为空");
               return null;
           }else {
               return mModel.getuppacscache(mResponse.getId());
           }
       }
      public void saveCacheUppacs(List<QuoteListInfo.InlistBean> list,String time,String remarks){
          if (mResponse==null){
              return;
          }
          DataHelper.SetStringSF(mApplication,mResponse.getId()+"upidtime",time);
          DataHelper.SetStringSF(mApplication,mResponse.getId()+"upidmark",remarks);
          mModel.savaupcaches(list,mResponse.getId());
      }
}
