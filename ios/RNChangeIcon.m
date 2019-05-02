#import "React/RCTLog.h"

#import "RNChangeIcon.h"

@implementation RNChangeIcon

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(changeIcon, iconName:(NSString *)iconName resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    NSError *error = nil;
    // Not supported
    if ([[UIApplication sharedApplication] supportsAlternateIcons] == NO) {
        reject(@"Error", @"Alternate icon not supported", error);
        RCTLog(@"Alternate icons are not supported");
        return;
    }

    NSString *currentIcon = [[UIApplication sharedApplication] alternateIconName];

    if ([iconName isEqualToString:currentIcon]) {
        reject(@"Error", @"Icon already in use", error);
        RCTLog(@"Icon already in use");
        return;
    }

    // Custom icon
    [[UIApplication sharedApplication] setAlternateIconName:iconName completionHandler:^(NSError * _Nullable error) {
        resolve(@(YES));
        RCTLog(@"%@", [error description]);
    }];
}

@end
