package com.dgg.hdforeman.mvp.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.app.view.StarBar;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerMineComponent;
import com.dgg.hdforeman.di.module.MineModule;
import com.dgg.hdforeman.mvp.contract.mine.MineContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.MineBean;
import com.dgg.hdforeman.mvp.presenter.mine.MinePresenter;
import com.dgg.hdforeman.mvp.ui.base.HDFragment;
import com.dgg.hdforeman.mvp.ui.mine.activity.DataActivity;
import com.dgg.hdforeman.mvp.ui.mine.activity.MessageActivity;
import com.dgg.hdforeman.mvp.ui.mine.activity.ScoreActivity;
import com.dgg.hdforeman.mvp.ui.mine.activity.SetActivity;
import com.dgg.hdforeman.mvp.ui.mine.activity.TeamActivity;
import com.dgg.hdforeman.mvp.ui.tool.activity.AnnouncementDetailActivity;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/20.
 */

public class MineFragment extends HDFragment<MinePresenter> implements MineContract.View,SwipeRefreshLayout.OnRefreshListener {

    @Nullable
    @BindView(R.id.toolbar_back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more_btn)
    ImageButton moreBtn;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.mine_user_image)
    ImageView mineUserImage;
    @BindView(R.id.mine_list_data)
    LinearLayout mineListData;
    @BindView(R.id.mine_list_team)
    LinearLayout mineListTeam;
    @BindView(R.id.mine_list_contract)
    LinearLayout mineListContract;
    @BindView(R.id.mine_list_insurance)
    LinearLayout mineListInsurance;
    @BindView(R.id.mine_list_score)
    LinearLayout mineListScore;
    @BindView(R.id.mine_list_set)
    LinearLayout mineListSet;
    @BindView(R.id.mine_ratingBar)
    StarBar mineRatingBar;
    @BindView(R.id.mine_score)
    TextView mineScore;
    @BindView(R.id.red_point)
    TextView redPoint;
    @BindView(R.id.mine_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View initView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.mine_fragment,null);
    }

    @Override
    protected void initData() {
//        DaoSession daoSession = ((HDApplication) getActivity().getApplication()).getDaoSession();
//        UserDao noteDao = daoSession.getUserDao();
//
//        User user = noteDao.queryBuilder().where(UserDao.Properties.Usid.eq(
//                Long.parseLong(DataHelper.getStringSF(getActivity().getApplicationContext()
//                        , SPUtil.CURRENT_USER_ID)))).build().unique();

        back.setVisibility(View.GONE);

//        ImageLoader imageLoader=((HDApplication) getActivity().getApplication()).getAppComponent().imageLoader();
//        imageLoader.loadImage(getActivity(),
//                GlideImageConfig
//                        .builder()
//                        .url(GlideImageLoader.getImageUrl(user.getUsimage(),"200","200"))
//                        .imagerView(mineUserImage)
//                        .errorPic(R.mipmap.default_image)
//                        .build());
        initSwipeRefreshLayout();
        mPresenter.getMyScore();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
    }


    @OnClick({R.id.mine_user_image, R.id.mine_list_data, R.id.mine_list_message, R.id.mine_list_team, R.id.mine_list_contract, R.id.mine_list_insurance, R.id.mine_list_score, R.id.mine_list_set})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        Intent intent=new Intent();
        Class<?> cls=null;
        switch (view.getId()) {
            case R.id.mine_user_image://头像
                cls=DataActivity.class;
                break;
            case R.id.mine_list_data://我的资料
                cls=DataActivity.class;
                break;
            case R.id.mine_list_message://我的消息
                cls=MessageActivity.class;
                DataHelper.SetBooleanSF(getActivity(), "newMsg", false);
                EventBus.getDefault().post(false,"new_message");
                bundle.putString("type", "1");
                intent.putExtras(bundle);
                break;
            case R.id.mine_list_team://团队管理
                cls=TeamActivity.class;
                break;
            case R.id.mine_list_contract://合同协议
                cls=AnnouncementDetailActivity.class;
                bundle.putString("url", Api.APP_DOMAIN + "/foremanContract.nk");
                bundle.putString("title", "合同协议");
                intent.putExtras(bundle);
                break;
            case R.id.mine_list_insurance://保险管理，HTML5
                cls=AnnouncementDetailActivity.class;
                bundle.putString("url", Api.APP_DOMAIN + "/HDMS/orgmain1.nk?id=6");
                bundle.putString("title", "保险管理");
                intent.putExtras(bundle);
                break;
            case R.id.mine_list_score://我的评分
                cls=ScoreActivity.class;
                break;
            case R.id.mine_list_set://设置SetActivity
                cls=SetActivity.class;
                break;
        }
        intent.setClass(getActivity(),cls);
        launchActivity(intent);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }



    @Subscriber(tag = "new_message", mode = ThreadMode.MAIN)
    public void showPoint(boolean b) {
        if(b){
            redPoint.setVisibility(View.VISIBLE);}else{
            redPoint.setVisibility(View.GONE)  ;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
       boolean flag = DataHelper.getBooleanSF(getActivity(),"newMsg");
        if(flag){
            redPoint.setVisibility(View.VISIBLE);
        }else {
            redPoint.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRefresh() {
        mPresenter.getMyScore();

    }

    @Override
    public void bindDataToView(MineBean mineBean) {
        mineRatingBar.setStarMark(mineBean.getWk_grade());
        mineScore.setText(mineBean.getWk_grade()==0?"0":String.format("%.1f",(mineBean.getWk_grade())));
        title.setText(mineBean.getWk_name());
        ImageLoader imageLoader=((HDApplication) getActivity().getApplication()).getAppComponent().imageLoader();
        imageLoader.loadImage(getActivity(),
                GlideImageConfig
                        .builder()
                        .url(GlideImageLoader.getImageUrl(mineBean.getWk_headpic(),"200","200"))
                        .imagerView(mineUserImage)
                        .errorPic(R.mipmap.default_image)
                        .build());
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerMineComponent.builder()
                .appComponent(appComponent)
                .mineModule(new MineModule(this))//请将LimitTimeModule()第一个首字母改为小写
                .build()
                .inject(this);
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
    public void showMessage(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        getActivity().finish();
    }
}
