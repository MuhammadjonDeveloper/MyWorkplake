package com.e.myworkplace.networks.res;

public class LoginResponse {
    private Error error;
    private Success success;

    public Error getError() {
        return error;
    }

    public Success getSuccess() {
        return success;
    }
}
