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

import com.androidapp.base.R;
import com.androidapp.base.utils.ScreenUtil;
import com.androidapp.filter.multiple.MultiPopupView;
import com.androidapp.filter.multiple.bean.MultiBean;
import com.androidapp.filter.single.SignalPopupView;
import com.androidapp.filter.single.bean.SignalBean;

import java.util.ArrayList;
import java.util.List;

public class FilterView extends LinearLayout {

    private View mBaseView;
    private Context mContext;
    private LinearLayout mHeaderLayout;
    private View mSplitView;

    private DropdownButton dropdownButton;
    private DropdownButton dropdownButton1;
    private LinearLayout linearLayout;

    public FilterView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.filter_base_layout, this, true);
        linearLayout = mBaseView.findViewById(R.id.filter_container);
        if (mSplitView == null) {
            mSplitView = new View(mContext);
            LayoutParams splitParams = new LayoutParams(ScreenUtil.dip2px(mContext, 0.5f), ViewGroup.LayoutParams.MATCH_PARENT);
            splitParams.setMargins(0, ScreenUtil.dip2px(mContext, 15), 0, ScreenUtil.dip2px(mContext, 15));
            mSplitView.setLayoutParams(splitParams);
            mSplitView.setBackgroundResource(R.color.gray_B4B4B4);
        }
        initParam();
    }

    public void addFilterData() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
        mHeaderLayout = mBaseView.findViewById(R.id.ll_filter_items);
        dropdownButton = new DropdownButton(getContext());
        dropdownButton.setText("测试11");
        dropdownButton.setFilterAction(action, 0);
        mHeaderLayout.addView(dropdownButton, layoutParams);
        mHeaderLayout.addView(mSplitView);

        dropdownButton1 = new DropdownButton(getContext());
        dropdownButton1.setText("测试22");
        dropdownButton1.setFilterAction(action, 1);
        mHeaderLayout.addView(dropdownButton1, layoutParams);
    }

    private List<MultiBean> dictList = new ArrayList<>();

    //这些是假数据，真实项目中直接接口获取添加进来，FiltrateBean对象可根据自己需求更改
    private void initParam() {
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
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        MultiBean fb2 = new MultiBean();
        fb2.setTypeName("颜色");
        List<MultiBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            MultiBean.Children cd = new MultiBean.Children();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        MultiBean fb3 = new MultiBean();
        fb3.setTypeName("企业");
        List<MultiBean.Children> childrenList3 = new ArrayList<>();
        for (int x = 0; x < company.length; x++) {
            MultiBean.Children cd = new MultiBean.Children();
            cd.setValue(company[x]);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);

        dictList.add(fb1);
        dictList.add(fb2);
        dictList.add(fb3);
    }

    List<SignalBean> chooseTypeData;
    FilterAction action = new FilterAction() {
        @Override
        public void onShowFilter(int index) {
            if (index == 0) {
                MultiPopupView multiPopupView = new MultiPopupView(mContext);
                multiPopupView.setViewData(dictList);
                multiPopupView.setFilterAction(action, onItemClick);
                linearLayout.removeAllViews();
                linearLayout.addView(multiPopupView);
            } else {
                if (chooseTypeData == null) {
                    chooseTypeData = new ArrayList<>();//选择类型
                    chooseTypeData.add(new SignalBean("0", "全部分类"));
                    chooseTypeData.add(new SignalBean("1", "分类1"));
                    chooseTypeData.add(new SignalBean("2", "分类2"));
                    chooseTypeData.add(new SignalBean("3", "分类3"));
                    chooseTypeData.add(new SignalBean("4", "分类4"));
                    chooseTypeData.get(0).setSelected(true);
                }
                SignalPopupView dropdownType = new SignalPopupView(mContext);
                dropdownType.bind(chooseTypeData, dropdownButton1, action, onItemClick);
                linearLayout.removeAllViews();
                linearLayout.addView(dropdownType);
            }
        }

        @Override
        public void onHideFilter(int index) {
            linearLayout.removeAllViews();
            dropdownButton.setChecked(false);
            dropdownButton1.setChecked(false);
        }
    };

    private OnItemClick onItemClick = new OnItemClick() {
        @Override
        public void onSignalItemClick(SignalBean signalBean) {
            Log.e("", "#######################" + signalBean.getText());
        }

        @Override
        public void onMultiItemClick(List<MultiBean> multiBean) {
            Log.e("", "#######################" + multiBean.size());
        }
    };

    public interface FilterAction {
        void onShowFilter(int index);

        void onHideFilter(int index);
    }

    public interface OnItemClick {
        void onSignalItemClick(SignalBean signalBean);

        void onMultiItemClick(List<MultiBean> multiBean);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        dropdownButton.dispatchTouchEvent(ev);
        dropdownButton1.dispatchTouchEvent(ev);
//        return super.onInterceptTouchEvent(ev);
        return true;
    }
}
