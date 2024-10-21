package com.example.proy_mobile2024.utils;
import android.content.Context;
import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.callback.Callback;
import com.auth0.android.result.Credentials;

/**
 * Singleton class to manage the Auth0 client instance.
 */
public class Auth0Client {
    private static Auth0Client instance;
    private final Auth0 auth0;

    // Private constructor to prevent direct instantiation
    private Auth0Client() {
        auth0 = new Auth0(
                "AGgSJc6ahkAxIvjBbQ3vgrgZkKSHoc2N",  // Client ID
                "dev-beq8aquv5nb4mfcn.us.auth0.com" // Domain
        );
    }

    /**
     * Provides the singleton instance of Auth0Client.
     */
    public static synchronized Auth0Client getInstance() {
        if (instance == null) {
            instance = new Auth0Client();
        }
        return instance;
    }

    /**
     * Returns the Auth0 object to manage authentication.
     */
    public Auth0 getAuth0() {
        return auth0;
    }

    /**
     * Method to initiate login and get JWT credentials.
     */
    public void loginUser(Context context, String username, String password, Callback<Credentials, AuthenticationException> callback) {
        AuthenticationAPIClient apiClient = new AuthenticationAPIClient(auth0);
        apiClient.login(username, password, "Username-Password-Authentication")
                .setScope("openid profile email")  // Customize scopes if needed
                .start(callback);
    }
}
