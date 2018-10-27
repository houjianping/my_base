package com.siyuan.enjoyreading.adapter.provider;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.bumptech.glide.Glide;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.OrderMovie;
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;

public class OrderMovieItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_ORDER_MOVIE;
    }

    @Override
    public int layout() {
        return R.layout.listitem_movie_item;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        OrderMovie item = (OrderMovie) multipleEntity;
        viewHolder.setText(R.id.lmi_title, item.filmName)
                .setText(R.id.lmi_actor, item.actors)
                .setText(R.id.lmi_grade, item.grade)
                .setText(R.id.lmi_describe, item.shortinfo).addOnClickListener(R.id.movie_item);
        Glide.with(mContext).load(item.picaddr).into((ImageView) viewHolder.getView(R.id.lmi_avatar));
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
        mContext.startActivity(new Intent(mContext, VideoListActivity.class));
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}