using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Change.Icon.RNChangeIcon
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNChangeIconModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNChangeIconModule"/>.
        /// </summary>
        internal RNChangeIconModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNChangeIcon";
            }
        }
    }
}
