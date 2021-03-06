package com.siyuan.enjoyreading.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siyuan.enjoyreading.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25 0025.
 */
public class ListFragment extends ViewPagerBaseFragment {
    Context mContext;
    private ArrayList<String> mDatas;
    private View view;
    private RecyclerView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    protected void loadData(boolean force) {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mListView = view.findViewById(R.id.list_view);
        initData();
        mListView.setLayoutManager(new LinearLayoutManager(mContext));
        mListView.setAdapter(new ListAdapter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_layout;
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
            return holder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }
        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public MyViewHolder(View view) {
                super(view);
                tv = view.findViewById(R.id.id_num);
            }
        }
    }
}

