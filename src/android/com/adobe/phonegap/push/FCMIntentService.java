package com.adobe.phonegap.push;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;

import java.util.Map;

public class FCMIntentService extends FirebaseMessagingService {

    private static final String TAG = "FCMIntentService";

    @Override
    public void onNewToken(String newToken) {
        Log.d(TAG, "token: " + newToken);
        SharedPreferences sharedPreferences = getSharedPreferences("miPref", MODE_PRIVATE); 
		String oldToken = sharedPreferences.getString("fcm", null);
        sharedPreferences.edit().putString("fcm", newToken).apply();
        /*if (oldToken != null) {
            try {
                PushPlugin.registerToken(newToken);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }*/
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        Log.d(TAG, "MessageReceived: " + data.toString());
        if (data != null && !data.isEmpty()) {
            String keyId = data.get("keyId");
            String massive = data.get("massive");
            String message = data.get("message");
            String ticker = data.get("ticker");
            String title = data.get("title");

            /*boolean massiveNotification = false;
            boolean validNotification = false;

            if (massive != null && massive.equalsIgnoreCase("true")) {
                massiveNotification = true;
                validNotification = true;
            } else {
                String uuid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                SecurePreferences preferences = new SecurePreferences(this, "my-preferences", uuid, true);
                String rut = preferences.getString("rut");

                if(keyId != null && rut != null && keyId.equals(Validator.generateKeyId(rut))) {
                    validNotification = true;
                }
            }

            if(validNotification) {
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("message", message);
                bundle.putString("ticker", ticker);
                bundle.putBoolean("massiveNotification", massiveNotification);
                try {
                    Intent intent = new Intent(this, Class.forName("cl.bancochile.notifications.custom.NotificationService"));
                    intent.putExtras(bundle);
                    startService(intent);
                }
                catch (Exception e) {
                    bundle.putBoolean("foreground", true);
                    PushPlugin.sendExtras(bundle);
                }
            }*/
        }
    }
}