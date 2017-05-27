package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.MineScoreModule;
import com.dgg.hdforeman.mvp.ui.mine.activity.ScoreActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/9.
 */
@ActivityScope
@Component(modules = MineScoreModule.class,dependencies = AppComponent.class)
public interface MineScoreComponent {
    void inject(ScoreActivity scoreActivity);
}
