package com.siyuan.enjoyreading.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.activity.BaseActivity;
import com.androidapp.utils.ToastUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.siyuan.enjoyreading.R;

import java.util.ArrayList;
import java.util.List;

public class MapUserActivity extends BaseActivity {
    private static final String LOG_CAT = "net_work";
    private MapView mapView = null;

    private BaiduMap mBaiduMap;
    private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    public LocationClient mLocationClient = null;

    public OnMapStatusChangeListener mOnMapStatusChangeListener = new OnMapStatusChangeListener();
    public OnMarkerClickListener mOnMarkerClickListener = new OnMarkerClickListener();
    public BDLocationListener myListener = new MyLocationListener();
    public OnMapClickListener mOnMapClickListener = new OnMapClickListener();
    private boolean isFirstLoc = true;
    private Boolean isLocation = false;
    private boolean isFirstLocate = true;
    private List<String> urlList = new ArrayList<String>();

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.map_activity);
    }

    @Override
    protected void initView() {
        mapView = findViewById(R.id.mapView);
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_26.jpg");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_2.jpg");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_137.jpg");
        urlList.add("https://upload-10014880.file.myqcloud.com/upload/20170412/1944034054.png");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_113.jpg");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_68.jpg");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_176.jpg");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_55.jpg");
        urlList.add("https://cvideo.kuosanyun.com/20170906082703444382");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_30.jpg");
        urlList.add("https://upload-10014880.file.myqcloud.com/static/images/portrait/rand_user_23.jpg");
        urlList.add("https://h5.m.kuosanyun.com/static/images/portrait/rand_user_84.jpg");
        Log.d(LOG_CAT, "urlList:" + urlList.size());

        mBaiduMap = mapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);// 开启定位图层
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, BitmapDescriptorFactory.fromResource(R.drawable.icon_map_current)));
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMaxAndMinZoomLevel(19, 3);
        mapView.showZoomControls(false);//隐藏地图上比例尺
        mapView.showScaleControl(false);//隐藏缩放控件
        mBaiduMap.getUiSettings().setCompassEnabled(false); //隐藏指南针
        mBaiduMap.getUiSettings().setRotateGesturesEnabled(false);//禁止旋转
        mBaiduMap.getUiSettings().setOverlookingGesturesEnabled(false);//禁止3D俯视效果
        mSearch = GeoCoder.newInstance();
        mBaiduMap.setOnMapClickListener(mOnMapClickListener);
        mBaiduMap.setOnMarkerClickListener(mOnMarkerClickListener);
        mBaiduMap.setOnMapStatusChangeListener(mOnMapStatusChangeListener);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();//开始定位
    }

    @Override
    protected void initData() {
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
            MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
            locationBuilder.latitude(location.getLatitude());
            locationBuilder.longitude(location.getLongitude());
            MyLocationData locationData = locationBuilder.build();
            mBaiduMap.setMyLocationData(locationData);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MapView.setMapCustomEnable(false);
        mLocationClient.stop();
        mapView.onDestroy();
        mSearch.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        int span = 1000;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    //定位监听回调事件
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
            if (mapView == null) {
                return;
            }
            if (isFirstLoc) {
                isFirstLoc = false;
                double latitude = location.getLatitude();//纬度
                double longitude = location.getLongitude();//经度
                String vir_point = latitude + "," + longitude;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                //判断如果定位点为百度地图的默认值,就去弹窗要求用户去开启GPS
                String lag_lng = latitude + "," + longitude;
                if (lag_lng.equals("4.9E-324,4.9E-324")) {
                    NetUtil.initGPS(MapUserActivity.this);
                }
                if (!isLocation) {
                    isLocation = true;
                    // 反Geo搜索
                    mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ll));
                    loadMapPoint(vir_point);
                }
            }
        }
    }

    private void loadMapPoint(String vir_point) {
        for (int i = 0; i < urlList.size(); i++) {
            String lagLng = NetUtil.getLagLng(vir_point);
            addMarker(lagLng, urlList.get(i));
        }
    }

    public class OnMapClickListener implements BaiduMap.OnMapClickListener {
        @Override
        public void onMapClick(LatLng latLng) {
            mBaiduMap.hideInfoWindow();
        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
        }
    }

    public class OnMarkerClickListener implements BaiduMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(Marker marker) {
            Bundle bundle = marker.getExtraInfo();
            if (bundle != null) {
                ToastUtils.show("" + marker.getExtraInfo().getString("link"));
            }
            return true;
        }
    }

    public class OnMapStatusChangeListener implements BaiduMap.OnMapStatusChangeListener {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {
        }

        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {
        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            float zoom = mapStatus.zoom;
            LatLng latLng_whole = mapStatus.target;
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng_whole));
        }
    }

    //动态添加marker
    public void addMarker(String actual_point, final String link) {
        if (!TextUtils.isEmpty(actual_point)) {
            String[] str = actual_point.split(",");
            Double latitude = Double.parseDouble(str[0]);
            Double longitude = Double.parseDouble(str[1]);

            final LatLng latLng = new LatLng(latitude, longitude);
            final Bundle bundle = new Bundle();
            bundle.putString("link", link);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    drawMapSpot(latLng, bundle, link);
                }
            }.start();
        }
    }

    private void drawMapSpot(final LatLng latLng, final Bundle bundle, final String link) {
        View view;
        ImageView img_head;
        Bitmap loadBitmap;
        view = View.inflate(this, R.layout.map_layout_item, null);
        img_head = view.findViewById(R.id.iv_p_v);
        if (TextUtils.isEmpty(link)) {
            loadBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.iv_default_photo);
        } else {
            loadBitmap = showImage.loadBitmap(link, true, false, R.drawable.iv_default_photo);
        }
        img_head.setImageBitmap(loadBitmap);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromView(view);
        MarkerOptions options = new MarkerOptions().position(latLng).icon(descriptor).extraInfo(bundle);
        Marker marker = (Marker) mBaiduMap.addOverlay(options);
        marker.setTitle("测试");
        if (loadBitmap != null && !loadBitmap.isRecycled()) {
            loadBitmap.isRecycled();
            loadBitmap = null;
        }
    }
}
