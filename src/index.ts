import { NativeModules } from "react-native";

export const changeIcon = (iconName?: string): Promise<string> => NativeModules.ChangeIcon.changeIcon(iconName);
export const resetIcon = () => changeIcon();
export const getIcon = (): Promise<string> => NativeModules.ChangeIcon.getIcon();
