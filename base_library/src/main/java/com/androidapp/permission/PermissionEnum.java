package com.androidapp.permission;

import android.Manifest;

public enum PermissionEnum {

    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE	, "读取存储", "允许程序读取外部存储数据，如SD卡上写文件"),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE	, "写入外部存储", "允许程序写入外部存储，如SD卡上写文件"),
    READ_CONTACTS(Manifest.permission.READ_CONTACTS, "读取联系人", "允许应用访问联系人通讯录信息"),
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS	, "写入联系人", "写入联系人，但不可读取"),
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO	, "录音", "录制声音通过手机或耳机的麦克"),
    SYSTEM_ALERT_WINDOW(Manifest.permission.SYSTEM_ALERT_WINDOW	, "显示系统窗口", "显示系统窗口"),
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION	, "获取粗略位置", "通过WiFi或移动基站的方式获取用户错略的经纬度信息,定位精度大概误差在30~1500米"),
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION, "获取精确位置", "通过GPS芯片接收卫星的定位信息,定位精度达10米以内"),
    ACCESS_NETWORK_STATE(Manifest.permission.ACCESS_NETWORK_STATE, "获取网络状态", "获取网络信息状态,如当前的网络连接是否有效"),
    CALL_PHONE(Manifest.permission.CALL_PHONE, "拨打电话", "允许程序从非系统拨号器里输入电话号码"),
    CALL_PRIVILEGED(Manifest.permission.CALL_PRIVILEGED, "通话", "允许程序拨打电话,替换系统的拨号器界面"),
    CAMERA(Manifest.permission.CAMERA, "拍照", "允许访问摄像头进行拍照"),
    INTERNET(Manifest.permission.INTERNET, "访问网络", "访问网络连接,可能产生GPRS流量"),
    READ_SMS(Manifest.permission.READ_SMS, "读取短信内容", "读取短信内容");

    private String permission;
    private String permissionName;
    private String permissionDesc;

    PermissionEnum(String permission, String permissionName, String permissionDesc) {
        this.permission = permission;
        this.permissionName = permissionName;
        this.permissionDesc = permissionDesc;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
}
