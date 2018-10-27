package com.siyuan.enjoyreading.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.pagedgridview.PagedGridLayout;
import com.androidapp.widget.AppRoundImageView;
import com.bumptech.glide.Glide;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class RecommendItemAdapter extends BaseAdapter {

    PagedGridLayout.OnGridItemClick onGridItemClick;
    private List<Movie> recommendPrasises = new ArrayList<>();
    private Context mContext;

    public RecommendItemAdapter(Context context, List<Movie> prasises) {
        mContext = context;
        recommendPrasises.clear();
        recommendPrasises.addAll(prasises);
    }

    @Override
    public int getCount() {
        return recommendPrasises.size();
    }

    @Override
    public Movie getItem(int position) {
        return recommendPrasises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.listitem_movie_item, null);
            holder.baseView = convertView.findViewById(R.id.movie_item);
            holder.iconView = convertView.findViewById(R.id.lmi_avatar);
            holder.titleView = convertView.findViewById(R.id.lmi_title);
            holder.summaryView = convertView.findViewById(R.id.lmi_describe);
            holder.contextView = convertView.findViewById(R.id.lmi_actor);
            holder.shortDescView = convertView.findViewById(R.id.lmi_grade);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleView.setText(getItem(position).getFilmName());
        holder.summaryView.setText(getItem(position).getShortinfo());
        holder.contextView.setText(getItem(position).getActors());
        holder.shortDescView.setText(getItem(position).getGrade());
        Glide.with(mContext).load(getItem(position).getPicaddr()).into(holder.iconView);
        return convertView;
    }

    public void setOnItemClick(PagedGridLayout.OnGridItemClick listener) {
        onGridItemClick = listener;
    }

    class ViewHolder {
        LinearLayout baseView;
        AppRoundImageView iconView;
        TextView titleView;
        TextView summaryView;
        TextView contextView;
        TextView shortDescView;
    }
}
