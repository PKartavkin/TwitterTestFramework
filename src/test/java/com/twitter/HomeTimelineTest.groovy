package com.twitter

class HomeTimelineTest extends BaseTest {

    def "should return recent tweets for user"() {
        expect:
            homeTimeLineSteps.userCanGetHomeTimeLine()
    }
}
