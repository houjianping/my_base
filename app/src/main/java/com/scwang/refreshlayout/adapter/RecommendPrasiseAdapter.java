package com.scwang.refreshlayout.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.pagedgridview.PagedGridLayout;
import com.androidapp.widget.AppRoundImageView;
import com.bumptech.glide.Glide;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.bean.RecommendPrasise;

import java.util.ArrayList;
import java.util.List;

public class RecommendPrasiseAdapter extends BaseAdapter {

    PagedGridLayout.OnGridItemClick onGridItemClick;
    private List<RecommendPrasise> recommendPrasises = new ArrayList<>();
    private Activity mContext;

    public RecommendPrasiseAdapter(Activity context, List<RecommendPrasise> prasises) {
        mContext = context;
        recommendPrasises.clear();
        recommendPrasises.addAll(prasises);
    }

    @Override
    public int getCount() {
        return recommendPrasises.size();
    }

    @Override
    public RecommendPrasise getItem(int position) {
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
            convertView = View.inflate(mContext, R.layout.item_recommend_prasise, null);
            holder.iconView = (ImageView) convertView.findViewById(R.id.prasise_icon);
            holder.titleView = (TextView) convertView.findViewById(R.id.prasise_title);
            holder.baseView = convertView.findViewById(R.id.prasise_container);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(getItem(position).getIconurl()) && !(mContext).isFinishing()) {
            Glide.with(mContext).load(getItem(position).getIconurl()).into(holder.iconView);
        }
        if (holder.titleView != null) {
            if (!TextUtils.isEmpty(getItem(position).getTitle())) {
                holder.titleView.setVisibility(View.VISIBLE);
                holder.titleView.setText(getItem(position).getTitle());
            } else {
                holder.titleView.setVisibility(View.GONE);
            }
        }
        holder.baseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGridItemClick != null) {
                    onGridItemClick.onGridItemClick(getItem(position));
                }
            }
        });
        return convertView;
    }

    public void setOnItemClick(PagedGridLayout.OnGridItemClick listener) {
        onGridItemClick = listener;
    }

    class ViewHolder {
        LinearLayout baseView;
        ImageView iconView;
        TextView titleView;
    }
}
