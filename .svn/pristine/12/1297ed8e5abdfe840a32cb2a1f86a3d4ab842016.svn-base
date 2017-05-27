package com.dgg.hdforeman.mvp.ui.project.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerLimitTimeComponent;
import com.dgg.hdforeman.di.module.LimitTimeModule;
import com.dgg.hdforeman.mvp.contract.project.LimitTimeContract;
import com.dgg.hdforeman.mvp.model.been.BudgetSheetBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.LimitTimePresenter;
import com.dgg.hdforeman.mvp.ui.base.HDFragment;
import com.dgg.hdforeman.mvp.ui.project.adapter.ConstructionPeriodAdapter;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jess.arms.utils.UiUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;

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
 * Created by Administrator on 2016/11/28.
 */

public class LimitTimeFragment extends HDFragment<LimitTimePresenter> implements LimitTimeContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.owner)
    TextView owner;
    @BindView(R.id.offer_amount)
    TextView offerAmount;
    @BindView(R.id.foreman)
    TextView foreman;
    @BindView(R.id.offer_time)
    TextView offerTime;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.scrollview)
    NestedScrollView scrollview;
    @BindView(R.id.send_owner)
    TextView sendOwner;
    @BindView(R.id.send_owner_lay)
    AutoRelativeLayout sendOwnerLay;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private ProjectInfoResponse mData;
    private WaitingDialog mWaitingDialog;
    private SimpleDateFormat mOirginFormat;
    private SimpleDateFormat mFormat;

    public static LimitTimeFragment newInstance() {
        LimitTimeFragment fragment = new LimitTimeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getBudgetLimitData(mData.getId(), "3");
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerLimitTimeComponent
                .builder()
                .appComponent(appComponent)
                .limitTimeModule(new LimitTimeModule(this))//请将LimitTimeModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.limit_time_fragment, null, false);
    }

    @Override
    protected void initData() {
        mOirginFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mWaitingDialog = new WaitingDialog(getActivity());
        mWaitingDialog.setCanceledOnTouchOutside(false);
        initRecyclerView();
        mPresenter.initAdapter();
    }

    private void initRecyclerView() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(mLinearLayoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
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
        if (data != null) {
            mData = (ProjectInfoResponse) data;
        }
    }


    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        if (mWaitingDialog != null)
            mWaitingDialog.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);
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


    @OnClick(R.id.send_owner_lay)
    public void onClick() {
        mPresenter.sendCustomer(mData.getId());
    }

    @Override
    public void bindDataToView(BudgetSheetBean budgetSheetBean) {
        if (budgetSheetBean != null) {
            owner.setText(budgetSheetBean.getPro().getPm_cusname());
            foreman.setText(budgetSheetBean.getPro().getPm_workmanagername());
            offerAmount.setText("¥ "+budgetSheetBean.getPro().getPm_quote());
            offerTime.setText(budgetSheetBean.getPro().getPm_quotetime());
            try {
                startTime.setText(mFormat.format(mOirginFormat.parse(budgetSheetBean.getPro().getPm_startdate())));
                endTime.setText(mFormat.format(mOirginFormat.parse(budgetSheetBean.getPro().getPm_planfinishdate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mPresenter.transformData(budgetSheetBean.getStage());
        }
    }

    @Override
    public void setAdapter(ConstructionPeriodAdapter adapter) {
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void hideWaiting() {
        if (mWaitingDialog != null)
            mWaitingDialog.dismiss();
    }

    @Override
    public void showWaiting() {
        mWaitingDialog.show();
    }

    @Override
    public void onRefresh() {
        mPresenter.getBudgetLimitData(mData.getId(), "3");
    }
}