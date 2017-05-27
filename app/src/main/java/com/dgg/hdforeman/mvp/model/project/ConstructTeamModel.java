package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.ConstructTeamContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ConstructTeam;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.been.TeamTypeNameBean;
import com.dgg.hdforeman.mvp.model.been.WorkerInfo;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


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

public class ConstructTeamModel extends BaseModel<ServiceManager, CacheManager> implements ConstructTeamContract.Model {
    private Gson mGson;
    private Application mApplication;
    private CommonService mCommonService;
    private CommonCache mCommonCache;

    public ConstructTeamModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
    }


    @Override
    public Observable<BaseData<List<ConstructTeam>>> getConstructTeamList(String proId) {
        BaseParams<ProjectInfoRequest> params=new BaseParams<>();
        params.setC(Api.RequestModule.getConstructTeamListApi.getClassName());
        params.setM(Api.RequestModule.getConstructTeamListApi.getMethodName());
        ProjectInfoRequest projectInfoRequest=new ProjectInfoRequest();
        projectInfoRequest.setProid(proId);
        params.setD(projectInfoRequest);
        return mCommonService.getConstructTeamListData(mGson.toJson(params));
    }

    /**
     * 组合数据
     * @param list
     * @return
     */
    @Override
    public List<ConstructTeamBean> getConstructTeamListData(List<ConstructTeam> list) {
        List<ConstructTeamBean> teamList=new ArrayList<>();
        for (ConstructTeam team: list) {
            ConstructTeamBean teamBean = new ConstructTeamBean();
            teamBean.setCcnstructTeamType("0");
            List<TeamTypeNameBean> teamTypeNameList = new ArrayList<TeamTypeNameBean>();
            TeamTypeNameBean teamTypeNameBean = new TeamTypeNameBean();
            teamTypeNameBean.setTeamMemberNumber("0");
            if(team.getTe()!=null && team.getTe().size()!=0){
                teamTypeNameBean.setTeamMemberNumber(String.valueOf(team.getTe().size()));
            }
            teamTypeNameBean.setTeamName(team.getPs_wtname());
            teamTypeNameList.add(teamTypeNameBean);
            teamBean.setCcnstructTeamTypeList(teamTypeNameList);
            teamList.add(teamBean);
            for (WorkerInfo teamSearchResult: team.getTe()) {
                ConstructTeamBean teamBean2 = new ConstructTeamBean();
                teamBean2.setCcnstructTeamType("1");
                List<WorkerInfo> teamSearchResultList = new ArrayList<>();
                teamSearchResultList.add(teamSearchResult);
                teamBean2.setCcnstructTeamUserList(teamSearchResultList);
                teamList.add(teamBean2);
            }
        }
        return teamList;
    }

    @Override
    public Observable<BaseData> deleteTeamMember(String proId, ConstructTeamBean constructTeamBean) {
        JSONObject json=new JSONObject();
        try {
            json.put("proid",proId);
            json.put("workid",constructTeamBean.getCcnstructTeamUserList().get(0).getPs_wkid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mServiceManager.getCommonService().deleteTeamMember(ApiUtil.getParams(
                Api.RequestModule.deleteTeamMember.getClassName(),
                Api.RequestModule.deleteTeamMember.getMethodName(),json));
    }
}