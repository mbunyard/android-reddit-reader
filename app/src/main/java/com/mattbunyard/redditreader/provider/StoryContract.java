package com.mattbunyard.redditreader.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Field and table name constants for {@link com.mattbunyard.redditreader.provider.StoryProvider}.
 */
public class StoryContract {

    /**
     * Private constructor to prevent external instantiation.
     */
    private StoryContract() {}

    /**
     * Content provider authority.
     */
    public static final String CONTENT_AUTHORITY = "com.mattbunyard.redditreader";

    /**
     * Base URI. (content://com.mattbunyard.redditreader)
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Path component for "story"-type resources.
     */
    private static final String PATH_STORIES = "stories";

    /**
     * Columns supported by "story" records.
     */
    public static class Story implements BaseColumns {
        /**
         * MIME type for lists of stories.
         */
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.redditreader.stories";

        /**
         * MIME type for individual stories.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.redditreader.story";

        /**
         * Fully qualified URI for "story" resources.
         */
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STORIES).build();

        /**
         * Table name where records are stored for "story" resources.
         */
        public static final String TABLE_NAME = "story";

        /**
         * Default story sort order.
         */
        public static final String DEFAULT_SORT_ORDER = Story.COLUMN_NAME_PUBLISHED + " ASC";

        /**
         * Story ID.
         */
        public static final String COLUMN_NAME_STORY_ID = "story_id";

        /**
         * Story title.
         */
        public static final String COLUMN_NAME_TITLE = "title";

        /**
         * Story author.
         */
        public static final String COLUMN_NAME_AUTHOR = "author";

        /**
         * Story URL.
         */
        public static final String COLUMN_NAME_URL = "url";

        /**
         * Story permalink.
         */
        public static final String COLUMN_NAME_PERMALINK = "permalink";

        /**
         * Story thumbnail URL.
         */
        public static final String COLUMN_NAME_THUMBNAIL = "thumbnail";

        /**
         * Story score.
         */
        public static final String COLUMN_NAME_SCORE = "score";

        /**
         * Date story was published/created remotely.
         */
        public static final String COLUMN_NAME_PUBLISHED = "published";

        /**
         * Date story was created within local cache/datastore.
         */
        public static final String COLUMN_NAME_CREATED = "created";
    }
}
