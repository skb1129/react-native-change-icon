import { NativeModules, Platform } from "react-native";
const defaultIcon = () => Platform.select({ ios: "AppIcon", android: "" });
export const changeIcon = (iconName) => NativeModules.ChangeIcon.changeIcon(iconName || defaultIcon());
export const resetIcon = () => changeIcon();
export const getIcon = () => NativeModules.ChangeIcon.getIcon();
