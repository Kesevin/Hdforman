package com.dgg.hdforeman.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.DensityUtil;




public class PromptBoxDialog extends Dialog {

	public PromptBoxDialog(Context context) {
		super(context);
	}

	public PromptBoxDialog(Context context, int theme) {
		super(context, theme);
	}
	public static class Builder {
        private Context context;  
        private String title;  
        private String message;  
        private String positiveButtonText;  
        private String negativeButtonText;  
        private View contentView;  
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
  
        public Builder(Context context) {  
            this.context = context;  
        }  
  
        public Builder setMessage(String message) {  
            this.message = message;  
            return this;  
        }  
  
        public Builder setMessage(int message) {  
            this.message = (String) context.getText(message);  
            return this;  
        }  
  
        public Builder setTitle(int title) {  
            this.title = (String) context.getText(title);  
            return this;  
        }  
        public Builder setTitle(String title) {  
            this.title = title;  
            return this;  
        }  
  
        public Builder setContentView(View v) {  
            this.contentView = v;  
            return this;  
        }  
  
        public Builder setPositiveButton(int positiveButtonText,  
                OnClickListener listener) {
            this.positiveButtonText = (String) context  
                    .getText(positiveButtonText);  
            this.positiveButtonClickListener = listener;  
            return this;  
        }  
  
        public Builder setPositiveButton(String positiveButtonText,  
                OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;  
            this.positiveButtonClickListener = listener;  
            return this;  
        }  
  
        public Builder setNegativeButton(int negativeButtonText,  
                OnClickListener listener) {
            this.negativeButtonText = (String) context  
                    .getText(negativeButtonText);  
            this.negativeButtonClickListener = listener;  
            return this;  
        }  
  
        public Builder setNegativeButton(String negativeButtonText,  
                OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;  
            this.negativeButtonClickListener = listener;  
            return this;  
        }  

        public PromptBoxDialog create() {
            LayoutInflater inflater = (LayoutInflater) context  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
            final PromptBoxDialog dialog = new PromptBoxDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
            dialog.setContentView(layout);
            Window window = dialog.getWindow();  
   		 WindowManager.LayoutParams params = 
   				 window.getAttributes();
   				 params.width = (int) (CommonUtil.getScreenWidth(context)*0.8);
   				 params.height = LayoutParams.WRAP_CONTENT ;
   				dialog.getWindow().setAttributes(params);
//            dialog.addContentView(layout, new LayoutParams(
//                    CommonUtil.dip2px(context,320), LayoutParams.WRAP_CONTENT));
            TextView   space= ((TextView) layout.findViewById(R.id.space));
            ((TextView) layout.findViewById(R.id.title)).setText(title);  
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positive))  
                        .setText(positiveButtonText);  
                if (positiveButtonClickListener != null) {  
                    ((TextView) layout.findViewById(R.id.positive))  
                            .setOnClickListener(new View.OnClickListener() {  
                                public void onClick(View v) {  
                                    positiveButtonClickListener.onClick(dialog,  
                                            DialogInterface.BUTTON_POSITIVE);  
                                }  
                            });  
                }  
            } else {  
                layout.findViewById(R.id.positive).setVisibility(
                        View.GONE);  
                space.setVisibility(View.GONE);
            }  
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negative))
                        .setText(negativeButtonText);  
                if (negativeButtonClickListener != null) {  
                    ((TextView) layout.findViewById(R.id.negative))  
                            .setOnClickListener(new View.OnClickListener() {  
                                public void onClick(View v) {  
                                    negativeButtonClickListener.onClick(dialog,  
                                            DialogInterface.BUTTON_NEGATIVE);  
                                }  
                            });  
                }  
            } else {  
                layout.findViewById(R.id.negative).setVisibility(
                        View.GONE);  
                space.setVisibility(View.GONE);
            }  
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);  
            } 
            dialog.setContentView(layout);  
            return dialog;  
        }  
    }
}
