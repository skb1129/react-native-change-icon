
import { NativeModules } from 'react-native';

const { RNChangeIcon } = NativeModules;

const changeIcon = (iconName) => {
  RNChangeIcon.changeIcon(iconName);
}

export { changeIcon };
