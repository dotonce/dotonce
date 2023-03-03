package com.dotonce.mainconfig.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.paris.Paris;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class InformationCollect {
    public static void show(Context context, boolean showChatTerm, boolean showLanguage){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_collect", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("chat_term","0").equals("0")){
            Dialog dialog;
            dialog=new Dialog(context,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
            dialog.setContentView(R.layout.activity_information_collect);
            MaterialRadioButton radioButtonEN = dialog.findViewById(R.id.radio1);
            MaterialRadioButton radioButtonAR = dialog.findViewById(R.id.radio2);
            MaterialCheckBox checkBox = dialog.findViewById(R.id.checkbox_1);
            RelativeLayout layout_accept = dialog.findViewById(R.id.layout_accept);
            TextView text_detail = dialog.findViewById(R.id.text_detail);
            TextView text_title = dialog.findViewById(R.id.text_title);
            TextView text_detail2 = dialog.findViewById(R.id.text_detail_2);
            TextView text_title2 = dialog.findViewById(R.id.text_title_2);
            View view1 = dialog.findViewById(R.id.view1);
            MaterialButton btn = dialog.findViewById(R.id.btn_ok);
            Paris.style(btn).apply(AppMainSettings.main_btn);
            Paris.style(text_title).apply(AppMainSettings.main_title);
            Paris.style(text_title2).apply(AppMainSettings.main_title);
            Paris.style(text_detail).apply(AppMainSettings.main_detail);
            Paris.style(text_detail2).apply(AppMainSettings.main_detail);
            Paris.style(checkBox).apply(AppMainSettings.check_style);
            Paris.style(radioButtonAR).apply(AppMainSettings.check_style);
            Paris.style(radioButtonEN).apply(AppMainSettings.check_style);
            Paris.style(layout_accept).apply(AppMainSettings.main_background);
            text_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,22f);
            text_title2.setTextSize(TypedValue.COMPLEX_UNIT_SP,22f);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                radioButtonAR.setButtonTintList(ColorStateList.valueOf(context.getResources().getColor(AppMainSettings.mainProgressColor)));
                radioButtonEN.setButtonTintList(ColorStateList.valueOf(context.getResources().getColor(AppMainSettings.mainProgressColor)));
                checkBox.setButtonTintList(ColorStateList.valueOf(context.getResources().getColor(AppMainSettings.mainProgressColor)));
            }

            radioButtonEN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        text_title.setText(context.getString(R.string.user_collect_en));
                        text_title2.setText(context.getString(R.string.chat_terms_en));
                        text_detail.setText(context.getString(R.string.use_msg_dotonce_en));
                        text_detail2.setText(context.getString(R.string.chat_msg_dotonce_en));
                        checkBox.setText(context.getString(R.string.i_accept_en));
                        btn.setText(context.getString(R.string.ok_en));
                    }else {
                        text_title.setText(context.getString(R.string.user_collect_ar));
                        text_title2.setText(context.getString(R.string.chat_terms_ar));
                        text_detail.setText(context.getString(R.string.use_msg_dotonce_ar));
                        text_detail2.setText(context.getString(R.string.chat_msg_dotonce_ar));
                        checkBox.setText(context.getString(R.string.i_accept_ar));
                        btn.setText(context.getString(R.string.ok_ar));
                    }
                }
            });
            if(!showLanguage){
                radioButtonEN.setVisibility(View.GONE);
                radioButtonAR.setVisibility(View.GONE);
            }
            if(!showChatTerm){
               view1.setVisibility(View.GONE);
               text_title2.setVisibility(View.GONE);
               text_detail2.setVisibility(View.GONE);
            }
            checkBox.setOnCheckedChangeListener((compoundButton, b) ->{
                if(b){
                    btn.setEnabled(true);
                    btn.setAlpha(1f);
                }else {
                    btn.setEnabled(false);
                    btn.setAlpha(0.5f);
                }
            });
            btn.setOnClickListener(view -> {
                SharedPreferences.Editor editor = context.getSharedPreferences("user_collect", Context.MODE_PRIVATE).edit();
                editor.putString("chat_term", "1");
                editor.apply();
                dialog.dismiss();
            });
            dialog.show();
        }

    }
}