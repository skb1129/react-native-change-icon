import { NativeModules } from 'react-native';

const { ChangeIcon } = NativeModules;

const changeIcon = enableIcon => ChangeIcon.changeIcon(enableIcon);

export { changeIcon };
