package com.scwang.refreshlayout.adapter;

import android.widget.ImageView;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.adapter.BaseViewHolder;
import com.bumptech.glide.Glide;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.bean.Movie;

public class QuickAdapter extends BaseQuickAdapter<Movie, BaseViewHolder> {
    public QuickAdapter() {
        super(R.layout.listitem_movie_item);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Movie item) {
        viewHolder.setText(R.id.lmi_title, item.filmName)
                .setText(R.id.lmi_actor, item.actors)
                .setText(R.id.lmi_grade, item.grade)
                .setText(R.id.lmi_describe, item.shortinfo).addOnClickListener(R.id.movie_item);
        Glide.with(mContext).load(item.picaddr).into((ImageView) viewHolder.getView(R.id.lmi_avatar));
    }
}
