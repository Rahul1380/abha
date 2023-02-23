package com.abym.abha.Models.Auth;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("Success")
    public String success;
    @SerializedName("Message")
    public String message;
    @SerializedName("Result")
    public String result;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}
