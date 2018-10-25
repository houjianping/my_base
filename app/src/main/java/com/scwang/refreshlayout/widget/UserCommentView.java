package com.scwang.refreshlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.utils.ImageLoaderUtils;
import com.scwang.refreshlayout.R;

public class UserCommentView extends LinearLayout {

    private View mBaseView;
    private ImageView mUserImageView;
    private TextView mUserNameTextView;
    private TextView mCommentDayTextView;
    private TextView mContentTextView;

    public UserCommentView(Context context) {
        super(context);
        initView();
    }

    public UserCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, this);
        mUserImageView = mBaseView.findViewById(R.id.comment_thumb);
        mUserNameTextView = mBaseView.findViewById(R.id.comment_user);
        mCommentDayTextView = mBaseView.findViewById(R.id.comment_day);
        mContentTextView = mBaseView.findViewById(R.id.comment_content);
        setComment();
    }

    public void setComment() {
        ImageLoaderUtils.displayRound(getContext(), mUserImageView, "https://headpic.lrts.me/2329215720170727093516uc.jpg?imageMogr/v2/auto-orient/thumbnail/180x180&e=1540879835&token=OOHK9_MIwdSJxAHYi5os2taDVS13CVvcEa1cZDb9:lpf75s5jRAXGk87ekt1-ZFM8Vt0=");
        mUserNameTextView.setText("张亦苓");
        mCommentDayTextView.setText("2017-07-27 09:45:40");
        mContentTextView.setText("这个课程内容真的很实用，原来我教育孩子的方式都错了");
    }
}
