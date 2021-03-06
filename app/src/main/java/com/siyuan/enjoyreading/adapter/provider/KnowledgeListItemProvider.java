package com.siyuan.enjoyreading.adapter.provider;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.KnowledgeListItem;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.NewsItem;
import com.siyuan.enjoyreading.ui.activity.knwoledge.ArticleVideoDetailPlayer;

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
        NewsItem videoItem = new NewsItem();
        videoItem.setMp4_url("http://flv3.bn.netease.com/videolib3/1604/14/LSwHa2712/SD/LSwHa2712-mobile.mp4");
        videoItem.setCover("http://vimg3.ws.126.net/image/snapshot/2016/4/F/M/VBJLSB3FM.jpg");
        videoItem.setTitle("67名电信诈骗嫌疑人被押回国");
        mContext.startActivity(ArticleVideoDetailPlayer.getIntent(mContext, videoItem));
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}