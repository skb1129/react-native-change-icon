import { NativeModules } from 'react-native';

const changeIcon = (iconName: string): Promise<string> =>
  NativeModules.ChangeIcon.changeIcon(iconName);

export { changeIcon };
