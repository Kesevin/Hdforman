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

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerAddMembersComponent;
import com.dgg.hdforeman.di.module.AddMembersModule;
import com.dgg.hdforeman.mvp.contract.project.AddMembersContract;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.been.WorkerInfo;
import com.dgg.hdforeman.mvp.presenter.project.AddMembersPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.AddMemberAdapter;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.dgg.hdforeman.mvp.ui.project.activity.ConstructTeamActivity.CONSTRUCT_TEAM_DATA;
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
 * Created by Administrator on 2016/11/15.
 */

public class AddMembersActivity extends BacksActivity<AddMembersPresenter> implements AddMembersContract.View, AddMembersContract.NetUtil, SwipeRefreshLayout.OnRefreshListener {

    public static final String ADD_MEMBERS = "add_members";
    @BindView(R.id.rv_add_members)
    RecyclerView mRecyclerView;
    @BindView(R.id.rv_add_SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;

    private ArrayList<ConstructTeamBean> mPreList;
    private List<TeamBean> mList = new ArrayList<>();
    private AddMemberAdapter adapter;
    private ProjectInfoResponse mData;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAddMembersComponent
                .builder()
                .appComponent(appComponent)
                .addMembersModule(new AddMembersModule(this))//请将AddMembersModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.add_members_activity, null, false);
    }

    @Override
    protected void initData() {
        mPreList = (ArrayList<ConstructTeamBean>) getIntent().getExtras().getSerializable(ADD_MEMBERS);
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable((CONSTRUCT_TEAM_DATA));
        initRecyleView();
        initAdapter();
        mPresenter.getMembersList(mPreList);
    }

    private void initRecyleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
    }

    private void initAdapter() {
        adapter = new AddMemberAdapter(this, this,mList);
        mRecyclerView.setAdapter(adapter);
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
    public void bindDataToView(List<TeamBean> teamList) {
        mList.addAll(teamList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mList.clear();
        mPresenter.getMembersList(mPreList);
    }

    private TeamBean mTeamBean;

    @Override
    public void addTeamMemberUtil(TeamBean teamBean) {
        if (mData == null)
            return;
        this.mTeamBean=teamBean;
        mPresenter.addTeamMember(mData.getId(), String.valueOf(teamBean.getTeamUserList().get(0).getWk_userid()));
    }

    @Override
    public void removeTeamList() {
        ConstructTeamBean constructTeamBean=new ConstructTeamBean();
        List<WorkerInfo> list=new ArrayList<>();
        for (TeamSearchResult teamSearchResult: mTeamBean.getTeamUserList()) {
            WorkerInfo workerInfo=new WorkerInfo();
            workerInfo.setWk_headpic(teamSearchResult.getWk_headpic());
            workerInfo.setPs_wkid(String.valueOf(teamSearchResult.getWk_userid()));
            workerInfo.setPs_wkname(teamSearchResult.getWk_name());
            list.add(workerInfo);
        }
        constructTeamBean.setCcnstructTeamUserList(list);
        mPreList.add(constructTeamBean);
//        mList.remove(mTeamBean);
        onRefresh();
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