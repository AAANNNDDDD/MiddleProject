package com.example.lbstest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public MyLocation myLocation = new MyLocation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getLocation = (Button) findViewById(R.id.button);

        getLocation.setOnClickListener(MainActivity.this);
        myLocation.ask4BDPermission(1,MainActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String [] permissions, int[] grantResults ){
        myLocation.onRecievePermission(requestCode,permissions,grantResults,1,MainActivity.this,new MyLocationListener(),getApplication());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                myLocation.requestLocation(MainActivity.this, new MyLocationListener(),getApplication());
                break;
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder currentPosition = new StringBuilder();
//            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
//            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
//            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition.append("区：").append(location.getDistrict()).append("\n");
            currentPosition.append("街道：").append(location.getStreet()).append("\n");
//            currentPosition.append("定位方式：");
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                currentPosition.append("GPS");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                currentPosition.append("网络");
//            }
            Toast.makeText(MainActivity.this, currentPosition, Toast.LENGTH_SHORT).show();
            myLocation.stop();
        }
    }
}




