package com.siyuan.enjoyreading.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.permission.PermissionEnum;
import com.androidapp.permission.PermissionUtils;
import com.siyuan.enjoyreading.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.test_permission);
        mContext = this;
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

    private void toCamera() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(intent);
    }

    @Override
    protected PermissionUtils.PermissionRequestSuccessCallBack getPermissionCallBack() {
        return new PermissionUtils.PermissionRequestSuccessCallBack() {
            @Override
            public void onHasPermission() {
                toCamera();
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
