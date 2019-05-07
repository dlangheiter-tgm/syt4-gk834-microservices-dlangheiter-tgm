package io.pivotal.microservices.users;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private boolean authenticate;

    AuthResponse() {
    }

    AuthResponse(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }
}