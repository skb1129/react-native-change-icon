package com.reactnativechangeicon

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.content.ComponentName
import android.os.Bundle

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class ChangeIconModule(reactContext: ReactApplicationContext, private val packageName: String) : ReactContextBaseJavaModule(reactContext), Application.ActivityLifecycleCallbacks {
    private var classToKill: String = ""
    private var iconChanged: Boolean = false;
    private var componentClass: String = ""
    override fun getName(): String {
        return "ChangeIcon"
    }

    @ReactMethod
    fun changeIcon(enableIcon: String, promise: Promise) {
        val activity: Activity? = currentActivity
        if (activity == null || enableIcon.isEmpty()) {
            promise.reject("Icon string is empty.")
            return
        }
        if (componentClass.isEmpty()) componentClass = activity.componentName.className
        val activeClass = "$packageName.MainActivity$enableIcon"
        if (componentClass == activeClass) {
            promise.reject("Icon already in use.")
            return
        }
        promise.resolve(true)
        activity.packageManager.setComponentEnabledSetting(
            ComponentName(packageName, activeClass),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
        classToKill = componentClass;
        componentClass = activeClass
        activity.application.registerActivityLifecycleCallbacks(this)
        iconChanged = true;
    }

    private fun completeIconChange() {
        if (iconChanged) {
          val activity: Activity? = currentActivity
          if (activity == null) {
            return
          }
          activity.packageManager.setComponentEnabledSetting(
            ComponentName(packageName, classToKill),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
          )
          iconChanged = false;
        }
    }

    override fun onActivityPaused(activity: Activity) {
        completeIconChange();
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}
