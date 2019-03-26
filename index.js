
import { NativeModules, Platform } from 'react-native';

const { RNChangeIcon } = NativeModules;

const changeIcon = (enableIcon, disableIcon) => {
  return Platform.OS === 'ios'
    ? RNChangeIcon.changeIcon(enableIcon)
    : RNChangeIcon.changeIcon(enableIcon, disableIcon);
}

export { changeIcon };
