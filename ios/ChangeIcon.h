#import <UIKit/UIKit.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "RNChangeIconSpec.h"
@interface ChangeIcon : NSObject <NativeChangeIconSpec, UIApplicationDelegate>
#else
#import <React/RCTBridgeModule.h>
@interface ChangeIcon : NSObject <RCTBridgeModule, UIApplicationDelegate>
#endif

@end
