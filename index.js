import { NativeModules } from "react-native";

/** Change the app icon to one configured in the app bundle. */
const changeIcon = (iconName) => NativeModules.ChangeIcon.changeIcon(iconName);

/** Returns the current icon name or **default** if using default configured icon. */
const getIcon = () => NativeModules.ChangeIcon.getIcon();

/** **iOS only** error codes when changing an icon. */
const ChangeIconErrorCode = {
  notSupported: "NOT_SUPPORTED",
  alreadyInUse: "ALREADY_IN_USE",
  systemError: "SYSTEM_ERROR",
}

export { changeIcon, ChangeIconErrorCode, getIcon };
