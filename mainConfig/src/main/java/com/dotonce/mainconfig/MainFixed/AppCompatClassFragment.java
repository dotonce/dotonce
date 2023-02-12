package com.dotonce.mainconfig.MainFixed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dotonce.mainconfig.R;

public class AppCompatClassFragment extends Fragment implements MyClassFragment {

    public ConfigurationClass configurationClass;


    public UserData userData;

    private View v;

    public AppCompatClassFragment() {

    }


    public static AppCompatClassFragment newInstance() {
        AppCompatClassFragment fragment = new AppCompatClassFragment();
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

        v=  inflater.inflate(R.layout.fragment_app_main, container, false);
        if(getActivity() != null){
            configurationClass = new ConfigurationClass(getActivity());
            userData = new UserData(getActivity());
            configurationClass = new ConfigurationClass(getActivity());
            userData = new UserData(getActivity());
        }

        return v;
    }


    @Override
    public void setActions(View v) {

    }

    @Override
    public void setInitialize(View v) {

    }

    public void setMethods(){
        setInitialize(v);
        setActions(v);
    }

}
