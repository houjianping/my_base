package com.siyuan.enjoyreading.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.utils.ImageLoaderUtils;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.SmallCategoryItem;

import java.util.ArrayList;
import java.util.List;

public class SmallCategoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<SmallCategoryItem> keywords = new ArrayList<>();

    public SmallCategoryAdapter(Context context, List<SmallCategoryItem> searchKeywords) {
        mContext = context;
        keywords.clear();
        keywords.addAll(searchKeywords);
    }

    @Override
    public int getCount() {
        return keywords.size();
    }

    @Override
    public SmallCategoryItem getItem(int position) {
        return keywords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_small_category, null);
            holder.iconImageView = convertView.findViewById(R.id.iv_keyword);
            holder.titleView = convertView.findViewById(R.id.tv_keyword);
            holder.baseView = convertView.findViewById(R.id.container);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleView.setText(getItem(position).getName());
        ImageLoaderUtils.display(mContext, holder.iconImageView, getItem(position).getIcon_url());
        return convertView;
    }

    class ViewHolder {
        LinearLayout baseView;
        ImageView iconImageView;
        TextView titleView;
    }
}
