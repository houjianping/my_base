package com.siyuan.enjoyreading.adapter.provider;

import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.bumptech.glide.Glide;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.Movie;

public class TestItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_TEST;
    }

    @Override
    public int layout() {
        return R.layout.listitem_movie_item;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        Movie item = (Movie) multipleEntity;
        viewHolder.setText(R.id.lmi_title, item.filmName)
                .setText(R.id.lmi_actor, item.actors)
                .setText(R.id.lmi_grade, item.grade)
                .setText(R.id.lmi_describe, item.shortinfo).addOnClickListener(R.id.movie_item);
        Glide.with(mContext).load(item.picaddr).into((ImageView) viewHolder.getView(R.id.lmi_avatar));
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}