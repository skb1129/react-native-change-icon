"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getIcon = exports.resetIcon = exports.changeIcon = void 0;
var react_native_1 = require("react-native");
var defaultIcon = function () { return react_native_1.Platform.select({
    ios: "AppIcon",
    android: "",
}); };
var changeIcon = function (iconName) { return react_native_1.NativeModules.ChangeIcon.changeIcon(iconName || defaultIcon()); };
exports.changeIcon = changeIcon;
var resetIcon = function () { return (0, exports.changeIcon)(); };
exports.resetIcon = resetIcon;
var getIcon = function () { return react_native_1.NativeModules.ChangeIcon.getIcon(); };
exports.getIcon = getIcon;
//# sourceMappingURL=index.js.map