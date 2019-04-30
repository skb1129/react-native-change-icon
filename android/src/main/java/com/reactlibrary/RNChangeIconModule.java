
package com.reactlibrary;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.ComponentName;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNChangeIconModule extends ReactContextBaseJavaModule {

    private final String packageName;
    private String componentClass = null;

    RNChangeIconModule(ReactApplicationContext reactContext, String packageName) {
        super(reactContext);
        this.packageName = packageName;
    }

    @Override
    public String getName() {
        return "RNChangeIcon";
    }

    @ReactMethod
    public void changeIcon(String enableIcon) {
        final Activity activity = getCurrentActivity();
        if (activity == null) return;
        if (this.componentClass == null) this.componentClass = activity.getComponentName().getClassName();
        String activeClass = this.packageName + ".MainActivity" + enableIcon;
        activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, activeClass),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
        );
        activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, this.componentClass),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
        this.componentClass = activeClass;
    }
}
