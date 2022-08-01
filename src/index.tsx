import {NativeModules} from 'react-native';

const changeIcon = (iconName: string): Promise<string> =>
  NativeModules.ChangeIcon.changeIcon(iconName);

const getIcon = (): Promise<string> =>
  NativeModules.ChangeIcon.getIcon()


export { changeIcon, getIcon };