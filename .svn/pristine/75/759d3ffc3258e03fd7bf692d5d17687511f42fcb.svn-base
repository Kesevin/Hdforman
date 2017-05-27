package com.dgg.hdforeman.mvp.ui.project.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerLimitTimeComponent;
import com.dgg.hdforeman.di.module.LimitTimeModule;
import com.dgg.hdforeman.mvp.contract.project.LimitTimeContract;
import com.dgg.hdforeman.mvp.model.been.BudgetSheetBean;
import com.dgg.hdforeman.mvp.model.been.PacBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.UppacBean;
import com.dgg.hdforeman.mvp.presenter.project.LimitTimePresenter;
import com.dgg.hdforeman.mvp.ui.base.HDFragment;
import com.dgg.hdforeman.mvp.ui.project.adapter.ConstructionPeriodAdapter;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jess.arms.utils.CharactorHandler;
import com.jess.arms.utils.UiUtils;
import com.zhy.autolayout.AutoLinearLayout;

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
 * Created by Administrator on 2016/11/29.
 */

public class BudgetFragment extends HDFragment<LimitTimePresenter> implements LimitTimeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.budget_customer_name)
    TextView budgetCustomerName;
    @BindView(R.id.budget_money)
    TextView budgetMoney;
    @BindView(R.id.budget_worker_name)
    TextView budgetWorkerName;
    @BindView(R.id.budget_time)
    TextView budgetTime;
    @BindView(R.id.basic_package_ll)
    AutoLinearLayout basicPackageLl;
    @BindView(R.id.basic_package_describe)
    TextView basicPackageDescribe;
    @BindView(R.id.upgrade_package_ll)
    AutoLinearLayout upgradePackageLl;
    @BindView(R.id.upgrade_package_allMoney)
    TextView upgradePackageAllMoney;
    @BindView(R.id.budget_allMoney)
    TextView budgetAllMoney;
    @BindView(R.id.budget_remark)
    TextView budgetRemark;
    @BindView(R.id.budget_address)
    TextView budgetAddress;
    @BindView(R.id.manager_money)
    TextView managerMoney;
    @BindView(R.id.manager_allMoney)
    TextView managerAllMoney;
    @BindView(R.id.protect_money)
    TextView protectMoney;
    @BindView(R.id.protect_allMoney)
    TextView protectAllMoney;
    @BindView(R.id.pop_money)
    TextView popMoney;
    @BindView(R.id.pop_allMoney)
    TextView popAllMoney;
    @BindView(R.id.budget_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ProjectInfoResponse mData;
    private boolean setData;
    private WaitingDialog mWaitingDialog;

    public static BudgetFragment newInstance() {
        BudgetFragment fragment = new BudgetFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getBudgetLimitData(mData.getId(), "2");
    }


    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerLimitTimeComponent
                .builder()
                .appComponent(appComponent)
                .limitTimeModule(new LimitTimeModule(this))//请将BudgetModule()第一个首字母改为小写
                .build()
                .inject(this);
    }


    @Override
    protected View initView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.budget_fragment, null, false);
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mWaitingDialog = new WaitingDialog(getActivity());
        mWaitingDialog.setCanceledOnTouchOutside(false);
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
        this.mData = (ProjectInfoResponse) data;
        this.setData = true;
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
        getActivity().finish();
    }

    @Override
    public void bindDataToView(BudgetSheetBean budgetSheetBean) {
        checkNotNull(budgetSheetBean);
        budgetCustomerName.setText(budgetSheetBean.getPro().getPm_cusname());
        budgetMoney.setText("¥ " + budgetSheetBean.getPro().getPm_quote());
        budgetWorkerName.setText(budgetSheetBean.getPro().getPm_workmanagername());
        budgetTime.setText(budgetSheetBean.getPro().getPm_quotetime());
        budgetAddress.setText(budgetSheetBean.getPro().getPm_housesaddress());
        managerMoney.setText(CharactorHandler.excludeEmptyWithZero(budgetSheetBean.getPro().getPm_baseprices()) + "元 x 8% = ");
        managerAllMoney.setText(CharactorHandler.excludeEmptyWithZero(budgetSheetBean.getPro().getPm_managefee()) + "元");
        protectMoney.setText("5元/㎡ x " + CharactorHandler.excludeEmptyWithZero(budgetSheetBean.getPro().getPm_acreage()) + "㎡ = ");
        protectAllMoney.setText(CharactorHandler.excludeEmptyWithZero(budgetSheetBean.getPro().getPm_protectfee()) + "元");
        popMoney.setText("6元/㎡ x " + CharactorHandler.excludeEmptyWithZero(budgetSheetBean.getPro().getPm_acreage()) + "㎡ = ");
        popAllMoney.setText(CharactorHandler.excludeEmptyWithZero(budgetSheetBean.getPro().getPm_cleanfee()) + "元");
        if (budgetSheetBean.getPac() != null && budgetSheetBean.getPac().size() != 0) {
            basicPackageLl.removeAllViews();
            for (PacBean item : budgetSheetBean.getPac()) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_budget, null);
                TextView basicNameTv = (TextView) view.findViewById(R.id.basic_package_name);
                basicNameTv.setText(item.getPp_pkname());
                TextView basicMoneyTv = (TextView) view.findViewById(R.id.basic_package_money);
                basicMoneyTv.setText(item.getPp_number() + "㎡" + " x " + item.getPp_pkprice() + "元/㎡" + "=");
                TextView basicAllMoneyTv = (TextView) view.findViewById(R.id.basic_package_allMoney);
                basicAllMoneyTv.setText("￥" + item.getPp_sum());
                basicPackageLl.addView(view);
                basicPackageDescribe.setText(item.getPp_remark());
            }
        }
        Double sumMoneys = 0.0;
        if (budgetSheetBean.getUppac() != null && budgetSheetBean.getUppac().size() != 0) {
            upgradePackageLl.removeAllViews();
            for (UppacBean item : budgetSheetBean.getUppac()) {
                if (item.getUg_ifthird() == 1)
                    continue;
                if (item.getPu_number() != 0) {
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_upgrade_package, null);
                    TextView nameTv = (TextView) view.findViewById(R.id.upgradePackage_name);
                    TextView moneyTv = (TextView) view.findViewById(R.id.upgradePackage_money);
                    nameTv.setText(item.getPu_ugname() + ":");
                    Double sum = item.getSumMoney();
                    moneyTv.setText(String.format("%.2f", item.getPu_number()) + item.getPu_ugunit() + " x " + item.getPu_ugprice() + "元/" + item.getPu_ugunit() + " = " + CommonUtil.formatMoney(String.valueOf(sum), 2) + "元");
                    if (sum != null)
                        sumMoneys += sum;
                    upgradePackageLl.addView(view);
                }
            }
        }
        upgradePackageAllMoney.setText("￥" + CommonUtil.formatMoney(String.valueOf(sumMoneys), 2));
//        upgradePackageAllMoney.setText("￥ " + budgetSheetBean.getPro().getPm_quote());
        if (sumMoneys == null)
            upgradePackageAllMoney.setText("0");
        budgetAllMoney.setText("￥" + CommonUtil.formatMoney(budgetSheetBean.getPro().getPm_quote(), 2));
        budgetRemark.setText(budgetSheetBean.getPro().getPm_remark());
    }

    @OnClick(R.id.budget_send)
    public void onClick() {
        mPresenter.sendCustomer(mData.getId());
    }

    @Override
    public void setAdapter(ConstructionPeriodAdapter adapter) {

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
        if (setData) {
            mPresenter.getBudgetLimitData(mData.getId(), "2");
        }
    }
}