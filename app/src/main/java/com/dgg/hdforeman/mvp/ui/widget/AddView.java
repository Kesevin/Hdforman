package com.dgg.hdforeman.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.KnifeUtil;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jess on 18/11/2016 20:53
 * Contact with jess.yan.effort@gmail.com
 */
public class AddView extends AutoLinearLayout {

    @BindView(R.id.tv_add_view_reduce)
    TextView mTvAddViewReduce;
    @BindView(R.id.tv_add_view_display)
    TextView mTvAddViewDisplay;
    @BindView(R.id.tv_add_view_increase)
    TextView mTvAddViewIncrease;

    private int mCount;

    public AddView(Context context) {
        this(context, null);
    }

    public AddView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.widget_add, this);
        KnifeUtil.bindTarget(this, view);
        display(mCount);
    }


    @Nullable
    @OnClick({R.id.tv_add_view_reduce, R.id.tv_add_view_increase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_view_reduce:
                display(--mCount);
                break;
            case R.id.tv_add_view_increase:
                display(++mCount);
                break;
        }
    }


    private void display(int count) {
        Observable.just(count)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        if (integer < 0)
                            mCount = 0;
                        return integer >= 0;
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer + "";
                    }
                })
                .subscribe(RxTextView.text(mTvAddViewDisplay));
    }

    public void setCount(String count) {
        if (!TextUtils.isEmpty(count)) {
            setCount(Integer.parseInt(count));
        }
    }

    public void setCount(int count) {
        mCount = count;
        if (count < 0)
            mCount = 0;
        mTvAddViewDisplay.setText(mCount + "");
    }

    /**
     * 获取数量
     *
     * @return
     */
    public int getCount() {
        return mCount;
    }
}
