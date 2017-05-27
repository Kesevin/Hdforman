package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectQuoteComponent;
import com.dgg.hdforeman.di.module.ProjectQuoteModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectQuoteContract;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.model.been.PackageInfo;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.StuffInfo;
import com.dgg.hdforeman.mvp.model.been.UpgradeInfo;
import com.dgg.hdforeman.mvp.presenter.project.ProjectQuotePresenter;
import com.dgg.hdforeman.mvp.ui.widget.AddView;
import com.dgg.hdforeman.mvp.ui.widget.ExtendView;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import timber.log.Timber;

import static com.dgg.hdforeman.app.config.EventBusTag.FREE_ITEMS;
import static com.dgg.hdforeman.app.config.EventBusTag.KILL_PROJECT_QUOTE;
import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_FREE_INFO;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_OTHER_UPGRADE;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_PACKAGE_ID;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_PRICE_EXPRESSION;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_PROJECT_ID;
import static com.dgg.hdforeman.mvp.ui.project.activity.QuoteListActivity.PACKAGE_UPGRADE_INFO;
import static com.jess.arms.utils.CharactorHandler.excludeEmpty;
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
 * Created by jess on 18/11/2016 10:03
 * Contact with jess.yan.effort@gmail.com
 */

public class ProjectQuoteActivity extends BacksActivity<ProjectQuotePresenter> implements ProjectQuoteContract.View {
    public static final String CACHE_PACKAGE = "package";
    public static final String CACHE_TOILET = "toilet";
    public static final String CACHE_POTENTIAL = "potential";
    public static final String CACHE_LINE = "line";
    public static final String CACHE_TV = "tv";
    public static final String CACHE_ACTION = "action";
    public static final String CACHE_UPGRADE = "upgrade";

    @BindView(R.id.ll_quote_package_container)
    LinearLayout mLlQuotePackageContainer;
    @BindView(R.id.tv_quote_toilet)
    TextView mTvQuoteToilet;
    @BindView(R.id.tv_quote_potential)
    TextView mTvQuotePotential;
    @BindView(R.id.tv_quote_potential_price)
    TextView mTvQuotePotentialPrice;
    @BindView(R.id.et_quote_potential_sum)
    EditText mEtQuotePotentialSum;
    @BindView(R.id.tv_quote_tv_title)
    TextView mTvQuoteTvTitle;
    @BindView(R.id.tv_quote_tv_container)
    LinearLayout mTvQuoteTvContainer;
    @BindView(R.id.tv_quote_action_title)
    TextView mTvQuoteActionTitle;
    @BindView(R.id.tv_quote_action_container)
    LinearLayout mTvQuoteActionContainer;
    @BindView(R.id.sv_project_detail)
    NestedScrollView mSvProjectDetail;
    @BindView(R.id.rl_quote_potential)
    RelativeLayout mRlQuotePotential;
    @BindView(R.id.tv_quote_line)
    TextView mTvQuoteLine;
    @BindView(R.id.tv_quote_line_price)
    TextView mTvQuoteLinePrice;
    @BindView(R.id.et_quote_line_sum)
    EditText mEtQuoteLineSum;
    @BindView(R.id.rl_quote_line)
    RelativeLayout mRlQuoteLine;
    @BindView(R.id.ll_quote_stuff_container)
    LinearLayout mLlQuoteStuffContainer;
    @BindView(R.id.tv_quote_acreage)
    TextView mTvQuoteAcreage;
    @BindView(R.id.rl_freeterm)
    RelativeLayout rl_freeterm;
    @BindView(R.id.av_quote)
    AddView mAvQuote;
    @BindView(R.id.tv_quote_house)
    TextView mHouse;

