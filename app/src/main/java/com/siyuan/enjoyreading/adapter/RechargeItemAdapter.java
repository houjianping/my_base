package com.siyuan.enjoyreading.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidapp.pagedgridview.PagedGridLayout;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.RechargeBean;

import java.util.ArrayList;
import java.util.List;

public class RechargeItemAdapter extends BaseAdapter {

    private List<RechargeBean> recommendPrasises = new ArrayList<>();
    private Context mContext;
    private RechargeBean mSelectedRechargeInfo;

    public RechargeItemAdapter(Context context, List<RechargeBean> prasises) {
        mContext = context;
        recommendPrasises.clear();
        recommendPrasises.addAll(prasises);
    }

    @Override
    public int getCount() {
        return recommendPrasises.size();
    }

    @Override
    public RechargeBean getItem(int position) {
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
            convertView = View.inflate(mContext, R.layout.item_recharge, null);
            holder.titleView = convertView.findViewById(R.id.tv_recharge_title);
            holder.contextView = convertView.findViewById(R.id.tv_recharge_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleView.setText("" + getItem(position).getTitle());
        holder.contextView.setText("￥" + getItem(position).getContent());
        convertView.setBackgroundResource(mSelectedRechargeInfo != null && mSelectedRechargeInfo == getItem(position) ? R.drawable.item_border_selected : R.drawable.item_border);
        if (mSelectedRechargeInfo == null && position == 0) {
            convertView.setBackgroundResource(R.drawable.item_border_selected);
        } else {
            convertView.setBackgroundResource(mSelectedRechargeInfo != null && mSelectedRechargeInfo == getItem(position) ? R.drawable.item_border_selected : R.drawable.item_border);
        }
        return convertView;
    }

    public void setSelected(RechargeBean rechargeInfo) {
        this.mSelectedRechargeInfo = rechargeInfo;
        notifyDataSetChanged();
    }

    public RechargeBean getRechargeInfo() {
        if (mSelectedRechargeInfo == null) {
            return getItem(0);
        } else {
            return mSelectedRechargeInfo;
        }
    }

    class ViewHolder {
        TextView titleView;
        TextView contextView;
    }
}
