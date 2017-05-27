package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Activity;
import android.app.Application;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.EventBusTag;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectMessureContract;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.RequestSpaceBean;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectMessureActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectMessureResultActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.AddSpaceAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.sun.mail.imap.protocol.ID;
import com.tbruyelle.rxpermissions.RxPermissions;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */
import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

@ActivityScope
public class ProjectMessurePresenter extends BasePresenter<ProjectMessureContract.Model, ProjectMessureContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;
    private List<SpaceBean.SpacenameEntity> spaceBeens=new ArrayList<>();
    private AddSpaceAdapter addSpaceAdapter;
    private View footerview;
    private TextView tvarea;
    int max = 0;
    @Inject
    public ProjectMessurePresenter(ProjectMessureContract.Model model, ProjectMessureContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void addData(String name,int i){
        SpaceBean.SpacenameEntity entity=new SpaceBean.SpacenameEntity();
        entity.setPe_name(name);
        entity.setSpacetype(i);
        if (addSpaceAdapter.getData().size()==0&&addSpaceAdapter.getFooterLayoutCount()==0){
            addSpaceAdapter.addFooterView(footerview);
            addSpaceAdapter.setFooterview();
        }
        addSpaceAdapter.add(0,entity);
    }
    public void initRecylerView(){
        addSpaceAdapter=new AddSpaceAdapter(spaceBeens);
        footerview= View.inflate(mApplication,R.layout.item_space_footer,null);
        tvarea= (TextView) footerview.findViewById(R.id.tv_area);
//        tvlength= (TextView) footerview.findViewById(R.id.tv_length);
        mRootView.setAdapter(addSpaceAdapter);
        addSpaceAdapter.addFooterView(footerview);
    }
        /*
        * 初始化数据，获得工作空间列表
        * */
    public void initdata(final String id){

        mModel.getMessurelist(id).compose(RxUtils.<BaseJson<SpaceBean>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<SpaceBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<SpaceBean> json) {
                        if (json.isSuccess()) {
                            if (json.getD()!=null&&json.getD().getSpacename()!=null&&json.getD().getSpacename().size()!=0) {
                                if (json.getD()!=null){
                                    List<SpaceBean.SpacenameEntity> lists=json.getD().getSpacename();
                                    for (int i=0;i<GlobalConfig.names.length;i++){
                                        ProjectMessureActivity.nums[i]=0;
                                    }
                                    findmax(lists,1);
                                    findmax(lists,2);
                                    findmax(lists,3);
                                    findmax(lists,4);
                                    findmax(lists,5);
//                                    findmax(lists,6);
                                    addSpaceAdapter.addData(((SpaceBean) json.getD()).getSpacename());
                                    tvarea.setText(json.getD().getAllforests() + "m²");
                                }
                            }
                        } else {
                            mRootView.showMessage(json.getMsg());
                        }
                    }
                });
    }
    /*
    * 添加工作空间上传至服务器
    * */
    public void addSpace2Server(String id){
        List<RequestSpaceBean.SpacenameEntity> list=new ArrayList<>();
        float allarea=0;
        for (int i=0;i<spaceBeens.size();i++){
            SpaceBean.SpacenameEntity item=spaceBeens.get(i);
            if (item.getPe_acreage()==0)
                continue;
            RequestSpaceBean.SpacenameEntity spacenameEntity= new RequestSpaceBean.SpacenameEntity();
            spacenameEntity.setMeasureresult(item.getPe_name());
            spacenameEntity.setForests(item.getPe_acreage()+"");
            spacenameEntity.setSpacetype(item.getSpacetype());
            list.add(spacenameEntity);
            allarea+=item.getPe_acreage();
        }
        //封装request
        RequestSpaceBean requestSpaceBean=new RequestSpaceBean();
        requestSpaceBean.setAllforests(CommonUtil.numformater4(allarea)+"");
        requestSpaceBean.setProid(id);

        RequestSpaceBean.SpacenameEntity spacenameEntity=new RequestSpaceBean.SpacenameEntity();
        requestSpaceBean.setSpacename(list);
        //封装结束,添加空间至服务器
        addSpace(requestSpaceBean);
    }
    public void addSpace(final RequestSpaceBean requestSpaceBean){
         mModel.addSpace(requestSpaceBean).compose(RxUtils.<BaseJson<String>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<String>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<String> stringBaseJson) {
                        if (stringBaseJson.isSuccess()){
                            EventBus.getDefault().post(requestSpaceBean.getAllforests(), EventBusTag.MESSURE_CHANGE);
                            mRootView.showMessage(stringBaseJson.getMsg());
                            startResultActivity(requestSpaceBean.getProid());
                            mRootView.killMyself();
                        }else {
                            mRootView.showMessage(stringBaseJson.getMsg());
                        }
                    }
                });
    }
    public void loadInitDataWithoutMessure(String id) {
        SpaceBean space=new SpaceBean();
        space.setAllforests(0);
        for (int i = 0; i< GlobalConfig.names.length; i++){
            SpaceBean.SpacenameEntity entity=new SpaceBean.SpacenameEntity();
            entity.setPe_acreage(0);
            entity.setPe_name(GlobalConfig.names[i]);
            entity.setSpacetype(i+1);
            space.getSpacename().add(entity);
        }
       addSpaceAdapter.addData(space.getSpacename());
    }
    public void findmax(List<SpaceBean.SpacenameEntity> lists, final int type){
        max=0;
        Observable.from(lists).filter(new Func1<SpaceBean.SpacenameEntity, Boolean>() {
            @Override
            public Boolean call(SpaceBean.SpacenameEntity spacenameEntity) {
                return spacenameEntity.getSpacetype()==type;
            }
        }).subscribe(new Action1<SpaceBean.SpacenameEntity>() {
            @Override
            public void call(SpaceBean.SpacenameEntity spacenameEntity) {
                String str=spacenameEntity.getPe_name();
                String reg = "[\u4e00-\u9fa5]";
                Pattern pat = Pattern.compile(reg);
                Matcher mat=pat.matcher(str);
                String repickStr = mat.replaceAll("");
                if (repickStr.length()>0){
                    try{
                        int index=Integer.parseInt(repickStr);
                        if (index>max){
                            max=index;
                            ProjectMessureActivity.nums[type-1]=max;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        });

    }
    public void startResultActivity(String id){
        ProjectMessureResultActivity.startActivity((Activity)mRootView,id,false);
    }
}
