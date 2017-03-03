package com.zeller.fastlibrary.dagger2.lesson2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zeller.fastlibrary.R;
import com.zeller.fastlibrary.dagger2.Poetry;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OtherActivity extends AppCompatActivity {

    @Bind(R.id.tv_other)
    TextView tvOther;
    @Inject
    Poetry mPoetry;
    @Inject
    Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        MainComponent.getInstance()
                .inject(this);

        initView();
    }

    private void initView() {
        String json = mGson.toJson(mPoetry);
        String text = json + ",mPoetry:" + mPoetry;
        tvOther.setText(text);
    }
}
