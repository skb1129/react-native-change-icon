import { NativeModules, Platform } from "react-native";

const defaultIcon = () => Platform.select({
    ios: "AppIcon",
    android: "",
});

export const changeIcon = (iconName?: string): Promise<string> => NativeModules.ChangeIcon.changeIcon(iconName || defaultIcon());
export const resetIcon = changeIcon();
export const getIcon = (): Promise<string> => NativeModules.ChangeIcon.getIcon();
