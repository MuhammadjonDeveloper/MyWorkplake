package com.e.myworkplace.service;


import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstainceIdService extends FirebaseInstanceIdService {
    private static final String TOKEN_BASE = "REG_TOKEN";


    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TOKEN_BASE, "nima buldi" + recent_token);
    }
}
