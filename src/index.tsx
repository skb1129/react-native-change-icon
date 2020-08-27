import { NativeModules } from 'react-native';

type ChangeIconType = {
  changeIcon(iconName: string): Promise<boolean>;
};

const { ChangeIcon } = NativeModules as { ChangeIcon: ChangeIconType };

const changeIcon = ChangeIcon.changeIcon;

export { changeIcon };
