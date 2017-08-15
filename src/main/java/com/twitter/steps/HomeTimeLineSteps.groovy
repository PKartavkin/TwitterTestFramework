package com.twitter.steps

import com.twitter.utils.OauthUtil

import static org.hamcrest.Matchers.*

class HomeTimeLineSteps extends BaseStep {
    private static final HOME_TIMELINE_ENDPOINT = TWITTER_DOMAIN + "1.1/statuses/home_timeline.json"
    private static final DEFAULT_TWEETS_COUNT = 20
    private static final EXPECTED_DATE_LENGTH = 30
    private static final MINIMUM_TWEET_SIZE = 10

    def userCanGetHomeTimeLine() {
        OauthUtil.oauth()
                .when()
                .get(HOME_TIMELINE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("size()", is(DEFAULT_TWEETS_COUNT))
                .body("created_at*.length()", everyItem(is(EXPECTED_DATE_LENGTH)))
                .body("retweet_count", everyItem(greaterThanOrEqualTo(0)))
                .body("text*.length()", everyItem(greaterThan(MINIMUM_TWEET_SIZE)))
    }

    def statusIsPresent(String statusText) {
        OauthUtil.oauth()
                .when()
                .get(HOME_TIMELINE_ENDPOINT)
                .then()
                .body("text", hasItem(statusText))
    }

    def statusIsNotPresent(String statusText) {
        OauthUtil.oauth()
                .when()
                .get(HOME_TIMELINE_ENDPOINT)
                .then()
                .body("text", not(hasItem(statusText)))
    }
}
