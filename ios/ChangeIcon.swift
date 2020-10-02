@objc(ChangeIcon)
class ChangeIcon: NSObject {

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
        UIApplication.shared.setAlternateIconName(iconName, completionHandler: { (error) -> Void in
            if error == nil {
                resolve(true)
            } else {
                reject("Error", "Unable to change the icon", error)
            }
        })
    }
}
