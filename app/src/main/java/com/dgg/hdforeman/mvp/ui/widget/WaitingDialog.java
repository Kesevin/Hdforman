package com.dgg.hdforeman.mvp.ui.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dgg.hdforeman.R;


public class WaitingDialog extends Dialog {

    private Context context;
    private ImageView img;

    public WaitingDialog(Context context) {
        super(context, R.style.WaitDialog);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_layout, null);
        img = (ImageView) view.findViewById(R.id.loading_img);
        setContentView(view);

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        img.setAnimation(anim);
    }

}
