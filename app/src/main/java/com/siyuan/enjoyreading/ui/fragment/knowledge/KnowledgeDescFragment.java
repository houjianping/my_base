package com.siyuan.enjoyreading.ui.fragment.knowledge;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.utils.ImageLoaderUtils;
import com.siyuan.enjoyreading.R;

public class KnowledgeDescFragment extends LazyLoadFragment {

    private ImageView mIconView;
    private TextView mTitleTextView;
    private TextView mTypeTextView;
    private TextView mOwnerTextView;
    private TextView mPlayBackTextView;

    private TextView mContentSummary;
    private TextView mAuthorIntroduction;

    @Override
    protected void loadData(boolean force) {
        ImageLoaderUtils.display(getContext(), mIconView, "http://bookpic.lrts.me/phzv9l8usfl4lhcc7vs2qmlt1ax7yxps.png");
        mTitleTextView.setText("系统小农女");
        mTypeTextView.setText("穿越架空");
        mOwnerTextView.setText("作者: 雪儿蜜朵");
        mPlayBackTextView.setText("播放量: 4251860");
        mContentSummary.setText(Html.fromHtml("<p>前世她是娇滴滴的大小姐，不曾想一朝穿越竟然成了农家小媳妇，孤苦可怜不说，身边还带着两个小可怜，嗷嗷待哺喊着娘。</p><p>为了她和孩子能吃饱穿暖，必须得撸起袖子开始干，只是没想到努力之后，得上天厚爱，竟然赐了一个随身百宝箱，百宝箱里出百宝，这金手指开的简直不要太大。但是，有限制，必须做任务才能开启，每做够十个任务可以开启一个宝物。</p><p>只是随身系统里的任务太变态了：</p><p> 1、要完成山林面积覆盖率高达百分之八十以上。李蕴严重怀疑，是不是二十一世界毁掉的树林太多了，老天送她来古代种树育林的。</p><p>2、还有假种田，连床上做夫妻恩爱之事都算任务，这样的话她是要和山里汉子“夜夜笙箫”。</p>"));
        mAuthorIntroduction.setText(Html.fromHtml("<p>大鱼，趣阅小说网签约作者，掌阅畅销大神，著有《系统小农女》，全网点击破千万。</p>"));
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIconView = view.findViewById(R.id.icon);
        mTitleTextView = view.findViewById(R.id.title);
        mTypeTextView = view.findViewById(R.id.type);
        mOwnerTextView = view.findViewById(R.id.owner);
        mPlayBackTextView = view.findViewById(R.id.playback);
        mContentSummary = view.findViewById(R.id.content_summary);
        mAuthorIntroduction = view.findViewById(R.id.author_introduction);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_knowledge_desc;
    }
}
