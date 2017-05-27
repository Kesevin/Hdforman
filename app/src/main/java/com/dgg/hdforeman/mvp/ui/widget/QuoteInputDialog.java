package com.dgg.hdforeman.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.KnifeUtil;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

import static com.jess.arms.utils.UiUtils.getResources;

/**
 * Created by jess on 27/11/2016 10:03
 * Contact with jess.yan.effort@gmail.com
 */

public class QuoteInputDialog extends Dialog {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_input_length)
    EditText mEtInputLength;
    @BindView(R.id.bt_input_confirm)
    Button mBtInputConfirm;
    @BindView(R.id.bt_input_cancel)
    Button mBtInputCancel;

    private ExtendChildView mParent;

    private List<SpaceBean.SpacenameEntity> mList;
    private Adapter mAdapter;
    private SpaceBean.SpacenameEntity mCurrentEntity;

    protected QuoteInputDialog(@NonNull Context context, ExtendChildView viewGroup, List<SpaceBean.SpacenameEntity> measureList) {
        super(context);
        this.mParent = viewGroup;
        init(measureList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag("tag").w("show------------>");
    }

    private void init(List<SpaceBean.SpacenameEntity> measureList) {

        //处理dialog的蓝线
        Observable.just(Build.VERSION.SDK_INT).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 17;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                int dividerID = getResources().getIdentifier("android:id/titleDivider", null, null);
                View divider = findViewById(dividerID);
                if (divider != null)
                    divider.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        setContentView(R.layout.widget_quote_input);
        KnifeUtil.bindTarget(this, this);

        setCancelable(true);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        UiUtils.configRecycleView(mRecyclerView, new GridLayoutManager(getContext(), 3));

        mList = new ArrayList<>(measureList.size());
        for (SpaceBean.SpacenameEntity entity : measureList) {
            mList.add(entity.copy());
        }


        mAdapter = new Adapter(mList);
        mCurrentEntity = mList.get(0);//默认选中第一个
        mCurrentEntity.setSelect(true);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener<SpaceBean.SpacenameEntity>() {
            @Override
            public void onItemClick(View view, SpaceBean.SpacenameEntity data, int position) {
                if (mCurrentEntity != null && mCurrentEntity != data) {
                    mCurrentEntity = data;
                    for (int i = 0; i < mList.size(); i++) {//将点击的item选中
                        SpaceBean.SpacenameEntity entity = mList.get(i);
                        if (i == position) {
                            entity.setSelect(true);
                        } else {
                            entity.setSelect(false);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @OnClick({R.id.bt_input_confirm, R.id.bt_input_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_input_confirm:
                confirm();
                dismiss();
                break;
            case R.id.bt_input_cancel:
                dismiss();
                break;
        }
    }


    public void changePackage(TextView textView, int color, Drawable drawablebyResource) {
        textView.setTextColor(color);
        textView.setBackground(drawablebyResource);
    }


    /**
     * 确定
     */
    private void confirm() {
        if (obtainInputAcreage()) {
            mParent.addChildView(mCurrentEntity);
        }
    }

    /**
     * 获取输入的面积
     */
    private boolean obtainInputAcreage() {
        String length = mEtInputLength.getText().toString().trim();
        CommonUtil.textWatcher(mEtInputLength);
        if (TextUtils.isEmpty(length) || !CommonUtil.isdecimal(length)) {
            UiUtils.SnackbarText("请输入整数或小数");
            return false;
        }

        float l = Float.parseFloat(length);
        if (l > 10000f) {
            UiUtils.SnackbarText("面积或数量不能超过10000");
            return false;
        }

        mCurrentEntity.setPre_acreage(l);
        return true;

    }


    public class Adapter extends DefaultAdapter<SpaceBean.SpacenameEntity> {


        public Adapter(List<SpaceBean.SpacenameEntity> infos) {
            super(infos);
        }

        @Override
        public BaseHolder<SpaceBean.SpacenameEntity> getHolder(View v) {
            return new Holder(v);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_quote_input;
        }
    }


    public class Holder extends BaseHolder<SpaceBean.SpacenameEntity> {
        @Nullable
        @BindView(R.id.tv_input_name)
        TextView mName;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(SpaceBean.SpacenameEntity data) {
            if (data.isSelect()) {
                changePackage(mName, UiUtils.getColor(R.color.colorLightOrange), UiUtils.getDrawablebyResource(R.drawable.check_stroke_orange_shape));
            } else {
                changePackage(mName, UiUtils.getColor(R.color.textGray), UiUtils.getDrawablebyResource(R.drawable.check_stroke_gray_shape));
            }
            Observable.just(data.getPe_name())
                    .subscribe(RxTextView.text(mName), new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
        }
    }

}
