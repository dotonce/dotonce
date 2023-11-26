package com.dotonce.dotonce;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dotonce.mainconfig.Views.AppMainActivity;

public class DotonceTestActivity extends AppMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dotonce_test);
    }
}