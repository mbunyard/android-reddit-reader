package com.mattbunyard.redditreader.rest.model;

import android.content.ContentValues;

import com.mattbunyard.redditreader.provider.StoryContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Network response model.
 */
public class StoryListingResponse {

    private StoryListingData data;

    public StoryListingData getData() {
        return data;
    }

    public void setData(StoryListingData data) {
        this.data = data;
    }

    /**
     * Utility method to transform collection of Stories to array of ContentValues.
     */
    public ContentValues[] getStoryContentValues() {
        List<ContentValues> contentValues = new ArrayList<ContentValues>();
        for(Story story : getData().getStories()) {
            ContentValues storyValues = new ContentValues();
            storyValues.put(StoryContract.Story.COLUMN_NAME_STORY_ID, story.getStoryDetails().getId());
            storyValues.put(StoryContract.Story.COLUMN_NAME_TITLE, story.getStoryDetails().getTitle());
            storyValues.put(StoryContract.Story.COLUMN_NAME_AUTHOR, story.getStoryDetails().getAuthor());
            storyValues.put(StoryContract.Story.COLUMN_NAME_URL, story.getStoryDetails().getUrl());
            storyValues.put(StoryContract.Story.COLUMN_NAME_PERMALINK, story.getStoryDetails().getPermalink());
            storyValues.put(StoryContract.Story.COLUMN_NAME_THUMBNAIL, story.getStoryDetails().getThumbnail());
            storyValues.put(StoryContract.Story.COLUMN_NAME_SCORE, story.getStoryDetails().getScore());
            storyValues.put(StoryContract.Story.COLUMN_NAME_PUBLISHED, story.getStoryDetails().getPublished());
            contentValues.add(storyValues);
        }
        return contentValues.toArray(new ContentValues[contentValues.size()]);
    }
}
