package com.multiplebundle

import android.os.Bundle
import android.util.Log
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactInstanceEventListener
import com.facebook.react.bridge.ReactContext
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate

class MainActivity : ReactActivity() {
    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName(): String = "MultipleBundle"

    /**
     * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
     * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
     */
    override fun createReactActivityDelegate(): ReactActivityDelegate = DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)

    override fun onCreate(savedInstanceState: Bundle?) {
        assets.list(".")?.forEach {
            Log.i("TestApp", "assets path $it")
        }

        val manager = reactNativeHost.reactInstanceManager
        Log.i("TestApp", "reactInstanceManager ${manager.hashCode()}")
        manager.addReactInstanceEventListener(
            object : ReactInstanceEventListener {
                override fun onReactContextInitialized(context: ReactContext) {
                    Log.i("TestApp", "Activity ReactNativeHost onReactContextInitialized")
//                    manager.currentReactContext?.catalystInstance?.loadScriptFromAssets(assets, "assets://common.android.bundle", true)
                    Log.i("TestApp", "reactInstanceManager ${reactInstanceManager.hashCode()}")

                    val instance = reactInstanceManager.currentReactContext?.catalystInstance
                    Log.i(
                        "TestApp",
                        "Activity ReactNativeHost catalystInstance is null? ${
                            instance == null
                        }",
                    )

                    try {
//                            Log.i("TestApp", "loading common bundle")
//                            instance.loadScriptFromAssets(assets, "assets://common.android.bundle", true)
//                            Log.i("TestApp", "loaded common bundle")
                        Log.i("TestApp", "loading biz bundle")
                        instance?.loadScriptFromAssets(assets, "assets://biz.android.bundle", false)
                        Log.i("TestApp", "loaded biz bundle")
//                        reactHost.reload("LoadBizBundle")

                    } catch (e: Exception) {
                        Log.e("TestApp", "load biz bundle fail", e)
                    }

                    manager.removeReactInstanceEventListener(this)
                }
            },
        )
        reactHost.addReactInstanceEventListener(
            object : ReactInstanceEventListener {
                override fun onReactContextInitialized(context: ReactContext) {
                    Log.i("TestApp", "Activity ReactHost onReactContextInitialized")
                }
            },
        )
        Log.i("TestApp", "Activity onCreate ${(reactHost)}")
        super.onCreate(savedInstanceState)
    }
}
