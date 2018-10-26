package com.scwang.refreshlayout.adapter.provider;

import android.app.Activity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.utils.ImageLoaderUtils;
import com.androidapp.utils.TimeUtil;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.adapter.CommentAdapter;
import com.scwang.refreshlayout.entity.MultipleEntity;
import com.scwang.refreshlayout.adapter.MultipleItemQuickAdapter;
import com.scwang.refreshlayout.entity.VideoItem;
import com.scwang.refreshlayout.entity.circle.CircleItem;
import com.scwang.refreshlayout.entity.circle.CommentItem;
import com.scwang.refreshlayout.ui.common.BigImagePagerActivity;
import com.scwang.refreshlayout.widget.CommentListView;
import com.scwang.refreshlayout.widget.ExpandableTextView;
import com.scwang.refreshlayout.widget.MultiImageView;

import java.util.List;

public class CircleItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_CIRCLE;
    }

    @Override
    public int layout() {
        return R.layout.item_circle_list;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        CircleItem item = (CircleItem) multipleEntity;
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

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}