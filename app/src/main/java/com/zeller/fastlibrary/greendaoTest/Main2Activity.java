package com.zeller.fastlibrary.greendaoTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.zeller.fastlibrary.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @Bind(R.id.start)
    Button start;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        dbManager = DBManager.getmInstance(this);


    }

    private void insert() {
//        List<User> users = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            User user = new User();
//            user.setId(i);
            user.setAge(i * 3);
            user.setName("第" + i + "人");
//            users.add(user);
            dbManager.insertUser(user);
        }
//        dbManager.insertUserList(users);
    }

    private void queryList() {
        List<User> userList = dbManager.queryUserList();
        for (User user : userList) {
            Log.e("TAG", "queryUserList--before-->" + user.getId() + "--" + user.getName() + "--" + user.getAge());
            if (user.getId() == 0) {
                dbManager.deleteUser(user);
            }
            if (user.getId() == 3) {
                user.setAge(10);
                dbManager.updateUser(user);
            }
        }
    }

    private void quertList2() {
        List<User> userList = dbManager.queryUserList();
        for (User user : userList) {
            Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName() + "--" + user.getAge());
        }
    }

    @OnClick(R.id.start)
    public void onClick() {
        insert();
        queryList();
        quertList2();
    }
}
