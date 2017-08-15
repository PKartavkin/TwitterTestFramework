package com.twitter

import static com.twitter.utils.StringUtils.getRandomString

class DestroyStatusTest extends BaseTest {

    def "should destroy status by id"() {
        given:
            final STATUS = getRandomString()
            statusSteps.userCreatesStatus(STATUS)
            homeTimeLineSteps.statusIsPresent(STATUS)

        when:
            statusSteps.userDeletesStatus(STATUS)

        then:
            homeTimeLineSteps.statusIsNotPresent(STATUS)
    }
}
