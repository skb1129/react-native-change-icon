import { NativeModules } from 'react-native';

const changeIcon = (iconName: string): Promise<string> =>
  NativeModules.ChangeIcon.changeIcon(iconName);

const getCurrentIcon = (): Promise<string | null> =>
  NativeModules.ChangeIcon.getCurrentIcon();

const isAvailable = (): Promise<boolean> =>
  NativeModules.ChangeIcon.isAvailable();

export { changeIcon, getCurrentIcon, isAvailable };
