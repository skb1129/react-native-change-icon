declare module 'react-native-change-icon' {
  /** Change the app icon to one configured in the app bundle. */
  declare const changeIcon: (iconName: string | null) => Promise<string>;

  /** Returns the current icon name or **default** if using default configured icon. */
  declare const getIcon: () => Promise<string>;

  /** **iOS only** error codes when changing an icon. */
  declare const ChangeIconErrorCode: {
    notSupported: string;
    alreadyInUse: string;
    systemError: string;
  };
}
