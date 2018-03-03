package com.appsinventiv.ecommerceadmin.Utils;

import android.app.Service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by AliAh on 01/03/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    DatabaseReference mDatabase;
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("admin").child("fcmKey").setValue(refreshedToken);
    }
}
