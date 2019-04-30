#import "React/RCTLog.h"

#import "RNChangeIcon.h"

@implementation RNChangeIcon

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(changeIcon:(NSString *)iconName) {
    // Not supported
    if ([[UIApplication sharedApplication] supportsAlternateIcons] == NO) {
        RCTLog(@"Alternate icons are not supported");
        return;
    }

    NSString *currentIcon = [[UIApplication sharedApplication] alternateIconName;

    if ([iconName isEqualToString:currentIcon]) {
        RCTLog(@"Icon already in use");
        return;
    }

    // Custom icon
    [[UIApplication sharedApplication] setAlternateIconName:iconName completionHandler:^(NSError * _Nullable error) {
        RCTLog(@"%@", [error description]);
    }];
}

@end
