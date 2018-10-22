package com.androidapp.pagedgridview;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.base.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class PagedGridViewAdapter<T extends PagedGridItem> extends BaseAdapter {

    private Activity mContext;
    private List<T> mDynamicList;
    private boolean mShowBigCategory;
    PagedGridLayout.OnGridItemClick onGridItemClick;

    public PagedGridViewAdapter(Activity context, boolean showBig, List<T> dynamicList) {
        this.mContext = context;
        mDynamicList = dynamicList;
        mShowBigCategory = showBig;
    }

    @Override
    public int getCount() {
        return (null == mDynamicList) ? 0 : mDynamicList.size();
    }

    @Override
    public T getItem(int position) {
        return mDynamicList.get(position);
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
            convertView = View.inflate(mContext, mShowBigCategory ? R.layout.paged_gridview_big_item : R.layout.paged_gridview_item, null);
            holder.dynamicImageView = (ImageView) convertView.findViewById(R.id.iv_dynamic_icon);
            holder.dynamicSummaryView = (TextView) convertView.findViewById(R.id.iv_dynamic_summary);
            holder.tvOperateTextView = (TextView) convertView.findViewById(R.id.tv_operate);
            holder.dynamicContainer = convertView.findViewById(R.id.dynamic_container);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(getItem(position).getIcon_url()) && !(mContext).isFinishing()) {
            Glide.with(mContext).load(getItem(position).getIcon_url()).into(holder.dynamicImageView);
        } else {
            Glide.with(mContext).load(getItem(position).getIcon()).into(holder.dynamicImageView);
        }
        if (getItem(position).getTitle() != null) {
            holder.dynamicSummaryView.setText(getItem(position).getTitle());
        }
        if (holder.tvOperateTextView != null) {
            if (!TextUtils.isEmpty(getItem(position).getBadge_text())) {
                holder.tvOperateTextView.setVisibility(View.VISIBLE);
                holder.tvOperateTextView.setText(getItem(position).getBadge_text());
            } else {
                holder.tvOperateTextView.setVisibility(View.GONE);
            }
        }
        holder.dynamicContainer.setOnClickListener(new View.OnClickListener() {
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
        ImageView dynamicImageView;
        TextView dynamicSummaryView;
        TextView tvOperateTextView;
        View dynamicContainer;
    }
}
