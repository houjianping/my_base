package com.androidapp.map.util;

import android.annotation.SuppressLint;

import com.androidapp.map.LocationUpdateListener;
import com.androidapp.map.MapApplication;
import com.androidapp.map.bean.MapLocation;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.concurrent.CopyOnWriteArrayList;

public class LocationFinder {

    private static final Object sInstanceObject = new Object();

    private static CopyOnWriteArrayList<LocationUpdateListener> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static LocationFinder sLocationFinder;
    @SuppressLint("StaticFieldLeak")
    private static LocationClient mLocClient;
    private static MapLocation sLastTimeLocation;

    public static LocationFinder getInstance() {
        if (sLocationFinder == null) {
            synchronized (sInstanceObject) {
                sLocationFinder = new LocationFinder();
                mLocClient = new LocationClient(MapApplication.getApplication());
                LocationClientOption option = new LocationClientOption();
                option.setOpenGps(false);
                option.setIsNeedAddress(true);
                option.setIsNeedLocationDescribe(true);
                option.setCoorType("gcj02");// 返回的定位结果是百度经纬度,默认值gcj02
                option.setScanSpan(15 * 1000);// 设置发起定位请求的间隔时间
                mLocClient.setLocOption(option);
                mLocClient.registerLocationListener(new MapLocationListener());
            }
        }
        return sLocationFinder;
    }

    public void startLocation() {
        mLocClient.start();
    }

    public void stopLocation() {
        mLocClient.stop();
    }

    public void setCallBack(LocationUpdateListener listener) {
        copyOnWriteArrayList.add(listener);
    }

    public void removeCallBack(LocationUpdateListener listener) {
        copyOnWriteArrayList.remove(listener);
    }

    public MapLocation getLocation() {
        return sLastTimeLocation;
    }

    private static void firOnLocationUpdate(MapLocation location) {
        for (LocationUpdateListener listener : copyOnWriteArrayList) {
            listener.onLocationUpdate(location);
        }
    }

    static class MapLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;
            sLastTimeLocation = new MapLocation();
            sLastTimeLocation.setLatitude(location.getLatitude());
            sLastTimeLocation.setLongitude(location.getLongitude());
            MapLocation.Address address = new MapLocation.Address();
            address.setCity(location.getCity());
            address.setProvince(location.getProvince());
            address.setCountry(location.getCountry());
            address.setCity(location.getCity());
            address.setAddress(location.getAddrStr());
            sLastTimeLocation.setAddress(address);
            firOnLocationUpdate(sLastTimeLocation);
            mLocClient.stop();
        }
    }
}
