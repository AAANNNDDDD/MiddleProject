# 获取位置

### 用法

`MyLocation`类只包含了四个方法

```java
public void ask4BDPermission(int BDPermissionCode,Context context);
public void onRecievePermission(int requestCode, String[] permissions, int[] grantResults, int BDPermissionCode, Context context, BDLocationListener myLocationListener,Application application);
public void requestLocation(Context context, BDLocationListener myLocationListener, Application application);
public void stop();
```

* `requestLocation()` 获取当前位置。要在Activity中重写下面函数

  ```java
  public class MyLocationListener implements BDLocationListener {
          @Override
          public void onReceiveLocation(BDLocation location) {
            //location中包含位置信息，在此获取
              StringBuilder currentPosition = new StringBuilder();
              currentPosition.append("省：").append(location.getProvince()).append("\n");
              currentPosition.append("市：").append(location.getCity()).append("\n");
              currentPosition.append("区：").append(location.getDistrict()).append("\n");
              currentPosition.append("街道：").append(location.getStreet()).append("\n");
              Toast.makeText(MainActivity.this, currentPosition, Toast.LENGTH_SHORT).show();
              myLocation.stop();
          }
      }
  ```

* `ask4BDPermission()` 在调用`requestLocation()`运行，用于获取权限

* `onRecievePermission()`在重载`onRequestPermissionsResult()`中运行，用于检测授权情况。

  ```java
  @Override
      public void onRequestPermissionsResult(int requestCode, String [] permissions, int[] grantResults ){
          myLocation.onRecievePermission(requestCode,permissions,grantResults,1,MainActivity.this,new MyLocationListener(),getApplication());
      }
  ```

* `stop()`结束定位。不用管，已经可以自动结束。