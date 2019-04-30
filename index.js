
import { NativeModules } from 'react-native';

const { RNChangeIcon } = NativeModules;

const changeIcon = enableIcon => RNChangeIcon.changeIcon(enableIcon);

export { changeIcon };
