package com.mattbunyard.redditreader.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.mattbunyard.redditreader.provider.StoryContract;

/**
 * Helper methods for working with the sync adapter framework.
 */
public class SyncUtil {

    public static final String TAG = SyncUtil.class.getSimpleName();

    /**
     * Shared Preferences constant used to indicate account setup status.
     */
    private static final String PREF_ACCT_SETUP_COMPLETE = "account_setup_complete";

    /**
     * Determines sync frequency in seconds.
     */
    private static final long SYNC_FREQUENCY = 60 * 60;  // 1 hour (in seconds)// TODO: redefine

    /**
     * Create an entry for this application in the system account list if not already present.
     */
    public static void CreateSyncAccount(Context context) {
        // Initialize flags indicating account status.
        boolean isNewAccount = false;
        boolean isAccountSetupComplete = PreferenceManager
                .getDefaultSharedPreferences(context).getBoolean(PREF_ACCT_SETUP_COMPLETE, false);

        // Create account if account not previously created or no longer exists.
        Account account = AuthenticatorService.GetAccount();
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(account, null, null)) {
            Log.d(TAG, "***** CreateSyncAccount() - added account: " + account);// TODO: remove

            // Inform the system that account supports sync.
            //ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1);

            // Inform the system that account is eligible for auto-sync when the network available.
            //ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true);

            // Recommend a schedule for automatic synchronization, which may be modified by the
            // system based on other scheduled syncs and network utilization.
            // TODO: re-evaluate
            //ContentResolver.addPeriodicSync(account, CONTENT_AUTHORITY, new Bundle(), SYNC_FREQUENCY);
            isNewAccount = true;
        }
        else {
            // TODO: REMOVE.
            // The account exists or some other error occurred.
            Log.d(TAG, "***** CreateSyncAccount() - account exists or error occurred: " + account);
        }

        // Schedule an initial sync if we detect problems with either our account or our local
        // data has been deleted.
        if (isNewAccount || !isAccountSetupComplete) {
            Log.d(TAG, "***** CreateSyncAccount() - perform initial sync");// TODO: remove
            refreshData();
            PreferenceManager.getDefaultSharedPreferences(context)
                    .edit().putBoolean(PREF_ACCT_SETUP_COMPLETE, true).commit();
        }
    }

    /**
     * Utility method to start an immediate sync/refresh from network.
     */
    public static void refreshData() {
        Log.d(TAG, "***** refreshData()");// TODO: remove

        // Ignore sync preferences and perform sync immediately.
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        // Request an immediate sync for the default account.
        ContentResolver.requestSync(
                AuthenticatorService.GetAccount(),
                StoryContract.CONTENT_AUTHORITY,
                bundle);
    }
}
