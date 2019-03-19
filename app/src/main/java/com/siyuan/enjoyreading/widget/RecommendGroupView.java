package com.siyuan.enjoyreading.widget;

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
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.RecommendItemAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.ui.activity.knwoledge.KnowledgeChapterActivity;

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
        List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
        }.getType());
        pagedGridView.setAdapter(new RecommendItemAdapter(getContext(), movies));
        pagedGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.startActivity(new Intent(getContext(), KnowledgeChapterActivity.class));
            }
        });
    }
}
