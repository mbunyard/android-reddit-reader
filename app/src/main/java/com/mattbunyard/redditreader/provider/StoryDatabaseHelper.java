package com.mattbunyard.redditreader.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite datastore for @{link StoryProvider}.
 *
 * Provides access to an disk-backed, SQLite datastore, which is utilized by StoryProvider. This
 * database should never be directly accessed by other parts of the application.
 */
public class StoryDatabaseHelper extends SQLiteOpenHelper {

    /**
     * Schema version.
     * */
    public static final int DATABASE_VERSION = 1;

    /**
     * Filename for SQLite file.
     */
    public static final String DATABASE_NAME = "story.db";

    /**
     * SQL DDL statement to create "story" table.
     */
    private static final String SQL_CREATE_STORIES =
            "CREATE TABLE " + StoryContract.Story.TABLE_NAME + " (" +
                    StoryContract.Story._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    StoryContract.Story.COLUMN_NAME_STORY_ID + " TEXT UNIQUE," +
                    StoryContract.Story.COLUMN_NAME_TITLE + " TEXT," +
                    StoryContract.Story.COLUMN_NAME_AUTHOR + " TEXT," +
                    StoryContract.Story.COLUMN_NAME_URL + " TEXT," +
                    StoryContract.Story.COLUMN_NAME_PERMALINK + " TEXT," +
                    StoryContract.Story.COLUMN_NAME_THUMBNAIL + " TEXT," +
                    StoryContract.Story.COLUMN_NAME_SCORE + " INTEGER," +
                    StoryContract.Story.COLUMN_NAME_PUBLISHED + " INTEGER," +
                    StoryContract.Story.COLUMN_NAME_CREATED + "INTEGER DEFAULT current_timestamp)";

    /**
     * SQL DDL statement to drop "story" table.
     */
    private static final String SQL_DELETE_STORIES =
            "DROP TABLE IF EXISTS " + StoryContract.Story.TABLE_NAME;

    public StoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over.
        db.execSQL(SQL_DELETE_STORIES);
        onCreate(db);
    }
}
