package com.siyuan.enjoyreading.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.pagedgridview.PagedGridLayout;
import com.androidapp.pagedgridview.PagedGridView;
import com.androidapp.utils.ToastUtils;
import com.androidapp.widget.CommonTitleBar;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.SearchKeywordAdapter;
import com.siyuan.enjoyreading.entity.SearchKeyword;

import java.util.ArrayList;
import java.util.List;

public class SearchKeyWordActivity extends BaseActivity {

    private CommonTitleBar mTitleBar;
    private PagedGridView mHistoryGridView;
    private PagedGridView mRecommendGridView;
    private PagedGridView mSearchGridView;
    private View historyView;

    private List<SearchKeyword> historyKeywordList = new ArrayList<>();
    private List<SearchKeyword> recommendKeywordList = new ArrayList<>();
    private List<SearchKeyword> searchKeywordList = new ArrayList<>();
    private SearchKeywordAdapter historyAdapter;
    private SearchKeywordAdapter recommendAdapter;
    private SearchKeywordAdapter searchAdapter;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_search_keyword);
        mHistoryGridView = findViewById(R.id.pgv_history);
        mRecommendGridView = findViewById(R.id.pgv_recommend);
        mSearchGridView = findViewById(R.id.pgv_search);
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titlebar);
        historyView = findViewById(R.id.ll_history);
        mTitleBar.getRightTextView().setText("搜索");
        mTitleBar.getRightTextView().setTextColor(getResources().getColor(R.color.colorPrimary));
        mTitleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = mTitleBar.getSearchKey();
                if (!TextUtils.isEmpty(keyWord)) {
                    ToastUtils.show(keyWord);
                }
            }
        });
        mTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                        || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                    onBackPressed();
                } else if (action == CommonTitleBar.ACTION_SEARCH_SUBMIT) {
                    if (!TextUtils.isEmpty(extra)) {
                        doUiUpdate(true);
                        ToastUtils.show(extra);
                    }
                } else if (action == CommonTitleBar.ACTION_SEARCH_DELETE) {
                    doUiUpdate(false);
                }
            }
        });
        mTitleBar.setOnSearchTextChanged(new CommonTitleBar.OnSearchTextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 0) {
                    doUiUpdate(false);
                } else {
                    doUiUpdate(true);
                }
            }
        });
    }

    private void doUiUpdate(boolean showSearch) {
        historyView.setVisibility(showSearch ? View.GONE : View.VISIBLE);
        mSearchGridView.setVisibility(showSearch ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initData() {
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛1"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛2"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛3"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛4"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛5"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛6"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛7"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛8"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛9"));
        historyKeywordList.add(new SearchKeyword("旭旭宝宝你真猛10"));

        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛111111111111111111111111111111111111111111111111111111"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛12"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛13"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛14"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛15"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛16"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛17"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛18"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛19"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛20"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛21"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛22"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛23"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛24"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛25"));
        recommendKeywordList.add(new SearchKeyword("旭旭宝宝你真猛26"));

        historyAdapter = new SearchKeywordAdapter(this, historyKeywordList);
        mHistoryGridView.setAdapter(historyAdapter);
        historyAdapter.setOnItemClick(mOnGridItemClick);

        recommendAdapter = new SearchKeywordAdapter(this, recommendKeywordList);
        mRecommendGridView.setAdapter(recommendAdapter);
        recommendAdapter.setOnItemClick(mOnGridItemClick);

        searchAdapter = new SearchKeywordAdapter(this, searchKeywordList);
        mSearchGridView.setAdapter(searchAdapter);
        searchAdapter.setOnItemClick(mOnGridItemClick);
    }

    private PagedGridLayout.OnGridItemClick mOnGridItemClick = new PagedGridLayout.OnGridItemClick() {
        @Override
        public void onGridItemClick(Object item) {
            if (item instanceof SearchKeyword) {
                ToastUtils.show("关键词点击:" + ((SearchKeyword)item).getKeyword());
            }
        }
    };
}
