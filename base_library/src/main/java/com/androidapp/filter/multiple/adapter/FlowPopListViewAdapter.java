package com.androidapp.filter.multiple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.filter.multiple.bean.MultiBean;
import com.androidapp.filter.multiple.view.SkuFlowLayout;
import com.androidapp.roundview.RoundTextView;

import java.util.List;

public class FlowPopListViewAdapter extends BaseAdapter {

    private Context context;
    private List<MultiBean> dictList;

    public FlowPopListViewAdapter(Context context, List<MultiBean> dictList) {
        this.context = context;
        this.dictList = dictList;
    }

    @Override
    public int getCount() {
        return dictList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.filter_item_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = (TextView) convertView.findViewById(R.id.tv_type_name);
            vh.layoutProperty = (SkuFlowLayout) convertView.findViewById(R.id.layout_property);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MultiBean multiBean = dictList.get(position);
        vh.tvTypeName.setText(multiBean.getTypeName());

        setFlowLayoutData(multiBean.getChildren(), vh.layoutProperty);

        return convertView;
    }

    private void setFlowLayoutData(final List<MultiBean.Children> childrenList, final SkuFlowLayout flowLayout) {
        flowLayout.removeAllViews();
        for (int x = 0; x < childrenList.size(); x++) {
            RoundTextView checkBox = (RoundTextView) View.inflate(context, R.layout.filter_item_check, null);
            checkBox.setText(childrenList.get(x).getValue());
            if (childrenList.get(x).isSelected()) {
//                checkBox.setChecked(true);
                checkBox.getDelegate().setStrokeColor(context.getResources().getColor(R.color.color_primary));
                childrenList.get(x).setSelected(true);
            } else {
//                checkBox.setChecked(false);
                checkBox.getDelegate().setStrokeColor(context.getResources().getColor(R.color.divider));
                childrenList.get(x).setSelected(false);
            }
            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(flowLayout, finalX, childrenList);
                }
            });
            flowLayout.addView(checkBox);
        }
    }

    private void refreshCheckBox(SkuFlowLayout flowLayout, int finalX, List<MultiBean.Children> propBeenList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            RoundTextView radio = (RoundTextView) flowLayout.getChildAt(y);
//            radio.setChecked(false);
            radio.getDelegate().setStrokeColor(context.getResources().getColor(R.color.divider));
            propBeenList.get(y).setSelected(false);
            if (finalX == y) {
//                radio.setChecked(true);
                radio.getDelegate().setStrokeColor(context.getResources().getColor(R.color.color_primary));
                propBeenList.get(y).setSelected(true);
            }
        }
    }

    class ViewHolder {
        private TextView tvTypeName;
        private SkuFlowLayout layoutProperty;
    }
}
