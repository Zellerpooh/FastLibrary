package com.zeller.fastlibrary.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zeller.fastlibrary.R;
import com.zeller.fastlibrary.dagger2.lesson2.MainComponent;
import com.zeller.fastlibrary.dagger2.lesson2.OtherActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dagger2Activity extends AppCompatActivity {

    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    Poetry mPoetry;
    @Inject
    Gson mGson;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_open)
    Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        ButterKnife.bind(this);

        // 使用Dagger2生成的类 生成组件进行构造，并注入
//        DaggerMainComponent.builder().build().inject(this);
        MainComponent.getInstance()
                .inject(this);

        initView();
    }

    private void initView() {
        String json = mGson.toJson(mPoetry);
        String text = json + ",mPoetry:" + mPoetry;
        tvTitle.setText(text);
    }

    @OnClick(R.id.btn_open)
    public void onClick() {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }
}
