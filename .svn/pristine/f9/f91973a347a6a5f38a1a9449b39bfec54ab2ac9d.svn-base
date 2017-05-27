package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DividerItemDecoration;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerMessageComponent;
import com.dgg.hdforeman.di.module.MessageModule;
import com.dgg.hdforeman.mvp.contract.mine.MessageContract;
import com.dgg.hdforeman.mvp.model.been.MessageBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.mine.MessagePresenter;
import com.dgg.hdforeman.mvp.ui.MainActivity;
import com.dgg.hdforeman.mvp.ui.mine.adapter.MessageAdapter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.DataHelper.CURRENT_USER_ID;
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
 * Created by Administrator on 2016/12/2.
 */

public class MessageActivity extends BacksActivity<MessagePresenter> implements MessageContract.View, SwipeRefreshLayout.OnRefreshListener {
    public static final String MESSAGE = "message";
    private final int PAGE_SIZE = 10;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.message_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.message_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;
    private ProjectInfoResponse mData;
    private MessageAdapter adapter;
    private List<MessageBean> mList = new ArrayList<>();
    private Paginate mPaginate;

    private boolean isLoadingMoreEnd=false;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕
    private String type="0";
    private String userId;
    private String proId;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMessageComponent
                .builder()
                .appComponent(appComponent)
                .messageModule(new MessageModule(this))//请将MessageModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.mine_message_activity, null, false);
    }

    @Override
    protected void initData() {
        title.setText("我的消息");
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(MESSAGE);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");//1 我的消息中心，2 项目消息
        userId = DataHelper.getStringSF(this, CURRENT_USER_ID);
        initAdapter();
        if (type.equals("1")) {
            mPresenter.getMineMessage(false, userId, PAGE_SIZE);
        } else {
            title.setText("施工消息");
            if(mData!=null){
                proId=mData.getId();
            }else{
                proId=bundle.getString("proId");
            }
            mPresenter.getProMessage(false, userId, proId, PAGE_SIZE);
        }
        initPaginate();
    }

    private void initAdapter() {
        adapter = new MessageAdapter(mList);
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
    public void startLoadMore() {
        isLoadingMoreEnd = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMoreEnd = false;
    }

    @Override
    public void setLoadEnd(boolean b) {
        this.isLoadEnd = b;
    }

    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    if (type.equals("1")) {
                        mPresenter.getMineMessage(false, userId, PAGE_SIZE);
                    } else {
                        mPresenter.getProMessage(false, userId, proId, PAGE_SIZE);
                    }
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
            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(mRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }

    }

    @Override
    public void bindDataToView(List<MessageBean> list) {
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintNodata() {
        noData.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        mList.clear();
        if (type.equals("1")) {
            mPresenter.getMineMessage(true, userId, PAGE_SIZE);
        } else {
            mPresenter.getProMessage(true, userId, proId, PAGE_SIZE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            LinkedList<BaseActivity> copy;
            synchronized (BaseActivity.class) {
                copy = new LinkedList<BaseActivity>(mApplication.getActivityList());
            }
            boolean hasMainActivity=false;
            for (BaseActivity baseActivity : copy) {
                if(baseActivity.getClass().getSimpleName().equals("MainActivity")){
                    hasMainActivity=true;
                }
            }
            if(hasMainActivity){
                finish();
            }else{
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}