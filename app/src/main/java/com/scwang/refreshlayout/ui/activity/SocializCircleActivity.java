package com.scwang.refreshlayout.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.utils.ToastUtils;
import com.androidapp.mvp.MvpBaseListActivity;
import com.androidapp.mvp.MvpBaseView;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.adapter.CircleQuickAdapter;
import com.scwang.refreshlayout.bean.BannerItem;
import com.scwang.refreshlayout.bean.circle.CircleItem;
import com.scwang.refreshlayout.model.user.ZoneModelLogic;
import com.scwang.refreshlayout.presenter.user.ZonePresenterImpl;
import com.scwang.refreshlayout.presenter.user.interfaces.IZonePresenter;
import com.scwang.refreshlayout.widget.ZoneHeaderView;

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
                            /*List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
                            }.getType());
                            mAdapter.replaceData(movies);*/
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
                /*final List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
                }.getType());
                mAdapter.addData(movies);*/
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        };
    }

    @Override
    protected BaseQuickAdapter getListViewAdapter() {
        if (mAdapter == null) {
            mAdapter = new CircleQuickAdapter();
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

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};

    private CircleQuickAdapter mAdapter;

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
        ToastUtils.showShortToast(this, "--1-----");
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
