package com.dotonce.mainconfig.Views;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppCompatClassFragment;
import com.dotonce.mainconfig.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class AppMainFragment extends AppCompatClassFragment {
    private Runnable runnable=null;
    private Handler handler;
    public int slider_time= 3000;
    public ViewPager viewPager;
    public DotsIndicator dotsIndicator;
    public RelativeLayout layout_images;
    public MainImagesAdapter mainImagesAdapter;

    public AppMainFragment() {

    }


    public static AppMainFragment newInstance() {
        AppMainFragment fragment = new AppMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=  inflater.inflate(R.layout.fragment_app_main, container, false);
        if(getActivity() != null){
          setMethods();
        }

        return v;
    }

    @Override
    public void setInitialize(View v) {
        super.setInitialize(v);
        handler=new Handler();
        viewPager = v.findViewById(R.id.viewPager);
        dotsIndicator = v.findViewById(R.id.dots_indicator);
        layout_images = v.findViewById(R.id.layout_images);
    }

    @Override
    public void onPause() {
        removeRunnable();
        super.onPause();
    }

    @Override
    public void onStop() {
        removeRunnable();
        super.onStop();
    }

    public void removeRunnable(){
        try {
            handler.removeCallbacks(runnable);
        }catch (Error | Exception ignored){}
    }
    public void startAutoSlider(final int count) {
        try {
            runnable = () -> {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, slider_time);
            };
            handler.postDelayed(runnable, slider_time);

        }catch (Error | Exception ignored){}
    }
}