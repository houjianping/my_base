package com.androidapp.filter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.androidapp.base.R;
import com.androidapp.base.utils.ScreenUtil;
import com.androidapp.base.utils.ToastUtils;
import com.androidapp.filter.multiple.MultiPopupView;
import com.androidapp.filter.multiple.bean.MultiBean;
import com.androidapp.filter.single.SignalPopupView;
import com.androidapp.filter.single.bean.SignalBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterView extends LinearLayout {

    private View mBaseView;
    private Context mContext;
    private LinearLayout mHeaderLayout;
    private LinearLayout linearLayout;
    private OnItemClick onItemClick;

    private Map<String, DropdownButton> dropdownButtons = new HashMap();
    private LayoutParams splitParams = null;

    private List<FilterHeaderItem> items;

    public FilterView(Context context) {
        super(context);
        mContext = context;
        splitParams = new LayoutParams(ScreenUtil.dip2px(mContext, 0.5f), ViewGroup.LayoutParams.MATCH_PARENT);
        splitParams.setMargins(0, ScreenUtil.dip2px(mContext, 15), 0, ScreenUtil.dip2px(mContext, 15));
        initView();
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        splitParams = new LayoutParams(ScreenUtil.dip2px(mContext, 0.5f), ViewGroup.LayoutParams.MATCH_PARENT);
        splitParams.setMargins(0, ScreenUtil.dip2px(mContext, 15), 0, ScreenUtil.dip2px(mContext, 15));
        initView();
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.filter_base_layout, this, true);
        linearLayout = mBaseView.findViewById(R.id.filter_container);
    }

    public void setFilterItems(List<FilterHeaderItem> data) {
        items = data;
        if (items == null) {
            items = getFilterItems();
        }
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
        mHeaderLayout = mBaseView.findViewById(R.id.ll_filter_items);
        dropdownButtons.clear();
        for (int i = 0; i < items.size(); i++) {
            FilterHeaderItem item = items.get(i);
            DropdownButton button = new DropdownButton(getContext());
            button.setText(item.getTitle());
            button.setFilterAction(action, item);
            button.setChecked(false, false);
            mHeaderLayout.addView(button, layoutParams);
            if (i != items.size() - 1) {
                View mSplitView = new View(mContext);
                mSplitView.setLayoutParams(splitParams);
                mSplitView.setBackgroundResource(R.color.gray_B4B4B4);
                mHeaderLayout.addView(mSplitView, splitParams);
            }

            dropdownButtons.put(item.getKey(), button);
        }
    }

    FilterAction action = new FilterAction() {
        @Override
        public void onShowFilter(FilterHeaderItem item) {
            onItemClick.onItemClick();
            if (item.isSelectable()) {
                if (item.getmDictList() != null) {
                    MultiPopupView multiPopupView = new MultiPopupView(mContext);
                    multiPopupView.setViewData(item);
                    multiPopupView.setFilterAction(action, onItemClick);
                    linearLayout.removeAllViews();
                    linearLayout.addView(multiPopupView);

                } else if (item.getmSignalBean() != null) {
                    SignalPopupView dropdownType = new SignalPopupView(mContext);
                    dropdownType.bind(item, dropdownButtons.get(item.getKey()), action, onItemClick);
                    linearLayout.removeAllViews();
                    linearLayout.addView(dropdownType);
                }
            } else {
                linearLayout.removeAllViews();
                onItemClick.doFilter(item);
            }
        }

        @Override
        public void onHideFilter(FilterHeaderItem item) {
            linearLayout.removeAllViews();
            for (Map.Entry<String, DropdownButton> entry : dropdownButtons.entrySet()) {
                if (!item.getKey().equals(entry.getKey())) {
                    entry.getValue().setChecked(false, false);
                } else {
                    entry.getValue().setChecked(true, false);
                }
            }
        }
    };

    public void setOnFilterItemClick(OnItemClick onFilterItemClick) {
        onItemClick = onFilterItemClick;
    }

    public interface FilterAction {
        void onShowFilter(FilterHeaderItem item);
        void onHideFilter(FilterHeaderItem item);
    }

    public interface OnItemClick {
        void doFilter(FilterHeaderItem item);
        void onItemClick();
    }

    private FilterHeaderItem getMultiItem() {
        List<MultiBean> dictList = new ArrayList<>();
        dictList.clear();
        String[] sexs = {"男", "女"};
        String[] colors = {"红色", "浅黄色", "橙子色", "鲜绿色", "青色", "天蓝色", "紫色", "黑曜石色", "白色", "五颜六色"};
        String[] company = {"阿里巴巴集团", "腾讯集团", "华为技术服务有限公司", "小米", "www.xiaomi.com"};

        MultiBean fb1 = new MultiBean();
        fb1.setTypeName("性别");
        List<MultiBean.Children> childrenList = new ArrayList<>();
        for (int x = 0; x < sexs.length; x++) {
            MultiBean.Children cd = new MultiBean.Children();
            cd.setValue(sexs[x]);
            if (x == 0) {
                cd.setSelected(true);
            }
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        MultiBean fb2 = new MultiBean();
        fb2.setTypeName("颜色");
        List<MultiBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            MultiBean.Children cd = new MultiBean.Children();
            cd.setValue(colors[x]);
            if (x == 0) {
                cd.setSelected(true);
            }
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        MultiBean fb3 = new MultiBean();
        fb3.setTypeName("企业");
        List<MultiBean.Children> childrenList3 = new ArrayList<>();
        for (int x = 0; x < company.length; x++) {
            MultiBean.Children cd = new MultiBean.Children();
            cd.setValue(company[x]);
            if (x == 0) {
                cd.setSelected(true);
            }
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);
        dictList.add(fb1);
        dictList.add(fb2);
        dictList.add(fb3);
        FilterHeaderItem headerItem = new FilterHeaderItem();
        headerItem.setKey("11");
        headerItem.setTitle("测试1");
        headerItem.setmDictList(dictList);
        return headerItem;
    }

    private FilterHeaderItem getSignalItem() {
        List<SignalBean> chooseTypeData = new ArrayList<>();
        chooseTypeData = new ArrayList<>();//选择类型
        chooseTypeData.add(new SignalBean("0", "全部分类"));
        chooseTypeData.add(new SignalBean("1", "分类1"));
        chooseTypeData.add(new SignalBean("2", "分类2"));
        chooseTypeData.add(new SignalBean("3", "分类3"));
        chooseTypeData.add(new SignalBean("4", "分类4"));
        chooseTypeData.get(0).setSelected(true);
        FilterHeaderItem headerItem = new FilterHeaderItem();
        headerItem.setKey("222");
        headerItem.setTitle("测试12");
        headerItem.setmSignalBean(chooseTypeData);
        return headerItem;
    }

    private FilterHeaderItem getEmptyItem() {
        FilterHeaderItem headerItem = new FilterHeaderItem();
        headerItem.setKey("333");
        headerItem.setTitle("测试123");
        return headerItem;
    }

    private List<FilterHeaderItem> getFilterItems() {
        List<FilterHeaderItem> items = new ArrayList<>();
        items.add(getMultiItem());
        items.add(getSignalItem());
        items.add(getEmptyItem());
        return items;
    }
}
