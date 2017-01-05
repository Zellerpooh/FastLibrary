package com.zeller.fastlibrary.tutorial.customview.bezier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.zeller.fastlibrary.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Bezier2Activity extends AppCompatActivity {

    @Bind(R.id.rbtn_one)
    RadioButton rbtnOne;
    @Bind(R.id.rbtn_two)
    RadioButton rbtnTwo;
    @Bind(R.id.bezier2)
    Bezier2 bezier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rbtn_one, R.id.rbtn_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn_one:
                bezier2.setMode(true);
                break;
            case R.id.rbtn_two:
                bezier2.setMode(false);
                break;
        }
    }
}
