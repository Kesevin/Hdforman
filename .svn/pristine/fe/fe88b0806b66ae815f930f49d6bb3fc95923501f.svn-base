package com.dgg.hdforeman.mvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.ui.widget.popwindow.HotalkMenuView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/13.
 */

public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.left, R.id.right, R.id.bottom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                switchSysMenuShow(2,null,1);
                break;
            case R.id.right:
                switchSysMenuShow(3,null,3);
                break;
            case R.id.bottom:
                switchSysMenuShow(1,null,3);
                break;
        }
        setOnclick();
    }

    private void setOnclick(){
        menuListView.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuListView.close();
                int key = Integer.parseInt(view.getTag().toString());
                switch (key) {
                    case HotalkMenuView.MENU_SEND_MSG_FORMULAR:
                        ToastUtils.showToast("1");
                        break;
                    case HotalkMenuView.MENU_VIEW_CONTACT:
                        ToastUtils.showToast("2");
                        break;
                    case HotalkMenuView.MENU_ADD_CONTACT:
                        ToastUtils.showToast("3");
                        break;
                    case HotalkMenuView.MENU_MEMBER_MANAGER:
                        ToastUtils.showToast("4");
                        break;
                    case HotalkMenuView.MENU_ADD_TO_FAVORITES:
                        ToastUtils.showToast("5");
                        break;
                }
            }
        });
    }


    public HotalkMenuView menuListView = null;
    /**
     * @param num 2左边弹出，3右边弹出
     * @param listImage 弹出列表图片
     * @param orientation 弹出列表内容类型（1表示回到主页和消息，2表示专业咨询室和专家工作室，3表示回到主页和消息、分享，4表示回到主页和消息、分享、查看课程）
     */
    public void switchSysMenuShow(int num, ArrayList<Integer> listImage, int orientation) {
        initSysMenu(num,listImage,orientation);
        if (!menuListView.getIsShow()) {
            menuListView.show();
        } else {
            menuListView.close();
        }
    }

    private void initSysMenu(int num,ArrayList<Integer> listImage,int orientation) {
        if (menuListView == null || HotalkMenuView.oldnum!=num) {
            menuListView = new HotalkMenuView(this,num,listImage,orientation);
        }
        menuListView.clear();
        switch (orientation) {
            case 1:
                menuListView.add(HotalkMenuView.MENU_ADD_CONTACT,"item1");
                menuListView.add(HotalkMenuView.MENU_MEMBER_MANAGER,"item2");
                break;
            case 2:
                menuListView.add(HotalkMenuView.MENU_SEND_MSG_FORMULAR,"item1");
                menuListView.add(HotalkMenuView.MENU_VIEW_CONTACT,"item2");
                break;
            case 3:
                menuListView.add(HotalkMenuView.MENU_SEND_MSG_FORMULAR,"item1");
                menuListView.add(HotalkMenuView.MENU_VIEW_CONTACT,"item2");
                menuListView.add(HotalkMenuView.MENU_ADD_TO_FAVORITES,"item3");
                break;
            case 4:
                menuListView.add(HotalkMenuView.MENU_SEND_MSG_FORMULAR,"item1");
                menuListView.add(HotalkMenuView.MENU_VIEW_CONTACT,"item2");
                menuListView.add(HotalkMenuView.MENU_ADD_TO_FAVORITES,"item3");
                menuListView.add(HotalkMenuView.MENU_DELETE_MULTI_MSG,"item4");
                break;
            default:
                break;
        }
    }
}
