package com.siyuan.enjoyreading.adapter.provider;

import android.view.View;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.pagedgridview.PagedGridLayout;
import com.androidapp.pagedgridview.PagedGridView;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.adapter.RecommendPrasiseAdapter;
import com.siyuan.enjoyreading.entity.GridItem;
import com.siyuan.enjoyreading.entity.HeaderItem;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.RecommendPrasise;
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;

import java.util.ArrayList;
import java.util.List;

public class IndexGridItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_GRID;
    }

    @Override
    public int layout() {
        return R.layout.item_grid_column_2;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        GridItem item = (GridItem) multipleEntity;
        PagedGridView pagedGridView = viewHolder.getView(R.id.praise_gridview);
        RecommendPrasiseAdapter recommendPrasiseAdapter = new RecommendPrasiseAdapter(mContext, item.getRecommendPrasise());
        recommendPrasiseAdapter.setOnItemClick(new PagedGridLayout.OnGridItemClick() {
            @Override
            public void onGridItemClick(Object item) {
                mContext.startActivity(VideoListActivity.getIntent(mContext));
            }
        });
        pagedGridView.setAdapter(recommendPrasiseAdapter);
        pagedGridView.setNumColumns(item.getColumn());
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}