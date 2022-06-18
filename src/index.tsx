import {NativeModules, Platform} from 'react-native';

const changeIcon = (iconName: string): Promise<string> =>
  NativeModules.ChangeIcon.changeIcon(iconName);

const getIcon = (): Promise<any> => {
    return Platform.OS == 'ios' ? NativeModules.ChangeIcon.getIcon('') : NativeModules.ChangeIcon.getIcon()
}

export { changeIcon, getIcon };