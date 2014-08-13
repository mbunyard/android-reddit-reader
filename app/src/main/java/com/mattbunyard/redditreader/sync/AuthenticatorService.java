package com.mattbunyard.redditreader.sync;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/**
 * A bound Service that instantiates the authenticator when started.
 */
public class AuthenticatorService extends Service {

    public static final String TAG = AuthenticatorService.class.getSimpleName();

    /**
     * An account type, in the form of a domain name.
     */
    public static final String ACCOUNT_TYPE = "com.mattbunyard.redditreader";

    /**
     * The account name.
     */
    public static final String ACCOUNT_NAME = "Default Account";

    /**
     * Account for use in syncing data.
     */
    private Account mAccount;

    /**
     * Instance field to store authenticator object.
     */
    private Authenticator mAuthenticator;

    /**
     * Obtain a handle to the {@link android.accounts.Account} used for sync in this application.
     */
    public static Account GetAccount() {
        // Normally the account name is set to the user's identity (username or email), but
        // since app isn't implementing user accounts, use a generic account name.
        return new Account(ACCOUNT_NAME, ACCOUNT_TYPE);
    }

    /**
     * Called by the system when the service is first created.
     */
    @Override
    public void onCreate() {
        // Instantiate and set new authenticator object.
        mAuthenticator = new Authenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call, return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }

    /*
     * AbstractAccountAuthenticator and stub methods.
     */
    public class Authenticator extends AbstractAccountAuthenticator {

        public Authenticator(Context context) {
            super(context);
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse r, String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse r, String s, String s2,
                                 String[] strings, Bundle bundle) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse r, Account account,
                                         Bundle bundle) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse r, Account account, String s,
                                   Bundle bundle) throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getAuthTokenLabel(String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse r, Account account, String s,
                                        Bundle bundle) throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse r, Account account, String[] strings)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }
    }
}
