package com.androidapp.fragment;

public abstract class LazyLoadFragment extends BaseFragment {

    boolean lazyLoaded = true;

    @Override
    public void onResume() {
        super.onResume();
        loadData(lazyLoaded);
        lazyLoaded = false;
    }
}

