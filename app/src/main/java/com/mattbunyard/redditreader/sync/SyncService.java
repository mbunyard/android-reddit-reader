package com.mattbunyard.redditreader.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Defines a Service that returns an IBinder for the sync adapter class, allowing the
 * sync adapter framework to call onPerformSync().
 */
public class SyncService extends Service {

    public static final String TAG = SyncService.class.getSimpleName();

    /**
     * Instance of the sync adapter.
     */
    private static SyncAdapter sSyncAdapter = null;

    /**
     * Object to use as a thread-safe lock.
     */
    private static final Object sSyncAdapterLock = new Object();

    /*
     * Instantiate the sync adapter object.
     */
    @Override
    public void onCreate() {
        // Create the sync adapter as a singleton.
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    /**
     * Returns an object that allows the system to invoke the sync adapter.
     */
    @Override
    public IBinder onBind(Intent intent) {
        // Get the object that allows external processes to call onPerformSync(). The object is
        // created in the base class code when the SyncAdapter constructors call super().
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
