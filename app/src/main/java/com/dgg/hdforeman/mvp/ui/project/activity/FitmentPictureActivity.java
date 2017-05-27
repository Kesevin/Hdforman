package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DividerItemDecoration;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerFitmentPictureComponent;
import com.dgg.hdforeman.di.module.FitmentPictureModule;
import com.dgg.hdforeman.mvp.contract.project.FitmentPictureContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.FitmentPicturePresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.FitmentPictureAdapter;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
 * Created by Administrator on 2016/11/16.
 */

public class FitmentPictureActivity extends BacksActivity<FitmentPicturePresenter> implements FitmentPictureContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String FITMENT_PICTURE = "fitment_picture";
    private final int PAGE_SIZE = 10;
    @BindView(R.id.fitment_pic_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fitment_pic_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.noData)
    LinearLayout noData;

    private ProjectInfoResponse mData;//项目信息
    private List<String> mList = new ArrayList<>();
    private FitmentPictureAdapter adapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFitmentPictureComponent
                .builder()
                .appComponent(appComponent)
                .fitmentPictureModule(new FitmentPictureModule(this))//请将FitmentPictureModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_fitment_picture_activity, null, false);
    }

    @Override
    protected void initData() {
        title.setText("装修示意图");
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(FITMENT_PICTURE);
        initRecyleView();
        initAdater();
        mPresenter.getFitmentPictureData(mData.getId());
    }

    private void initAdater() {
        adapter = new FitmentPictureAdapter(mList);
        mRecyclerView.setAdapter(adapter);
    }

    private void initRecyleView() {
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor,
                R.color.colorLightRed, R.color.colorPrimaryDark);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void bindDataToView(List<String> list) {
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mList.clear();
        mPresenter.getFitmentPictureData(mData.getId());
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintNodata() {
        noData.setVisibility(View.GONE);
    }

}