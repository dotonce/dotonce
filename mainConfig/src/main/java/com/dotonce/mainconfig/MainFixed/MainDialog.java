package com.dotonce.mainconfig.MainFixed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.airbnb.paris.Paris;
import com.dotonce.mainconfig.Interfaces.onDialogAction;
import com.dotonce.mainconfig.R.color;
import com.dotonce.mainconfig.R.id;
import com.dotonce.mainconfig.R.layout;
import com.google.android.material.button.MaterialButton;

public class MainDialog {
    static AlertDialog alertDialog;

    public MainDialog() {
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public static void show(Activity activity, int icon, String title, String description, String ok_text, String no_text, boolean iconTint, onDialogAction onDialogAction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = View.inflate(activity, layout.layout_main_dialog, null);
        MaterialButton d_btn_ok = dialogView.findViewById(id.btn_ok);
        RelativeLayout relativeLayout = dialogView.findViewById(id.relativeLayout);
        MaterialButton d_btn_no = dialogView.findViewById(id.btn_no);
        TextView d_title = dialogView.findViewById(id.title);
        TextView d_message = dialogView.findViewById(id.message);
        ImageView d_icon = dialogView.findViewById(id.icon);
        d_title.setText(title);
        d_icon.setImageDrawable(activity.getResources().getDrawable(icon));
        d_message.setText(description);
        d_btn_ok.setText(ok_text);
        d_btn_no.setText(no_text);
        if (no_text.equals("")) {
            d_btn_no.setVisibility(View.GONE);
        }

        builder.setCancelable(false);
        d_btn_ok.setOnClickListener((v12) -> {
            onDialogAction.onOkClick();
            alertDialog.dismiss();
        });
        d_btn_no.setOnClickListener((view) -> {
            onDialogAction.onCancelClick();
            alertDialog.dismiss();
        });
        if (iconTint && VERSION.SDK_INT >= 21) {
            d_icon.setImageTintList(ColorStateList.valueOf(activity.getResources().getColor(color.black_white)));
        }

        try{
        Paris.style(d_btn_no).apply(AppMainSettings.main_btn);
        Paris.style(d_btn_ok).apply(AppMainSettings.main_btn);
        Paris.style(relativeLayout).apply(AppMainSettings.main_background);
        Paris.style(d_title).apply(AppMainSettings.main_title);
        Paris.style(d_message).apply(AppMainSettings.main_detail);
        }catch (Exception | Error ignored){}
        builder.setView(dialogView);
        alertDialog = builder.create();
        if (dialogView.getParent() != null) {
            ((ViewGroup)dialogView.getParent()).removeView(dialogView);
        }

        alertDialog.show();
    }
}
