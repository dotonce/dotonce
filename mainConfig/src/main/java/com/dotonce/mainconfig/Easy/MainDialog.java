package com.dotonce.mainconfig.Easy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.airbnb.paris.Paris;
import com.dotonce.mainconfig.Interfaces.onDialogAction;
import com.dotonce.mainconfig.R;
import com.google.android.material.button.MaterialButton;

public class MainDialog {
    static AlertDialog alertDialog;
    /**
     * Default ok button text is ok, and default cancel text button is cancel;
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public static void show(Activity activity, int icon , String title, String description, String ok_text, String no_text, boolean iconTint, onDialogAction onDialogAction){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = View.inflate(activity, R.layout.layout_main_dialog, null);
        MaterialButton d_btn_ok = dialogView.findViewById(R.id.btn_ok);
        RelativeLayout relativeLayout = dialogView.findViewById(R.id.relativeLayout);
        MaterialButton d_btn_no = dialogView.findViewById(R.id.btn_no);
        TextView d_title = dialogView.findViewById(R.id.title);
        TextView d_message = dialogView.findViewById(R.id.message);
        ImageView d_icon = dialogView.findViewById(R.id.icon);
        d_title.setText(title);
        d_icon.setImageDrawable(activity.getResources().getDrawable(icon));
        d_message.setText(description);
        d_btn_ok.setText(ok_text);
        d_btn_no.setText(no_text);
        if(no_text.equals("")){
            d_btn_no.setVisibility(View.GONE);
        }
        builder.setCancelable(false);
        d_btn_ok.setOnClickListener(v12 -> {
            onDialogAction.onOkClick();
            alertDialog.dismiss();

        });
        d_btn_no.setOnClickListener(view -> {
            onDialogAction.onCancelClick();
            alertDialog.dismiss();

        });
        if(iconTint){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                d_icon.setImageTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.black_white)));
            }
        }


        Paris.style(d_btn_no).apply(AppMainSettings.mainDialog_buttonsStyle);
        Paris.style(d_btn_ok).apply(AppMainSettings.mainDialog_buttonsStyle);
        relativeLayout.setBackgroundColor(activity.getResources().getColor(AppMainSettings.mainDialog_background));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            d_title.setTextAppearance(AppMainSettings.mainDialog_buttonsStyle);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            d_message.setTextAppearance(AppMainSettings.mainDialog_messageStyle);
        }
        builder.setView(dialogView);
        alertDialog = builder.create();
        if (dialogView.getParent() != null) {
            ((ViewGroup) dialogView.getParent()).removeView(dialogView);
        }
        alertDialog.show();
    }
}
