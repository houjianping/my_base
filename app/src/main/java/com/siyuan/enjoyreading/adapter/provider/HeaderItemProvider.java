package com.siyuan.enjoyreading.adapter.provider;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.HeaderItem;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.util.IntentUtil;

public class HeaderItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_HEADER;
    }

    @Override
    public int layout() {
        return R.layout.view_header;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        final HeaderItem item = (HeaderItem) multipleEntity;
        viewHolder.setText(R.id.tv_header_title, item.getLeftTitle())
                .setText(R.id.tv_header_total, item.getRightTitle());
        viewHolder.getView(R.id.ll_header_total).setVisibility(item.isShowMoreItem() ? View.VISIBLE : View.GONE);
        viewHolder.getView(R.id.ll_header_total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = IntentUtil.getIntent(item.getAction());
                    if (intent != null) {
                        mContext.startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}