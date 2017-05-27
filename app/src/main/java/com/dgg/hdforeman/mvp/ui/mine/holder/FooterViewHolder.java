package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/11/1.
 */

public class FooterViewHolder extends BaseAbstractViewHolder {
    @BindView(R.id.footer_textView)
    public TextView footerTextView;
    @BindView(R.id.footer_progressBar)
    public ProgressBar footerProgressBar;
    @BindView(R.id.footer)
    public AutoLinearLayout footer;

    public FooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItemView(TeamBean teamBean) {

    }
}
