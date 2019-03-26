
package com.reactlibrary;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.ComponentName;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNChangeIconModule extends ReactContextBaseJavaModule {

    private final String packageName;

    RNChangeIconModule(ReactApplicationContext reactContext, String packageName) {
        super(reactContext);
        this.packageName = packageName;
    }

    @Override
    public String getName() {
        return "RNChangeIcon";
    }

    @ReactMethod
    public void changeIcon(String enableIcon, String disableIcon) {
        final Activity activity = getCurrentActivity();
        if (activity == null) return;
        String activeName = this.packageName + ".MainActivity" + enableIcon;
        String inactiveName = this.packageName + ".MainActivity" + disableIcon;
        activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, activeName),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
        );
        activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, inactiveName),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
    }
}
