
package com.reactlibrary;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.List;

public class RNChangeIconModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private final String packageName;
  private final List<String> iconNames;

  public RNChangeIconModule(ReactApplicationContext reactContext, String packageName, List<String> iconNames) {
    super(reactContext);
    this.reactContext = reactContext;
    this.packageName = packageName;
    this.iconNames = iconNames;
  }

  @Override
  public String getName() {
    return "RNChangeIcon";
  }

  @ReactMethod
  public void changeIcon(String iconName) {
    final Activity activity = getCurrentActivity();
    String activeName = this.packageName + ".MainActivity" + iconName;
    if (activity == null) return;
    activity.getPackageManager().setComponentEnabledSetting(
      new ComponentName(this.packageName, activeName),
      PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
      PackageManager.DONT_KILL_APP
    );
    for (String icon:iconNames) {
      if (!icon.equals(iconName)) {
        try {
          String active = this.packageName + ".MainActivity" + icon;
          activity.getPackageManager().setComponentEnabledSetting(
            new ComponentName(this.packageName, active),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
          );
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}