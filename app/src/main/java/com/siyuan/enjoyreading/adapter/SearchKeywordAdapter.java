package com.siyuan.enjoyreading.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.pagedgridview.PagedGridLayout;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.SearchKeyword;

import java.util.ArrayList;
import java.util.List;

public class SearchKeywordAdapter extends BaseAdapter {

    private PagedGridLayout.OnGridItemClick onGridItemClick;
    private List<SearchKeyword> keywords = new ArrayList<>();
    private Context mContext;

    public SearchKeywordAdapter(Context context, List<SearchKeyword> searchKeywords) {
        mContext = context;
        keywords.clear();
        keywords.addAll(searchKeywords);
    }

    @Override
    public int getCount() {
        return keywords.size();
    }

    @Override
    public SearchKeyword getItem(int position) {
        return keywords.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_search_keyword, null);
            holder.titleView = convertView.findViewById(R.id.tv_keyword);
            holder.baseView = convertView.findViewById(R.id.container);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleView.setText(getItem(position).getKeyword());
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
        TextView titleView;
    }
}
