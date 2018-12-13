
import { NativeModules } from 'react-native';

const { RNChangeIcon } = NativeModules;

export default {
  changeIcon(iconName) {
    RNChangeIcon.changeIcon(iconName);
  }
};
