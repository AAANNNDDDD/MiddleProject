package com.example.lbstest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MyLocation {

    public LocationClient mLocationClient;

    public void ask4BDPermission(int BDPermissionCode,Context context){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions((Activity) context, permissions, BDPermissionCode);
        }
    }

    private void onRecievePermission(int requestCode, String[] permissions, int[] grantResults, int BDPermissionCode, Context context, MyLocationListener myLocationListener){
        if (requestCode == BDPermissionCode) {
            if (grantResults.length > 0) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                requestLocation(context,myLocationListener);
            } else {
                Toast.makeText(context, "发生未知错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestLocation(Context context, MyLocationListener myLocationListener){
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(myLocationListener);
        SDKInitializer.initialize(context);
        LocationClientOption option = new LocationClientOption();
        //option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
//            StringBuilder currentPosition = new StringBuilder();
//            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
//            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
//            currentPosition.append("国家：").append(location.getCountry()).append("\n");
//            currentPosition.append("省：").append(location.getProvince()).append("\n");
//            currentPosition.append("市：").append(location.getCity()).append("\n");
//            currentPosition.append("区：").append(location.getDistrict()).append("\n");
//            currentPosition.append("街道：").append(location.getStreet()).append("\n");
//            currentPosition.append("定位方式：");
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                currentPosition.append("GPS");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                currentPosition.append("网络");
//            }
//            positionText.setText(currentPosition);
            mLocationClient.stop();
        }

    }

}
