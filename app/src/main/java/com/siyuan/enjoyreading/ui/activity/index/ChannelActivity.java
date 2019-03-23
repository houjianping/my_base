package com.siyuan.enjoyreading.ui.activity.index;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.androidapp.activity.BaseActivity;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.ChannelAdapter;
import com.siyuan.enjoyreading.entity.ChannelEntity;
import com.siyuan.enjoyreading.util.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ChannelAdapter mChannelAdapter;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_channel);
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recy);
        mTitleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChannelAdapter.changeEditMode(mRecyclerView);
                mTitleBar.getRightTextView().setText(mChannelAdapter.isEditMode() ? R.string.finish : R.string.edit);
            }
        });
    }

    @Override
    protected void initData() {
        final List<ChannelEntity> items = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName("频道" + i);
            items.add(entity);
        }
        final List<ChannelEntity> otherItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName("其他" + i);
            otherItems.add(entity);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        final ItemTouchHelper helper = new ItemTouchHelper(new ItemDragHelperCallback());
        helper.attachToRecyclerView(mRecyclerView);

        mChannelAdapter = new ChannelAdapter(this, helper, items, otherItems, mChannelCallBack);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mChannelAdapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(mChannelAdapter);
        mChannelAdapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelActivity.this, items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    ChannelAdapter.ChannelCallBack mChannelCallBack = new ChannelAdapter.ChannelCallBack() {
        @Override
        public void onItemLongPress() {
            mTitleBar.getRightTextView().setText(mChannelAdapter.isEditMode() ? R.string.finish : R.string.edit);
        }
    };
}
