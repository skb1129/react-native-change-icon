package com.reactnativechangeicon

import android.app.Activity
import android.content.pm.PackageManager
import android.content.ComponentName

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class ChangeIconModule(reactContext: ReactApplicationContext, packageName: string) : ReactContextBaseJavaModule(reactContext) {
    var componentClass: String
    override fun getName(): String {
        return "ChangeIcon"
    }

    @ReactMethod
    fun changeIcon(enableIcon: String, promise: Promise) {
        val activity: Activity = getCurrentActivity()
        if (activity == null || enableIcon == null || enableIcon.isEmpty()) {
            promise.reject("Icon string is empty.")
            return
        }
        if (componentClass == null) componentClass = activity.getComponentName().getClassName()
        var activeClass: String = packageName + ".MainActivity" + enableIcon
        if (componentClass.equals(activeClass)) {
            promise.reject("Icon already in use.")
            return
        }
        promise.resolve(true)
        activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(packageName, activeClass),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
        );
        activity.getPackageManager().setComponentEnabledSetting(
                new ComponentName(packageName, componentClass),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
        componentClass = activeClass;
    }
}
