package com.reactnativechangeicon;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.os.Bundle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class ChangeIconModule extends ReactContextBaseJavaModule implements Application.ActivityLifecycleCallbacks {
    private final ReactApplicationContext reactContext;
    private final String packageName;
    private List<String> classesToKill = new ArrayList<>();
    private Boolean iconChanged = false;
    private String componentClass = "";

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
    public void getIcon(Promise promise){
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.reject("ACTIVITY_NOT_FOUND");
            return;
        }
        if (this.componentClass.isEmpty()) {
            this.componentClass = activity.getComponentName().getClassName();
        }
        String currentIcon = this.componentClass.split("MainActivity")[1];
        promise.resolve(currentIcon.isEmpty() ? "default" : currentIcon);
        return;
    }

    @ReactMethod
    public void changeIcon(String enableIcon, Promise promise) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.reject("ACTIVITY_NOT_FOUND");
            return;
        }
        if (enableIcon.isEmpty()) {
            promise.reject("EMPTY_ICON_STRING");
            return;
        }
        if (this.componentClass.isEmpty()) {
            this.componentClass = activity.getComponentName().getClassName();
        }
        final String activeClass = this.packageName + ".MainActivity" + enableIcon;
        if (this.componentClass.equals(activeClass)) {
            promise.reject("ICON_ALREADY_USED");
            return;
        }
        try {
            activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, activeClass),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            );
            promise.resolve(enableIcon);
        } catch (Exception e) {
            promise.reject("ICON_INVALID");
            return;
        }
        this.classesToKill.add(this.componentClass);
        this.componentClass = activeClass;
        activity.getApplication().registerActivityLifecycleCallbacks(this);
        iconChanged = true;
    }

    private void completeIconChange() {
        if (!iconChanged) return;
        final Activity activity = getCurrentActivity();
        if (activity == null) return;
        classesToKill.forEach((cls) -> activity.getPackageManager().setComponentEnabledSetting(
            new ComponentName(this.packageName, cls),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        ));
        classesToKill.clear();
        iconChanged = false;
    }

    @Override
    private void onActivityPaused(Activity activity) {
        completeIconChange();
    }

    @Override
    private void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    private void onActivityStarted(Activity activity) {
    }

    @Override
    private void onActivityResumed(Activity activity) {
    }

    @Override
    private void onActivityStopped(Activity activity) {
    }

    @Override
    private void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    private void onActivityDestroyed(Activity activity) {
    }
}
