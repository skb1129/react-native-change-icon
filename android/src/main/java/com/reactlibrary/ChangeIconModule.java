package com.reactlibrary;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.ComponentName;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class ChangeIconModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private final String packageName;
    private String componentClass = null;

    public ChangeIconModule(ReactApplicationContext reactContext, String packageName) {
        super(reactContext);
        this.reactContext = reactContext;
        this.packageName = packageName;
    }

    @Override
    public String getName() {
        return "ChangeIcon";
    }

    @ReactMethod
    public void changeIcon(String enableIcon, Promise promise) {
        final Activity activity = getCurrentActivity();
        if (activity == null || enableIcon == null || enableIcon.isEmpty()) {
            promise.reject("Icon string is empty.");
            return;
        }
        if (this.componentClass == null) this.componentClass = activity.getComponentName().getClassName();
        String activeClass = this.packageName + ".MainActivity" + enableIcon;
        if (this.componentClass.equals(activeClass)) {
            promise.reject("Icon already in use.");
            return;
        }
        promise.resolve(true);
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
