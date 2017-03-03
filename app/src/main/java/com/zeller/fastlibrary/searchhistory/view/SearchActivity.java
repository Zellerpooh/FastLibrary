package com.zeller.fastlibrary.searchhistory.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeller.fastlibrary.R;
import com.zeller.fastlibrary.searchhistory.CleanEditText;
import com.zeller.fastlibrary.searchhistory.OnSearchHistoryListener;
import com.zeller.fastlibrary.searchhistory.SearchHistoryAdapter;
import com.zeller.fastlibrary.searchhistory.bean.SearchHistoryModel;
import com.zeller.fastlibrary.searchhistory.presenter.SearchPresenter;
import com.zeller.fastlibrary.searchhistory.presenter.SearchPresenterImpl;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private SearchPresenter mSearchPresenter;
    private SearchHistoryAdapter searchHistoryAdapter;
    private ArrayList<SearchHistoryModel> histories = new ArrayList<>();
    @Bind(R.id.layout_statusbar)
    LinearLayout layoutStatusbar;
    @Bind(R.id.btn_search_cancel)
    TextView btnSearchCancel;
    @Bind(R.id.et_search)
    CleanEditText etSearch;
    @Bind(R.id.listView_history)
    ListView listViewHistory;
    @Bind(R.id.ll_search_empty)
    LinearLayout llSearchEmpty;
    @Bind(R.id.ll_search_history)
    LinearLayout llSearchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initStatusBar();
        mSearchPresenter = new SearchPresenterImpl(this, this);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(etSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
        searchHistoryAdapter = new SearchHistoryAdapter(this, histories);
        searchHistoryAdapter.setOnSearchHistoryListener(new OnSearchHistoryListener() {
            @Override
            public void onDelete(String key) {
                mSearchPresenter.remove(key);
            }

            @Override
            public void onSelect(String content) {
                etSearch.setText(content);
                etSearch.setSelection(content.length());
                search(content);
            }
        });
        listViewHistory.setAdapter(searchHistoryAdapter);
        etSearch.addTextChangedListener(textWatcher);
        mSearchPresenter.sortHistory();
    }

    /**
     * 初始化手机状态栏，保持状态栏和手机app的颜色主题一致
     */
    private void initStatusBar() {
        //将手机状态栏透明化，
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {

        }
    }

    @Override
    public void showHistories(ArrayList<SearchHistoryModel> results) {
        llSearchEmpty.setVisibility(0 != results.size() ? View.VISIBLE : View.GONE);
        searchHistoryAdapter.refreshData(results);
    }

    @Override
    public void searchSuccess(String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_search_cancel, R.id.btn_search_check, R.id.ll_search_empty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_cancel:
                break;
            case R.id.btn_search_check:
                String value = etSearch.getText().toString().trim();
                search(value);
                break;
            case R.id.ll_search_empty:
                mSearchPresenter.clear();
                break;
        }
    }

    public void search(String value) {
        if (!TextUtils.isEmpty(value)) {
            // 先隐藏键盘
            ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            mSearchPresenter.search(value);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //在500毫秒内改变时不发送
            if (mHandler.hasMessages(MSG_SEARCH)) {
                mHandler.removeMessages(MSG_SEARCH);
            }
            if (TextUtils.isEmpty(s)) {
                llSearchHistory.setVisibility(View.VISIBLE);
                mSearchPresenter.sortHistory();
            } else {
                llSearchHistory.setVisibility(View.GONE);
                //否则延迟500ms开始模糊搜索
                Message message = mHandler.obtainMessage();
                message.obj = s;
                message.what = MSG_SEARCH;
                mHandler.sendMessageDelayed(message, 500); //自动搜索功能 删除
            }
        }
    };
    //模糊搜索
    private static final int MSG_SEARCH = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            search(etSearch.getText().toString().trim());
        }
    };
}
