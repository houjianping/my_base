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
        RecommendPrasise recommendPrasise = new RecommendPrasise();
        recommendPrasise.setTitle("1如何2一分钟3聊到男神?");
        recommendPrasise.setIconurl("http://p1.meituan.net/movie/55c57c37c9baa412aa9351f385275ef861052.jpg");
        List<RecommendPrasise> prasises = new ArrayList<>();
        prasises.add(recommendPrasise);
        prasises.add(recommendPrasise);
        prasises.add(recommendPrasise);
        prasises.add(recommendPrasise);
        PagedGridView pagedGridView = viewHolder.getView(R.id.praise_gridview);
        RecommendPrasiseAdapter recommendPrasiseAdapter = new RecommendPrasiseAdapter(mContext, prasises);
        recommendPrasiseAdapter.setOnItemClick(new PagedGridLayout.OnGridItemClick() {
            @Override
            public void onGridItemClick(Object item) {
                mContext.startActivity(VideoListActivity.getIntent(mContext));
            }
        });
        pagedGridView.setAdapter(recommendPrasiseAdapter);
        switch (item.getColumn()) {
            case 4:
                pagedGridView.setNumColumns(4);
                break;
            case 2:
                pagedGridView.setNumColumns(2);
                break;
            default:
                pagedGridView.setNumColumns(1);
                break;
        }
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}