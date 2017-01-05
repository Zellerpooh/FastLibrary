package com.zeller.fastlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zeller.fastlibrary.tutorial.customview.bezier.Bezier1Activity;
import com.zeller.fastlibrary.tutorial.customview.bezier.Bezier2Activity;
import com.zeller.fastlibrary.tutorial.customview.bezier.Bezier3Activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bezier1)
    Button bezier1;
    @Bind(R.id.bezier2)
    Button bezier2;
    @Bind(R.id.bezier3)
    Button bezier3;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bezier1, R.id.bezier2, R.id.bezier3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bezier1:
                changeTo(Bezier1Activity.class);
                break;
            case R.id.bezier2:
                changeTo(Bezier2Activity.class);
                break;
            case R.id.bezier3:
                changeTo(Bezier3Activity.class);
                break;
        }
    }

    private void changeTo(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }


}
