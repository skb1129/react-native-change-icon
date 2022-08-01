import { NativeModules } from 'react-native';

const changeIcon = (iconName: string): Promise<boolean> =>
  NativeModules.ChangeIcon.changeIcon(iconName);

export { changeIcon };
