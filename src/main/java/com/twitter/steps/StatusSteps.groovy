package com.twitter.steps

import com.twitter.utils.OauthUtil

class StatusSteps extends BaseStep {
    private Map<String, String> createdStatuses = [:]
    private static final STATUS_UPDATE_ENDPOINT = TWITTER_DOMAIN + "1.1/statuses/update.json"
    private static final STATUS_DESTROY_ENDPOINT = TWITTER_DOMAIN + "1.1/statuses/destroy/{id}.json"

    def userCreatesStatus(String status) {
        def statusId = OauthUtil.oauth()
                .queryParam("status", status)
                .when()
                .post(STATUS_UPDATE_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .path("id_str")
        createdStatuses.put(status, statusId)
        LOG.info("Status is created. Text: ${status}. ID: ${statusId}")
    }

    def userCantDuplicateStatus(String status) {
        OauthUtil.oauth()
                .queryParam("status", status)
                .when()
                .post(STATUS_UPDATE_ENDPOINT)
                .then()
                .statusCode(403)
    }

    def userDeletesStatus(String status) {
        deleteStatus(status)
        createdStatuses.remove(status)
    }

    private def deleteStatus(String status) {
        def statusId = createdStatuses.get(status)
        OauthUtil.oauth()
                .when()
                .post(STATUS_DESTROY_ENDPOINT, statusId)
                .then()
                .statusCode(200)

        LOG.info("Removed status ${status} with ID: ${statusId}")
    }

    def rollbackStatuses() {
        if (!createdStatuses.isEmpty()) {
            createdStatuses.each { k, v ->
                deleteStatus(k)
            }
        }
        createdStatuses.clear()
    }
}
