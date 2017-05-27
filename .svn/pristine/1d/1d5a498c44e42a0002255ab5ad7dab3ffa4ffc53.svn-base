package com.dgg.hdforeman.mvp.model.api.service;

import com.jess.arms.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jess on 8/5/16 13:01
 * contact with jess.yan.effort@gmail.com
 */
@Singleton
public class ServiceManager extends BaseServiceManager {
    private CommonService mCommonService;
    private ProjectService mProjectService;
    private IncomeService mIncomeService;

    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     *
     * @param commonService
     */
    @Inject
    public ServiceManager(CommonService commonService, ProjectService projectService, IncomeService incomeService) {
        this.mCommonService = commonService;
        this.mProjectService = projectService;
        this.mIncomeService = incomeService;
    }

    public CommonService getCommonService() {
        return mCommonService;
    }

    public ProjectService getProjectService() {
        return mProjectService;
    }

    public IncomeService getIncomeService() {
        return mIncomeService;
    }
}
