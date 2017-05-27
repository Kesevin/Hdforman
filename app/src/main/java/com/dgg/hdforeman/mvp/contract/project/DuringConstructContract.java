package com.dgg.hdforeman.mvp.contract.project;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/2.
 */

public interface DuringConstructContract {
    interface View extends ListBaseView {
        void setAdapter(BaseQuickAdapter adapter);
        void startLoadMore();//开始加载更多
        void endLoadMore();//结束加载更多
        void callPhone(@NotNull String phone);
        ProjectContract.TabTypeItem getTabItem();
        String getProtype();
        void doFreeitemsUtil(ProjectResponse projectResponse);//确认项目
    }


    interface Model {
        @WorkerThread
        Observable<BaseJson<List<ProjectResponse>>> qryDuringConstructList(@Nullable String protype, @Nullable int pageStart);
        Observable<BaseData> doFreeitems(String proid);
    }
}
