package com.twitter

import spock.lang.Shared
import spock.lang.Specification
import com.twitter.steps.HomeTimeLineSteps

import com.twitter.steps.StatusSteps

abstract class BaseTest extends Specification {
    @Shared
    protected StatusSteps statusSteps = new StatusSteps()
    protected HomeTimeLineSteps homeTimeLineSteps = new HomeTimeLineSteps()

    def cleanup() {
        statusSteps.rollbackStatuses()
    }
}