    private List<TextView> mPackageInfos;
    private List<TextView> mtvInfos;
    private List<TextView> mActionInfos;
    private UpgradeInfo mUpgradeInfo;
    private ProjectInfoResponse mProjectInfo;
    private PackageInfo mSelctPackageInfo;//选择的基础包
    private Map<StuffInfo, List<SpaceBean.SpacenameEntity>> mUpgradInfos = new HashMap<>();
    private Observable<List<FreeTermlistBean>> mListObservable;
    private List<UpgradeInfo.InfoBean> mUpgrads = new ArrayList<>();
    private Map<String, String> mCache;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectQuoteComponent
                .builder()
                .appComponent(appComponent)
                .projectQuoteModule(new ProjectQuoteModule(this)) //请将ProjectQuoteModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_project_quote, null, false);
    }

    @Override
    protected void initData() {

        this.mProjectInfo = (ProjectInfoResponse) getIntent().getExtras().getSerializable(PROJECTINFO_DATA);

        if (mProjectInfo != null)
            this.mListObservable = mPresenter.getFreedatas(mProjectInfo.getId());

        updateView();
        //读取本地缓存
        loadCache();
        //获得基础包
        getProjectPackage();

        //获得材料包
        getStuff();
        //获得升级包
        getUpgrade();
    }

    /**
     * 读取缓存
     */
    private void loadCache() {
        String data = DataHelper.getStringSF(getApplicationContext(), mProjectInfo.getId());
        Observable.just(data)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (s == null)
                            mCache = new HashMap<String, String>();
                        return s != null;
                    }
                }).map(new Func1<String, Map<String, String>>() {
            @Override
            public Map<String, String> call(String s) {
                Map<String, String> map = mHDApplication.getAppComponent().gson()
                        .fromJson(s, new TypeToken<Map<String, String>>() {
                        }.getType());
                return map;
            }
        }).subscribe(new Action1<Map<String, String>>() {
            @Override
            public void call(Map<String, String> map) {
                mCache = map;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }


    private void updateView() {
        Observable.just(mProjectInfo)
                .filter(new Func1<ProjectInfoResponse, Boolean>() {
                    @Override
                    public Boolean call(ProjectInfoResponse projectInfoResponse) {
                        return projectInfoResponse != null;
                    }
                }).doOnNext(new Action1<ProjectInfoResponse>() {
            @Override
            public void call(ProjectInfoResponse projectInfoResponse) {
                mHouse.setText(projectInfoResponse.getPm_housetype());
            }
        }).map(new Func1<ProjectInfoResponse, String>() {
            @Override
            public String call(ProjectInfoResponse projectInfoResponse) {
                return excludeEmpty(projectInfoResponse.getPm_acreage()) + "㎡";
            }
        }).subscribe(RxTextView.text(mTvQuoteAcreage), new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void getStuff() {
        mPresenter.getProjectStuff()
                .filter(new Func1<Map<String, List<StuffInfo>>, Boolean>() {
                    @Override
                    public Boolean call(Map<String, List<StuffInfo>> stringListMap) {
                        return stringListMap != null;
                    }
                }).flatMap(new Func1<Map<String, List<StuffInfo>>, Observable<List<StuffInfo>>>() {
            @Override
            public Observable<List<StuffInfo>> call(final Map<String, List<StuffInfo>> map) {
                return Observable.just(map.get("0"));//0为父节点
            }
        }).zipWith(mPresenter.getMessurelist(mProjectInfo.getId()), new Func2<List<StuffInfo>, List<SpaceBean.SpacenameEntity>, Observable<ExtendView>>() {
            //获取测量数据
            @Override
            public Observable<ExtendView> call(final List<StuffInfo> stuffInfos, final List<SpaceBean.SpacenameEntity> spacenameEntities) {
                Timber.tag(TAG).w("zipwith");
                return Observable.from(stuffInfos)
                        .map(new Func1<StuffInfo, ExtendView>() {
                            @Override
                            public ExtendView call(StuffInfo stuffInfo) {
                                ExtendView extendView = new ExtendView(ProjectQuoteActivity.this);
                                extendView.setTitle(stuffInfo.getName());
                                extendView.setExtendData(mPresenter.getStuffs().get(stuffInfo.getId()), spacenameEntities);
                                Timber.tag(TAG).w("-------------->" + stuffInfo.getName());
                                return extendView;
                            }
                        });
            }
        }).subscribe(new ErrorHandleSubscriber<Observable<ExtendView>>(mHDApplication.getAppComponent().rxErrorHandler()) {
            @Override
            public void onNext(Observable<ExtendView> observable) {
                Timber.tag(TAG).w("---->ending");
                observable.subscribe(new Action1<ExtendView>() {
                    @Override
                    public void call(ExtendView extendView) {
                        Timber.tag(TAG).w("ExtendView-------------->" + extendView.getId());
                        mLlQuoteStuffContainer.addView(extendView);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

                //将本读数据加载到view
                String s = mCache.get(CACHE_UPGRADE);
                Map<StuffInfo, List<SpaceBean.SpacenameEntity>> map = mHDApplication
                        .getAppComponent().gson().fromJson(s,
                                new TypeToken<Map<StuffInfo, List<SpaceBean.SpacenameEntity>>>() {
                                }.getType());
                if (mLlQuoteStuffContainer != null && map != null)
                    for (int i = 0; i < mLlQuoteStuffContainer.getChildCount(); i++) {
                        ExtendView child = (ExtendView) mLlQuoteStuffContainer.getChildAt(i);
                        child.loadUpgradeInfo(map);
                    }
            }
        });
    }


    private void getUpgrade() {
        mPresenter.getUpgrade()
                .filter(new Func1<UpgradeInfo, Boolean>() {
                    @Override
                    public Boolean call(UpgradeInfo upgradeInfo) {
                        return upgradeInfo != null;
                    }
                })
                .doOnNext(new Action1<UpgradeInfo>() {
                    @Override
                    public void call(UpgradeInfo upgradeInfo) {
                        ProjectQuoteActivity.this.mUpgradeInfo = upgradeInfo;//将升级包信息保存到内存中
                    }
                })
                .doOnNext(new Action1<UpgradeInfo>() {//厕所
                    @Override
                    public void call(UpgradeInfo upgradeInfo) {
                        Observable.just(upgradeInfo.getToilet())
                                .filter(new Func1<UpgradeInfo.InfoBean, Boolean>() {
                                    @Override
                                    public Boolean call(UpgradeInfo.InfoBean infoBean) {
                                        return infoBean != null;
                                    }
                                }).map(new Func1<UpgradeInfo.InfoBean, String>() {
                            @Override
                            public String call(UpgradeInfo.InfoBean infoBean) {
                                return infoBean.getUg_typename().trim();
                            }
                        }).subscribe(RxTextView.text(mTvQuoteToilet));
                    }
                }).doOnNext(new Action1<UpgradeInfo>() {//水电位
            @Override
            public void call(UpgradeInfo upgradeInfo) {
                Observable.just(upgradeInfo.getPotential())
                        .doOnNext(new Action1<UpgradeInfo.InfoBean>() {
                            @Override
                            public void call(UpgradeInfo.InfoBean infoBean) {
                                if (!infoBean.getUg_typename().isEmpty())
                                    mTvQuotePotential.setText(infoBean.getUg_typename().trim());
                            }
                        }).map(new Func1<UpgradeInfo.InfoBean, String>() {
                    @Override
                    public String call(UpgradeInfo.InfoBean infoBean) {
                        return "¥" + infoBean.getUg_price() + "/" + infoBean.getUg_unit().trim();
                    }
                }).subscribe(RxTextView.text(mTvQuotePotentialPrice), new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).doOnNext(new Action1<UpgradeInfo>() { //石膏线
            @Override
            public void call(UpgradeInfo upgradeInfo) {
                Observable.just(upgradeInfo.getLine())
                        .doOnNext(new Action1<UpgradeInfo.InfoBean>() {
                            @Override
                            public void call(UpgradeInfo.InfoBean infoBean) {
                                if (!infoBean.getUg_typename().isEmpty())
                                    mTvQuoteLine.setText(infoBean.getUg_typename().trim());
                            }
                        }).map(new Func1<UpgradeInfo.InfoBean, String>() {
                    @Override
                    public String call(UpgradeInfo.InfoBean infoBean) {
                        return "¥" + infoBean.getUg_price() + "/" + infoBean.getUg_unit().trim();
                    }
                }).subscribe(RxTextView.text(mTvQuoteLinePrice), new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).doOnNext(new Action1<UpgradeInfo>() {//电视墙
            @Override
            public void call(UpgradeInfo upgradeInfo) {
                if (upgradeInfo.getTv() == null || upgradeInfo.getTv().size() == 0)
                    mTvQuoteTvTitle.setVisibility(View.GONE);
                else
                    Observable.just(upgradeInfo.getTv().get(0).getUg_typename().trim())
                            .subscribe(RxTextView.text(mTvQuoteTvTitle));

            }
        }).doOnNext(new Action1<UpgradeInfo>() {//电视墙的选项
            @Override
            public void call(UpgradeInfo upgradeInfo) {
                Observable.from(upgradeInfo.getTv())
                        .doOnNext(new Action1<UpgradeInfo.InfoBean>() {
                            @Override
                            public void call(UpgradeInfo.InfoBean infoBean) {
                                if (mtvInfos == null)
                                    mtvInfos = new ArrayList<>();
                            }
                        })
                        .flatMap(new Func1<UpgradeInfo.InfoBean, Observable<TextView>>() {
                            @Override
                            public Observable<TextView> call(UpgradeInfo.InfoBean infoBean) {
                                return mPresenter.creatTVView(mtvInfos).zipWith(Observable.just(infoBean), new Func2<TextView, UpgradeInfo.InfoBean, TextView>() {
                                    @Override
                                    public TextView call(TextView textView, UpgradeInfo.InfoBean infoBean) {
                                        textView.setText(infoBean.getUg_name());
                                        textView.setTag(infoBean);
                                        return textView;
                                    }
                                });
                            }
                        }).subscribe(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        mTvQuoteTvContainer.addView(textView);
                        mtvInfos.add(textView);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).doOnNext(new Action1<UpgradeInfo>() {//是否远程施工
            @Override
            public void call(UpgradeInfo upgradeInfo) {
                Observable.from(upgradeInfo.getAction())
                        .doOnNext(new Action1<UpgradeInfo.InfoBean>() {
                            @Override
                            public void call(UpgradeInfo.InfoBean infoBean) {
                                if (mActionInfos == null)
                                    mActionInfos = new ArrayList<>();
                                if (infoBean == null) {
                                    mTvQuoteActionTitle.setVisibility(View.GONE);
                                }
                            }
                        })
                        .flatMap(new Func1<UpgradeInfo.InfoBean, Observable<TextView>>() {
                            @Override
                            public Observable<TextView> call(UpgradeInfo.InfoBean infoBean) {
                                return mPresenter.creatTVView(mActionInfos).zipWith(Observable.just(infoBean), new Func2<TextView, UpgradeInfo.InfoBean, TextView>() {
                                    @Override
                                    public TextView call(TextView textView, UpgradeInfo.InfoBean infoBean) {
                                        textView.setText(infoBean.getUg_name());
                                        textView.setTag(infoBean);
                                        return textView;
                                    }
                                });
                            }
                        }).subscribe(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        mTvQuoteActionContainer.addView(textView);
                        mActionInfos.add(textView);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        }).subscribe(new ErrorHandleSubscriber<UpgradeInfo>(mHDApplication.getAppComponent().rxErrorHandler()) {
            @Override
            public void onNext(UpgradeInfo upgradeInfo) {
                Observable.just(mCache)//获取升级包的本地数据
                        .filter(new Func1<Map<String, String>, Boolean>() {
                            @Override
                            public Boolean call(Map<String, String> map) {
                                return map != null;
                            }
                        }).doOnNext(new Action1<Map<String, String>>() {
                    @Override
                    public void call(Map<String, String> map) {//取出洗手间的缓存
                        Observable.just(map.get(CACHE_TOILET))
                                .filter(new Func1<String, Boolean>() {
                                    @Override
                                    public Boolean call(String s) {
                                        return s != null;
                                    }
                                }).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mAvQuote.setCount(s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }).doOnNext(new Action1<Map<String, String>>() {//取出水电位的缓存
                    @Override
                    public void call(Map<String, String> map) {
                        Observable.just(map.get(CACHE_POTENTIAL))
                                .filter(new Func1<String, Boolean>() {
                                    @Override
                                    public Boolean call(String s) {
                                        return s != null;
                                    }
                                }).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mEtQuotePotentialSum.setText(s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }).doOnNext(new Action1<Map<String, String>>() {//取出石膏线的缓存
                    @Override
                    public void call(Map<String, String> map) {
                        Observable.just(map.get(CACHE_LINE))
                                .filter(new Func1<String, Boolean>() {
                                    @Override
                                    public Boolean call(String s) {
                                        return s != null;
                                    }
                                }).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mEtQuoteLineSum.setText(s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }).doOnNext(new Action1<Map<String, String>>() {//取出电视墙
                    @Override
                    public void call(Map<String, String> map) {
                        Observable.just(map.get(CACHE_TV))
                                .filter(new Func1<String, Boolean>() {
                                    @Override
                                    public Boolean call(String s) {
                                        return s != null;
                                    }
                                }).subscribe(new Action1<String>() {
                            @Override
                            public void call(final String id) {
                                Observable.from(mtvInfos)
                                        .subscribe(new Action1<TextView>() {
                                            @Override
                                            public void call(TextView textView) {
                                                UpgradeInfo.InfoBean info = (UpgradeInfo.InfoBean) textView.getTag();
                                                if (id.equals(info.getId())) {//根据本地数据恢复之前的状态
                                                    changePackage(textView, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
                                                } else {
                                                    changePackage(textView, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                                                }
                                            }
                                        });
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }).doOnNext(new Action1<Map<String, String>>() {//取出远程施工
                    @Override
                    public void call(Map<String, String> map) {
                        Observable.just(map.get(CACHE_ACTION))
                                .filter(new Func1<String, Boolean>() {
                                    @Override
                                    public Boolean call(String s) {
                                        return s != null;
                                    }
                                }).subscribe(new Action1<String>() {
                            @Override
                            public void call(final String id) {
                                Observable.from(mActionInfos)
                                        .subscribe(new Action1<TextView>() {
                                            @Override
                                            public void call(TextView textView) {
                                                UpgradeInfo.InfoBean info = (UpgradeInfo.InfoBean) textView.getTag();
                                                if (id.equals(info.getId())) {//根据本地数据恢复之前的状态
                                                    changePackage(textView, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
                                                } else {
                                                    changePackage(textView, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                                                }
                                            }
                                        });
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }).subscribe(new Action1<Map<String, String>>() {
                    @Override
                    public void call(Map<String, String> stringStringMap) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mTvQuoteActionTitle.setVisibility(View.GONE);
                mTvQuoteTvTitle.setVisibility(View.GONE);
                mRlQuotePotential.setVisibility(View.GONE);
                mRlQuoteLine.setVisibility(View.GONE);
            }
        });
    }

    private void getProjectPackage() {
        mPresenter.projectPackage()
                .doOnNext(new Action1<PackageInfo>() {
                    @Override
                    public void call(PackageInfo packageInfo) {
                        Timber.tag(TAG).w("next---------");
                        if (mPackageInfos == null)
                            mPackageInfos = new ArrayList<TextView>();
                    }
                })
                .flatMap(new Func1<PackageInfo, Observable<TextView>>() {
                    @Override
                    public Observable<TextView> call(PackageInfo packageInfo) {

                        return mPresenter.creatPackageView(mPackageInfos).zipWith(Observable.just(packageInfo), new Func2<TextView, PackageInfo, TextView>() {
                            @Override
                            public TextView call(TextView textView, PackageInfo packageInfo) {
                                Timber.tag(TAG).w("4---------" + textView.toString());
                                textView.setText(packageInfo.getPk_name());

                                textView.setTag(packageInfo);
                                return textView;
                            }
                        });
                    }
                })
                .doOnNext(new Action1<TextView>() {
                    @Override
                    public void call(TextView textView) {
                        Timber.tag(TAG).w("add---------");
                        mLlQuotePackageContainer.addView(textView);
                    }
                }).subscribe(new ErrorHandleSubscriber<TextView>(mHDApplication.getAppComponent().rxErrorHandler()) {
            @Override
            public void onNext(TextView textView) {
                mPackageInfos.add(textView);
                Timber.tag(TAG).w("5---------");
                if (mPackageInfos.indexOf(textView) == 0) {
                    mSelctPackageInfo = (PackageInfo) textView.getTag();
                    changePackage(textView, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
                }

                Observable.just(mCache)//获取基础包的本地数据
                        .filter(new Func1<Map<String, String>, Boolean>() {
                            @Override
                            public Boolean call(Map<String, String> map) {
                                return map != null;
                            }
                        }).map(new Func1<Map<String, String>, String>() {
                    @Override
                    public String call(Map<String, String> map) {
                        return map.get(CACHE_PACKAGE);
                    }
                }).filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s != null;
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(final String id) {
                        Observable.from(mPackageInfos)
                                .subscribe(new Action1<TextView>() {
                                    @Override
                                    public void call(TextView textView) {
                                        PackageInfo info = (PackageInfo) textView.getTag();
                                        if (id.equals(info.getId() + "")) {//根据本地数据恢复之前的状态
                                            changePackage(textView, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
                                            mSelctPackageInfo = info;
                                        } else {
                                            changePackage(textView, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
                                        }
                                    }
                                });
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
     * 设置选择的基础包
     *
     * @aram selctPackageInfo
     */
    @Override
    public void setSelctPackageInfo(PackageInfo selctPackageInfo) {
        mSelctPackageInfo = selctPackageInfo;
    }

    @Override
    public void changePackage(TextView textView, int color, Drawable drawablebyResource) {
        textView.setTextColor(color);
        textView.setBackground(drawablebyResource);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
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

    @OnClick({R.id.rl_freeterm, R.id.btn_quote_action})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_freeterm:
                FreeTemActivity.starActivity(this, mProjectInfo.getId());
                break;
            case R.id.btn_quote_action:
                launchQuoteList();
                break;
        }
    }

    @Subscriber(tag = FREE_ITEMS)
    public void onReceive(Observable<List<FreeTermlistBean>> list) {
        this.mListObservable = list;
    }


    @Subscriber(tag = KILL_PROJECT_QUOTE)
    public void onKill(boolean b) {
        killMyself();
    }


    @Override
    protected boolean useEventBus() {
        return true;
    }

    private void launchQuoteList() {
        Observable.just(new Bundle())
                .doOnNext(new Action1<Bundle>() {//检查基础包数据,添加进bundle
                    @Override
                    public void call(final Bundle bundle) {
                        Observable.just(mSelctPackageInfo)
                                .filter(new Func1<PackageInfo, Boolean>() {
                                    @Override
                                    public Boolean call(PackageInfo packageInfo) {
                                        return packageInfo != null;
                                    }
                                }).doOnNext(new Action1<PackageInfo>() {
                            @Override
                            public void call(PackageInfo packageInfo) {//添加到本地缓存
                                mCache.put(CACHE_PACKAGE, packageInfo.getId() + "");
                            }
                        }).map(new Func1<PackageInfo, String>() {
                            @Override
                            public String call(PackageInfo packageInfo) {
                                float acreage = Float.parseFloat(mProjectInfo.getPm_acreage());
                                bundle.putString(PACKAGE_PACKAGE_ID, packageInfo.getId() + "");//基础包id
                                return String.format("%.2f㎡ x %d元/㎡=￥%.2f", acreage
                                        , packageInfo.getPk_price(), acreage * packageInfo.getPk_price());
                            }
                        }).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                bundle.putString(PACKAGE_PRICE_EXPRESSION, s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }).doOnNext(new Action1<Bundle>() {//检查升级包,添加进bundle
            @Override
            public void call(Bundle bundle) {
                if (mLlQuoteStuffContainer != null) {
                    for (int i = 0; i < mLlQuoteStuffContainer.getChildCount(); i++) {
                        ExtendView child = (ExtendView) mLlQuoteStuffContainer.getChildAt(i);
                        child.addUpgradeInfo(mUpgradInfos);
                    }
                }
                bundle.putString(PACKAGE_UPGRADE_INFO, mHDApplication.getAppComponent().gson().toJson(mUpgradInfos));
                //将数据存储到本地
                mCache.put(CACHE_UPGRADE, mHDApplication.getAppComponent().gson().toJson(mUpgradInfos));
            }
        }).doOnNext(new Action1<Bundle>() {//检查自由项添加进bundle
            @Override
            public void call(final Bundle bundle) {
                if (mListObservable != null) {
                    mListObservable
                            .subscribe(new Action1<List<FreeTermlistBean>>() {
                                @Override
                                public void call(List<FreeTermlistBean> list) {
                                    bundle.putString(PACKAGE_FREE_INFO, mHDApplication.getAppComponent().gson().toJson(list));
                                    Timber.tag(TAG).w(mHDApplication.getAppComponent().gson().toJson(list));
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            });
                }
            }
        }).doOnNext(new Action1<Bundle>() {//检查多选的升级包添加进bundle
            @Override
            public void call(final Bundle bundle) {

                Observable.just(mUpgrads)
                        .doOnNext(new Action1<List<UpgradeInfo.InfoBean>>() {
                            @Override
                            public void call(List<UpgradeInfo.InfoBean> infoBeen) {
                                infoBeen.clear();
                            }
                        })
                        .doOnNext(new Action1<List<UpgradeInfo.InfoBean>>() {
                            @Override
                            public void call(List<UpgradeInfo.InfoBean> InfoBean) {//检查厕所
                                if (mAvQuote.getCount() > 0) {
                                    mUpgradeInfo.getToilet().setInput_num(mAvQuote.getCount() + "");
                                    InfoBean.add(mUpgradeInfo.getToilet());
                                    //存入本地缓存
                                    mCache.put(CACHE_TOILET, mAvQuote.getCount() + "");
                                }
                            }
                        }).doOnNext(new Action1<List<UpgradeInfo.InfoBean>>() {//检查水电位
                    @Override
                    public void call(List<UpgradeInfo.InfoBean> InfoBean) {
                        String num = mEtQuotePotentialSum.getText().toString().trim();
                        if (!TextUtils.isEmpty(num)) {
                            mUpgradeInfo.getPotential().setInput_num(num);
                            InfoBean.add(mUpgradeInfo.getPotential());
                            //存入本地缓存
                            mCache.put(CACHE_POTENTIAL, num);
                        }
                    }
                }).doOnNext(new Action1<List<UpgradeInfo.InfoBean>>() {//检查石膏线使用
                    @Override
                    public void call(List<UpgradeInfo.InfoBean> infoBean) {
                        String num = mEtQuoteLineSum.getText().toString().trim();
                        if (!TextUtils.isEmpty(num)) {
                            mUpgradeInfo.getLine().setInput_num(num);
                            infoBean.add(mUpgradeInfo.getLine());
                            ////存入本地缓存
                            mCache.put(CACHE_LINE, num);
                        }

                    }
                }).doOnNext(new Action1<List<UpgradeInfo.InfoBean>>() {//检查电视墙
                    @Override
                    public void call(List<UpgradeInfo.InfoBean> infoBeen) {
                        for (TextView textView : mtvInfos) {
                            if (textView.getCurrentTextColor() == UiUtils.getColor(R.color.colorLightOrange)) {//说明被选择
                                UpgradeInfo.InfoBean infoBean = (UpgradeInfo.InfoBean) textView.getTag();//拿出实体
                                infoBean.setInput_num("1");
                                infoBeen.add(infoBean);
                                //存入本地缓存
                                mCache.put(CACHE_TV, infoBean.getId());
                            }
                        }
                    }
                }).doOnNext(new Action1<List<UpgradeInfo.InfoBean>>() {//检查远程施工
                    @Override
                    public void call(List<UpgradeInfo.InfoBean> infoBeen) {
                        for (TextView textView : mActionInfos) {
                            if (textView.getCurrentTextColor() == UiUtils.getColor(R.color.colorLightOrange)) {//说明被选择
                                UpgradeInfo.InfoBean infoBean = (UpgradeInfo.InfoBean) textView.getTag();//拿出实体
                                infoBean.setInput_num("1");
                                infoBeen.add(infoBean);
                                //存入本地缓存
                                mCache.put(CACHE_ACTION, infoBean.getId());
                            }
                        }
                    }
                }).subscribe(new Action1<List<UpgradeInfo.InfoBean>>() {//存入bundle
                    @Override
                    public void call(List<UpgradeInfo.InfoBean> infoBean) {
                        bundle.putString(PACKAGE_OTHER_UPGRADE, mHDApplication.getAppComponent().gson().toJson(infoBean));
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
            public void call(Bundle bundle) {//将所以缓存存入sp
                DataHelper.SetStringSF(getApplicationContext(), mProjectInfo.getId()
                        , mHDApplication.getAppComponent().gson().toJson(mCache));
            }
        }).subscribe(new Action1<Bundle>() {
            @Override
            public void call(Bundle bundle) {
                bundle.putString(PACKAGE_PROJECT_ID, mProjectInfo.getId());//项目id
                bundle.putSerializable(PROJECTINFO_DATA, mProjectInfo);
                Intent intent = new Intent(getApplicationContext(), QuoteListActivity.class);
                launchActivity(intent.putExtras(bundle));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });


    }
}
