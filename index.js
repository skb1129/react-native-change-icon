import { NativeModules } from "react-native";

const changeIcon = (iconName) => NativeModules.ChangeIcon.changeIcon(iconName);

const getIcon = () => NativeModules.ChangeIcon.getIcon();

export { changeIcon, getIcon };
