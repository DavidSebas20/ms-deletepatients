package com.example.deletepatients.entity;

public class VerifyRequest {
    private String password;
    private String hash;

    public VerifyRequest() {}

    public VerifyRequest(String password, String hash) {
        this.password = password;
        this.hash = hash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
