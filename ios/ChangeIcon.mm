#import "ChangeIcon.h"

@implementation ChangeIcon
RCT_EXPORT_MODULE()

+ (BOOL)requiresMainQueueSetup {
    return NO;
}

RCT_REMAP_METHOD(getIcon, resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    dispatch_async(dispatch_get_main_queue(), ^{
        NSString *currentIcon = [[UIApplication sharedApplication] alternateIconName];
        if (currentIcon) {
            resolve(currentIcon);
        } else {
            resolve(@"Default");
        }
    });
}

RCT_REMAP_METHOD(changeIcon, iconName:(NSString *)iconName resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    dispatch_async(dispatch_get_main_queue(), ^{
        NSError *error = nil;

        if ([[UIApplication sharedApplication] supportsAlternateIcons] == NO) {
            reject(@"Error", @"IOS:NOT_SUPPORTED", error);
            return;
        }

        NSString *currentIcon = [[UIApplication sharedApplication] alternateIconName];

        if ([iconName isEqualToString:currentIcon]) {
            reject(@"Error", @"IOS:ICON_ALREADY_USED", error);
            return;
        }

        NSString *newIconName;
        if (iconName == nil || [iconName length] == 0 || [iconName isEqualToString:@"Default"]) {
            newIconName = nil;
            resolve(@"Default");
        } else {
            newIconName = iconName;
            resolve(newIconName);
        }

        UIApplication *sharedApplication = [UIApplication sharedApplication];
         
        if ([sharedApplication respondsToSelector:@selector(supportsAlternateIcons)] &&
             [sharedApplication supportsAlternateIcons]) {
             
             NSMutableString *selectorString = [[NSMutableString alloc] initWithCapacity:40];
             [selectorString appendString:@"_setAlternate"];
             [selectorString appendString:@"IconName:"];
             [selectorString appendString:@"completionHandler:"];
             
             SEL selector = NSSelectorFromString(selectorString);
             
             if ([sharedApplication respondsToSelector:selector]) {
                 NSMethodSignature *signature = [sharedApplication methodSignatureForSelector:selector];
                 
                 if (signature) {
                     NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:signature];
                     [invocation setTarget:sharedApplication];
                     [invocation setSelector:selector];
                     
                     [invocation setArgument:&newIconName atIndex:2];
                     
                     void (^completionHandler)(NSError * _Nullable) = ^(NSError * _Nullable error) {};
                     [invocation setArgument:&completionHandler atIndex:3];
                     
                     [invocation invoke];
                 }
             }
        }
    });
}

@end
