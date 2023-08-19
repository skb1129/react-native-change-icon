import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
    readonly getConstants: () => {};
    changeIcon: (iconName?: string) => Promise<string>;
    resetIcon: () => Promise<string>;
    getIcon: () => Promise<string>;
}

export default TurboModuleRegistry.get<Spec>('ChangeIcon');