package com.twitter

import static com.twitter.utils.StringUtils.getRandomString

class UpdateStatusTest extends BaseTest {

    def "should update status"() {
        given:
            final STATUS = getRandomString()

        when:
            statusSteps.userCreatesStatus(STATUS)

        then:
            homeTimeLineSteps.statusIsPresent(STATUS)
    }

    def "status duplication should cause error"() {
        given:
            final STATUS = getRandomString()

        when:
            statusSteps.userCreatesStatus(STATUS)

        then:
            statusSteps.userCantDuplicateStatus(STATUS)

    }
}
