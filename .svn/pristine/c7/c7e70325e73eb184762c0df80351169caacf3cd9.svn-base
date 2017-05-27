package com.dgg.hdforeman.mvp.ui.project.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerMaterialBagComponent;
import com.dgg.hdforeman.di.module.MaterialBagModule;
import com.dgg.hdforeman.mvp.contract.project.MaterialBagContract;
import com.dgg.hdforeman.mvp.contract.project.ProjectContract;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.MaterialBagPresenter;
import com.dgg.hdforeman.mvp.ui.base.HDFragment;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInformationActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.ConstructMaterialAdapter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.activity.PhotoPreviewActivity;

import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.FILL_LIST_ITEM;
import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.TAB_METHOD;
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
 * Created by Rex on 2016/11/18.
 */

public class MaterialBagFragment extends HDFragment<MaterialBagPresenter> implements MaterialBagContract.View ,SwipeRefreshLayout.OnRefreshListener{

    private  int order;
    private int type;
    private ProjectContract.TabTypeItem mTabTypeItem;
    @BindView(R.id.material_rev)
    RecyclerView materialRev;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;
    private ConstructMaterialAdapter materialAdapter;

    private ArrayList<String> imgList = new ArrayList<String>();
    private List<MaterialInfo> materialList = new ArrayList<MaterialInfo>();
    private  String proid;
    private ProjectInfoResponse mData;
    private Paginate mPaginate;
    private boolean isLoadingMoreEnd;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕

    private boolean isFrist;
    private boolean isCreate;

    public static MaterialBagFragment newInstance(int order,int type, ProjectContract.TabTypeItem typeItem) {
        MaterialBagFragment fragment = new MaterialBagFragment();
         fragment.order=order;
        fragment.type = type;
        fragment.mTabTypeItem = typeItem;
        CommonUtil.logDebug("type "+order);
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerMaterialBagComponent
                .builder()
                .appComponent(appComponent)
                .materialBagModule(new MaterialBagModule(this))//请将MaterialBagModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.material_bag_fragment, null, false);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mData =(ProjectInfoResponse)bundle.getSerializable("material");
        proid=  mData.getId();
        initRecyclerView();
        initPaginate();
        if (isCreate) {
            fillListItem();
            isFrist = true;
        }
    }

    private void initRecyclerView() {
        refreshLayout.setOnRefreshListener(this);
        materialRev.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mPresenter.initAdapter();
        materialRev.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 上拉加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getMaterial(proid,order,type,false);
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
            mPaginate = Paginate.with(materialRev, callbacks)
                    .setLoadingTriggerThreshold(0)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(teamAddRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);

        }
    }

    @Override
    public void setData() {
        this.isCreate = true;
    }

    /**
     * 填充列表数据
     */
    private void fillListItem() {
        if (!isFrist) {//第一次才刷新，并且只刷新一次
            mPresenter.getMaterial(proid,order,type,true);
            this.isFrist = true;
        }
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传bundle,里面存一个what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事,和message同理
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onActivityCreated
     * 还没执行,setData里调用presenter的方法时,是会报空的,因为dagger注入是在onActivityCreated
     * 方法中执行的,如果要做一些初始化操作,可以不必让外部调setData,在内部onActivityCreated中
     * 初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {
        if (data instanceof Bundle) {//接受到上层fragment的请求根据方法唯一码分配对应的方法
            Bundle bundle = (Bundle) data;
            int method = bundle.getInt(TAB_METHOD);
            switch (method) {
                case FILL_LIST_ITEM://填充列表数据
                    fillListItem();
                    break;
            }
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void stopRefresh() {
        if(refreshLayout.isRefreshing())
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void startLoadMore() {
        isLoadingMoreEnd = true;
    }

    @Override
    public void setIsLoad(boolean isLoad) {
        this.isLoadEnd = isLoad;
    }

    @Override
    public void stopLoadeMore() {
        isLoadingMoreEnd = false;
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
    public ProjectContract.TabTypeItem getTabItem() {
        return mTabTypeItem;
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(getActivity(), intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void callPhone(MaterialInfo info) {
        if(info==null)
            return;
        if(TextUtils.isEmpty(info.getSfcontactno())){
            showMessage("暂无联系号码！");
        }else{
        callPhone(info.getSfcontactno());
        }
    }

    @Override
    public void previewImage(MaterialInfo info,int Position) {
        if(TextUtils.isEmpty(info.getAccessory()))
            return;
        imgList.clear();
        imgList.add(CommonUtil.getImageUrl(info.getAccessory(),1500,1500));
        startActivity(PhotoPreviewActivity.newIntent(getActivity(), null, imgList, 1));
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        materialRev.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
            mPresenter.getMaterial(proid,order,type,true);
    }
    public void callPhone(final String phone) {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + phone)));
            }
        }, RxPermissions.getInstance(getActivity()), this, mHDApplication.getAppComponent().rxErrorHandler());
    }
}