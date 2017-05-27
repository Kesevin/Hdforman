package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectMessureComponent;
import com.dgg.hdforeman.di.module.ProjectMessureModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectMessureContract;
import com.dgg.hdforeman.mvp.presenter.project.ProjectMessurePresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.AddSpaceAdapter;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.CustomPopupWindow;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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
 * author:zhangjing
 * 作用:
 * return:
 */

public class ProjectMessureActivity extends BacksActivity<ProjectMessurePresenter> implements ProjectMessureContract.View {
    /*
        * 工地测量分两种情况，一种是未测量，二是修改测量的情况
   * */
    private static final String IS_MESSURE = "is_messure";
    private static final String P_ID = "project_id";
    public static int[] nums={0,0,0,0,0};//维护一个数组控制增加的名字的下标
    @BindView(R.id.rv_space)
    RecyclerView rvSpace;
    @BindView(R.id.toolbar_right)
    TextView ivtoolbar_right;
    @BindView(R.id.tv_calculate)
    TextView tv_calculate;
    @BindView(R.id.iv_mask)
    ImageView iv_mask;
    private WaitingDialog mWaitingDialog;
    private boolean isMessured;
    private String id;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectMessureComponent
                .builder()
                .appComponent(appComponent)
                .projectMessureModule(new ProjectMessureModule(this))//请将ProjectMessureModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_projectmessure, null, false);
    }

    @Override
    protected void initData() {
        for (int i=0;i<nums.length;i++){
            nums[i]=0;
        }
        id = getIntent().getStringExtra(P_ID);
        isMessured = getIntent().getBooleanExtra(IS_MESSURE, false);
        ivtoolbar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });
        tv_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传空间至服务器
                mPresenter.addSpace2Server(id + "");
            }
        });

        mPresenter.initRecylerView();
        if (isMessured) {
            mPresenter.initdata(id);
        } else {
            //默认有几个空间
            mPresenter.loadInitDataWithoutMessure(id);
        }

    }


    @Override
    public void showLoading() {
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
            mWaitingDialog = null;
        }
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

    //显示弹框，选择增加的空间
    @Override
    public void showPopwindow() {
        View popview = getLayoutInflater().inflate(R.layout.popwindow_addspace, null, false);
        final TagFlowLayout flowLayout = (TagFlowLayout) popview.findViewById(R.id.id_flowlayout);
        final CustomPopupWindow mPopupWindow = CustomPopupWindow.builder().contentView(popview)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {

                    }
                }).isWrap(false)
                .animationStyle(R.style.AnimBottom)
                .parentView(ivtoolbar_right).build();
        mPopupWindow.setHeight(AutoLinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                hidemask();
            }
        });
        flowLayout.setAdapter(new TagAdapter<String>(GlobalConfig.names) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_tag_name,
                        flowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                nums[position]+=1;
                mPresenter.addData(GlobalConfig.names[position]+nums[position],position+1);
                rvSpace.smoothScrollToPosition(0);
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                return false;
            }
        });

        if (!mPopupWindow.isShowing())
            mPopupWindow.show();
            showmask();
//            mPopupWindow.showAtLocation(ivtoolbar_right, Gravity.BOTTOM,0,0);
    }

    @Override
    public void showsum(float area, float circumference) {

    }

    @Override
    public void notifydatachange() {

    }

    @Override
    public void setAdapter(AddSpaceAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSpace.setLayoutManager(linearLayoutManager);
        rvSpace.setAdapter(adapter);
    }

    public static void startActivity(Context context, String id, boolean isMessured) {
        Intent intent = new Intent(context, ProjectMessureActivity.class);
        intent.putExtra(IS_MESSURE, isMessured);
        intent.putExtra(P_ID, id);
        context.startActivity(intent);
    }
    private void showmask(){
        iv_mask.setVisibility(View.VISIBLE);
    }
    private void hidemask(){
        iv_mask.setVisibility(View.GONE);
    }
}
