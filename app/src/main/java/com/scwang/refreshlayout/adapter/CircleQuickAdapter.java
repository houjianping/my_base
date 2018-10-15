package com.scwang.refreshlayout.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.androidapp.base.adapter.BaseQuickAdapter;
import com.androidapp.base.adapter.BaseViewHolder;
import com.androidapp.base.utils.ImageLoaderUtils;
import com.androidapp.base.utils.TimeUtil;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.bean.circle.CircleItem;
import com.scwang.refreshlayout.bean.circle.CommentItem;
import com.scwang.refreshlayout.ui.ui.common.BigImagePagerActivity;
import com.scwang.refreshlayout.widget.CommentListView;
import com.scwang.refreshlayout.widget.ExpandableTextView;
import com.scwang.refreshlayout.widget.MultiImageView;

import java.util.List;

public class CircleQuickAdapter extends BaseQuickAdapter<CircleItem, BaseViewHolder> {
    public CircleQuickAdapter() {
        super(R.layout.item_circle_list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, CircleItem item) {
        viewHolder.setText(R.id.nameTv, item.getNickName())
                .setText(R.id.timeTv, TimeUtil.getfriendlyTime(item.getCreateTime()))
                .setText(R.id.tv_address_or_distance, "广州 <7KM").addOnClickListener(R.id.movie_item);
        ExpandableTextView expandableTextView = viewHolder.getView(R.id.contentTv);
        expandableTextView.setText(item.getContent());
        ImageLoaderUtils.displayRound(mContext, (ImageView) viewHolder.getView(R.id.headIv), "http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg");
        if (viewHolder.getView(R.id.multiImagView) == null) {
            ViewStub linkOrImgViewStub = (ViewStub) viewHolder.getView(R.id.linkOrImgViewStub);
            linkOrImgViewStub.setLayoutResource(R.layout.item_circle_viewstub_imgbody);
            linkOrImgViewStub.inflate();
        }
        final List<String> photos = item.getPictureList();
        if (photos != null && photos.size() > 0) {
            MultiImageView multiImageView = (MultiImageView) viewHolder.getView(R.id.multiImagView);
            multiImageView.setVisibility(View.VISIBLE);
            multiImageView.setList(photos);
            multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    BigImagePagerActivity.startImagePagerActivity((Activity) mContext, photos, position);
                }
            });
        } else {
        }

        CommentListView commentList = (CommentListView) viewHolder.getView(R.id.commentList);
        CommentAdapter commentAdapter = new CommentAdapter(mContext);
        commentList.setAdapter(commentAdapter);

        final List<CommentItem> commentsDatas = item.getReplys();
        commentAdapter.setDatas(commentsDatas);
        commentAdapter.notifyDataSetChanged();
        commentList.setOnItemClick(new CommentListView.OnItemClickListener() {
            @Override
            public void onItemClick(int commentPosition) {
                CommentItem commentItem = commentsDatas.get(commentPosition);
            }
        });
        commentList.setOnItemLongClick(new CommentListView.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int commentPosition) {
                CommentItem commentItem = commentsDatas.get(commentPosition);
            }
        });
        viewHolder.getView(R.id.digCommentBody).setVisibility(commentsDatas.size() > 0 ? View.VISIBLE : View.GONE);
    }
}
