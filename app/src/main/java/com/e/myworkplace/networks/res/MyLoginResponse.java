package com.e.myworkplace.networks.res;

public class MyLoginResponse {
    private String id_token;

    public MyLoginResponse(String id_token) {
        this.id_token = id_token;
    }

    public String getId_token() {
        return id_token;
    }
}
