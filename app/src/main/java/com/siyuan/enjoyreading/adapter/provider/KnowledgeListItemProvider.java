package com.siyuan.enjoyreading.adapter.provider;

import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.KnowledgeListItem;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.util.IntentUtil;

public class KnowledgeListItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_KNOWLEDGE_LIST_ITEM;
    }

    @Override
    public int layout() {
        return R.layout.item_knowledge;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        KnowledgeListItem item = (KnowledgeListItem) multipleEntity;
        viewHolder.setText(R.id.article_time, "2018-01-05")
                .setText(R.id.article_read_num, "139次播放")
                .setText(R.id.article_title, "第5集 荒山野岭").addOnClickListener(R.id.article_item);
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
        mContext.startActivity(IntentUtil.getIntent("{\"page\":\"SettingAbout\",\"type\":1}"));
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}