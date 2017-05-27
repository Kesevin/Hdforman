package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.AnnouncementModule;
import com.dgg.hdforeman.mvp.ui.tool.activity.AnnouncementActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/9.
 */

@ActivityScope
@Component(modules = AnnouncementModule.class,dependencies = AppComponent.class)
public interface AnnouncementComponent {
    void inject(AnnouncementActivity announcementActivity);
}
