package com.mattbunyard.redditreader.rest.service;

import com.mattbunyard.redditreader.rest.model.StoryListingResponse;

import retrofit.http.GET;

/**
 * Defines reddit Listings REST API as a Java interface.
 */
public interface ListingsService {

    /**
     * Gets a list of reddit stories tagged "Hot".
     */
    @GET("/r/hot.json")
    StoryListingResponse listHotStories();
}
