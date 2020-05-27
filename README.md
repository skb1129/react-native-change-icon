
# React Native Change Icon

[![npm version](https://badge.fury.io/js/react-native-change-icon.svg)](https://badge.fury.io/js/react-native-change-icon)

Change Application Icon Programmatically.
- [x] iOS
- [x] Android

## Getting started

`$ yarn add react-native-change-icon`

### Automatic installation

`$ react-native link react-native-change-icon`

## Usage

#### iOS

1. Open your project in Xcode and create an **App Icons** group inside you app's group.
2. Add all the app icons you need inside this group with names like *icon@2x.png*, *icon@3x.png*.
3. Your directory structure in Xcode would look like:

![Xcode Directory](images/App_Icons.png)

4. Open the **Info.plist** file.
5. Add `Icon files (iOS 5)` to the **Information Property List**.
6. Add `CFBundleAlternateIcons` as a dictionary to the `Icon files (iOS 5)`, it is used for alternative icons.
7. Add dictionaries under `CFBundleAlternateIcons` named as your icon names in **App Icons** group.
8. For each dictionary, two properties, `UIPrerenderedIcon` and `CFBundleIconFiles` need to be configured.
9. Set the type of `UIPrerenderedIcon` to `String` and its value to `NO`.
10. Set the type of `CFBundleIconFiles` to `Array` and set its first key, `Item 0`'s type to `String` and its value to the corresponding icon names.
11. After all these steps, your **Info.plist** file should look like:

![Info.plist](images/Info.plist.png)

#### Android

1. Add all the icons you need inside your project's `android/app/src/main/res/mipmap-*` directories:

![Android Directory](images/Android_Icons.png)

2. Modify your `AndroidManifest.xml` file's `<application>` tag as following:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example">

    <uses-permission android:name="android.permission.INTERNET" />

	<application
		android:name=".MainApplication"
		android:label="@string/app_name"
		android:icon="@mipmap/checked"
		android:allowBackup="false"
		android:theme="@style/AppTheme">

		<activity android:name=".MainActivity" />

		<activity-alias
			android:name="com.example.MainActivitychecked"
			android:enabled="true"
			android:icon="@mipmap/checked"
			android:targetActivity=".MainActivity">
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />
				<category android:name="android.intent.category.LAUNCHER" />
			</intent-filter>
		</activity-alias>

		<activity-alias
			android:name="com.example.MainActivitycancel"
			android:enabled="false"
			android:icon="@mipmap/cancel"
			android:targetActivity=".MainActivity">
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />
				<category android:name="android.intent.category.LAUNCHER" />
			</intent-filter>
		</activity-alias>

		<activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />

	</application>

</manifest>
```
You can create more `<activity-alias>` tags to make more alternate icons.
*Note that the name in <activity-alias> should be "com.{package_name}.MainActivity%", where `%` is the icon name.*

**NOTE: Steps 3 & 4 only required for React-Native Version < 60**

3. Open the `MainApplication.java` file.
4. Pass `BuildConfig.APPLICATION_ID` to `new ChangeIconPackage()` inside the `getPackages()` function.
```java
@Override
protected List<ReactPackage> getPackages() {
    @SuppressWarnings("UnnecessaryLocalVariable")
    List<ReactPackage> packages = new PackageList(this).getPackages();
    packages.add(new ChangeIconPackage(BuildConfig.APPLICATION_ID))
    return packages;
}
```

**Note that all the icon names must be in lowercase and only limited to alphabets `a-z`**

Now you can use the following code to change application icon:

```javascript
import { changeIcon } from 'react-native-change-icon';

// Pass the name of icon to be enabled
changeIcon('iconname');
```

`changeIcon` function returns a promise. The promise is resolved only when the icon is changed.

**Please refer to the example app for demo on implementation**
