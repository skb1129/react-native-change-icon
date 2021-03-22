@objc(ChangeIcon)
class ChangeIcon: NSObject {

    @objc
    static func requiresMainQueueSetup() -> Bool {
        return false
    }

    @available(iOS 10.3, *)
    @objc(changeIcon:withResolver:withRejecter:)
    func changeIcon(iconName: String, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        if !UIApplication.shared.supportsAlternateIcons {
            reject("Error", "Alternate icon not supported", nil)
            return
        }
        let currentIcon = UIApplication.shared.alternateIconName
        if iconName == currentIcon {
            reject("Error", "Icon already in use", nil)
            return
        }
        resolve(true)
        UIApplication.shared.setAlternateIconName(iconName)
    }
}