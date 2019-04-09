package com.siyuan.enjoyreading.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.map.LocationUpdateListener;
import com.androidapp.map.bean.MapLocation;
import com.androidapp.map.util.LocationFinder;
import com.androidapp.permission.PermissionEnum;
import com.androidapp.permission.PermissionUtils;
import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.map.MapUserActivity;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.test_permission);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_request_permissions1:
                doRequestPermission();
                break;
        }
    }

    private void startMapActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MapUserActivity.class);
        startActivity(intent);
    }

    @Override
    protected PermissionUtils.PermissionRequestSuccessCallBack getPermissionCallBack() {
        return new PermissionUtils.PermissionRequestSuccessCallBack() {
            @Override
            public void onHasPermission() {
                LocationFinder.getInstance().setCallBack(new LocationUpdateListener() {
                    @Override
                    public void onLocationUpdate(MapLocation location) {
                        ToastUtils.show("$$$$$$$" + location.toString());
                    }
                });
                LocationFinder.getInstance().startLocation();
                startMapActivity();
            }
        };
    }

    @Override
    protected List<PermissionEnum> getPermissionEnums() {
        List<PermissionEnum> PERMISSIONS = new ArrayList<PermissionEnum>() {};
        PERMISSIONS.add(PermissionEnum.CAMERA);
        PERMISSIONS.add(PermissionEnum.ACCESS_COARSE_LOCATION);
        PERMISSIONS.add(PermissionEnum.ACCESS_FINE_LOCATION);
        return PERMISSIONS;
    }
}
