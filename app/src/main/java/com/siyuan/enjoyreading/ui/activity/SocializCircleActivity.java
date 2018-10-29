package com.siyuan.enjoyreading.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.mvp.MvpBaseListActivity;
import com.androidapp.mvp.MvpBaseView;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.circle.CircleItem;
import com.siyuan.enjoyreading.model.user.ZoneModelLogic;
import com.siyuan.enjoyreading.presenter.user.ZonePresenterImpl;
import com.siyuan.enjoyreading.presenter.user.interfaces.IZonePresenter;
import com.siyuan.enjoyreading.widget.ZoneHeaderView;

import java.util.ArrayList;
import java.util.List;

public class SocializCircleActivity extends MvpBaseListActivity<ZoneModelLogic, ZonePresenterImpl> implements IZonePresenter.View {

    ZoneHeaderView zoneHeaderView;

    @Override
    protected OnRefreshListener getOnRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                        }
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        };
    }

    @Override
    protected OnLoadMoreListener getOnLoadMoreListener() {
        return new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        };
    }

    @Override
    protected BaseQuickAdapter getListViewAdapter() {
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
            mAdapter.openLoadAnimation();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    doStartActivity(SocializCircleActivity.class, null);
                }
            });
        }
        return mAdapter;
    }

    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        //添加Header
        SpacesItemDecoration dividerItemDecoration = new SpacesItemDecoration();
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        zoneHeaderView = new ZoneHeaderView(this);
        zoneHeaderView.setData("测试用户名", "http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg");
        mAdapter.addHeaderView(zoneHeaderView, 0);
        mPresenter.getCircleListItems(1);
        updateNotReadNewsCount(10, null);
    }

    @Override
    protected void onEvent() {
    }

    @Override
    protected MvpBaseView getView() {
        return this;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBar.setTitle("我的动态");
    }

    @Override
    public void onCircleListUpdate(List<CircleItem> circleItemList) {
        mAdapter.replaceData(circleItemList);
    }

    /**
     * 未读消息总数
     *
     * @param count
     */
    public void updateNotReadNewsCount(int count, String icon) {
        zoneHeaderView.setNotReadMsgData(count, icon);
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个上边和底部的间距  这些间隔大小可以自行修改
            int pos = parent.getChildLayoutPosition(view);  //当前条目的position
            int itemCount = state.getItemCount();           //整体条目长度
            if (pos % 2 == 1) {  //下面一行
                if(pos==itemCount-1){
                    outRect.bottom = 0;
                    outRect.top = 30;
                }else {
                    outRect.bottom = 30;
                    outRect.top = 30;
                }
            }
            else { //上面一行
                if(pos==0){
                    outRect.bottom = 30;
                    outRect.top = 0;
                }else {
                    if(pos==itemCount-1){
                        outRect.bottom = 0;
                        outRect.top = 30;
                    }else {
                        outRect.top = 30;
                        outRect.bottom = 30;
                    }
                }
            }
        }
    }
}
