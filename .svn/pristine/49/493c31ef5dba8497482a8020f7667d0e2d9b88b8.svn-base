package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.IncomeModule;
import com.dgg.hdforeman.mvp.ui.income.fragment.IncomeFragment;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/11.
 */
@ActivityScope
@Component(modules = IncomeModule.class,dependencies = AppComponent.class)
public interface IncomeComponent {
    void inject(IncomeFragment incomeFragment);
}
