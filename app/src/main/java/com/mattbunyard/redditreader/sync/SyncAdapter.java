package com.mattbunyard.redditreader.sync;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.mattbunyard.redditreader.provider.StoryContract;
import com.mattbunyard.redditreader.rest.RedditRestAdapter;
import com.mattbunyard.redditreader.rest.model.StoryListingResponse;

import static android.os.Build.VERSION_CODES.HONEYCOMB;

/**
 * Handle the transfer of data between a server and an app, using the Android sync adapter framework.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    public static final String TAG = SyncAdapter.class.getSimpleName();

    /**
     * Define a variable to contain a content resolver instance.
     */
    private ContentResolver mContentResolver;  // TODO: remove?

    /**
     * Initialize the sync adapter.
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();  // TODO: remove?
    }

    /**
     * Initialize the sync adapter. This form of the constructor maintains compatibility with
     * Android 3.0 and later platform versions.
     */
    @TargetApi(HONEYCOMB)
    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();  // TODO: remove?
    }

    /**
     * Specify the code you want to run in the sync adapter. The entire sync adapter runs in a
     * background thread, so you don't have to set up your own background processing.
     */
    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        Log.d(TAG, "***** onPerformSync()");

        StoryListingResponse hotStoriesResponse = RedditRestAdapter.getListingsService().listHotStories();
        if (hotStoriesResponse != null) {
            try {
                Log.d(TAG, "***** Attempt to insert/update stories: " + hotStoriesResponse.getData().getStories().size());
                provider.bulkInsert(StoryContract.Story.CONTENT_URI, hotStoriesResponse.getStoryContentValues());
            } catch (RemoteException e) {
                Log.d(TAG, Log.getStackTraceString(e));
            }
        }
        else {
            Log.d(TAG, "***** No stories returned from web service");
        }
    }
}
