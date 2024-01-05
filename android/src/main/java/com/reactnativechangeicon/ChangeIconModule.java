package com.reactnativechangeicon;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.os.Bundle;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.HashSet;
import java.util.Set;

@ReactModule(name = "ChangeIcon")
public class ChangeIconModule extends ReactContextBaseJavaModule implements Application.ActivityLifecycleCallbacks {
    public static final String NAME = "ChangeIcon";
    private final String packageName;
    private final Set<String> classesToKill = new HashSet<>();
    private Boolean iconChanged = false;

    private String _currentLaunchClassName = "";

    public ChangeIconModule(ReactApplicationContext reactContext, String packageName) {
        super(reactContext);
        this.packageName = packageName;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    public String getLaunchClassName() {
        if (_currentLaunchClassName.isEmpty()) {
            final Activity activity = getCurrentActivity();
            final PackageManager packageManager = activity.getPackageManager();
            String launchClassName = "";
            try {
                Intent intent = packageManager.getLaunchIntentForPackage(packageName);
                _currentLaunchClassName = intent.resolveActivity(packageManager).getClassName();
                if (_currentLaunchClassName.endsWith("Activity")) {
                    _currentLaunchClassName = launchClassName + "Default";
                }
            } catch(Exception e) {
                //
            }
        }
        return _currentLaunchClassName;
    }

    public String getNewLaunchClassName(String iconName) {
        String currentLaunchClassName = getLaunchClassName();
        if (currentLaunchClassName.isEmpty()) {
            return "";
        }

        String[] activityNameSplit = currentLaunchClassName.split("Activity");

        return activityNameSplit[0] + "Activity" + iconName;
    }

    @ReactMethod
    public void getIcon(Promise promise) {
        String launchClassName = getLaunchClassName();

        if (launchClassName.isEmpty()) {
            promise.reject("ANDROID:ACTIVITY_NOT_FOUND");
            return;
        }

        String[] activityNameSplit = launchClassName.split("Activity");
        promise.resolve(activityNameSplit[1]);
    }

    @ReactMethod
    public void changeIcon(String iconName, Promise promise) {
        Activity activity = getCurrentActivity();
        String currentLaunchClassName = getLaunchClassName();

        if (currentLaunchClassName.isEmpty()) {
            promise.reject("ANDROID:ACTIVITY_NOT_FOUND");
            return;
        }

        final String newIconName = (iconName == null || iconName.isEmpty()) ? "Default" : iconName;
        final String newLaunchClassName = getNewLaunchClassName(newIconName);
        if (newLaunchClassName.isEmpty()) {
            promise.reject("ANDROID:ACTIVITY_NOT_FOUND");
            return;
        }
        if (currentLaunchClassName.equals(newLaunchClassName)) {
            promise.reject("ANDROID:ICON_ALREADY_USED:" + currentLaunchClassName);
            return;
        }
        try {
            activity.getPackageManager().setComponentEnabledSetting(
                    new ComponentName(this.packageName, newLaunchClassName),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
            promise.resolve(newIconName);
        } catch (Exception e) {
            promise.reject("ANDROID:ICON_INVALID");
            return;
        }
        this.classesToKill.add(currentLaunchClassName);
        this._currentLaunchClassName = newLaunchClassName;
        activity.getApplication().registerActivityLifecycleCallbacks(this);
        iconChanged = true;
    }

    private void completeIconChange() {
        if (!iconChanged)
            return;
        final Activity activity = getCurrentActivity();
        if (activity == null)
            return;
        classesToKill.remove(this._currentLaunchClassName);
        classesToKill.forEach((cls) -> activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.packageName, cls),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP));
        classesToKill.clear();
        iconChanged = false;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        completeIconChange();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
