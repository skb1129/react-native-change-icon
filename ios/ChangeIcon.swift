@objc(ChangeIcon)
class ChangeIcon: NSObject {

    @objc
    static func requiresMainQueueSetup() -> Bool {
        return false
     }

    @available(iOS 10.3, *)
    @objc(changeIcon:withResolver:withRejecter:)
    func changeIcon(iconName: String?, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) -> Void {
         DispatchQueue.main.async {
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
    @objc(getIcon:withResolver:withRejecter:)
    func getIcon(_ resolve: @escaping RCTPromiseResolveBlock, withRejecter reject: @escaping RCTPromiseRejectBlock)->Void{
        DispatchQueue.main.async {
            resolve(UIApplication.shared.alternateIconName == nil ? "default" : UIApplication.shared.alternateIconName)
        }
    }
}
