package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.AddMembersContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.model.been.Team;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.been.TeamTypeNameBean;
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

public class AddMembersModel extends BaseModel<ServiceManager, CacheManager> implements AddMembersContract.Model {
    private Gson mGson;
    private Application mApplication;
    private ServiceManager mServiceManager;
    private CacheManager mCacheManager;

    public AddMembersModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mServiceManager=serviceManager;
        this.mCacheManager=cacheManager;
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public List<TeamBean> getAddMembersListData(List<Team> list,List<ConstructTeamBean> mPreList) {
        List<TeamBean> teamList=new ArrayList<>();
        for (Team team: list) {
            TeamBean teamBean = new TeamBean();
            teamBean.setTeamType("0");
            List<TeamTypeNameBean> teamTypeNameList = new ArrayList<TeamTypeNameBean>();
            TeamTypeNameBean teamTypeNameBean = new TeamTypeNameBean();
            teamTypeNameBean.setTeamName(team.getTypename());
            int count=Integer.parseInt(team.getCount());
            teamTypeNameBean.setTeamMemberNumber(team.getCount());
            teamTypeNameList.add(teamTypeNameBean);
            teamBean.setTeamTypeList(teamTypeNameList);
            teamList.add(teamBean);
            for (TeamSearchResult teamSearchResult: team.getMember()) {
                TeamBean teamBean2 = new TeamBean();
                teamBean2.setTeamType("1");
                List<TeamSearchResult> teamSearchResultList = new ArrayList<>();
                teamSearchResultList.add(teamSearchResult);
                teamBean2.setTeamUserList(teamSearchResultList);
                teamList.add(teamBean2);
                for (ConstructTeamBean constructTeamBean: mPreList) {
                    if(constructTeamBean.getCcnstructTeamUserList()!=null && constructTeamBean.getCcnstructTeamUserList().size()!=0){
                        if(constructTeamBean.getCcnstructTeamUserList().get(0).getPs_wkid().equals(String.valueOf(teamSearchResult.getWk_userid()))){
                            teamList.remove(teamBean2);
                            count--;
                            int index=teamList.indexOf(teamBean);
                            teamList.get(index).getTeamTypeList().get(0).setTeamMemberNumber(String.valueOf(count));
                            break;
                        }
                    }
                }
            }

        }
        return teamList;
    }

    @Override
    public Observable<BaseData<List<Team>>> getAddMembersListDatas() {
        return mServiceManager.getCommonService().teamAll(ApiUtil.getParams("ForemanApi","selectForemanMember",new JSONObject()));
    }

    @Override
    public Observable<BaseData> addTeamMember(String proId,String workId) {
        JSONObject json=new JSONObject();
        try {
            json.put("proid",proId);
            json.put("workid",workId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mServiceManager.getCommonService().addTeamMember(ApiUtil.getParams(
                Api.RequestModule.addTeamMember.getClassName(),
                Api.RequestModule.addTeamMember.getMethodName(),json));
    }
}