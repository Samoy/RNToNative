package com.rntonative;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by Samoy on 2017/2/10.
 */

public class ExampleInterface extends ReactContextBaseJavaModule {
    private ReactApplicationContext mContext;

    public ExampleInterface(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "ExampleInterface";
    }

    @ReactMethod
    public void HandleMessage(String message) {
        Log.i("RNMessage", "received message from RN:" + message);
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        Bundle bundle = new Bundle();
        mContext.startActivityForResult(intent, 1, bundle);
    }

    public void sendMessage(String message) {
        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("AndroidToRNMessage", message);
    }
}
