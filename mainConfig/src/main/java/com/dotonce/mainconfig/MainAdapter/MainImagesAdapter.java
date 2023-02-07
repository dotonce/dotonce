package com.dotonce.mainconfig.MainAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.dotonce.mainconfig.MainModel.MainImagesModel;
import com.dotonce.mainconfig.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MainImagesAdapter extends PagerAdapter {
    Context context;
    ArrayList<MainImagesModel> arrayList;
    MainImagesModel contentsModel;
    View custom = null;

    public MainImagesAdapter(Context context, ArrayList<MainImagesModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public  Object instantiateItem(@NonNull ViewGroup container, int position) {
        try {
            custom = View.inflate(context, R.layout.layout_main_flipper, null);
            contentsModel = arrayList.get(position);
            ShapeableImageView imageView = custom.findViewById(R.id.imageView);
            RelativeLayout relativeLayout = custom.findViewById(R.id.relative_layout);

            relativeLayout.setOnClickListener(v -> {
                if(arrayList.get(position).getIsLink().equals("1")){
                    try {
                        Uri url = Uri.parse(arrayList.get(position).getAction());
                        Intent launch = new Intent(Intent.ACTION_VIEW, url);
                        context.startActivity(launch);
                    } catch (Exception | Error ignored) {
                    }
                }else {
                    try {
                        Class<?> action = Class.forName(arrayList.get(position).getAction());
                        Intent intent = new Intent(context, action);
                        intent.putExtra("action", arrayList.get(position).getAction());
                        intent.putExtra("extra1", arrayList.get(position).getExtra1());
                        intent.putExtra("extra2", arrayList.get(position).getExtra2());
                        context.startActivity(intent);
                    } catch (Exception | Error ignored) {
                    }

                }



            });
            Glide.with(context).load(arrayList.get(position).getUrl()).into(imageView);
            container.addView(custom);
            return custom;
        }catch (Exception | Error ignored){}
        return custom;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}