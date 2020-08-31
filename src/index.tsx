import { NativeModules } from 'react-native';

const changeIcon = (iconName: string) =>
  NativeModules.ChangeIcon.changeIcon(iconName);

export { changeIcon };
