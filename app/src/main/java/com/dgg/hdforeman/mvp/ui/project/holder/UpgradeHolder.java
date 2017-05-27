package com.dgg.hdforeman.mvp.ui.project.holder;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.UppacInfo;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jess on 01/12/2016 18:20
 * Contact with jess.yan.effort@gmail.com
 */

public class UpgradeHolder extends BaseHolder<UppacInfo> {

    @Nullable
    @BindView(R.id.cb_upgrade)
    CheckBox mCheckBox;
    @Nullable
    @BindView(R.id.tv_upgrade_riding)
    TextView mTextView;

    private HDApplication mApplication;

    public UpgradeHolder(View itemView) {
        super(itemView);
        this.mApplication = (HDApplication) itemView.getContext().getApplicationContext();
    }

    @Override
    public void setData(final UppacInfo data) {
        if (data.isCheck()) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setCheck(isChecked);
            }
        });


        Observable.just(new StringBuilder())
                .doOnNext(new Action1<StringBuilder>() {
                    @Override
                    public void call(StringBuilder builder) {
                        builder.append(data.getName());
                    }
                }).doOnNext(new Action1<StringBuilder>() {
            @Override
            public void call(StringBuilder builder) {
                if (!TextUtils.isEmpty(data.getSpacecname())) {
                    builder.append(" (" + data.getSpacecname() + ") ");
                }
            }
        }).doOnNext(new Action1<StringBuilder>() {
            @Override
            public void call(StringBuilder builder) {
                if (!TextUtils.isEmpty(data.getUpmeasure()) && !TextUtils.isEmpty(data.getPrice())) {
                    float num = Float.parseFloat(data.getUpmeasure());//数量
                    float price = Float.parseFloat(data.getPrice());//价格

                    builder.append("\n");
                    builder.append(String.format("%.2f%s", num, data.getUnit()));
                    builder.append(" x ");
                    builder.append(String.format("%.2f/%s", price, data.getUnit()));
                    builder.append(" = ");
                    builder.append(String.format("¥ %.2f", num * price));
                }
            }
        }).map(new Func1<StringBuilder, String>() {
            @Override
            public String call(StringBuilder builder) {
                return builder.toString();
            }
        }).subscribe(RxTextView.text(mTextView), new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }
}
