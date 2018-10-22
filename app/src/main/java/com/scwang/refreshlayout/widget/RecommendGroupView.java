package com.scwang.refreshlayout.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.androidapp.pagedgridview.PagedGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.adapter.RecommendItemAdapter;
import com.scwang.refreshlayout.api.ApiConfig;
import com.scwang.refreshlayout.bean.Movie;
import com.scwang.refreshlayout.ui.activity.KnowledgeDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RecommendGroupView extends LinearLayout {

    private View mBaseView;
    private Activity mActivity;

    public RecommendGroupView(Activity context) {
        super(context);
        mActivity = context;
        initView();
    }

    public RecommendGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.index_recommend_group, this);
        PagedGridView pagedGridView = mBaseView.findViewById(R.id.recommend_group);
        List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES_SHORT, new TypeToken<ArrayList<Movie>>() {
        }.getType());
        pagedGridView.setAdapter(new RecommendItemAdapter(getContext(), movies));
        pagedGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.startActivity(new Intent(getContext(), KnowledgeDetailActivity.class));
            }
        });
    }
}
