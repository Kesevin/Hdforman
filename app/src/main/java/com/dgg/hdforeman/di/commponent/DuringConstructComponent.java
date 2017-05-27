package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.DuringConstructModule;
import com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@FragmentScope
@Component(modules = DuringConstructModule.class,dependencies = AppComponent.class)
public interface DuringConstructComponent {
    void inject(DuringConstructFragment fragment);
}
