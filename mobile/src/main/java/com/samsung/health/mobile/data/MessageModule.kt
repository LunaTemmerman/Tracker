package com.samsung.health.mobile.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.modules.core.DeviceEventManagerModule


private const val TAG = "MessageModule"

class MessageModule (
    private val reactContext: ReactApplicationContext
):  ReactContextBaseJavaModule(reactContext), LifecycleEventListener {

    private val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null && intent.action == "com.example.reactnative.MESSAGE_RECEIVED") {
                val message = intent.getStringExtra("message")
                if (message != null) {
                    sendEvent("onMessageReceived", message)
                }
            }
        }
    }

    init {
        val filter = IntentFilter("com.example.reactnative.MESSAGE_RECEIVED")
        reactContext.registerReceiver(messageReceiver, filter)
        reactContext.addLifecycleEventListener(this)
    }

    override fun getName(): String {
        return "MessageModule"
    }

    private fun sendEvent(eventName: String, message: String) {
        if (reactContext.hasActiveCatalystInstance()) {
            reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit(eventName, message)
        }
    }

    override fun onHostResume() {
        // Activity `onResume`
    }

    override fun onHostPause() {
        // Activity `onPause`
    }

    override fun onHostDestroy() {
        reactContext.unregisterReceiver(messageReceiver)
    }
}